package stockwatch;

import java.util.Map;
import java.util.Vector;

public interface QuotesParser {
    
    Map<String, Vector<Company>> parseQuotes();
    void parseInfo();

}
