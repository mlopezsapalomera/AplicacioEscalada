package objets;

public class Escalador {
    private int id;
    private String nom;
    private String alias;
    private int edat;
    private String nivellMax;
    private int viaMaxId;
    private String estilPreferit;
    private String historial;
    private String fita;

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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    public String getNivellMax() {
        return nivellMax;
    }

    public void setNivellMax(String nivellMax) {
        this.nivellMax = nivellMax;
    }

    public int getViaMaxId() {
        return viaMaxId;
    }

    public void setViaMaxId(int viaMaxId) {
        this.viaMaxId = viaMaxId;
    }

    public String getEstilPreferit() {
        return estilPreferit;
    }

    public void setEstilPreferit(String estilPreferit) {
        this.estilPreferit = estilPreferit;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    public String getFita() {
        return fita;
    }

    public void setFita(String fita) {
        this.fita = fita;
    }
}
