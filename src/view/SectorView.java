package view;

import objets.Sector;

import java.util.List;
import java.util.Scanner;

public class SectorView {
    private static final Scanner scanner = new Scanner(System.in);

    // Mostrar un mensaje para crear un Sector
    public static Sector mostrarCrearSector() {
        Sector sector = new Sector();
        System.out.println("=== Crear Sector ===");
        System.out.print("Nom: ");
        sector.setNom(scanner.nextLine());
        System.out.print("ID de l'Escola: ");
        sector.setEscolaId(scanner.nextInt());
        scanner.nextLine(); // Consumir el salto de línea
        System.out.print("Número de Vies: ");
        sector.setNumVies(scanner.nextInt());
        scanner.nextLine(); // Consumir el salto de línea
        System.out.print("Coordenades (lat, lon): ");
        sector.setCoordenades(scanner.nextLine());
        System.out.print("Aproximació: ");
        sector.setAproximacio(scanner.nextLine());
        System.out.print("Popularitat (baixa, mitjana, alta): ");
        sector.setPopularitat(scanner.nextLine());
        System.out.print("Restriccions: ");
        sector.setRestriccions(scanner.nextLine());
        return sector;
    }

    // Mostrar un mensaje para modificar un Sector
    public static Sector mostrarModificarSector(Sector sector) {
        System.out.println("=== Modificar Sector ===");
        System.out.println("Deixa en blanc per mantenir el valor actual.");

        System.out.print("Nom (" + sector.getNom() + "): ");
        String nom = scanner.nextLine();
        if (!nom.isEmpty()) {
            sector.setNom(nom);
        }

        System.out.print("Número de Vies (" + sector.getNumVies() + "): ");
        String numVies = scanner.nextLine();
        if (!numVies.isEmpty()) {
            sector.setNumVies(Integer.parseInt(numVies));
        }

        return sector;
    }

    // Mostrar un mensaje para eliminar un Sector
    public static int mostrarEliminarSector() {
        System.out.println("=== Introduir ID del Sector ===");
        System.out.print("Introdueix l'ID del Sector: ");
        return scanner.nextInt();
    }

    // Mostrar un Sector
    public static void mostrarSector(Sector sector) {
        if (sector != null) {
            System.out.println("=== Informació del Sector ===");
            System.out.println("ID: " + sector.getId());
            System.out.println("Nom: " + sector.getNom());
            System.out.println("ID de l'Escola: " + sector.getEscolaId());
            System.out.println("Número de Vies: " + sector.getNumVies());
        } else {
            System.out.println("Sector no trobat.");
        }
    }

    // Mostrar tots els Sectors
    public static void mostrarListarTodosLosSectores(List<Sector> sectors) {
        System.out.println("=== Llista de Sectors ===");
        if (sectors.isEmpty()) {
            System.out.println("No hi ha Sectors registrats.");
        } else {
            for (Sector sector : sectors) {
                mostrarSector(sector);
                System.out.println("--------------------");
            }
        }
    }
}