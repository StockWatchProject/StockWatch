package stockwatch;

import static org.mockito.Mockito.*;
import junit.framework.TestCase;

import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;

import stockwatch.securities.Security;
import stockwatch.securities.Share;
import stockwatch.stockmarkets.parsers.WSEParserCallbackFactory;

public class WseParserCallbackFactoryTest extends TestCase {

    private WSEParserCallbackFactory callbackFactory;
    
    @Before
    protected void setUp() throws Exception {
        callbackFactory = new WSEParserCallbackFactory();
    }

    @Test
    public void testGet_setPercentageChange() {
        String tag = "zmiana";   
        Element mockedElem = mock(Element.class);
        Security mockedShare = mock(Share.class);
        
        when(mockedElem.text()).thenReturn("20.5");       
        callbackFactory.get(tag).call(mockedShare, mockedElem);     
        verify(mockedShare, only()).setPercentageChange("20.5");
    }
    
    @Test
    public void testGet_setLastChangedTime() {
        String tag = "czas";   
        Element mockedElem = mock(Element.class);
        Security mockedShare = mock(Share.class);
        
        when(mockedElem.ownText()).thenReturn("12:08");       
        callbackFactory.get(tag).call(mockedShare, mockedElem);     
        verify(mockedShare, only()).setLastChangedTime("12:08");
    }
    
    @Test
    public void testGet_setOpenPrice() {
        String tag = "o";   
        Element mockedElem = mock(Element.class);
        Security mockedShare = mock(Share.class);
        
        when(mockedElem.text()).thenReturn("0.10");
        callbackFactory.get(tag).call(mockedShare, mockedElem);
        verify(mockedShare, only()).setOpenPrice("0.10");
    }
    
    @Test
    public void testGet_setLastTransactionPrice() {
        String tag = "c";   
        Element mockedElem = mock(Element.class);
        Security mockedShare = mock(Share.class);
        
        when(mockedElem.text()).thenReturn("40");        
        callbackFactory.get(tag).call(mockedShare, mockedElem);        
        verify(mockedShare, only()).setLastTransactionPrice("40");
    }
    
    @Test
    public void testGet_setHigh() {
        String tag = "h";   
        Element mockedElem = mock(Element.class);
        Security mockedShare = mock(Share.class);
        
        when(mockedElem.text()).thenReturn("666");        
        callbackFactory.get(tag).call(mockedShare, mockedElem);        
        verify(mockedShare, only()).setHigh("666");
    }
    
    @Test
    public void testGet_setLow() {
        String tag = "l";   
        Element mockedElem = mock(Element.class);
        Security mockedShare = mock(Share.class);
        
        when(mockedElem.text()).thenReturn("888");        
        callbackFactory.get(tag).call(mockedShare, mockedElem);
        verify(mockedShare, only()).setLow("888");
    }
    
    @Test
    public void testGet_setLop() {
        String tag = "lop";   
        Element mockedElem = mock(Element.class);
        Security mockedShare = mock(Share.class);
        
        when(mockedElem.text()).thenReturn("999");
        callbackFactory.get(tag).call(mockedShare, mockedElem);
        verify(mockedShare, only()).setLop("999");
    }
    
    @Test
    public void testGet_setLopChange() {
        String tag = "zmiana_lop";   
        Element mockedElem = mock(Element.class);
        Security mockedShare = mock(Share.class);
        
        when(mockedElem.text()).thenReturn("888");
        callbackFactory.get(tag).call(mockedShare, mockedElem);
        verify(mockedShare, only()).setLopChange("888");
    }
    
    @Test
    public void testGet_setVolume() {
        String tag = "obrot";   
        Element mockedElem = mock(Element.class);
        Security mockedShare = mock(Share.class);
        
        when(mockedElem.text()).thenReturn("333");
        callbackFactory.get(tag).call(mockedShare, mockedElem);
        verify(mockedShare, only()).setVolume("333");
    }
    
    @Test
    public void testGet_setExpirationDate() {
        String tag = "czas_wygasniecia";   
        Element mockedElem = mock(Element.class);
        Security mockedShare = mock(Share.class);
        
        when(mockedElem.text()).thenReturn("2011-09-08");
        callbackFactory.get(tag).call(mockedShare, mockedElem);
        verify(mockedShare, only()).setExpirationDate("2011-09-08");
    }
    
    @Test
    public void testGet_wrongTag() {
        String tag = "WRONG_TAG";   
        Element mockedElem = mock(Element.class);
        Security mockedShare = mock(Share.class);       
        
        try {
            callbackFactory.get(tag).call(mockedShare, mockedElem);
            fail("No exception thrown!");
        } catch (IllegalArgumentException e) {
        }
    }
    
}
