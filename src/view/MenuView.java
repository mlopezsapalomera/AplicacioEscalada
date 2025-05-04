package view;

public class MenuView {
    // Mostrar el menú principal de l'aplicació
  public static void menuPrincipalVista() {
    System.out.println("------------");
    System.out.println("-- INICI --- ");
    System.out.println("------------");
    System.out.println("1) Crear");
    System.out.println("2) Modificar");
    System.out.println("3) Llistar Un");
    System.out.println("4) Llistar Tot");
    System.out.println("5) Eliminar");
    System.out.println("0) Sortir");
  }
  // Mostrar el menú per crear elements
  public static void menuCrear() {
    System.out.println("=== Crear ===");
    System.out.println("1. Crear Escola");
    System.out.println("2. Crear Sector");
    System.out.println("3. Crear Via");
    System.out.println("4. Crear Escalador");
    System.out.println("5. Crear Llarg");
    System.out.println("0. Tornar al menú principal");
    System.out.print("Selecciona una opció: ");
  }

  // Mostrar el menú per modificar elements
  public static void menuModificar() {
    System.out.println("=== Modificar ===");
    System.out.println("1. Modificar Escola");
    System.out.println("2. Modificar Sector");
    System.out.println("3. Modificar Via");
    System.out.println("4. Modificar Escalador");
    System.out.println("5. Modificar Llarg");
    System.out.println("0. Tornar al menú principal");
    System.out.print("Selecciona una opció: ");
  }
  // Mostrar el menú per llistar un element
  public static void menuLlistarUn() {
    System.out.println("=== Llistar Un ===");
    System.out.println("1. Llistar Escola");
    System.out.println("2. Llistar Sector");
    System.out.println("3. Llistar Via");
    System.out.println("4. Llistar Escalador");
    System.out.println("5. Llistar Llarg");
    System.out.println("0. Tornar al menú principal");
    System.out.print("Selecciona una opció: ");
  }

  // Mostrar el menú per llistar tots els elements
  public static void menuLlistarTot() {
    System.out.println("=== Llistar Tot ===");
    System.out.println("1. Llistar Totes les Escoles");
    System.out.println("2. Llistar Tots els Sectors");
    System.out.println("3. Llistar Totes les Vies");
    System.out.println("4. Llistar Tots els Escaladors");
    System.out.println("5. Llistar Tots els Llargs d'una Via");
    System.out.println("0. Tornar al menú principal");
    System.out.print("Selecciona una opció: ");
  }

  
  // Mostrar el menú per eliminar elements
  public static void menuEliminar() {
    System.out.println("=== Eliminar ===");
    System.out.println("1. Eliminar Escola");
    System.out.println("2. Eliminar Sector");
    System.out.println("3. Eliminar Via");
    System.out.println("4. Eliminar Escalador");
    System.out.println("5. Eliminar Llarg");
    System.out.println("0. Tornar al menú principal");
    System.out.print("Selecciona una opció: ");
  }
}