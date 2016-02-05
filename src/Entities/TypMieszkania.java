package Entities;

/**
 * Created by tomasz on 04.02.16.
 */
public class TypMieszkania {
    private int id;
    private int ile;
    private int budynekId;
    private String klasa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIle() {
        return ile;
    }

    public void setIle(int ile) {
        this.ile = ile;
    }

    public int getBudynekId() {
        return budynekId;
    }

    public void setBudynekId(int budynekId) {
        this.budynekId = budynekId;
    }

    public String getKlasa() {
        return klasa;
    }

    public void setKlasa(String klasa) {
        this.klasa = klasa;
    }

    public TypMieszkania(int id, int ile, int budynekId, String klasa) {

        this.id = id;
        this.ile = ile;
        this.budynekId = budynekId;
        this.klasa = klasa;
    }
}
