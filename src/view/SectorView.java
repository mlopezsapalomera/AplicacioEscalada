package view;

import objets.Sector;

import java.util.List;
import java.util.Scanner;

public class SectorView {
    private static final Scanner scanner = new Scanner(System.in);

    // Mostrar un missatge per crear un Sector
    public static Sector mostrarCrearSector() {
        Sector sector = new Sector();
        System.out.println("=== Crear Sector ===");
        System.out.print("Nom: "); // Sol·licitar el nom del sector
        sector.setNom(scanner.nextLine());
        System.out.print("ID de l'Escola: "); // Sol·licitar l'ID de l'escola associada
        sector.setEscolaId(scanner.nextInt());
        scanner.nextLine(); // Consumir el salt de línia
        System.out.print("Número de Vies: "); // Sol·licitar el número de vies
        sector.setNumVies(scanner.nextInt());
        scanner.nextLine(); // Consumir el salt de línia
        System.out.print("Coordenades (lat, lon): "); // Sol·licitar les coordenades
        sector.setCoordenades(scanner.nextLine());
        System.out.print("Aproximació: "); // Sol·licitar l'aproximació
        sector.setAproximacio(scanner.nextLine());
        System.out.print("Popularitat (baixa, mitjana, alta): "); // Sol·licitar la popularitat
        sector.setPopularitat(scanner.nextLine());
        System.out.print("Restriccions: "); // Sol·licitar les restriccions
        sector.setRestriccions(scanner.nextLine());
        return sector;
    }

    // Mostrar un missatge per modificar un Sector
    public static Sector mostrarModificarSector(Sector sector) {
        System.out.println("=== Modificar Sector ===");
        System.out.println("Deixa en blanc per mantenir el valor actual.");

        System.out.print("Nom (" + sector.getNom() + "): "); // Sol·licitar el nou nom
        String nom = scanner.nextLine();
        if (!nom.isEmpty()) {
            sector.setNom(nom);
        }

        System.out.print("Número de Vies (" + sector.getNumVies() + "): "); // Sol·licitar el nou número de vies
        String numVies = scanner.nextLine();
        if (!numVies.isEmpty()) {
            sector.setNumVies(Integer.parseInt(numVies));
        }

        return sector;
    }

    // Mostrar un missatge per eliminar un Sector
    public static int mostrarEliminarSector() {
        System.out.println("=== Introduir ID del Sector ===");
        System.out.print("Introdueix l'ID del Sector: "); // Sol·licitar l'ID del sector
        return scanner.nextInt();
    }

    // Mostrar un Sector
    public static void mostrarSector(Sector sector) {
        if (sector != null) {
            System.out.println("=== Informació del Sector ===");
            System.out.println("ID: " + sector.getId()); // Mostrar l'ID del sector
            System.out.println("Nom: " + sector.getNom()); // Mostrar el nom del sector
            System.out.println("ID de l'Escola: " + sector.getEscolaId()); // Mostrar l'ID de l'escola associada
            System.out.println("Número de Vies: " + sector.getNumVies()); // Mostrar el número de vies
        } else {
            System.out.println("Sector no trobat."); // Missatge si el sector no es troba
        }
    }

    // Mostrar tots els Sectors
    public static void mostrarListarTodosLosSectores(List<Sector> sectors) {
        System.out.println("=== Llista de Sectors ===");
        if (sectors.isEmpty()) {
            System.out.println("No hi ha Sectors registrats."); // Missatge si no hi ha sectors
        } else {
            for (Sector sector : sectors) {
                mostrarSector(sector); // Mostrar cada sector
                System.out.println("--------------------");
            }
        }
    }
}