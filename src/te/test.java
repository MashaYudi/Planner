package te;

import java.util.*;

/**
 * Created by Мария on 29.05.2016.
 */
public class test {

    int index(List c, Object a) {
        for (int i:c.
             ) {

        }
        forea (int i = 0; i < c.size(); i++) {
            if (c.get(i) == a) return i;
        }
        return -1;
    }

    static String[] sort11(String[] i) {
        LinkedList<String> l = new LinkedList<String>();
        l.sort(Collections.reverseOrder());
        return l.toArray(new String[0]);
        //return (String[]) l.toArray();
    }

}
