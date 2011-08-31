package stockwatch;

import org.jsoup.nodes.Element;

public class WseParserCallbackFactory implements CallbackFactory <Security, Element> {
    public Callback<Security, Element> get(String tag) throws IllegalArgumentException {
        if (tag == "zmiana") {
            return new Callback<Security, Element>() {
                @Override
                public void call(Security type, Element arg) {
                    type.setPercentageChange(arg.text());
                }
            };
        } else if (tag == "czas") {
            return new Callback<Security, Element>() {
                @Override
                public void call(Security type, Element arg) {
                    type.setLastChangedTime(arg.ownText());
                }
            };
        } else if (tag == "o") {
            return new Callback<Security, Element>() {
                @Override
                public void call(Security type, Element arg) {
                    type.setOpenPrice(arg.text());
                }
            };
        } else if (tag == "c") {
            return new Callback<Security, Element>() {
                @Override
                public void call(Security type, Element arg) {
                    type.setLastTransactionPrice(arg.text());
                }
            };
        } else {
            throw new IllegalArgumentException("Wrong tag: " + tag);
        }
    }
}