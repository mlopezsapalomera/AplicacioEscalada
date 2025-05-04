package models;

public class DatabaseConfig {
    public static final String DB_HOST = "localhost";
    public static final String DB_NAME = "escalada_db";
    public static final String DB_USER = "root"; 
    public static final String DB_PASS = "";    

    public static String getDatabaseUrl() {
        return "jdbc:mysql://" + DB_HOST + ":3306/" + DB_NAME;
    }
}
