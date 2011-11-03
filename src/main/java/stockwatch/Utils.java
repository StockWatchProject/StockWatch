package stockwatch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import stockwatch.securities.ISecurity;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

public class Utils {

    public final static int UNDEFINED_VALUE = -1;
    
    static public Predicate<ISecurity> isUp = new Predicate<ISecurity>() {
        @Override
        public boolean apply(ISecurity arg) {
            return arg.getPercentageChange() > 0;
        }
    };
    
    static public Predicate<ISecurity> isDown = new Predicate<ISecurity>() {
        @Override
        public boolean apply(ISecurity arg) {
            return arg.getPercentageChange() < 0;
        }
    };
    
    public static <T> ArrayList<T> findIf(Collection<T> coll, Predicate<T> predicate) {
        ArrayList<T> filteredList = Lists.newArrayList();
        for (T obj : coll) {
             if (predicate.apply(obj)) {
                 filteredList.add(obj);
             }
        }
        return filteredList;
    }
    
    static public <T> int countIf(Collection<T> coll, Predicate<T> predicate) {
        int countedPred = 0;
        for (T elem : coll) {
            if (predicate.apply(elem)) {
                ++countedPred;
            }
        }
        return countedPred;
    }

    static public class CompanyComparator implements Comparator<ISecurity> {
        @Override
        public int compare(ISecurity o1, ISecurity o2) {
            if (o1.getPercentageChange() < o2.getPercentageChange()) {
                return 1;
            }
            else if (o1.getPercentageChange() > o2.getPercentageChange()) {
                return -1;
            }
            else if (o1.getPercentageChange() == UNDEFINED_VALUE || o2.getPercentageChange() == UNDEFINED_VALUE) {
                return 0;
            }
            else {
                return 0;
            }
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
