package ke.co.safaricom.config;

import org.sql2o.Sql2o;

public class Database {
    // pick values from environment variables : export DB_URL='value'
    private final static String DB_USER = System.getenv("DB_USER");
    private final static String DB_PASS = System.getenv("DB_PASS");
    private final static String DB_URL = System.getenv("DB_URL");
    private static final Sql2o sql2o = new Sql2o(DB_URL, DB_USER, DB_PASS);

    public static Sql2o getDatabase(){
        return Database.sql2o;
    }
}
