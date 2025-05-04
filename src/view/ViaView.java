package view;

import objets.Via;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class ViaView {
    private static final Scanner scanner = new Scanner(System.in);

    // Mostrar un missatge per crear una Via
    public static Via mostrarCrearVia() {
        Via via = new Via();
        System.out.println("=== Crear Via ===");
        System.out.print("Nom: "); // Sol·licitar el nom de la via
        via.setNom(scanner.nextLine());

        // Mostrar i seleccionar el tipus de via
        System.out.println("Selecciona el tipus de via:");
        System.out.println("1. Esportiva"); // Opció per via esportiva
        System.out.println("2. Clàssica"); // Opció per via clàssica
        System.out.println("3. Gel"); // Opció per via de gel
        System.out.print("Tipus: ");
        int tipusOpcio = scanner.nextInt();
        scanner.nextLine(); // Consumir el salt de línia

        List<String> dificultats = new ArrayList<>();
        switch (tipusOpcio) {
            case 1: // Esportiva
            case 2: // Clàssica
                dificultats = List.of("4", "4+", "5", "5+", "6a", "6a+", "6b", "6b+", "6c", "6c+", "7a", "7a+", "7b", "7b+", "7c", "7c+", "8a", "8a+", "8b", "8b+", "8c", "8c+", "9a", "9a+", "9b", "9b+", "9c", "9c+");
                via.setTipus(tipusOpcio == 1 ? "esportiva" : "clàssica"); // Assignar el tipus de via
                break;
            case 3: // Gel
                dificultats = List.of("WI1", "WI2", "WI3", "WI4", "WI5", "WI6", "WI7");
                via.setTipus("gel"); // Assignar el tipus de via
                break;
            default:
                System.out.println("Tipus no vàlid. Torna a intentar."); // Missatge d'error
                return mostrarCrearVia(); // Reiniciar el procés
        }

        // Seleccionar la dificultat
        System.out.println("Selecciona la dificultat:");
        for (int i = 0; i < dificultats.size(); i++) {
            System.out.println((i + 1) + ". " + dificultats.get(i));
        }
        System.out.print("Dificultat: ");
        int dificultatOpcio = scanner.nextInt();
        scanner.nextLine(); // Consumir el salt de línia
        if (dificultatOpcio < 1 || dificultatOpcio > dificultats.size()) {
            System.out.println("Dificultat no vàlida. Torna a intentar."); // Missatge d'error
            return mostrarCrearVia(); // Reiniciar el procés
        }
        via.setDificultat(dificultats.get(dificultatOpcio - 1)); // Assignar la dificultat

        // Sol·licitar altres dades
        System.out.print("ID del Sector: "); // Sol·licitar l'ID del sector
        via.setSectorId(scanner.nextInt());
        scanner.nextLine(); // Consumir el salt de línia
        System.out.print("ID de l'Escola: "); // Sol·licitar l'ID de l'escola
        via.setEscolaId(scanner.nextInt());
        scanner.nextLine(); // Consumir el salt de línia
        System.out.print("Llargada (en metres): "); // Sol·licitar la llargada
        via.setLlargada(scanner.nextInt());
        scanner.nextLine(); // Consumir el salt de línia

        return via;
    }

    // Mostrar un missatge per modificar una Via
    public static Via mostrarModificarVia(Via via) {
        System.out.println("=== Modificar Via ===");
        System.out.println("Deixa en blanc per mantenir el valor actual.");

        System.out.print("Nom (" + via.getNom() + "): "); // Sol·licitar el nou nom
        String nom = scanner.nextLine();
        if (!nom.isEmpty()) {
            via.setNom(nom);
        }

        System.out.print("Dificultat (" + via.getDificultat() + "): "); // Sol·licitar la nova dificultat
        String dificultat = scanner.nextLine();
        if (!dificultat.isEmpty()) {
            via.setDificultat(dificultat);
        }

        System.out.print("Llargada (" + via.getLlargada() + "): "); // Sol·licitar la nova llargada
        String llargada = scanner.nextLine();
        if (!llargada.isEmpty()) {
            via.setLlargada(Integer.parseInt(llargada));
        }

        System.out.print("Tipus (" + via.getTipus() + "): "); // Sol·licitar el nou tipus
        String tipus = scanner.nextLine();
        if (!tipus.isEmpty()) {
            via.setTipus(tipus);
        }

        return via;
    }

    // Mostrar un missatge per eliminar una Via
    public static int mostrarEliminarVia() {
        System.out.println("=== Introduir ID de la Via ===");
        System.out.print("Introdueix l'ID de la Via: "); // Sol·licitar l'ID de la via
        return scanner.nextInt();
    }

    // Mostrar una Via
    public static void mostrarVia(Via via) {
        if (via != null) {
            System.out.println("=== Informació de la Via ===");
            System.out.println("ID: " + via.getId()); // Mostrar l'ID de la via
            System.out.println("Nom: " + via.getNom()); // Mostrar el nom de la via
            System.out.println("Dificultat: " + via.getDificultat()); // Mostrar la dificultat de la via
            System.out.println("ID del Sector: " + via.getSectorId()); // Mostrar l'ID del sector
            System.out.println("Llargada: " + via.getLlargada() + " metres"); // Mostrar la llargada de la via
            System.out.println("Tipus: " + via.getTipus()); // Mostrar el tipus de la via
        } else {
            System.out.println("Via no trobada."); // Missatge si la via no es troba
        }
    }

    // Mostrar totes les Vies
    public static void mostrarListarTodosLosViaes(List<Via> vies) {
        System.out.println("=== Llista de Vies ===");
        if (vies.isEmpty()) {
            System.out.println("No hi ha Vies registrades."); // Missatge si no hi ha vies
        } else {
            for (Via via : vies) {
                mostrarVia(via); // Mostrar cada via
                System.out.println("--------------------");
            }
        }
    }
}