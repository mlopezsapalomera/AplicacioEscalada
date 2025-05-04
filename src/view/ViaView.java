package view;

import objets.Via;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class ViaView {
    private static final Scanner scanner = new Scanner(System.in);

    // Mostrar un mensaje para crear una Via
    public static Via mostrarCrearVia() {
        Via via = new Via();
        System.out.println("=== Crear Via ===");
        System.out.print("Nom: ");
        via.setNom(scanner.nextLine());

        // Mostrar y seleccionar dificultad
        System.out.println("Selecciona el tipus de via:");
        System.out.println("1. Esportiva");
        System.out.println("2. Clàssica");
        System.out.println("3. Gel");
        System.out.print("Tipus: ");
        int tipusOpcio = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        List<String> dificultats = new ArrayList<>();
        switch (tipusOpcio) {
            case 1: // Esportiva
            case 2: // Clàssica
                dificultats = List.of("4", "4+", "5", "5+", "6a", "6a+", "6b", "6b+", "6c", "6c+", "7a", "7a+", "7b", "7b+", "7c", "7c+", "8a", "8a+", "8b", "8b+", "8c", "8c+", "9a", "9a+", "9b", "9b+", "9c", "9c+");
                via.setTipus(tipusOpcio == 1 ? "esportiva" : "clàssica");
                break;
            case 3: // Gel
                dificultats = List.of("WI1", "WI2", "WI3", "WI4", "WI5", "WI6", "WI7");
                via.setTipus("gel");
                break;
            default:
                System.out.println("Tipus no vàlid. Torna a intentar.");
                return mostrarCrearVia(); // Reiniciar el proceso
        }

        System.out.println("Selecciona la dificultat:");
        for (int i = 0; i < dificultats.size(); i++) {
            System.out.println((i + 1) + ". " + dificultats.get(i));
        }
        System.out.print("Dificultat: ");
        int dificultatOpcio = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        if (dificultatOpcio < 1 || dificultatOpcio > dificultats.size()) {
            System.out.println("Dificultat no vàlida. Torna a intentar.");
            return mostrarCrearVia(); // Reiniciar el proceso
        }
        via.setDificultat(dificultats.get(dificultatOpcio - 1));

        // Solicitar otros datos
        System.out.print("ID del Sector: ");
        via.setSectorId(scanner.nextInt());
        scanner.nextLine(); // Consumir el salto de línea
        System.out.print("ID de l'Escola: ");
        via.setEscolaId(scanner.nextInt());
        scanner.nextLine(); // Consumir el salto de línea
        System.out.print("Llargada (en metres): ");
        via.setLlargada(scanner.nextInt());
        scanner.nextLine(); // Consumir el salto de línea

        return via;
    }

    // Mostrar un mensaje para modificar una Via
    public static Via mostrarModificarVia(Via via) {
        System.out.println("=== Modificar Via ===");
        System.out.println("Deixa en blanc per mantenir el valor actual.");

        System.out.print("Nom (" + via.getNom() + "): ");
        String nom = scanner.nextLine();
        if (!nom.isEmpty()) {
            via.setNom(nom);
        }

        System.out.print("Dificultat (" + via.getDificultat() + "): ");
        String dificultat = scanner.nextLine();
        if (!dificultat.isEmpty()) {
            via.setDificultat(dificultat);
        }

        System.out.print("Llargada (" + via.getLlargada() + "): ");
        String llargada = scanner.nextLine();
        if (!llargada.isEmpty()) {
            via.setLlargada(Integer.parseInt(llargada));
        }

        System.out.print("Tipus (" + via.getTipus() + "): ");
        String tipus = scanner.nextLine();
        if (!tipus.isEmpty()) {
            via.setTipus(tipus);
        }

        return via;
    }

    // Mostrar un mensaje para eliminar una Via
    public static int mostrarEliminarVia() {
        System.out.println("=== Introduir ID de la Via ===");
        System.out.print("Introdueix l'ID de la Via: ");
        return scanner.nextInt();
    }

    // Mostrar una Via
    public static void mostrarVia(Via via) {
        if (via != null) {
            System.out.println("=== Informació de la Via ===");
            System.out.println("ID: " + via.getId());
            System.out.println("Nom: " + via.getNom());
            System.out.println("Dificultat: " + via.getDificultat());
            System.out.println("ID del Sector: " + via.getSectorId());
            System.out.println("Llargada: " + via.getLlargada() + " metres");
            System.out.println("Tipus: " + via.getTipus());
        } else {
            System.out.println("Via no trobada.");
        }
    }

    // Mostrar totes les Vies
    public static void mostrarListarTodosLosViaes(List<Via> vies) {
        System.out.println("=== Llista de Vies ===");
        if (vies.isEmpty()) {
            System.out.println("No hi ha Vies registrades.");
        } else {
            for (Via via : vies) {
                mostrarVia(via);
                System.out.println("--------------------");
            }
        }
    }
}