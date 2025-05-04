package controllers;

import view.SectorView;
import models.ModelController;
import objets.Sector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SectorController {
    private static final ModelController modelController = new ModelController();

    // Crear un nou Sector
    public static void crearSectorController() {
        // Solicitar los datos del sector desde la vista
        Sector sector = SectorView.mostrarCrearSector();

        // Validar que el ID de la escuela existe
        if (!escolaExiste(sector.getEscolaId())) {
            System.out.println("Error: L'ID de l'Escola proporcionat no existeix.");
            return;
        }

        // Crear el sector
        crearSector(sector);
    }

    private static boolean escolaExiste(int escolaId) {
        String sql = "SELECT COUNT(*) FROM Escoles WHERE id = ?";
        try {
            ResultSet rs = ModelController.ejecutarConsulta(sql, escolaId);
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al validar l'Escola: " + e.getMessage());
        }
        return false;
    }

    public static void crearSector(Sector sector) {
        String sql = "INSERT INTO Sectors (nom, escola_id, num_vies, coordenades, aproximacio, popularitat, restriccions) " +
                     "VALUES (?, ?, ?, ST_GeomFromText(?), ?, ?, ?)";
        try {
            String coordenades = "POINT(" + sector.getCoordenades().replace(",", " ") + ")";
            ModelController.ejecutarActualizacion(sql, sector.getNom(), sector.getEscolaId(), sector.getNumVies(),
                    coordenades, sector.getAproximacio(), sector.getPopularitat(), sector.getRestriccions());
            System.out.println("Sector creat correctament.");
        } catch (SQLException e) {
            System.out.println("Error al crear el Sector: " + e.getMessage());
        }
    }

    // Modificar un Sector existent
    public static void modificarSectorController() {
        // Sol·licitar l'ID del Sector a modificar
        int id = SectorView.mostrarEliminarSector();

        // Obtenir el Sector actual des de la base de dades
        Sector sector = obtenerSectorPorId(id);
        if (sector == null) {
            System.out.println("Sector no trobat.");
            return;
        }

        // Cridar a la vista per modificar les dades
        Sector sectorModificat = SectorView.mostrarModificarSector(sector);

        // Cridar al mètode que interactua amb la base de dades
        modificarSector(sectorModificat);
    }

    public static void modificarSector(Sector sector) {
        String sql = "UPDATE Sectors SET nom = ?, escola_id = ?, num_vies = ? WHERE id = ?";
        try {
            ModelController.ejecutarActualizacion(sql, sector.getNom(), sector.getEscolaId(), sector.getNumVies(),
                    sector.getId());
            System.out.println("Sector modificat correctament.");
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
    }

    // Llistar un Sector per ID
    public static void llistarSectorController() {
        // Sol·licitar l'ID del Sector a llistar
        int id = SectorView.mostrarEliminarSector();

        // Obtenir el Sector des de la base de dades
        Sector sector = obtenerSectorPorId(id);

        // Mostrar el Sector a la vista
        SectorView.mostrarSector(sector);
    }

    // Llistar tots els Sectors
    public static void llistarTotsSectorsController() {
        String sql = "SELECT * FROM Sectors";
        List<Sector> sectors = new ArrayList<>();
        try {
            ResultSet rs = ModelController.ejecutarConsulta(sql);
            while (rs.next()) {
                sectors.add(mapearSector(rs));
            }
            // Mostrar tots els Sectors a la vista
            SectorView.mostrarListarTodosLosSectores(sectors);
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
    }

    // Eliminar un Sector per ID
    public static void eliminarSectorController() {
        // Sol·licitar l'ID del Sector a eliminar
        int id = SectorView.mostrarEliminarSector();

        // Cridar al mètode que interactua amb la base de dades
        eliminarSector(id);
    }

    public static void eliminarSector(int id) {
        String sql = "DELETE FROM Sectors WHERE id = ?";
        try {
            ModelController.ejecutarActualizacion(sql, id);
            System.out.println("Sector eliminat correctament.");
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
    }

    // Obtenir un Sector per ID
    private static Sector obtenerSectorPorId(int id) {
        String sql = "SELECT * FROM Sectors WHERE id = ?";
        try {
            ResultSet rs = ModelController.ejecutarConsulta(sql, id);
            if (rs.next()) {
                return mapearSector(rs);
            }
        } catch (SQLException e) {
            ModelController.manejarExcepcion(e);
        }
        return null;
    }

    // Mapar un ResultSet a un objecte Sector
    private static Sector mapearSector(ResultSet rs) throws SQLException {
        Sector sector = new Sector();
        sector.setId(rs.getInt("id"));
        sector.setNom(rs.getString("nom"));
        sector.setEscolaId(rs.getInt("escola_id"));
        sector.setNumVies(rs.getInt("num_vies"));
        return sector;
    }
}
