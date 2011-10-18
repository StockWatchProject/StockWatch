package stockwatch.stockmarkets;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import stockwatch.stockmarkets.descriptions.IMarketTypes;
import stockwatch.stockmarkets.descriptions.WSEDescription;

public class InternalMarketTest extends TestCase {
    private WSEDescription desc;
    private IMarketTypes allMarket[];
    private InternalMarket internalMarket;
    
    @Before
    public void setUp() throws Exception {
        desc = new WSEDescription();
        allMarket = desc.getInternalMarkets();
        internalMarket = new InternalMarket(allMarket[0]);
    }
    
    @Test
    public void testGetName() {
        assertEquals(internalMarket.getMarketType(), allMarket[0]);
    }
    
    @Test
    public void testGetSecurities() {
        assertNotNull(internalMarket.getSecurities());
    }
    
    @Test
    public void testGetStats() {
        assertNotNull(internalMarket.getStats());
    }
}
