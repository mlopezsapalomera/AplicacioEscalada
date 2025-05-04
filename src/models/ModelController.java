package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModelController {
    private static final String URL = "jdbc:mysql://localhost:3306/escalada_db"; // URL de connexió a la base de dades
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection; // Connexió compartida a la base de dades

    // Obtenir una connexió a la base de dades
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Connectar a la base de dades
    public static Connection conectar() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Carregar el controlador JDBC
            } catch (ClassNotFoundException e) {
                throw new SQLException("No s'ha pogut carregar el controlador JDBC", e);
            }
            String url = DatabaseConfig.getDatabaseUrl(); // Obtenir la URL de connexió des de la configuració
            String user = DatabaseConfig.DB_USER; // Obtenir l'usuari des de la configuració
            String password = DatabaseConfig.DB_PASS; // Obtenir la contrasenya des de la configuració
            connection = DriverManager.getConnection(url, user, password); // Establir la connexió
        }
        return connection;
    }

    // Tancar la connexió a la base de dades
    public static void cerrarConexion() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close(); // Tancar la connexió
            System.out.println("Connexió tancada amb la base de dades.");
        }
    }

    // Executar una actualització i retornar l'ID generat
    public static int ejecutarActualizacionConRetornId(String sql, Object... parametros) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Assignar els paràmetres a la consulta
            for (int i = 0; i < parametros.length; i++) {
                stmt.setObject(i + 1, parametros[i]);
            }

            // Executar la consulta
            stmt.executeUpdate();

            // Obtenir l'ID generat automàticament
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Retornar l'ID generat
                } else {
                    throw new SQLException("No s'ha generat cap ID.");
                }
            }
        }
    }

    // Executar consultes SELECT
    public static ResultSet ejecutarConsulta(String sql, Object... parametros) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        // Assignar els paràmetres a la consulta
        for (int i = 0; i < parametros.length; i++) {
            stmt.setObject(i + 1, parametros[i]);
        }

        return stmt.executeQuery(); // Retornar el resultat de la consulta
    }

    // Executar una actualització sense retorn
    public static void ejecutarActualizacion(String sql, Object... parametros) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Assignar els paràmetres a la consulta
            for (int i = 0; i < parametros.length; i++) {
                stmt.setObject(i + 1, parametros[i]);
            }

            stmt.executeUpdate(); // Executar l'actualització
        }
    }

    // Gestionar excepcions SQL
    public static void manejarExcepcion(SQLException e) {
        System.err.println("Error SQL: " + e.getMessage()); // Mostrar el missatge d'error
        e.printStackTrace(); // Mostrar la traça de l'error
    }
}
