package stockwatch.stockmarkets.parsers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;

import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;

import stockwatch.securities.ISecurity;
import stockwatch.securities.Share;

public class WseParserCallbackFactoryTest extends TestCase {
    private WSEParserCallbackFactory callbackFactory;
    Element mockedElem = mock(Element.class);
    ISecurity mockedShare = mock(Share.class);
    
    @Before
    protected void setUp() throws Exception {
        callbackFactory = new WSEParserCallbackFactory();
        mockedElem = mock(Element.class);
        mockedShare = mock(Share.class);
    }

    @Override
    protected void tearDown() throws Exception {
        reset(mockedElem);
        reset(mockedShare);
    }
    
    @Test
    public void testGet_setPercentageChange() {
        final String tag = "zmiana";   
        final String result = "20.5";
        
        when(mockedElem.text()).thenReturn(result);
        callbackFactory.get(tag).call(mockedShare, mockedElem);
        verify(mockedElem, only()).text();
        verify(mockedShare, only()).setPercentageChange(result);
    }
    
    @Test
    public void testGet_setLastChangedTime() {
        final String tag = "czas";   
        final String result = "12:08";
        
        when(mockedElem.ownText()).thenReturn(result);
        callbackFactory.get(tag).call(mockedShare, mockedElem);
        verify(mockedElem, only()).ownText();
        verify(mockedShare, only()).setLastChangedTime(result);
    }
    
    @Test
    public void testGet_setOpenPrice() {
        final String tag = "o";
        final String result = "0.10";
        
        when(mockedElem.text()).thenReturn(result);
        callbackFactory.get(tag).call(mockedShare, mockedElem);
        verify(mockedElem, only()).text();
        verify(mockedShare, only()).setOpenPrice(result);
    }
    
    @Test
    public void testGet_setLastTransactionPrice() {
        final String tag = "c";   
        final String result = "40";
        
        when(mockedElem.text()).thenReturn(result);
        callbackFactory.get(tag).call(mockedShare, mockedElem);
        verify(mockedElem, only()).text();
        verify(mockedShare, only()).setLastTransactionPrice(result);
    }
    
    @Test
    public void testGet_setHigh() {
        final String tag = "h";   
        final String result = "666";
        
        when(mockedElem.text()).thenReturn(result);
        callbackFactory.get(tag).call(mockedShare, mockedElem);
        verify(mockedElem, only()).text();
        verify(mockedShare, only()).setHigh(result);
    }
    
    @Test
    public void testGet_setLow() {
        final String tag = "l";   
        final String result = "888";
        
        when(mockedElem.text()).thenReturn(result);
        callbackFactory.get(tag).call(mockedShare, mockedElem);
        verify(mockedElem, only()).text();
        verify(mockedShare, only()).setLow(result);
    }
    
    @Test
    public void testGet_setLop() {
        final String tag = "lop";
        final String result = "999";
        
        when(mockedElem.text()).thenReturn(result);
        callbackFactory.get(tag).call(mockedShare, mockedElem);
        verify(mockedElem, only()).text();
        verify(mockedShare, only()).setLop(result);
    }
    
    @Test
    public void testGet_setLopChange() {
        final String tag = "zmiana_lop";
        final String result = "888";
        
        when(mockedElem.text()).thenReturn(result);
        callbackFactory.get(tag).call(mockedShare, mockedElem);
        verify(mockedElem, only()).text();
        verify(mockedShare, only()).setLopChange(result);
    }
    
    @Test
    public void testGet_setVolume() {
        final String tag = "obrot";
        final String result = "333";
        
        when(mockedElem.text()).thenReturn(result);
        callbackFactory.get(tag).call(mockedShare, mockedElem);
        verify(mockedElem, only()).text();
        verify(mockedShare, only()).setVolume(result);
    }
    
    @Test
    public void testGet_setExpirationDate() {
        final String tag = "czas_wygasniecia";
        final String result = "2011-09-08";
        
        when(mockedElem.text()).thenReturn(result);
        callbackFactory.get(tag).call(mockedShare, mockedElem);
        verify(mockedElem, only()).text();
        verify(mockedShare, only()).setExpirationDate(result);
    }
    
    @Test
    public void testGet_wrongTag() {
        String tag = "WRONG_TAG";
        
        try {
            callbackFactory.get(tag).call(mockedShare, mockedElem);
            fail("No exception thrown!");
        } catch (IllegalArgumentException e) {
        }
    }
    
}
