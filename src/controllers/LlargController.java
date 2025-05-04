package controllers;

import view.LlargView;
import models.ModelController;
import objets.Llarg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LlargController {
    private static final ModelController modelController = new ModelController();

    // Crear un nou Llarg
    public static void crearLlargController(int viaId) {
        // Obtenir les dades del nou llarg des de la vista
        Llarg llarg = LlargView.mostrarCrearLlarg();
        llarg.setViaId(viaId); // Assignar l'ID de la via al llarg
        crearLlarg(llarg);
    }

    public static void crearLlarg(Llarg llarg) {
        // Inserir un nou llarg a la base de dades
        String sql = "INSERT INTO Llargs (via_id, numero_llarg, llargada, dificultat, orientacio, estat, data_no_apta_until, ancoratge) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ModelController.ejecutarActualizacion(sql, llarg.getViaId(), llarg.getNumeroLlarg(), llarg.getLlargada(),
                    llarg.getDificultat(), llarg.getOrientacio(), llarg.getEstat(), llarg.getDataNoAptaUntil(),
                    llarg.getAncoratge());
            System.out.println("Llarg creat correctament.");
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
    }

    // Llistar tots els Llargs d'una Via
    public static void llistarLlargsPerVia(int viaId) {
        // Obtenir tots els llargs associats a una via des de la base de dades
        String sql = "SELECT * FROM Llargs WHERE via_id = ?";
        List<Llarg> llargs = new ArrayList<>();
        try {
            ResultSet rs = ModelController.ejecutarConsulta(sql, viaId);
            while (rs.next()) {
                llargs.add(mapearLlarg(rs)); // Mapar cada resultat a un objecte Llarg
            }
            // Mostrar els llargs a la vista
            LlargView.mostrarListarTodosLosLlargs(llargs);
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
    }

    // Eliminar un llarg per ID
    public static void eliminarLlarg(int llargId) {
        // Eliminar un llarg de la base de dades
        String sql = "DELETE FROM Llargs WHERE id = ?";
        try {
            ModelController.ejecutarActualizacion(sql, llargId);
            System.out.println("Llarg eliminat correctament.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar el llarg: " + e.getMessage());
        }
    }

    // Mapar un ResultSet a un objecte Llarg
    private static Llarg mapearLlarg(ResultSet rs) throws SQLException {
        // Crear un objecte Llarg a partir del ResultSet
        Llarg llarg = new Llarg();
        llarg.setId(rs.getInt("id"));
        llarg.setViaId(rs.getInt("via_id"));
        llarg.setNumeroLlarg(rs.getInt("numero_llarg"));
        llarg.setLlargada(rs.getInt("llargada"));
        llarg.setDificultat(rs.getString("dificultat"));
        llarg.setOrientacio(rs.getString("orientacio"));
        llarg.setEstat(rs.getString("estat"));
        llarg.setDataNoAptaUntil(rs.getString("data_no_apta_until"));
        llarg.setAncoratge(rs.getString("ancoratge"));
        return llarg;
    }

    // Modificar un llarg existent
    public static void modificarLlargController(int llargId) {
        // Obtenir el llarg actual des de la base de dades
        Llarg llarg = obtenerLlargPorId(llargId);
        if (llarg == null) {
            System.out.println("Llarg no trobat.");
            return;
        }

        // Sol·licitar les noves dades des de la vista
        Llarg llargModificat = LlargView.mostrarModificarLlarg(llarg);

        // Actualitzar el llarg a la base de dades
        modificarLlarg(llargModificat);
    }

    public static void modificarLlarg(Llarg llarg) {
        // Actualitzar un llarg existent a la base de dades
        String sql = "UPDATE Llargs SET numero_llarg = ?, llargada = ?, dificultat = ?, orientacio = ?, estat = ?, ancoratge = ? WHERE id = ?";
        try {
            ModelController.ejecutarActualizacion(sql, llarg.getNumeroLlarg(), llarg.getLlargada(), llarg.getDificultat(),
                    llarg.getOrientacio(), llarg.getEstat(), llarg.getAncoratge(), llarg.getId());
            System.out.println("Llarg modificat correctament.");
        } catch (SQLException e) {
            System.out.println("Error al modificar el Llarg: " + e.getMessage());
        }
    }

    // Obtenir un llarg per ID
    private static Llarg obtenerLlargPorId(int llargId) {
        // Consultar un llarg a la base de dades pel seu ID
        String sql = "SELECT * FROM Llargs WHERE id = ?";
        try {
            ResultSet rs = ModelController.ejecutarConsulta(sql, llargId);
            if (rs.next()) {
                Llarg llarg = new Llarg();
                llarg.setId(rs.getInt("id"));
                llarg.setViaId(rs.getInt("via_id"));
                llarg.setNumeroLlarg(rs.getInt("numero_llarg"));
                llarg.setLlargada(rs.getInt("llargada"));
                llarg.setDificultat(rs.getString("dificultat"));
                llarg.setOrientacio(rs.getString("orientacio"));
                llarg.setEstat(rs.getString("estat"));
                llarg.setAncoratge(rs.getString("ancoratge"));
                return llarg;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtenir el Llarg: " + e.getMessage());
        }
        return null;
    }
}
