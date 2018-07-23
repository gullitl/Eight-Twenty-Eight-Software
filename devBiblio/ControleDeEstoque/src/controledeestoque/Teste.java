package controledeestoque;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author plamedi.lusembo
 */
public class Teste {

    public static void main(String args[]) {
        Date today = new Date();
        Timestamp timestamp = new Timestamp(today.getTime());
        Timestamp t2 = getTimestamp(today);
        System.out.println("date: " + today);
        System.out.println("timestamp: " + timestamp);
        System.out.println("timestamp2: " + t2);

    }

    public static Timestamp getTimestamp(Date date) {
        return date == null ? null : new java.sql.Timestamp(date.getTime());
    }

}
