package Entities;

/**
 * Created by tomasz on 05.02.16.
 */
public class Osiedle {
    private int id;
    private String nazwa;
    private int dzielnicaId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getDzielnicaId() {
        return dzielnicaId;
    }

    public void setDzielnicaId(int dzielnicaId) {
        this.dzielnicaId = dzielnicaId;
    }

    public Osiedle(int id, String nazwa, int dzielnicaId) {

        this.id = id;
        this.nazwa = nazwa;
        this.dzielnicaId = dzielnicaId;
    }
}
