package objets;

public class Sector {
    private int id;
    private String nom;
    private int escolaId;
    private int numVies;
    private String coordenades; // Nuevas coordenadas
    private String aproximacio; // Nueva aproximación
    private String popularitat; // Nueva popularidad
    private String restriccions; // Nuevas restricciones

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEscolaId() {
        return escolaId;
    }

    public void setEscolaId(int escolaId) {
        this.escolaId = escolaId;
    }

    public String getCoordenades() {
        return coordenades;
    }

    public void setCoordenades(String coordenades) {
        this.coordenades = coordenades;
    }

    public String getAproximacio() {
        return aproximacio;
    }

    public void setAproximacio(String aproximacio) {
        this.aproximacio = aproximacio;
    }

    public int getNumVies() {
        return numVies;
    }

    public void setNumVies(int numVies) {
        this.numVies = numVies;
    }

    public String getPopularitat() {
        return popularitat;
    }

    public void setPopularitat(String popularitat) {
        this.popularitat = popularitat;
    }

    public String getRestriccions() {
        return restriccions;
    }

    public void setRestriccions(String restriccions) {
        this.restriccions = restriccions;
    }
}
