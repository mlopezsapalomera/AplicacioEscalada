package controllers;

import view.ViaView;
import models.ModelController;
import objets.Via;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViaController {
    private static final ModelController modelController = new ModelController();

    // Crear una nova Via
    public static void crearViaController() {
        // Cridar a la vista per obtenir les dades de l'usuari
        Via via = ViaView.mostrarCrearVia();

        // Cridar al mètode que interactua amb la base de dades
        crearVia(via);
    }

    public static void crearVia(Via via) {
        // Inserir una nova Via a la base de dades
        String sql = "INSERT INTO Vies (nom, dificultat, llargada, tipus, sector_id, escola_id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ModelController.ejecutarActualizacion(sql, via.getNom(), via.getDificultat(), via.getLlargada(),
                    via.getTipus(), via.getSectorId(), via.getEscolaId());
            System.out.println("Via creada correctament.");
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
    }

    // Modificar una Via existent
    public static void modificarViaController() {
        // Sol·licitar l'ID de la Via a modificar
        int id = ViaView.mostrarEliminarVia();

        // Obtenir la Via actual des de la base de dades
        Via via = obtenerViaPorId(id);
        if (via == null) {
            System.out.println("Via no trobada.");
            return;
        }

        // Cridar a la vista per modificar les dades
        Via viaModificada = ViaView.mostrarModificarVia(via);

        // Cridar al mètode que interactua amb la base de dades
        modificarVia(viaModificada);
    }

    public static void modificarVia(Via via) {
        // Actualitzar una Via existent a la base de dades
        String sql = "UPDATE Vies SET nom = ?, dificultat = ?, llargada = ?, tipus = ?, sector_id = ? WHERE id = ?";
        try {
            ModelController.ejecutarActualizacion(sql, via.getNom(), via.getDificultat(), via.getLlargada(),
                    via.getTipus(), via.getSectorId(), via.getId());
            System.out.println("Via modificada correctament.");
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
    }

    // Llistar una Via per ID
    public static void llistarViaController() {
        // Sol·licitar l'ID de la Via a llistar
        int id = ViaView.mostrarEliminarVia();

        // Obtenir la Via des de la base de dades
        Via via = obtenerViaPorId(id);

        // Mostrar la Via a la vista
        ViaView.mostrarVia(via);
    }

    // Llistar totes les Vies
    public static void llistarTotesViesController() {
        // Obtenir totes les Vies de la base de dades
        String sql = "SELECT * FROM Vies";
        List<Via> vies = new ArrayList<>();
        try {
            ResultSet rs = ModelController.ejecutarConsulta(sql);
            while (rs.next()) {
                vies.add(mapearVia(rs));
            }
            // Mostrar totes les Vies a la vista
            ViaView.mostrarListarTodosLosViaes(vies);
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
    }

    // Eliminar una Via per ID
    public static void eliminarViaController() {
        // Sol·licitar l'ID de la Via a eliminar
        int id = ViaView.mostrarEliminarVia();

        // Cridar al mètode que interactua amb la base de dades
        eliminarVia(id);
    }

    public static void eliminarVia(int id) {
        // Eliminar una Via de la base de dades
        String sql = "DELETE FROM Vies WHERE id = ?";
        try {
            ModelController.ejecutarActualizacion(sql, id);
            System.out.println("Via eliminada correctament.");
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
    }

    // Obtenir una Via per ID
    private static Via obtenerViaPorId(int id) {
        // Consultar una Via a la base de dades pel seu ID
        String sql = "SELECT * FROM Vies WHERE id = ?";
        try {
            ResultSet rs = ModelController.ejecutarConsulta(sql, id);
            if (rs.next()) {
                return mapearVia(rs);
            }
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
        return null;
    }

    // Mapar un ResultSet a un objecte Via
    private static Via mapearVia(ResultSet rs) throws SQLException {
        // Crear un objecte Via a partir del ResultSet
        Via via = new Via();
        via.setId(rs.getInt("id"));
        via.setNom(rs.getString("nom"));
        via.setDificultat(rs.getString("dificultat"));
        via.setLlargada(rs.getInt("llargada"));
        via.setTipus(rs.getString("tipus"));
        via.setSectorId(rs.getInt("sector_id"));
        return via;
    }
}
