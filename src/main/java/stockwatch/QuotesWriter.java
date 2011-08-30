package stockwatch;

import java.util.Map;
import java.util.Vector;

public interface QuotesWriter {
    public void write(Map<String, Vector<Security>> companies);
}
