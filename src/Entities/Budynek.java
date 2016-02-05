package Entities;

/**
 * Created by tomasz on 04.02.16.
 */
public class Budynek {
    private int id;
    private int projektId;
    private String ulica;
    private String nrBudynku;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjektId() {
        return projektId;
    }

    public void setProjektId(int projektId) {
        this.projektId = projektId;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNrBudynku() {
        return nrBudynku;
    }

    public void setNrBudynku(String nrBudynku) {
        this.nrBudynku = nrBudynku;
    }

    public Budynek(int id, int projektId, String ulica, String nrBudynku) {

        this.id = id;
        this.projektId = projektId;
        this.ulica = ulica;
        this.nrBudynku = nrBudynku;
    }
}
