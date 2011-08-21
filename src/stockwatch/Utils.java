package stockwatch;

import java.util.Collection;
import java.util.Comparator;

public class Utils {

    public final static int UNDEFINED_VALUE = -1;
    
    private interface Predicate {
        public boolean test(Object obj);
    }

    static public class Up implements Predicate {
        public boolean test(Object obj) {
            Security sec = (Security) obj;
            return sec.getChange() > 0 ? true : false;
        }
    }

    static public class Down implements Predicate {
        public boolean test(Object obj) {
            Security sec = (Security) obj;
            return sec.getChange() < 0 ? true : false;
        }
    }

    static public <T> int countIf(Collection<T> coll, Predicate p) {
        int countedPred = 0;
        for (T elem : coll) {
            if (p.test(elem))
                ++countedPred;
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

}
