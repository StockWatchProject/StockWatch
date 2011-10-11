package stockwatch.securities;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import stockwatch.securities.Bond;
import stockwatch.securities.FutureContract;
import stockwatch.securities.Index;
import stockwatch.securities.Option;
import stockwatch.securities.SecuritiesFactory;
import stockwatch.securities.ISecurity;
import stockwatch.securities.Share;
import stockwatch.stockmarkets.descriptions.WSEDescription.MarketTypes;

public class SecuritiesFactoryTest extends TestCase {

    SecuritiesFactory securitiesFactory;
    
    @Before
    public void setUp() throws Exception {
        securitiesFactory = new SecuritiesFactory();
    }

    @Test
    public void testGetSecurity() {
        ISecurity newSecurity = securitiesFactory.getSecurity(MarketTypes.MainMarket);
        assertTrue(newSecurity instanceof Share);
    }

    @Test
    public void testGetStock() {
        ISecurity newSecurity = securitiesFactory.getSecurity(MarketTypes.NewConnect);
        assertTrue(newSecurity instanceof Share);
    }

    @Test
    public void testGetBond() {
        ISecurity newSecurity = securitiesFactory.getSecurity(MarketTypes.Catalyst);
        assertTrue(newSecurity instanceof Bond);
    }

    @Test
    public void testGetFutureContract() {
        ISecurity newSecurity = securitiesFactory.getSecurity(MarketTypes.Futures);
        assertTrue(newSecurity instanceof FutureContract);
    }

    @Test
    public void testGetOption() {
        ISecurity newSecurity = securitiesFactory.getSecurity(MarketTypes.Options);
        assertTrue(newSecurity instanceof Option);
    }

    @Test
    public void testGetIndex() {
        ISecurity newSecurity = securitiesFactory.getSecurity(MarketTypes.Indexes);
        assertTrue(newSecurity instanceof Index);
    }

}
