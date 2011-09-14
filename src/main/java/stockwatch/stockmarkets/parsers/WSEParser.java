package stockwatch.stockmarkets.parsers;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import stockwatch.securities.SecuritiesFactory;
import stockwatch.securities.Security;
import stockwatch.stockmarkets.InternalMarket;
import stockwatch.stockmarkets.descriptions.IMarketTypes;

import com.google.common.base.Preconditions;

public class WSEParser implements QuotesParser {

    private static final String reqex = "[A-Z][A-Z].{10}";
    private Vector<InternalMarket> wseInternalMarkets;
    private CallbackFactory<Security, Element> callbackFactory;
    private SecuritiesFactory securityFactory;

    public WSEParser(Vector<InternalMarket> markets) {
        this.wseInternalMarkets = markets;
        this.callbackFactory = new WSEParserCallbackFactory();
        this.securityFactory = new SecuritiesFactory();
    }

    private void addSecurities(final Elements parsedSecurities, Vector<Security> market, IMarketTypes marketType) {
        if (market.size() == parsedSecurities.size())
            return;

        market.clear();

        for (Element security : parsedSecurities) {
            Elements securityName = security.getElementsByClass("nazwa");
            try {
                Security newSecurity = securityFactory.getSecurity(marketType);
                newSecurity.setSecurityName(securityName.last().text());
                market.addElement(newSecurity);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

    }

    private void addSecuritiesId(final Elements parsedSecurities, Vector<Security> market) {
        int i = 0;
        for (Element security : parsedSecurities) {
            Elements securityName = security.getElementsByAttribute("id");
            market.elementAt(i).setSecurityId(securityName.last().id());
            ++i;
        }
    }
    
    private void initMarket(final Elements parsedSecurities, Vector<Security> market, IMarketTypes marketType) {
        addSecurities(parsedSecurities, market, marketType);
        addSecuritiesId(parsedSecurities, market);
    }

    private void parseTag(final Elements parsedSecurities, Vector<Security> market, String tag) {
        try {
            Preconditions.checkArgument(parsedSecurities.size() == market.size(),
                    "Size incorectness: Parsed elements: %d , Market: %d", parsedSecurities.size(), market.size());

            int i = 0;
            for (Element elem : parsedSecurities) {
                Elements elemValue = elem.getElementsByClass(tag);
                callbackFactory.get(tag).call(market.elementAt(i), elemValue.last());
                i++;
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }

    private void parseTags(final Elements parsedSecurities, Vector<Security> market, String[] tagList) {
        for (String tag : tagList) {
            parseTag(parsedSecurities, market, tag);
        }
    }

    private Elements getAllSecurities(String pageAddr) {
        try {
            Document sourceDocument = Jsoup.connect(pageAddr).get();
            Elements parsedElems = sourceDocument.getElementsByAttributeValueMatching("id", reqex);
            return parsedElems;
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private void parse(Vector<Security> market, String pageAddr, String[] tagList, IMarketTypes marketType) {
        try {
            final Elements allSecurities = getAllSecurities(pageAddr);
            Preconditions.checkNotNull(allSecurities);
            initMarket(allSecurities, market, marketType);
            parseTags(allSecurities, market, tagList);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }
    
    @Override
    public Vector<InternalMarket> parseQuotes() {
        // Iterate over all internal markets of WSE and parse it's quotes.
        Preconditions.checkNotNull(wseInternalMarkets);
        
        for (InternalMarket market : wseInternalMarkets) {
            IMarketTypes marketType = market.getName();
            parse(market.getSecurities(), marketType.getPageAddress(), marketType.getTags(), marketType);
            market.makeStatistics();
        }
        return wseInternalMarkets;
    }

    @Override
    public void parseInfo() {
        // TODO Auto-generated method stub

    }

}
