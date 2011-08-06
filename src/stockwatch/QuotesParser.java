package stockwatch;

import java.util.Vector;

public interface QuotesParser {
    
    Vector<Company> parseQuotes();
    void parseInfo();

}
