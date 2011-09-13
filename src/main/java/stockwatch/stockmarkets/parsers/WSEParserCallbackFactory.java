package stockwatch.stockmarkets.parsers;

import org.jsoup.nodes.Element;

import stockwatch.securities.Security;

public class WSEParserCallbackFactory implements CallbackFactory <Security, Element> {
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
        } else if (tag == "h") {
            return new Callback<Security, Element>() {
                @Override
                public void call(Security type, Element arg) {
                    type.setHigh(arg.text());
                }
            };
        } else if (tag == "l") {
            return new Callback<Security, Element>() {
                @Override
                public void call(Security type, Element arg) {
                    type.setLow(arg.text());
                }
            };
        } else if (tag == "lop") {
            return new Callback<Security, Element>() {
                @Override
                public void call(Security type, Element arg) {
                    type.setLop(arg.text());
                }
            };
        } else if (tag == "zmiana_lop") {
            return new Callback<Security, Element>() {
                @Override
                public void call(Security type, Element arg) {
                    type.setLopChange(arg.text());
                }
            };
        } else if (tag == "obrot") {
            return new Callback<Security, Element>() {
                @Override
                public void call(Security type, Element arg) {
                    type.setVolume(arg.text());
                }
            };
        } else if (tag == "czas_wygasniecia") {
            return new Callback<Security, Element>() {
                @Override
                public void call(Security type, Element arg) {
                    type.setExpirationDate(arg.text());
                }
            };
        } else {
            throw new IllegalArgumentException("Wrong tag: " + tag);
        }
    }
}