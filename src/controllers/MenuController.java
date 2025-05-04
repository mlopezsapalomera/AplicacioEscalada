package controllers;
import view.*;
import java.util.Scanner;

public class MenuController {
  public static Scanner scann = new Scanner(System.in);

    // Menú principal de l'aplicació
    public static void menuPrincipal() {
      int opcio;
      
      do {
        MenuView.menuPrincipalVista(); // Mostrar el menú principal
        opcio = scann.nextInt();
        scann.nextLine();
        try {
          switch (opcio) {
            case 1:
              queVolsCrearMenuController(); // Opció per crear
              break;
            case 2:
              queVolsModificarMenuController(); // Opció per modificar
              break;
            case 3:
              queVolsLlistarUnMenuController1(); // Opció per llistar un element
              break;
            case 4:
              queVolsLlistarTotMenuController2(); // Opció per llistar tots els elements
              break;
            case 5:
              queVolsEliminarMenuController(); // Opció per eliminar
              break;
            case 0:
              System.out.println("Sortint..."); // Sortir del menú
              break;
            default:
              System.out.println("Opció no vàlida, torna a provar.");
          }
        } catch (Exception e) {
          System.out.println("Error: " + e.getMessage());
          scann.nextLine(); 
          } 
      } while (opcio != 0);
    }

    // Menú per crear elements
    public static void queVolsCrearMenuController() {
      int opcio;

      do {
          MenuView.menuCrear(); // Mostrar el menú de creació
          opcio = scann.nextInt();
          scann.nextLine();
          try {
              switch (opcio) {
                  case 1:
                      EscolaController.crearEscolaController(); // Crear una escola
                      break;
                  case 2:
                      SectorController.crearSectorController(); // Crear un sector
                      break;
                  case 3:
                      ViaController.crearViaController(); // Crear una via
                      break;
                  case 4:
                      EscaladorController.crearEscaladorController(); // Crear un escalador
                      break;
                  case 5:
                      System.out.print("Introdueix l'ID de la Via: ");
                      int viaId = scann.nextInt();
                      scann.nextLine();
                      LlargController.crearLlargController(viaId); // Crear un llarg
                      break;
                  case 0:
                      System.out.println("Tornant al menú principal...");
                      break;
                  default:
                      System.out.println("Opció no vàlida, torna a provar.");
              }
          } catch (Exception e) {
              System.out.println("Error: " + e.getMessage());
              scann.nextLine();
          }
      } while (opcio != 0);
    }

    // Menú per modificar elements
    public static void queVolsModificarMenuController(){
      int opcio;

      do {
          MenuView.menuModificar(); // Mostrar el menú de modificació
          opcio = scann.nextInt();
          scann.nextLine();
          try {
              switch (opcio) {
                  case 1:
                      EscolaController.modificarEscolaController(); // Modificar una escola
                      break;
                  case 2:
                      SectorController.modificarSectorController(); // Modificar un sector
                      break;
                  case 3:
                      ViaController.modificarViaController(); // Modificar una via
                      break;
                  case 4:
                      EscaladorController.modificarEscaladorController(); // Modificar un escalador
                      break;
                  case 5:
                      System.out.print("Introdueix l'ID del Llarg a modificar: ");
                      int llargId = scann.nextInt();
                      scann.nextLine();
                      LlargController.modificarLlargController(llargId); // Modificar un llarg
                      break;
                  case 0:
                      System.out.println("Tornant al menú principal...");
                      break;
                  default:
                      System.out.println("Opció no vàlida, torna a provar.");
              }
          } catch (Exception e) {
              System.out.println("Error: " + e.getMessage());
              scann.nextLine();
          }
      } while (opcio != 0);
    }

    // Menú per llistar un element
    public static void queVolsLlistarUnMenuController1(){
      int opcio;
      
      do {
        MenuView.menuLlistarUn(); // Mostrar el menú per llistar un element
        opcio = scann.nextInt();
        scann.nextLine();
        try {
          switch (opcio) {
            case 1:
              EscolaController.llistarEscolaController(); // Llistar una escola
              break;
            case 2:
              SectorController.llistarSectorController(); // Llistar un sector
              break;
            case 3:
              ViaController.llistarViaController(); // Llistar una via
              break;
            case 4:
              int id = EscaladorView.mostrarEliminarEscalador(); // Sol·licitar l'ID de l'escalador
              EscaladorController.llistarEscaladorController(id); // Llistar un escalador
              break;
            case 0:
              System.out.println("Tornant al menú principal...");
              System.out.println("-----------------");
              menuPrincipal();
              break;
            default:
              System.out.println("Opció no vàlida, torna a provar.");
          }
        } catch (Exception e) {
          System.out.println("Error: " + e.getMessage());
          scann.nextLine(); 
        }
      } while (opcio != 0);
    }

    // Menú per llistar tots els elements
    public static void queVolsLlistarTotMenuController2(){
      int opcio;
      
      do {
        MenuView.menuLlistarTot(); // Mostrar el menú per llistar tots els elements
        opcio = scann.nextInt();
        scann.nextLine();
        try {
          switch (opcio) {
            case 1:
              EscolaController.llistarTotesEscolesController(); // Llistar totes les escoles
              break;
            case 2:
              SectorController.llistarSectorController(); // Llistar tots els sectors
              break;
            case 3:
              ViaController.llistarViaController(); // Llistar totes les vies
              break;
            case 4:
              EscaladorController.llistarTotsEscaladorsController(); // Llistar tots els escaladors
              break;
            case 0:
              System.out.println("Tornant al menú principal...");
              System.out.println("-----------------");
              menuPrincipal();
              break;
            default:
              System.out.println("Opció no vàlida, torna a provar.");
          }
        } catch (Exception e) {
          System.out.println("Error: " + e.getMessage());
          scann.nextLine(); 
        }
      } while (opcio != 0);
    }

    // Menú per eliminar elements
    public static void queVolsEliminarMenuController(){
      int opcio;
      
      do {
        MenuView.menuEliminar(); // Mostrar el menú d'eliminació
        opcio = scann.nextInt();
        scann.nextLine();
        try {
          switch (opcio) {
            case 1:
              EscolaController.eliminarEscolaController(); // Eliminar una escola
              break;
            case 2:
              SectorController.eliminarSectorController(); // Eliminar un sector
              break;
            case 3:
              ViaController.eliminarViaController(); // Eliminar una via
              break;
            case 4:
              int escaladorId = EscaladorView.mostrarEliminarEscalador(); // Sol·licitar l'ID de l'escalador
              EscaladorController.eliminarEscaladorController(escaladorId); // Eliminar un escalador
              break;
            case 5:
              int llargId = LlargView.mostrarEliminarLlarg(); // Sol·licitar l'ID del llarg
              LlargController.eliminarLlarg(llargId); // Eliminar un llarg
              break;
            case 0:
              System.out.println("Tornant al menú principal...");
              System.out.println("-----------------");
              menuPrincipal();
              break;
            default:
              System.out.println("Opció no vàlida, torna a provar.");
          }
        } catch (Exception e) {
          System.out.println("Error: " + e.getMessage());
          scann.nextLine(); 
        }
      } while (opcio != 0);
    }
}
