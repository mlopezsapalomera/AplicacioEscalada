package view;

import objets.Escola;

import java.util.List;
import java.util.Scanner;

public class EscolaView {
    private static final Scanner scanner = new Scanner(System.in);

    // Mostrar un missatge per crear una Escola
    public static Escola mostrarCrearEscola() {
        Escola escola = new Escola();
        System.out.println("=== Crear Escola ===");
        System.out.print("Nom: "); // Sol·licitar el nom de l'escola
        escola.setNom(scanner.nextLine());
        System.out.print("Lloc (Població): "); // Sol·licitar el lloc de l'escola
        escola.setLloc(scanner.nextLine());
        System.out.print("Aproximació: "); // Sol·licitar l'aproximació
        escola.setAproximacio(scanner.nextLine());
        System.out.print("Número de Vies: "); // Sol·licitar el número de vies
        escola.setNumVies(scanner.nextInt());
        scanner.nextLine(); // Consumir el salt de línia
        System.out.print("Popularitat (baixa, mitjana, alta): "); // Sol·licitar la popularitat
        escola.setPopularitat(scanner.nextLine());
        System.out.print("Restriccions: "); // Sol·licitar les restriccions
        escola.setRestriccions(scanner.nextLine());
        return escola;
    }

    // Mostrar un missatge per modificar una Escola
    public static Escola mostrarModificarEscola(Escola escola) {
        System.out.println("=== Modificar Escola ===");
        System.out.println("Deixa en blanc per mantenir el valor actual.");

        // Consumir el salt de línia pendent
        scanner.nextLine();

        // Modificar el nom
        System.out.print("Nom (" + escola.getNom() + "): ");
        String nom = scanner.nextLine();
        if (!nom.isEmpty()) {
            escola.setNom(nom);
        }

        // Modificar el lloc
        System.out.print("Lloc (" + escola.getLloc() + "): ");
        String lloc = scanner.nextLine();
        if (!lloc.isEmpty()) {
            escola.setLloc(lloc);
        }

        // Modificar l'aproximació
        System.out.print("Aproximació (" + escola.getAproximacio() + "): ");
        String aproximacio = scanner.nextLine();
        if (!aproximacio.isEmpty()) {
            escola.setAproximacio(aproximacio);
        }

        // Modificar el número de vies
        System.out.print("Número de Vies (" + escola.getNumVies() + "): ");
        String numVies = scanner.nextLine();
        if (!numVies.isEmpty()) {
            escola.setNumVies(Integer.parseInt(numVies));
        }

        // Modificar la popularitat
        System.out.print("Popularitat (" + escola.getPopularitat() + "): ");
        String popularitat = scanner.nextLine();
        if (!popularitat.isEmpty()) {
            escola.setPopularitat(popularitat);
        }

        // Modificar les restriccions
        System.out.print("Restriccions (" + escola.getRestriccions() + "): ");
        String restriccions = scanner.nextLine();
        if (!restriccions.isEmpty()) {
            escola.setRestriccions(restriccions);
        }

        return escola;
    }

    // Mostrar un missatge per eliminar una Escola
    public static int mostrarEliminarEscola() {
        System.out.println("=== Introduir ID de l'Escola ===");
        System.out.print("Introdueix l'ID de l'Escola: "); // Sol·licitar l'ID de l'escola
        return scanner.nextInt();
    }

    // Mostrar una Escola
    public static void mostrarEscola(Escola escola) {
        if (escola != null) {
            System.out.println("=== Informació de l'Escola ===");
            System.out.println("ID: " + escola.getId());
            System.out.println("Nom: " + escola.getNom());
            System.out.println("Lloc: " + escola.getLloc());
            System.out.println("Aproximació: " + escola.getAproximacio());
            System.out.println("Número de Vies: " + escola.getNumVies());
            System.out.println("Popularitat: " + escola.getPopularitat());
            System.out.println("Restriccions: " + escola.getRestriccions());
        } else {
            System.out.println("Escola no trobada.");
        }
    }

    // Mostrar totes les Escoles
    public static void mostrarListarTodosLosEscoles(List<Escola> escoles) {
        System.out.println("=== Llista d'Escoles ===");
        if (escoles.isEmpty()) {
            System.out.println("No hi ha Escoles registrades.");
        } else {
            for (Escola escola : escoles) {
                mostrarEscola(escola);
                System.out.println("--------------------");
            }
        }
    }
}