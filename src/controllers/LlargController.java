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
        Llarg llarg = LlargView.mostrarCrearLlarg();
        llarg.setViaId(viaId);
        crearLlarg(llarg);
    }

    public static void crearLlarg(Llarg llarg) {
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
        String sql = "SELECT * FROM Llargs WHERE via_id = ?";
        List<Llarg> llargs = new ArrayList<>();
        try {
            ResultSet rs = ModelController.ejecutarConsulta(sql, viaId);
            while (rs.next()) {
                llargs.add(mapearLlarg(rs));
            }
            LlargView.mostrarListarTodosLosLlargs(llargs);
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
    }

    // Método para eliminar un llarg por ID
    public static void eliminarLlarg(int llargId) {
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

    public static void modificarLlargController(int llargId) {
        // Obtener el llarg actual desde la base de datos
        Llarg llarg = obtenerLlargPorId(llargId);
        if (llarg == null) {
            System.out.println("Llarg no trobat.");
            return;
        }

        // Solicitar los nuevos datos desde la vista
        Llarg llargModificat = LlargView.mostrarModificarLlarg(llarg);

        // Actualizar el llarg en la base de datos
        modificarLlarg(llargModificat);
    }

    public static void modificarLlarg(Llarg llarg) {
        String sql = "UPDATE Llargs SET numero_llarg = ?, llargada = ?, dificultat = ?, orientacio = ?, estat = ?, ancoratge = ? WHERE id = ?";
        try {
            ModelController.ejecutarActualizacion(sql, llarg.getNumeroLlarg(), llarg.getLlargada(), llarg.getDificultat(),
                    llarg.getOrientacio(), llarg.getEstat(), llarg.getAncoratge(), llarg.getId());
            System.out.println("Llarg modificat correctament.");
        } catch (SQLException e) {
            System.out.println("Error al modificar el Llarg: " + e.getMessage());
        }
    }

    private static Llarg obtenerLlargPorId(int llargId) {
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
