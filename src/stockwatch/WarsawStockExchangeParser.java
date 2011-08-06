/**
 * 
 */
package stockwatch;

import java.io.IOException;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ParserExceptions.ParserException;

public class WarsawStockExchangeParser implements QuotesParser {
    
    private final static String pageAddress = "http://www.parkiet.com/temat/63.html";

    Vector<Company> Companies;
    private boolean parseCompanies;
    
    public WarsawStockExchangeParser() {
        Companies = new Vector<Company>();
        parseCompanies = true;
    }

    void checkConsistency(Elements parsedElems, Vector<Company> companies) throws ParserException {
        if (parsedElems.size() != companies.size()) {
            throw new ParserException("Difference beetween parsed elements size and companies number");
        }
    }

    private void parseCompanies(Document parsedDocument) {
        Elements companies = parsedDocument.getElementsByClass("nazwa");
        for (Element company : companies) {
            Company newCompany = new Company();
            newCompany.setCompanyName(company.text());
            Companies.addElement(newCompany);
        }
    }

    private void parseComapaniesIds(Document parsedDocument) {
        Elements l_params = parsedDocument.getElementsByAttribute("onClick");
        int i = 0;
        for (Element src : l_params) {
            String[] l_id = src.attributes().get("onClick").split("'");
            if (l_id.length > 4) {
                Companies.elementAt(i).setCompanyId(l_id[5]);
            }
            i++;
        }
    }

    private void parseLastTransactionPrice(Document parsedDocument) throws ParserException {
        Elements closePrices = parsedDocument.getElementsByClass("c");
        checkConsistency(closePrices, Companies);

        int i = 0;
        for (Element closePrice : closePrices) {
            Companies.elementAt(i).setLastTransactionPrice(closePrice.text());
            i++;
        }
    }

    private void parseOpenPrice(Document parsedDocument) throws ParserException {
        Elements openPrices = parsedDocument.getElementsByClass("o");
        checkConsistency(openPrices, Companies);

        int i = 0;
        for (Element openPrice : openPrices) {
            Companies.elementAt(i).setOpenPrice(openPrice.text());
            i++;
        }
    }

    private void parsePercentageChange(Document parsedDocument) throws ParserException {
        Elements percentageChanges = parsedDocument.getElementsByClass("zmiana");
        checkConsistency(percentageChanges, Companies);

        int i = 0;
        for (Element change : percentageChanges) {
            Companies.elementAt(i).setPercentageChange(change.text());
            i++;
        }
    }

    private void parseLastTransactionTime(Document parsedDocument) throws ParserException {
        Elements lastTimeTransaction = parsedDocument.getElementsByClass("czas");
        checkConsistency(lastTimeTransaction, Companies);

        int i = 0;
        for (Element time : lastTimeTransaction) {
            Companies.elementAt(i).setLastChanged(time.ownText());
            i++;
        }
    }

    
    @Override
    public Vector<Company> parseQuotes() {
        try {
            Document sourceDocument = Jsoup.connect(pageAddress).get();

            if (parseCompanies) {
                parseCompanies(sourceDocument);
                parseComapaniesIds(sourceDocument);
                parseCompanies = false;
            }

            parseOpenPrice(sourceDocument);
            parseLastTransactionPrice(sourceDocument);
            parsePercentageChange(sourceDocument);
            parseLastTransactionTime(sourceDocument);

        } catch (IOException e) {
            System.out.print(e.getMessage());
            e.printStackTrace();
        } catch (ParserException e) {
            System.out.print(e.getMessage());
            e.printStackTrace();
        }
        
        return Companies;
    }

    @Override
    public void parseInfo() {
        // TODO Auto-generated method stub
        
    }

}
