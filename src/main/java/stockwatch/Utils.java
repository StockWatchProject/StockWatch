package stockwatch;

import java.util.Collection;
import java.util.Comparator;

import com.google.common.base.Predicate;

public class Utils {

    public final static int UNDEFINED_VALUE = -1;
    
    static public Predicate<Security> isUp = new Predicate<Security>() {
        @Override
        public boolean apply(Security arg) {
            return arg.getChange() > 0 ? true : false;
        }
    };
    
    static public Predicate<Security> isDown = new Predicate<Security>() {
        @Override
        public boolean apply(Security arg) {
            return arg.getChange() < 0 ? true : false;
        }
    };

    static public <T> int countIf(Collection<T> coll, Predicate<T> p) {
        int countedPred = 0;
        for (T elem : coll) {
            if (p.apply(elem)) {
                ++countedPred;
            }
        }
        return countedPred;
    }

    static public class CompanyComparator implements Comparator<Security> {

        @Override
        public int compare(Security o1, Security o2) {

            if (o1.getChange() < o2.getChange())
                return 1;
            else if (o1.getChange() > o2.getChange())
                return -1;
            else if (o1.getChange() == UNDEFINED_VALUE || o2.getChange() == UNDEFINED_VALUE)
                return 0;
            else
                return 0;
        }

    }
    
    public static <T extends Enum<T>> String[] enumToStringArray(T[] values) {  
        int i = 0;  
        String[] result = new String[values.length];  
        for (T value: values) {  
            result[i++] = value.name();  
        }  
        return result;  
    }

}
