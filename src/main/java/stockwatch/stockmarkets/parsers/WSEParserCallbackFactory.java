package stockwatch.stockmarkets.parsers;

import org.jsoup.nodes.Element;

import stockwatch.securities.ISecurity;

public class WSEParserCallbackFactory implements CallbackFactory <ISecurity, Element> {
    public Callback<ISecurity, Element> get(String tag) throws IllegalArgumentException {
        if (tag.equals("zmiana")) {
            return new Callback<ISecurity, Element>() {
                @Override
                public void call(ISecurity type, Element arg) {
                    type.setPercentageChange(arg.text());
                }
            };
        } else if (tag.equals("czas")) {
            return new Callback<ISecurity, Element>() {
                @Override
                public void call(ISecurity type, Element arg) {
                    type.setLastChangedTime(arg.ownText());
                }
            };
        } else if (tag.equals("o")) {
            return new Callback<ISecurity, Element>() {
                @Override
                public void call(ISecurity type, Element arg) {
                    type.setOpenPrice(arg.text());
                }
            };
        } else if (tag.equals("c")) {
            return new Callback<ISecurity, Element>() {
                @Override
                public void call(ISecurity type, Element arg) {
                    type.setLastTransactionPrice(arg.text());
                }
            };
        } else if (tag.equals("h")) {
            return new Callback<ISecurity, Element>() {
                @Override
                public void call(ISecurity type, Element arg) {
                    type.setHigh(arg.text());
                }
            };
        } else if (tag.equals("l")) {
            return new Callback<ISecurity, Element>() {
                @Override
                public void call(ISecurity type, Element arg) {
                    type.setLow(arg.text());
                }
            };
        } else if (tag.equals("lop")) {
            return new Callback<ISecurity, Element>() {
                @Override
                public void call(ISecurity type, Element arg) {
                    type.setLop(arg.text());
                }
            };
        } else if (tag.equals("zmiana_lop")) {
            return new Callback<ISecurity, Element>() {
                @Override
                public void call(ISecurity type, Element arg) {
                    type.setLopChange(arg.text());
                }
            };
        } else if (tag.equals("obrot")) {
            return new Callback<ISecurity, Element>() {
                @Override
                public void call(ISecurity type, Element arg) {
                    type.setVolume(arg.text());
                }
            };
        } else if (tag.equals("czas_wygasniecia")) {
            return new Callback<ISecurity, Element>() {
                @Override
                public void call(ISecurity type, Element arg) {
                    type.setExpirationDate(arg.text());
                }
            };
        } else {
            throw new IllegalArgumentException("Wrong tag: " + tag);
        }
    }
}