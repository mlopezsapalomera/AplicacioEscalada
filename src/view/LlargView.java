package view;
import objets.Llarg;

import java.util.List;
import java.util.Scanner;

public class LlargView {
    private static final Scanner scanner = new Scanner(System.in);

    // Mostrar un mensaje para crear un Llarg
    public static Llarg mostrarCrearLlarg() {
        Llarg llarg = new Llarg();
        System.out.println("=== Crear Llarg ===");
        System.out.print("Número del Llarg: ");
        llarg.setNumeroLlarg(scanner.nextInt());
        System.out.print("Llargada (en metres): ");
        llarg.setLlargada(scanner.nextInt());
        scanner.nextLine(); // Consumir salto de línea

        System.out.print("Dificultat: ");
        llarg.setDificultat(scanner.nextLine());

        // Validar orientación
        String orientacio;
        do {
            System.out.print("Orientació (N, S, E, W, NE, NW, SE, SW): ");
            orientacio = scanner.nextLine().toUpperCase();
            if (!orientacio.matches("N|S|E|W|NE|NW|SE|SW")) {
                System.out.println("Error: Orientació no vàlida. Torna a provar.");
            }
        } while (!orientacio.matches("N|S|E|W|NE|NW|SE|SW"));
        llarg.setOrientacio(orientacio);

        System.out.print("Estat (Apte, Construcció, Tancada): ");
        llarg.setEstat(scanner.nextLine());

        System.out.print("Data No Apta Until (YYYY-MM-DD o deixar en blanc): ");
        String dataNoApta = scanner.nextLine();
        llarg.setDataNoAptaUntil(dataNoApta.isEmpty() ? null : dataNoApta);

        System.out.print("Ancoratge: ");
        llarg.setAncoratge(scanner.nextLine());

        return llarg;
    }

    // Mostrar un mensaje para listar todos los Llargs
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

    public static int mostrarEliminarLlarg() {
        System.out.println("=== Eliminar Llarg ===");
        System.out.print("Introdueix l'ID del Llarg: ");
        return scanner.nextInt();
    }

    public static Llarg mostrarModificarLlarg(Llarg llarg) {
        System.out.println("=== Modificar Llarg ===");
        System.out.println("Deja en blanco para mantener el valor actual.");

        // Modificar el número del llarg
        System.out.println("Número actual del Llarg: " + llarg.getNumeroLlarg());
        System.out.print("Nuevo Número del Llarg: ");
        String numeroLlarg = scanner.nextLine();
        if (!numeroLlarg.isEmpty()) {
            llarg.setNumeroLlarg(Integer.parseInt(numeroLlarg));
        }

        // Modificar la llargada
        System.out.println("Llargada actual: " + llarg.getLlargada() + " metres");
        System.out.print("Nueva Llargada (en metres): ");
        String llargada = scanner.nextLine();
        if (!llargada.isEmpty()) {
            llarg.setLlargada(Integer.parseInt(llargada));
        }

        // Modificar la dificultat
        System.out.println("Dificultat actual: " + llarg.getDificultat());
        System.out.print("Nueva Dificultat: ");
        String dificultat = scanner.nextLine();
        if (!dificultat.isEmpty()) {
            llarg.setDificultat(dificultat);
        }

        // Modificar la orientació
        System.out.println("Orientació actual: " + llarg.getOrientacio());
        System.out.print("Nueva Orientació (N, S, E, W, NE, NW, SE, SW): ");
        String orientacio = scanner.nextLine();
        if (!orientacio.isEmpty()) {
            llarg.setOrientacio(orientacio);
        }

        // Modificar el estat
        System.out.println("Estat actual: " + llarg.getEstat());
        System.out.print("Nuevo Estat (Apte, Construcció, Tancada): ");
        String estat = scanner.nextLine();
        if (!estat.isEmpty()) {
            llarg.setEstat(estat);
        }

        // Modificar el ancoratge
        System.out.println("Ancoratge actual: " + llarg.getAncoratge());
        System.out.print("Nuevo Ancoratge: ");
        String ancoratge = scanner.nextLine();
        if (!ancoratge.isEmpty()) {
            llarg.setAncoratge(ancoratge);
        }

        return llarg;
    }
}
