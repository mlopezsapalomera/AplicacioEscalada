package controllers;

import view.EscolaView;
import models.ModelController;
import objets.Escola;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EscolaController {
    private static final ModelController modelController = new ModelController();

    // Crear una nova Escola
    public static void crearEscolaController() {
        // Cridar a la vista per obtenir les dades de l'usuari
        Escola escola = EscolaView.mostrarCrearEscola();

        // Cridar al mètode que interactua amb la base de dades
        crearEscola(escola);
    }

    public static void crearEscola(Escola escola) {
        String sql = "INSERT INTO Escoles (nom, lloc, aproximacio, num_vies, popularitat, restriccions) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            int idGenerat = models.ModelController.ejecutarActualizacionConRetornId(sql, escola.getNom(), escola.getLloc(),
                    escola.getAproximacio(), escola.getNumVies(), escola.getPopularitat(), escola.getRestriccions());
            System.out.println("Escola creada correctament amb ID: " + idGenerat);
        } catch (SQLException e) {
            System.out.println("Error al crear l'Escola: " + e.getMessage());
        }
    }

    // Modificar una Escola existent
    public static void modificarEscolaController() {
        // Sol·licitar l'ID de l'Escola a modificar
        int id = EscolaView.mostrarEliminarEscola();

        // Obtenir l'Escola actual des de la base de dades
        Escola escola = obtenerEscolaPorId(id);
        if (escola == null) {
            System.out.println("Escola no trobada.");
            return;
        }

        // Cridar a la vista per modificar les dades
        Escola escolaModificada = EscolaView.mostrarModificarEscola(escola);

        // Cridar al mètode que interactua amb la base de dades
        modificarEscola(escolaModificada);
    }

    public static void modificarEscola(Escola escola) {
        String sql = "UPDATE Escoles SET nom = ?, lloc = ?, aproximacio = ?, num_vies = ?, popularitat = ?, restriccions = ? WHERE id = ?";
        try {
            models.ModelController.ejecutarActualizacion(sql, escola.getNom(), escola.getLloc(), escola.getAproximacio(),
                    escola.getNumVies(), escola.getPopularitat(), escola.getRestriccions(), escola.getId());
            System.out.println("Escola modificada correctament.");
        } catch (SQLException e) {
            models.ModelController.manejarExcepcion(e);
        }
    }

    // Llistar una Escola per ID
    public static void llistarEscolaController() {
        // Sol·licitar l'ID de l'Escola a llistar
        int id = EscolaView.mostrarEliminarEscola();

        // Obtenir l'Escola des de la base de dades
        Escola escola = obtenerEscolaPorId(id);

        // Mostrar l'Escola a la vista
        EscolaView.mostrarEscola(escola);
    }

    // Llistar totes les Escoles
    public static void llistarTotesEscolesController() {
        String sql = "SELECT * FROM Escoles";
        List<Escola> escoles = new ArrayList<>();
        try {
            ResultSet rs = models.ModelController.ejecutarConsulta(sql);
            while (rs.next()) {
                escoles.add(mapearEscola(rs));
            }
            // Mostrar totes les Escoles a la vista
            EscolaView.mostrarListarTodosLosEscoles(escoles);
        } catch (SQLException e) {
            models.ModelController.manejarExcepcion(e);
        }
    }

    // Eliminar una Escola per ID
    public static void eliminarEscolaController() {
        // Sol·licitar l'ID de l'Escola a eliminar
        int id = EscolaView.mostrarEliminarEscola();

        // Cridar al mètode que interactua amb la base de dades
        eliminarEscola(id);
    }

    public static void eliminarEscola(int id) {
        String sql = "DELETE FROM Escoles WHERE id = ?";
        try {
            models.ModelController.ejecutarActualizacion(sql, id);
            System.out.println("Escola eliminada correctament.");
        } catch (SQLException e) {
            models.ModelController.manejarExcepcion(e);
        }
    }

    // Obtenir una Escola per ID
    private static Escola obtenerEscolaPorId(int id) {
        String sql = "SELECT * FROM Escoles WHERE id = ?";
        try {
            ResultSet rs = models.ModelController.ejecutarConsulta(sql, id);
            if (rs.next()) {
                return mapearEscola(rs);
            }
        } catch (SQLException e) {
            models.ModelController.manejarExcepcion(e);
        }
        return null;
    }

    // Mapar un ResultSet a un objecte Escola
    private static Escola mapearEscola(ResultSet rs) throws SQLException {
        Escola escola = new Escola();
        escola.setId(rs.getInt("id"));
        escola.setNom(rs.getString("nom"));
        escola.setLloc(rs.getString("lloc"));
        escola.setAproximacio(rs.getString("aproximacio"));
        escola.setNumVies(rs.getInt("num_vies"));
        escola.setPopularitat(rs.getString("popularitat"));
        escola.setRestriccions(rs.getString("restriccions"));
        return escola;
    }
}
