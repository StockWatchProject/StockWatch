package stockwatch.stockmarkets;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import stockwatch.exceptions.SecuritiesParsingException;
import stockwatch.stockmarkets.parsers.QuotesParser;
import stockwatch.stockmarkets.parsers.WSEParser;

public class StockMarketTest extends TestCase {
    private static final int NUM_OF_INT_MARKETS = 6;
    private MarketNames name;
    private StockMarket stockMarket;
    private QuotesParser parser;
    private ArrayList<InternalMarket> markets;
    
    @Before
    protected void setUp() throws Exception {
        name = MarketNames.WSE;
        stockMarket = new StockMarket(name);
        
        parser = mock(WSEParser.class);
        stockMarket.setParser(parser);
        
        markets = new ArrayList<InternalMarket>();
        markets.add(mock(InternalMarket.class));
        
        for (int i = 0; i < NUM_OF_INT_MARKETS; i++) {
            markets.add(mock(InternalMarket.class));
        }

        stockMarket.setInternalMarkets(markets);
    }
    
    @Override
    protected void tearDown() throws Exception {
        reset(parser);
    }
    
    @Test
    public void testGetName() {
        assertEquals(name, stockMarket.getName());
    }
    
    @Test
    public void testUpdateQuotes() {
        try {
            doReturn(markets).when(parser).parseQuotes();
            stockMarket.updateQuotes();
            
            verify(parser, times(1)).parseQuotes();
            
            for (InternalMarket market : markets) {
                verify(market, times(1)).makeStatistics();
            }
        } catch (SecuritiesParsingException e) {
            fail("Unexpected SecuritiesParsingException!");
        }
    }
    
    @Test
    public void testUpdateQuotes_notOk() {
        try {
            doThrow(new SecuritiesParsingException()).when(parser).parseQuotes();
            stockMarket.updateQuotes();
            
            verify(parser, times(1)).parseQuotes();
            
            for (InternalMarket market : markets) {
                verify(market, never()).makeStatistics();
            }
        } catch (SecuritiesParsingException e) {
            fail("Unexpected SecuritiesParsingException!");
        }
    }
}
