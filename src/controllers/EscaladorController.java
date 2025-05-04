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

    // Crear un nou escalador
    public static void crearEscaladorController() {
        // Cridar a la vista per obtenir les dades de l'usuari
        Escalador escalador = EscaladorView.mostrarCrearEscalador();
        
        // Cridar al mètode que interactua amb la base de dades
        crearEscalador(escalador);
    }

    public static void crearEscalador(Escalador escalador) {
        String sql = "INSERT INTO Escaladors (nom, alias, edat, nivell_max, via_max_id, estil_preferit, historial, fita) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ModelController.ejecutarActualizacion(sql, escalador.getNom(), escalador.getAlias(), escalador.getEdat(),
                    escalador.getNivellMax(), escalador.getViaMaxId(), escalador.getEstilPreferit(),
                    escalador.getHistorial(), escalador.getFita());
            System.out.println("Escalador creat correctament.");
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
    }

    // Modificar un escalador existent
    public static void modificarEscaladorController() {
        // Sol·licitar l'ID de l'escalador a modificar des de la vista
        int id = EscaladorView.mostrarEliminarEscalador(); // Reutilitzem aquest mètode per demanar l'ID

        // Buscar l'escalador a la base de dades
        Escalador escalador = llistarEscaladorController(id);
        if (escalador != null) {
            // Sol·licitar les noves dades a l'usuari
            escalador = EscaladorView.mostrarModificarEscalador(escalador);

            // Actualitzar l'escalador a la base de dades
            modificarEscalador(escalador);
        } else {
            System.out.println("Escalador no trobat.");
        }
    }

    public static void modificarEscalador(Escalador escalador) {
        String sql = "UPDATE Escaladors SET nom = ?, alias = ?, edat = ?, nivell_max = ?, via_max_id = ?, estil_preferit = ?, historial = ?, fita = ? WHERE id = ?";
        try {
            ModelController.ejecutarActualizacion(sql, escalador.getNom(), escalador.getAlias(), escalador.getEdat(),
                    escalador.getNivellMax(), escalador.getViaMaxId(), escalador.getEstilPreferit(),
                    escalador.getHistorial(), escalador.getFita(), escalador.getId());
            System.out.println("Escalador modificat correctament.");
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
    }

    // Llistar un escalador per ID
    public static Escalador llistarEscaladorController(int id) {
        String sql = "SELECT * FROM Escaladors WHERE id = ?";
        try {
            ResultSet rs = ModelController.ejecutarConsulta(sql, id);
            if (rs.next()) {
                Escalador escalador = mapearEscalador(rs);
                EscaladorView.mostrarEscalador(escalador); // Mostrar l'escalador a la vista
                return escalador;
            } else {
                System.out.println("Escalador no trobat.");
            }
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
        return null;
    }

    // Llistar tots els escaladors
    public static void llistarTotsEscaladorsController() {
        String sql = "SELECT * FROM Escaladors";
        List<Escalador> escaladors = new ArrayList<>();
        try {
            ResultSet rs = ModelController.ejecutarConsulta(sql);
            while (rs.next()) {
                escaladors.add(mapearEscalador(rs));
            }
            // Cridar a la vista per mostrar els escaladors
            EscaladorView.mostrarListarTodosLosEscaladores(escaladors);
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
    }

    // Eliminar un escalador per ID
    public static void eliminarEscaladorController(int id) {
        String sql = "DELETE FROM Escaladors WHERE id = ?";
        try {
            ModelController.ejecutarActualizacion(sql, id);
            System.out.println("Escalador eliminat correctament.");
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
    }

    // Mapar un ResultSet a un objecte Escalador
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
