package view;

import objets.Escalador;

import java.util.List;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.ModelController;

public class EscaladorView {
    private static final Scanner scanner = new Scanner(System.in);

    // Mostrar un mensaje para crear un escalador
    public static Escalador mostrarCrearEscalador() {
        Escalador escalador = new Escalador();
        System.out.println("=== Crear Escalador ===");
        System.out.print("Nombre: ");
        escalador.setNom(scanner.nextLine());
        System.out.print("Alias: ");
        escalador.setAlias(scanner.nextLine());
        System.out.print("Edad: ");
        escalador.setEdat(scanner.nextInt());
        scanner.nextLine(); // Consumir salto de línea

        // Obtener y mostrar los niveles disponibles
        List<String> niveles = obtenerNivelesDisponibles();
        if (!niveles.isEmpty()) {
            System.out.println("Selecciona el nivel máximo:");
            for (int i = 0; i < niveles.size(); i++) {
                System.out.println((i + 1) + ". " + niveles.get(i));
            }
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            if (opcion > 0 && opcion <= niveles.size()) {
                escalador.setNivellMax(niveles.get(opcion - 1));
            } else {
                System.out.println("Opción no válida. Nivel no asignado.");
            }
        } else {
            System.out.println("No hay niveles disponibles.");
        }

        System.out.print("ID de Vía Máxima: ");
        escalador.setViaMaxId(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Estilo Preferido (esportiva, clàssica, gel): ");
        escalador.setEstilPreferit(scanner.nextLine());
        System.out.print("Historial: ");
        escalador.setHistorial(scanner.nextLine());
        System.out.print("Fita: ");
        escalador.setFita(scanner.nextLine());
        return escalador;
    }

    private static List<String> obtenerNivelesDisponibles() {
        List<String> niveles = new ArrayList<>();
        String sql = "SHOW COLUMNS FROM Escaladors LIKE 'nivell_max'";
        try {
            ResultSet rs = ModelController.ejecutarConsulta(sql);
            if (rs.next()) {
                String enumValues = rs.getString("Type"); // Obtiene el tipo ENUM
                // Extraer los valores del ENUM (formato: enum('4','4+','5',...))
                enumValues = enumValues.substring(enumValues.indexOf("(") + 1, enumValues.indexOf(")"));
                String[] valores = enumValues.replace("'", "").split(",");
                for (String valor : valores) {
                    niveles.add(valor);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los niveles: " + e.getMessage());
        }
        return niveles;
    }

    // Mostrar un mensaje para modificar un escalador
    public static Escalador mostrarModificarEscalador(Escalador escalador) {
        System.out.println("=== Modificar Escalador ===");
        System.out.println("Deja en blanco para mantener el valor actual.");

        // Consumir el salto de línea pendiente
        scanner.nextLine();

        // Modificar el nombre
        System.out.print("Nombre (" + escalador.getNom() + "): ");
        String nom = scanner.nextLine();
        if (!nom.isEmpty()) {
            escalador.setNom(nom);
        }

        // Modificar el alias
        System.out.print("Alias (" + escalador.getAlias() + "): ");
        String alias = scanner.nextLine();
        if (!alias.isEmpty()) {
            escalador.setAlias(alias);
        }

        // Modificar la edad
        System.out.print("Edad (" + escalador.getEdat() + "): ");
        String edat = scanner.nextLine();
        if (!edat.isEmpty()) {
            escalador.setEdat(Integer.parseInt(edat));
        }

        // Modificar el nivel máximo
        List<String> niveles = obtenerNivelesDisponibles();
        if (!niveles.isEmpty()) {
            System.out.println("Selecciona el nivel máximo:");
            for (int i = 0; i < niveles.size(); i++) {
                System.out.println((i + 1) + ". " + niveles.get(i));
            }
            System.out.print("Nivel Máximo (" + escalador.getNivellMax() + "): ");
            String opcion = scanner.nextLine();
            if (!opcion.isEmpty()) {
                try {
                    int index = Integer.parseInt(opcion);
                    if (index > 0 && index <= niveles.size()) {
                        escalador.setNivellMax(niveles.get(index - 1));
                    } else {
                        System.out.println("Opción no válida. Nivel no modificado.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida. Nivel no modificado.");
                }
            }
        } else {
            System.out.println("No hay niveles disponibles.");
        }

        // Modificar el ID de la vía máxima
        System.out.print("ID de Vía Máxima (" + escalador.getViaMaxId() + "): ");
        String viaMaxId = scanner.nextLine();
        if (!viaMaxId.isEmpty()) {
            escalador.setViaMaxId(Integer.parseInt(viaMaxId));
        }

        // Modificar el estilo preferido
        System.out.print("Estilo Preferido (" + escalador.getEstilPreferit() + "): ");
        String estilPreferit = scanner.nextLine();
        if (!estilPreferit.isEmpty()) {
            escalador.setEstilPreferit(estilPreferit);
        }

        // Modificar el historial
        System.out.print("Historial (" + escalador.getHistorial() + "): ");
        String historial = scanner.nextLine();
        if (!historial.isEmpty()) {
            escalador.setHistorial(historial);
        }

        // Modificar la fita
        System.out.print("Fita (" + escalador.getFita() + "): ");
        String fita = scanner.nextLine();
        if (!fita.isEmpty()) {
            escalador.setFita(fita);
        }

        return escalador;
    }

    // Mostrar un mensaje para eliminar un escalador
    public static int mostrarEliminarEscalador() {
        System.out.println("=== Introducir ID de Escalador ===");
        System.out.print("Introduce el ID del escalador: ");
        return scanner.nextInt();
    }

    // Mostrar un escalador
    public static void mostrarEscalador(Escalador escalador) {
        if (escalador != null) {
            System.out.println("=== Información del Escalador ===");
            System.out.println("ID: " + escalador.getId());
            System.out.println("Nombre: " + escalador.getNom());
            System.out.println("Alias: " + escalador.getAlias());
            System.out.println("Edad: " + escalador.getEdat());
            System.out.println("Nivel Máximo: " + escalador.getNivellMax());
            System.out.println("ID de Vía Máxima: " + escalador.getViaMaxId());
            System.out.println("Estilo Preferido: " + escalador.getEstilPreferit());
            System.out.println("Historial: " + escalador.getHistorial());
            System.out.println("Fita: " + escalador.getFita());
        } else {
            System.out.println("Escalador no encontrado.");
        }
    }

    // Mostrar todos los escaladores
    public static void mostrarListarTodosLosEscaladores(List<Escalador> escaladors) {
        System.out.println("=== Lista de Escaladores ===");
        if (escaladors.isEmpty()) {
            System.out.println("No hay escaladores registrados.");
        } else {
            for (Escalador escalador : escaladors) {
                mostrarEscalador(escalador);
                System.out.println("--------------------");
            }
        }
    }
}
