package stockwatchTests;

import static org.mockito.Mockito.*;

import org.jsoup.nodes.Element;
import org.junit.Test;

import stockwatch.Security;
import stockwatch.Share;
import stockwatch.WseParserCallbackFactory;

public class WseParserCallbackFactoryTest {

    @Test
    public void testGet() {
        WseParserCallbackFactory callbackFactory = new WseParserCallbackFactory();

        String tag = "zmiana";
        
        Element mockedElem = mock(Element.class);
        Security mockedShare = mock(Share.class);
        
        when(mockedElem.text()).thenReturn("20.5");
        
        callbackFactory.get(tag).call(mockedShare, mockedElem);
        
        verify(mockedShare, only()).setPercentageChange("20.5");

    }

}
