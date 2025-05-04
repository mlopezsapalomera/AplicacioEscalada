package controllers;
import view.EscaladorView;
import models.ModelController;
import objets.Escalador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EscaladorController {
    private static final ModelController modelController = new ModelController();

    // Crear un nuevo escalador
    public static void crearEscaladorController() {
        // Llamar a la vista para obtener los datos del usuario
        Escalador escalador = EscaladorView.mostrarCrearEscalador();
        
        // Llamar al método que interactúa con la base de datos
        crearEscalador(escalador);
    }

    public static void crearEscalador(Escalador escalador) {
        String sql = "INSERT INTO Escaladors (nom, alias, edat, nivell_max, via_max_id, estil_preferit, historial, fita) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ModelController.ejecutarActualizacion(sql, escalador.getNom(), escalador.getAlias(), escalador.getEdat(),
                    escalador.getNivellMax(), escalador.getViaMaxId(), escalador.getEstilPreferit(),
                    escalador.getHistorial(), escalador.getFita());
            System.out.println("Escalador creado correctamente.");
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
    }

    // Modificar un escalador existente
    public static void modificarEscaladorController() {
        // Solicitar el ID del escalador a modificar desde la vista
        int id = EscaladorView.mostrarEliminarEscalador(); // Reutilizamos este método para pedir el ID

        // Buscar el escalador en la base de datos
        Escalador escalador = llistarEscaladorController(id);
        if (escalador != null) {
            // Solicitar los nuevos datos al usuario
            escalador = EscaladorView.mostrarModificarEscalador(escalador);

            // Actualizar el escalador en la base de datos
            modificarEscalador(escalador);
        } else {
            System.out.println("Escalador no encontrado.");
        }
    }

    public static void modificarEscalador(Escalador escalador) {
        String sql = "UPDATE Escaladors SET nom = ?, alias = ?, edat = ?, nivell_max = ?, via_max_id = ?, estil_preferit = ?, historial = ?, fita = ? WHERE id = ?";
        try {
            ModelController.ejecutarActualizacion(sql, escalador.getNom(), escalador.getAlias(), escalador.getEdat(),
                    escalador.getNivellMax(), escalador.getViaMaxId(), escalador.getEstilPreferit(),
                    escalador.getHistorial(), escalador.getFita(), escalador.getId());
            System.out.println("Escalador modificado correctamente.");
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
    }

    // Listar un escalador por ID
    public static Escalador llistarEscaladorController(int id) {
        String sql = "SELECT * FROM Escaladors WHERE id = ?";
        try {
            ResultSet rs = ModelController.ejecutarConsulta(sql, id);
            if (rs.next()) {
                Escalador escalador = mapearEscalador(rs);
                EscaladorView.mostrarEscalador(escalador); // Mostrar el escalador en la vista
                return escalador;
            } else {
                System.out.println("Escalador no encontrado.");
            }
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
        return null;
    }

    // Listar todos los escaladores
    public static void llistarTotsEscaladorsController() {
        String sql = "SELECT * FROM Escaladors";
        List<Escalador> escaladors = new ArrayList<>();
        try {
            ResultSet rs = ModelController.ejecutarConsulta(sql);
            while (rs.next()) {
                escaladors.add(mapearEscalador(rs));
            }
            // Llamar a la vista para mostrar los escaladores
            EscaladorView.mostrarListarTodosLosEscaladores(escaladors);
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
    }

    // Eliminar un escalador por ID
    public static void eliminarEscaladorController(int id) {
        String sql = "DELETE FROM Escaladors WHERE id = ?";
        try {
            ModelController.ejecutarActualizacion(sql, id);
            System.out.println("Escalador eliminado correctamente.");
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
    }

    // Mapear un ResultSet a un objeto Escalador
    private static Escalador mapearEscalador(ResultSet rs) throws SQLException {
        Escalador escalador = new Escalador();
        escalador.setId(rs.getInt("id"));
        escalador.setNom(rs.getString("nom"));
        escalador.setAlias(rs.getString("alias"));
        escalador.setEdat(rs.getInt("edat"));
        escalador.setNivellMax(rs.getString("nivell_max"));
        escalador.setViaMaxId(rs.getInt("via_max_id"));
        escalador.setEstilPreferit(rs.getString("estil_preferit"));
        escalador.setHistorial(rs.getString("historial"));
        escalador.setFita(rs.getString("fita"));
        return escalador;
    }
}
