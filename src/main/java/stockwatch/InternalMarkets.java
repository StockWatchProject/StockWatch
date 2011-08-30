package stockwatch;

import java.util.Map;
import java.util.Vector;

/*
 * Interface used for describing all internal markets which are part of given stock exchange.
 * For e.g Warsaw Stock Exchange contains:
 * - main market
 * - newconnect
 * - catalyst
 * - futures
 */
public interface InternalMarkets {
    public abstract Map<String, Vector<Security>> getQuotes();
    public abstract Map<String, SessionStatistics> getStatistics();
    public abstract void updateMarkets(InternalMarkets updatedMarkets);
}