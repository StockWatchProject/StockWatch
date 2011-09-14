package stockwatch;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import stockwatch.securities.Bond;
import stockwatch.securities.FutureContract;
import stockwatch.securities.Index;
import stockwatch.securities.Option;
import stockwatch.securities.SecuritiesFactory;
import stockwatch.securities.Security;
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
        Security newSecurity = securitiesFactory.getSecurity(MarketTypes.MainMarket);
        assertTrue(newSecurity instanceof Share);
    }

    @Test
    public void testGetStock() {
        Security newSecurity = securitiesFactory.getSecurity(MarketTypes.NewConnect);
        assertTrue(newSecurity instanceof Share);
    }

    @Test
    public void testGetBond() {
        Security newSecurity = securitiesFactory.getSecurity(MarketTypes.Catalyst);
        assertTrue(newSecurity instanceof Bond);
    }

    @Test
    public void testGetFutureContract() {
        Security newSecurity = securitiesFactory.getSecurity(MarketTypes.Futures);
        assertTrue(newSecurity instanceof FutureContract);
    }

    @Test
    public void testGetOption() {
        Security newSecurity = securitiesFactory.getSecurity(MarketTypes.Options);
        assertTrue(newSecurity instanceof Option);
    }

    @Test
    public void testGetIndex() {
        Security newSecurity = securitiesFactory.getSecurity(MarketTypes.Indexes);
        assertTrue(newSecurity instanceof Index);
    }

}
