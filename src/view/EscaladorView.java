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

    // Mostrar un missatge per crear un escalador
    public static Escalador mostrarCrearEscalador() {
        Escalador escalador = new Escalador();
        System.out.println("=== Crear Escalador ===");
        System.out.print("Nom: ");
        escalador.setNom(scanner.nextLine());
        System.out.print("Àlies: ");
        escalador.setAlias(scanner.nextLine());
        System.out.print("Edat: ");
        escalador.setEdat(scanner.nextInt());
        scanner.nextLine(); // Consumir el salt de línia

        // Obtenir i mostrar els nivells disponibles
        List<String> nivells = obtenirNivellsDisponibles();
        if (!nivells.isEmpty()) {
            System.out.println("Selecciona el nivell màxim:");
            for (int i = 0; i < nivells.size(); i++) {
                System.out.println((i + 1) + ". " + nivells.get(i));
            }
            int opcio = scanner.nextInt();
            scanner.nextLine(); // Netejar el buffer
            if (opcio > 0 && opcio <= nivells.size()) {
                escalador.setNivellMax(nivells.get(opcio - 1));
            } else {
                System.out.println("Opció no vàlida. Nivell no assignat.");
            }
        } else {
            System.out.println("No hi ha nivells disponibles.");
        }

        System.out.print("ID de la Via Màxima: ");
        escalador.setViaMaxId(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Estil Preferit (esportiva, clàssica, gel): ");
        escalador.setEstilPreferit(scanner.nextLine());
        System.out.print("Historial: ");
        escalador.setHistorial(scanner.nextLine());
        System.out.print("Fita: ");
        escalador.setFita(scanner.nextLine());
        return escalador;
    }

    // Obtenir els nivells disponibles des de la base de dades
    private static List<String> obtenirNivellsDisponibles() {
        List<String> nivells = new ArrayList<>();
        String sql = "SHOW COLUMNS FROM Escaladors LIKE 'nivell_max'";
        try {
            ResultSet rs = ModelController.ejecutarConsulta(sql);
            if (rs.next()) {
                String enumValues = rs.getString("Type"); // Obté el tipus ENUM
                // Extreure els valors de l'ENUM (format: enum('4','4+','5',...))
                enumValues = enumValues.substring(enumValues.indexOf("(") + 1, enumValues.indexOf(")"));
                String[] valors = enumValues.replace("'", "").split(",");
                for (String valor : valors) {
                    nivells.add(valor);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtenir els nivells: " + e.getMessage());
        }
        return nivells;
    }

    // Mostrar un missatge per modificar un escalador
    public static Escalador mostrarModificarEscalador(Escalador escalador) {
        System.out.println("=== Modificar Escalador ===");
        System.out.println("Deixa en blanc per mantenir el valor actual.");

        // Consumir el salt de línia pendent
        scanner.nextLine();

        // Modificar el nom
        System.out.print("Nom (" + escalador.getNom() + "): ");
        String nom = scanner.nextLine();
        if (!nom.isEmpty()) {
            escalador.setNom(nom);
        }

        // Modificar l'àlies
        System.out.print("Àlies (" + escalador.getAlias() + "): ");
        String alias = scanner.nextLine();
        if (!alias.isEmpty()) {
            escalador.setAlias(alias);
        }

        // Modificar l'edat
        System.out.print("Edat (" + escalador.getEdat() + "): ");
        String edat = scanner.nextLine();
        if (!edat.isEmpty()) {
            escalador.setEdat(Integer.parseInt(edat));
        }

        // Modificar el nivell màxim
        List<String> nivells = obtenirNivellsDisponibles();
        if (!nivells.isEmpty()) {
            System.out.println("Selecciona el nivell màxim:");
            for (int i = 0; i < nivells.size(); i++) {
                System.out.println((i + 1) + ". " + nivells.get(i));
            }
            System.out.print("Nivell Màxim (" + escalador.getNivellMax() + "): ");
            String opcio = scanner.nextLine();
            if (!opcio.isEmpty()) {
                try {
                    int index = Integer.parseInt(opcio);
                    if (index > 0 && index <= nivells.size()) {
                        escalador.setNivellMax(nivells.get(index - 1));
                    } else {
                        System.out.println("Opció no vàlida. Nivell no modificat.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no vàlida. Nivell no modificat.");
                }
            }
        } else {
            System.out.println("No hi ha nivells disponibles.");
        }

        // Modificar l'ID de la via màxima
        System.out.print("ID de la Via Màxima (" + escalador.getViaMaxId() + "): ");
        String viaMaxId = scanner.nextLine();
        if (!viaMaxId.isEmpty()) {
            escalador.setViaMaxId(Integer.parseInt(viaMaxId));
        }

        // Modificar l'estil preferit
        System.out.print("Estil Preferit (" + escalador.getEstilPreferit() + "): ");
        String estilPreferit = scanner.nextLine();
        if (!estilPreferit.isEmpty()) {
            escalador.setEstilPreferit(estilPreferit);
        }

        // Modificar l'historial
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

    // Mostrar un missatge per eliminar un escalador
    public static int mostrarEliminarEscalador() {
        System.out.println("=== Introduir ID de l'Escalador ===");
        System.out.print("Introdueix l'ID de l'escalador: ");
        return scanner.nextInt();
    }

    // Mostrar un escalador
    public static void mostrarEscalador(Escalador escalador) {
        if (escalador != null) {
            System.out.println("=== Informació de l'Escalador ===");
            System.out.println("ID: " + escalador.getId());
            System.out.println("Nom: " + escalador.getNom());
            System.out.println("Àlies: " + escalador.getAlias());
            System.out.println("Edat: " + escalador.getEdat());
            System.out.println("Nivell Màxim: " + escalador.getNivellMax());
            System.out.println("ID de la Via Màxima: " + escalador.getViaMaxId());
            System.out.println("Estil Preferit: " + escalador.getEstilPreferit());
            System.out.println("Historial: " + escalador.getHistorial());
            System.out.println("Fita: " + escalador.getFita());
        } else {
            System.out.println("Escalador no trobat.");
        }
    }

    // Mostrar tots els escaladors
    public static void mostrarListarTodosLosEscaladores(List<Escalador> escaladors) {
        System.out.println("=== Llista d'Escaladors ===");
        if (escaladors.isEmpty()) {
            System.out.println("No hi ha escaladors registrats.");
        } else {
            for (Escalador escalador : escaladors) {
                mostrarEscalador(escalador);
                System.out.println("--------------------");
            }
        }
    }
}
