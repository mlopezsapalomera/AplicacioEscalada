package view;

import objets.Escola;

import java.util.List;
import java.util.Scanner;

public class EscolaView {
    private static final Scanner scanner = new Scanner(System.in);

    // Mostrar un mensaje para crear una Escola
    public static Escola mostrarCrearEscola() {
        Escola escola = new Escola();
        System.out.println("=== Crear Escola ===");
        System.out.print("Nombre: ");
        escola.setNom(scanner.nextLine());
        System.out.print("Lloc (Población): ");
        escola.setLloc(scanner.nextLine());
        System.out.print("Aproximación: ");
        escola.setAproximacio(scanner.nextLine());
        System.out.print("Número de Vías: ");
        escola.setNumVies(scanner.nextInt());
        scanner.nextLine(); // Consumir salto de línea
        System.out.print("Popularidad (baixa, mitjana, alta): ");
        escola.setPopularitat(scanner.nextLine());
        System.out.print("Restricciones: ");
        escola.setRestriccions(scanner.nextLine());
        return escola;
    }

    // Mostrar un mensaje para modificar una Escola
    public static Escola mostrarModificarEscola(Escola escola) {
        System.out.println("=== Modificar Escola ===");
        System.out.println("Deja en blanco para mantener el valor actual.");

        // Consumir el salto de línea pendiente
        scanner.nextLine();

        // Modificar el nombre
        System.out.print("Nombre (" + escola.getNom() + "): ");
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

        // Modificar la aproximación
        System.out.print("Aproximación (" + escola.getAproximacio() + "): ");
        String aproximacio = scanner.nextLine();
        if (!aproximacio.isEmpty()) {
            escola.setAproximacio(aproximacio);
        }

        // Modificar el número de vías
        System.out.print("Número de Vías (" + escola.getNumVies() + "): ");
        String numVies = scanner.nextLine();
        if (!numVies.isEmpty()) {
            escola.setNumVies(Integer.parseInt(numVies));
        }

        // Modificar la popularidad
        System.out.print("Popularidad (" + escola.getPopularitat() + "): ");
        String popularitat = scanner.nextLine();
        if (!popularitat.isEmpty()) {
            escola.setPopularitat(popularitat);
        }

        // Modificar las restricciones
        System.out.print("Restricciones (" + escola.getRestriccions() + "): ");
        String restriccions = scanner.nextLine();
        if (!restriccions.isEmpty()) {
            escola.setRestriccions(restriccions);
        }

        return escola;
    }

    // Mostrar un mensaje para eliminar una Escola
    public static int mostrarEliminarEscola() {
        System.out.println("=== Introducir ID de Escola ===");
        System.out.print("Introduce el ID de la Escola: ");
        return scanner.nextInt();
    }

    // Mostrar una Escola
    public static void mostrarEscola(Escola escola) {
        if (escola != null) {
            System.out.println("=== Información de la Escola ===");
            System.out.println("ID: " + escola.getId());
            System.out.println("Nombre: " + escola.getNom());
            System.out.println("Lloc: " + escola.getLloc());
            System.out.println("Aproximación: " + escola.getAproximacio());
            System.out.println("Número de Vías: " + escola.getNumVies());
            System.out.println("Popularidad: " + escola.getPopularitat());
            System.out.println("Restricciones: " + escola.getRestriccions());
        } else {
            System.out.println("Escola no encontrada.");
        }
    }

    // Mostrar todas las Escoles
    public static void mostrarListarTodosLosEscoles(List<Escola> escoles) {
        System.out.println("=== Lista de Escoles ===");
        if (escoles.isEmpty()) {
            System.out.println("No hay Escoles registradas.");
        } else {
            for (Escola escola : escoles) {
                mostrarEscola(escola);
                System.out.println("--------------------");
            }
        }
    }
}