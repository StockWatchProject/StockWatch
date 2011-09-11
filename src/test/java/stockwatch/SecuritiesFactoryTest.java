package stockwatch;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import stockwatch.Bond;
import stockwatch.FutureContract;
import stockwatch.Index;
import stockwatch.Option;
import stockwatch.SecuritiesFactory;
import stockwatch.Security;
import stockwatch.Share;
import stockwatch.WseMarketTypes.EWseMarketTypes;

public class SecuritiesFactoryTest extends TestCase {

    SecuritiesFactory securitiesFactory;
    
    @Before
    public void setUp() throws Exception {
        securitiesFactory = new SecuritiesFactory();
    }

    @Test
    public void testGetSecurity() {
        Security newSecurity = securitiesFactory.getSecurity(EWseMarketTypes.MainMarket);
        assertTrue(newSecurity instanceof Share);
    }

    @Test
    public void testGetStock() {
        Security newSecurity = securitiesFactory.getSecurity(EWseMarketTypes.NewConnect);
        assertTrue(newSecurity instanceof Share);
    }

    @Test
    public void testGetBond() {
        Security newSecurity = securitiesFactory.getSecurity(EWseMarketTypes.Catalyst);
        assertTrue(newSecurity instanceof Bond);
    }

    @Test
    public void testGetFutureContract() {
        Security newSecurity = securitiesFactory.getSecurity(EWseMarketTypes.Futures);
        assertTrue(newSecurity instanceof FutureContract);
    }

    @Test
    public void testGetOption() {
        Security newSecurity = securitiesFactory.getSecurity(EWseMarketTypes.Options);
        assertTrue(newSecurity instanceof Option);
    }

    @Test
    public void testGetIndex() {
        Security newSecurity = securitiesFactory.getSecurity(EWseMarketTypes.Indexes);
        assertTrue(newSecurity instanceof Index);
    }

}
