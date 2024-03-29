package stockwatch.stockmarkets.parsers;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import stockwatch.exceptions.SecuritiesGettingException;
import stockwatch.exceptions.SecuritiesParsingException;
import stockwatch.securities.ISecurity;
import stockwatch.securities.SecuritiesFactory;
import stockwatch.stockmarkets.InternalMarket;
import stockwatch.stockmarkets.descriptions.IMarketTypes;

import com.google.common.base.Preconditions;

public class WSEParser implements QuotesParser {
    private static final Logger logger = Logger.getLogger(WSEParser.class);
    private static final String reqex = "[A-Z][A-Z].{10}";
    private static final int TIMEOUT = 3000;
    private ArrayList<InternalMarket> wseInternalMarkets;
    private CallbackFactory<ISecurity, Element> callbackFactory;
    private SecuritiesFactory securityFactory;
    private Connection connection;
    
    public WSEParser(ArrayList<InternalMarket> markets) {
        this.wseInternalMarkets = markets;
        this.callbackFactory = new WSEParserCallbackFactory();
        this.securityFactory = new SecuritiesFactory();
    }
    
    private void addSecurities(final Elements parsedSecurities, InternalMarket internalMarket) 
            throws IllegalArgumentException {
        Vector<ISecurity> market = internalMarket.getSecurities();
        // add new securities only if there are new 
        if (market.size() == parsedSecurities.size()) {
            logger.debug("Security list is up to date.");
            return;
        }

        market.clear();
        
        for (Element security : parsedSecurities) {
            Elements securityName = security.getElementsByClass("nazwa");
            ISecurity newSecurity = securityFactory.getSecurity(internalMarket.getMarketType(), internalMarket.getId());
            newSecurity.setSecurityName(securityName.last().text());
            market.addElement(newSecurity);
        }
    }
    
    private void addSecuritiesId(final Elements parsedSecurities, InternalMarket market) {
        int i = 0;
        for (Element security : parsedSecurities) {
            Elements securityName = security.getElementsByAttribute("id");
            market.getSecurities().elementAt(i).setSecurityId(securityName.last().id());
            ++i;
        }
    }
    
    private void initMarket(final Elements parsedSecurities, InternalMarket market) 
            throws IllegalArgumentException {
        addSecurities(parsedSecurities, market);
        addSecuritiesId(parsedSecurities, market);
    }
    
    private void parseTag(final Elements parsedSecurities, Vector<ISecurity> market, String tag) 
            throws IllegalArgumentException {
        Preconditions.checkArgument(parsedSecurities.size() == market.size(),
                "Parsed too much stuff! Check parsed web site for changes. Parsed elements: %d , Market: %d", parsedSecurities.size(), market.size());
        int i = 0;
        for (Element elem : parsedSecurities) {
            Elements elemValue = elem.getElementsByClass(tag);
            callbackFactory.get(tag).call(market.elementAt(i), elemValue.last());
            i++;
        }
    }
    
    private void parseTags(final Elements parsedSecurities, Vector<ISecurity> market, String[] tagList) {
        for (String tag : tagList) {
            parseTag(parsedSecurities, market, tag);
        }
    }
    
    private Elements getAllSecurities(String pageAddr) throws SecuritiesGettingException {
        try {
            connection = Jsoup.connect(pageAddr);
            connection.timeout(TIMEOUT);
            Document sourceDocument = connection.get();
            Elements parsedElems = sourceDocument.getElementsByAttributeValueMatching("id", reqex);
            return parsedElems;
        } catch (UnknownHostException e) {
            throw new SecuritiesGettingException("Unknown host! ", e);
        } catch (SocketTimeoutException e) {
            throw new SecuritiesGettingException("Timeout while parsing quotes! ", e);
        } catch (IllegalArgumentException e) {
            throw new SecuritiesGettingException(e.getMessage(), e);
        } catch (IOException e) {
            throw new SecuritiesGettingException("IOException caught! ", e);
        }
    }
    
    private void parse(String pageAddr, String[] tagList, InternalMarket market) 
            throws SecuritiesParsingException {
        try {
            Elements allSecurities = getAllSecurities(pageAddr);
            initMarket(allSecurities, market);
            parseTags(allSecurities, market.getSecurities(), tagList);
        } catch (SecuritiesGettingException e) {
            throw new SecuritiesParsingException("Securities not parsed, cause: " + e.getMessage(), e);  
        }
    }
    
    @Override
    public List<InternalMarket>  parseQuotes() throws SecuritiesParsingException {
        // Iterate over all internal markets of WSE and parse it's quotes.
        for (InternalMarket market : wseInternalMarkets) {
            IMarketTypes marketType = market.getMarketType();
            parse(marketType.getPageAddress(), marketType.getTags(), market);
            logger.trace(market.toString());
        }
        return wseInternalMarkets;
    }
    
    @Override
    public void parseInfo() {
        // TODO Auto-generated method stub
        
    }
    
}
