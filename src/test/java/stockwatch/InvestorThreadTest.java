package stockwatch;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import stockwatch.messages.QuoteMessages.AvailableSecuritiesReq;
import stockwatch.messages.QuoteMessages.MessageType;
import stockwatch.messages.QuoteMessages.MsgWrapper;
import stockwatch.messages.QuoteMessages.Quote;
import stockwatch.messages.QuoteMessages.QuoteList;
import stockwatch.stockmarkets.WorldWideMarket;

public class InvestorThreadTest extends TestCase {
    private Socket socket;
    private WorldWideMarket worldWideMarket;
    private InvestorThread investorThread;
    
    @Before
    public void setUp() throws Exception {
        socket = mock(Socket.class);
        worldWideMarket = mock(WorldWideMarket.class);
        investorThread = new InvestorThread(socket, worldWideMarket, 0);
    }

    @Override
    protected void tearDown() throws Exception {
        reset(socket);
        reset(worldWideMarket);
    }
    
    private QuoteList prepareSimpleQuoteList() {
        Quote quote = 
                Quote.newBuilder()
                .setName("MCI")
                .setId("ID-MCI")
                .setMarketId(0).build();
        
        QuoteList quoteListSimple = 
                QuoteList.newBuilder()
                .addQuote(quote)
                .addQuote(quote)
                .addQuote(quote)
                .addQuote(quote)
                .build();
        return quoteListSimple;
    }
    
    private QuoteList prepareFullQuoteList() {
        Quote quoteFull = 
                Quote.newBuilder()
                .setName("MCI")
                .setId("ID-MCI")
                .setMarketId(0)
                .setLastPrice(2.34)
                .setPercentageChange(4)
                .setOpen(1)
                .setLow(1)
                .setHigh(2.34)
                .setVolume(100000)
                .setLastChangeTime("00:00:00")
                .build();
        
        QuoteList quoteListFull = 
                QuoteList.newBuilder()
                .addQuote(quoteFull)
                .addQuote(quoteFull)
                .addQuote(quoteFull)
                .addQuote(quoteFull)
                .addQuote(quoteFull)
                .build();
        
        return quoteListFull;
    }
    
   @Test
    public void testRun_AvailableSecuritiesReq_MSG() {
        QuoteList quoteList = prepareSimpleQuoteList();
        
        doReturn(quoteList).when(worldWideMarket).getQuotes();
        
        AvailableSecuritiesReq req = AvailableSecuritiesReq.newBuilder().build();
        MsgWrapper msgWrapper = MsgWrapper.newBuilder().setType(MessageType.AVAILABLE_REQ).setReq(req).build();
        
        assertFalse(msgWrapper.hasQuoteList());
        assertTrue(msgWrapper.hasReq());
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ByteArrayInputStream input = new ByteArrayInputStream(msgWrapper.toByteArray());
        
        try {
            when(socket.getInputStream()).thenReturn(input);
            when(socket.getOutputStream()).thenReturn(output);
            
            investorThread.run();
            
            verify(worldWideMarket, times(1)).getQuotes();
            verify(socket, times(1)).getInputStream();
            verify(socket, times(1)).getOutputStream();
            verify(socket, times(1)).close();
            
            assertEquals(quoteList.toByteArray().length, output.toByteArray().length);
            assertEquals(quoteList, QuoteList.parseFrom(output.toByteArray()));
            
        } catch (IOException e) {
            fail("Something went wrong");
        }
        
    }
    
    @Test
    public void testRun_QuoteList_MSG() {
        QuoteList quoteListSimple = prepareSimpleQuoteList();
        QuoteList quoteListFull   = prepareFullQuoteList();
        
        doReturn(quoteListFull).when(worldWideMarket).getQuotes(quoteListSimple);
        
        MsgWrapper msgWrapper = MsgWrapper.newBuilder().setType(MessageType.QUOTE_LIST).setQuoteList(quoteListSimple).build();
        
        assertFalse(msgWrapper.hasReq());
        assertTrue(msgWrapper.hasQuoteList());
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ByteArrayInputStream input = new ByteArrayInputStream(msgWrapper.toByteArray());
        
        try {
            when(socket.getInputStream()).thenReturn(input);
            when(socket.getOutputStream()).thenReturn(output);
            
            investorThread.run();
            
            verify(worldWideMarket, times(1)).getQuotes(quoteListSimple);
            verify(socket, times(1)).getInputStream();
            verify(socket, times(1)).getOutputStream();
            verify(socket, times(1)).close();
            
            assertEquals(quoteListFull.toByteArray().length, output.toByteArray().length);
            assertEquals(quoteListFull, QuoteList.parseFrom(output.toByteArray()));
            
        } catch (IOException e) {
            fail("Something went wrong");
        }
        
    }
}
