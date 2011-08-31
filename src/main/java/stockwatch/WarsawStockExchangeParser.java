/**
 * 
 */
package stockwatch;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import stockwatch.WseMarketTypes.EWseMarketTypes;

import com.google.common.base.Preconditions;

public class WarsawStockExchangeParser implements QuotesParser {

    WSEInternalMarkets wseInternalMarkets;
    CallbackFactory<Security, Element> callbackFactory;

    public WarsawStockExchangeParser() {
        wseInternalMarkets = new WSEInternalMarkets();
        callbackFactory = new WseParserCallbackFactory();
    }

    private void addCompanies(Document parsedDocument, Vector<Security> market) {
        Elements companies = parsedDocument.getElementsByClass("nazwa");

        if (market.size() == companies.size())
            return;

        market.clear();

        for (Element company : companies) {
            Shares newCompany = new Shares();
            newCompany.setSecurityName(company.text());
            market.addElement(newCompany);
        }
    }

    private void addComapaniesIds(Document parsedDocument, Vector<Security> market) {
        Elements l_params = parsedDocument.getElementsByAttribute("onClick");

        if (market.size() == l_params.size())
            return;

        int i = 0;
        for (Element src : l_params) {
            String[] l_id = src.attributes().get("onClick").split("'");
            if (l_id.length > 4) {
                market.elementAt(i).setSecurityId(l_id[5]);
            }
            i++;
        }
    }
    
    void initMarket(Document parsedDocument, Vector<Security> market) {
        addCompanies(parsedDocument, market);
        addComapaniesIds(parsedDocument, market);
    }
    
    void parseTag(Document parsedDocument, Vector<Security> market, String tag) {
        Elements parsedElems = parsedDocument.getElementsByClass(tag);
        
        try {
            Preconditions.checkArgument(parsedElems.size() == market.size(),
                    "Size incorectness: Parsed elements: %d , Market: %d", parsedElems.size(), market.size());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        
        int i = 0;
        for (Element elem : parsedElems) {
            callbackFactory.get(tag).call(market.elementAt(i), elem);
            i++;
        }
    }

    void parseTags(Document parsedDocument, Vector<Security> market, String[] tagList) {
        for (String tag : tagList) {
            parseTag(parsedDocument, market, tag);
        }
    }
    
    void parse(Vector<Security> market, String pageAddr, String[] tagList) {
        try {
            Document sourceDocument = Jsoup.connect(pageAddr).get();
            initMarket(sourceDocument, market);
            parseTags(sourceDocument, market, tagList);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public InternalMarkets parseQuotes() {
        // Iterate over all internal markets of WSE and parse it's quotes.
        EWseMarketTypes allMarkets[] = EWseMarketTypes.values();
        for (EWseMarketTypes market : allMarkets) {
            parse(wseInternalMarkets.getQuotes().get(market.name()), market.getAddress(), market.getTags());
        }
        return wseInternalMarkets;
    }

    @Override
    public void parseInfo() {
        // TODO Auto-generated method stub

    }

}
