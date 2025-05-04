package controllers;
import view.*;
import java.util.Scanner;


  
public class MenuController {
  public static Scanner scann = new Scanner(System.in);


    public static void menuPrincipal() {
      int opcio;
      
      do {
        MenuView.menuPrincipalVista();
        opcio = scann.nextInt();
        scann.nextLine();
        try {
          switch (opcio) {
            case 1:
              queVolsCrearMenuController();
              break;
            case 2:
              queVolsModificarMenuController();
              break;
            case 3:
              queVolsLlistarUnMenuController1();
              break;
            case 4:
            queVolsLlistarTotMenuController2();
              break;
            case 5:
              queVolsEliminarMenuController();
              break;
            case 0:
              System.out.println("Sortint...");
              break;
            default:
              System.out.println("Opcio no valida, torna a provar.");
          }
        } catch (Exception e) {
          System.out.println("Error: " + e.getMessage());
          scann.nextLine(); 
          } 
      } while (opcio != 0);
    }

  public static void queVolsCrearMenuController() {
    int opcio;

    do {
        MenuView.menuCrear();
        opcio = scann.nextInt();
        scann.nextLine();
        try {
            switch (opcio) {
                case 1:
                    EscolaController.crearEscolaController();
                    break;
                case 2:
                    SectorController.crearSectorController();
                    break;
                case 3:
                    ViaController.crearViaController();
                    break;
                case 4:
                    EscaladorController.crearEscaladorController();
                    break;
                case 5:
                    System.out.print("Introdueix l'ID de la Via: ");
                    int viaId = scann.nextInt();
                    scann.nextLine();
                    LlargController.crearLlargController(viaId);
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

  public static void queVolsModificarMenuController(){
    int opcio;

    do {
        MenuView.menuModificar();
        opcio = scann.nextInt();
        scann.nextLine();
        try {
            switch (opcio) {
                case 1:
                    EscolaController.modificarEscolaController();
                    break;
                case 2:
                    SectorController.modificarSectorController();
                    break;
                case 3:
                    ViaController.modificarViaController();
                    break;
                case 4:
                    EscaladorController.modificarEscaladorController();
                    break;
                case 5:
                    System.out.print("Introdueix l'ID del Llarg a modificar: ");
                    int llargId = scann.nextInt();
                    scann.nextLine();
                    LlargController.modificarLlargController(llargId);
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

  public static void queVolsLlistarUnMenuController1(){
    int opcio;
    
    do {
      MenuView.menuLlistarUn();
      opcio = scann.nextInt();
      scann.nextLine();
      try {
        switch (opcio) {
          case 1:
            EscolaController.llistarEscolaController();
            break;
          case 2:
            SectorController.llistarSectorController();
            break;
          case 3:
            ViaController.llistarViaController();
            break;
          case 4:
            // Solicitar el ID del escalador a listar desde la vista
            int id = EscaladorView.mostrarEliminarEscalador(); // Reutilizamos este método para pedir el ID
            EscaladorController.llistarEscaladorController(id);
            break;
          case 0:
            System.out.println("Tornant al menu principal...");
            System.out.println("-----------------");
            menuPrincipal();
            break;
          default:
            System.out.println("Opcio no valida, torna a provar.");
        }
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        scann.nextLine(); 
      }
    } while (opcio != 0);
  }

  public static void queVolsLlistarTotMenuController2(){
    int opcio;
    
    do {
      MenuView.menuLlistarTot();
      opcio = scann.nextInt();
      scann.nextLine();
      try {
        switch (opcio) {
          case 1:
            EscolaController.llistarTotesEscolesController();
            break;
          case 2:
            SectorController.llistarSectorController();
            break;
          case 3:
            ViaController.llistarViaController();
            break;
          case 4:
            EscaladorController.llistarTotsEscaladorsController();
            break;
          case 0:
            System.out.println("Tornant al menu principal...");
            System.out.println("-----------------");
            menuPrincipal();
            break;
          default:
            System.out.println("Opcio no valida, torna a provar.");
        }
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        scann.nextLine(); 
      }
    } while (opcio != 0);

  }

  public static void queVolsEliminarMenuController(){
    int opcio;
    
    do {
      MenuView.menuEliminar();
      opcio = scann.nextInt();
      scann.nextLine();
      try {
        switch (opcio) {
          case 1:
            EscolaController.eliminarEscolaController();
            break;
          case 2:
            SectorController.eliminarSectorController();
            break;
          case 3:
            ViaController.eliminarViaController();
            break;
            case 4:
            // Solicitar el ID del escalador a eliminar desde la vista
            int escaladorId = EscaladorView.mostrarEliminarEscalador();
            EscaladorController.eliminarEscaladorController(escaladorId);
            break;
            case 5:
            // Solicitar el ID del llarg a eliminar desde la vista
            int llargId = LlargView.mostrarEliminarLlarg();
            LlargController.eliminarLlarg(llargId);
            break;
          case 0:
            System.out.println("Tornant al menu principal...");
            System.out.println("-----------------");
            menuPrincipal();
            break;
          default:
            System.out.println("Opcio no valida, torna a provar.");
        }
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        scann.nextLine(); 
      }
    } while (opcio != 0);
  }


}
