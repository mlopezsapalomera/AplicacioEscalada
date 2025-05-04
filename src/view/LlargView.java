package view;
import objets.Llarg;

import java.util.List;
import java.util.Scanner;

public class LlargView {
    private static final Scanner scanner = new Scanner(System.in);

    // Mostrar un missatge per crear un Llarg
    public static Llarg mostrarCrearLlarg() {
        Llarg llarg = new Llarg();
        System.out.println("=== Crear Llarg ===");
        System.out.print("Número del Llarg: "); // Sol·licitar el número del llarg
        llarg.setNumeroLlarg(scanner.nextInt());
        System.out.print("Llargada (en metres): "); // Sol·licitar la llargada
        llarg.setLlargada(scanner.nextInt());
        scanner.nextLine(); // Consumir el salt de línia

        System.out.print("Dificultat: "); // Sol·licitar la dificultat
        llarg.setDificultat(scanner.nextLine());

        // Validar l'orientació
        String orientacio;
        do {
            System.out.print("Orientació (N, S, E, W, NE, NW, SE, SW): "); // Sol·licitar l'orientació
            orientacio = scanner.nextLine().toUpperCase();
            if (!orientacio.matches("N|S|E|W|NE|NW|SE|SW")) {
                System.out.println("Error: Orientació no vàlida. Torna a provar.");
            }
        } while (!orientacio.matches("N|S|E|W|NE|NW|SE|SW"));
        llarg.setOrientacio(orientacio);

        System.out.print("Estat (Apte, Construcció, Tancada): "); // Sol·licitar l'estat
        llarg.setEstat(scanner.nextLine());

        System.out.print("Data No Apta Until (YYYY-MM-DD o deixar en blanc): "); // Sol·licitar la data no apta
        String dataNoApta = scanner.nextLine();
        llarg.setDataNoAptaUntil(dataNoApta.isEmpty() ? null : dataNoApta);

        System.out.print("Ancoratge: "); // Sol·licitar l'ancoratge
        llarg.setAncoratge(scanner.nextLine());

        return llarg;
    }

    // Mostrar un missatge per llistar tots els Llargs
    public static void mostrarListarTodosLosLlargs(List<Llarg> llargs) {
        System.out.println("=== Llista de Llargs ===");
        if (llargs.isEmpty()) {
            System.out.println("No hi ha Llargs registrats.");
        } else {
            for (Llarg llarg : llargs) {
                mostrarLlarg(llarg);
                System.out.println("--------------------");
            }
        }
    }

    // Mostrar un Llarg
    public static void mostrarLlarg(Llarg llarg) {
        System.out.println("ID: " + llarg.getId());
        System.out.println("Número del Llarg: " + llarg.getNumeroLlarg());
        System.out.println("Llargada: " + llarg.getLlargada() + " metres");
        System.out.println("Dificultat: " + llarg.getDificultat());
        System.out.println("Orientació: " + llarg.getOrientacio());
        System.out.println("Estat: " + llarg.getEstat());
        System.out.println("Data No Apta Until: " + llarg.getDataNoAptaUntil());
        System.out.println("Ancoratge: " + llarg.getAncoratge());
    }

    // Mostrar un missatge per eliminar un Llarg
    public static int mostrarEliminarLlarg() {
        System.out.println("=== Eliminar Llarg ===");
        System.out.print("Introdueix l'ID del Llarg: "); // Sol·licitar l'ID del llarg
        return scanner.nextInt();
    }

    // Mostrar un missatge per modificar un Llarg
    public static Llarg mostrarModificarLlarg(Llarg llarg) {
        System.out.println("=== Modificar Llarg ===");
        System.out.println("Deixa en blanc per mantenir el valor actual.");

        // Modificar el número del llarg
        System.out.println("Número actual del Llarg: " + llarg.getNumeroLlarg());
        System.out.print("Nou Número del Llarg: "); // Sol·licitar el nou número del llarg
        String numeroLlarg = scanner.nextLine();
        if (!numeroLlarg.isEmpty()) {
            llarg.setNumeroLlarg(Integer.parseInt(numeroLlarg));
        }

        // Modificar la llargada
        System.out.println("Llargada actual: " + llarg.getLlargada() + " metres");
        System.out.print("Nova Llargada (en metres): "); // Sol·licitar la nova llargada
        String llargada = scanner.nextLine();
        if (!llargada.isEmpty()) {
            llarg.setLlargada(Integer.parseInt(llargada));
        }

        // Modificar la dificultat
        System.out.println("Dificultat actual: " + llarg.getDificultat());
        System.out.print("Nova Dificultat: "); // Sol·licitar la nova dificultat
        String dificultat = scanner.nextLine();
        if (!dificultat.isEmpty()) {
            llarg.setDificultat(dificultat);
        }

        // Modificar l'orientació
        System.out.println("Orientació actual: " + llarg.getOrientacio());
        System.out.print("Nova Orientació (N, S, E, W, NE, NW, SE, SW): "); // Sol·licitar la nova orientació
        String orientacio = scanner.nextLine();
        if (!orientacio.isEmpty()) {
            llarg.setOrientacio(orientacio);
        }

        // Modificar l'estat
        System.out.println("Estat actual: " + llarg.getEstat());
        System.out.print("Nou Estat (Apte, Construcció, Tancada): "); // Sol·licitar el nou estat
        String estat = scanner.nextLine();
        if (!estat.isEmpty()) {
            llarg.setEstat(estat);
        }

        // Modificar l'ancoratge
        System.out.println("Ancoratge actual: " + llarg.getAncoratge());
        System.out.print("Nou Ancoratge: "); // Sol·licitar el nou ancoratge
        String ancoratge = scanner.nextLine();
        if (!ancoratge.isEmpty()) {
            llarg.setAncoratge(ancoratge);
        }

        return llarg;
    }
}
