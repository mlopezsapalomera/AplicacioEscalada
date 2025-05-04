package models;

public class DatabaseConfig {
    // Configuració de la base de dades
    public static final String DB_HOST = "localhost";
    public static final String DB_NAME = "escalada_db";
    public static final String DB_USER = "root";
    public static final String DB_PASS = "";
    // Obtenir la URL de connexió a la base de dades
    public static String getDatabaseUrl() {
        return "jdbc:mysql://" + DB_HOST + ":3306/" + DB_NAME; // Retorna la URL de connexió
    }
}
