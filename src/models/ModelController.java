package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModelController {
    private static final String URL = "jdbc:mysql://localhost:3306/escalada_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection; // Cambiar a estático

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static Connection conectar() throws SQLException { // Cambiar a estático
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException("No se pudo cargar el controlador JDBC", e);
            }
            String url = DatabaseConfig.getDatabaseUrl();
            String user = DatabaseConfig.DB_USER;
            String password = DatabaseConfig.DB_PASS;
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    public static void cerrarConexion() throws SQLException { // Cambiar a estático
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Conexión cerrada con la base de datos.");
        }
    }

    // Método para ejecutar una actualización y devolver el ID generado
    public static int ejecutarActualizacionConRetornId(String sql, Object... parametros) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Asignar los parámetros a la consulta
            for (int i = 0; i < parametros.length; i++) {
                stmt.setObject(i + 1, parametros[i]);
            }

            // Ejecutar la consulta
            stmt.executeUpdate();

            // Obtener el ID generado automáticamente
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Retornar el ID generado
                } else {
                    throw new SQLException("No s'ha generat cap ID.");
                }
            }
        }
    }

    // Método para ejecutar consultas SELECT
    public static ResultSet ejecutarConsulta(String sql, Object... parametros) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        // Asignar los parámetros a la consulta
        for (int i = 0; i < parametros.length; i++) {
            stmt.setObject(i + 1, parametros[i]);
        }

        return stmt.executeQuery();
    }

    // Método para ejecutar una actualización sin retorno
    public static void ejecutarActualizacion(String sql, Object... parametros) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Asignar los parámetros a la consulta
            for (int i = 0; i < parametros.length; i++) {
                stmt.setObject(i + 1, parametros[i]);
            }

            stmt.executeUpdate();
        }
    }

    // Método para manejar excepciones de SQL
    public static void manejarExcepcion(SQLException e) { // Cambiar a estático
        System.err.println("Error SQL: " + e.getMessage());
        e.printStackTrace();
    }
}
