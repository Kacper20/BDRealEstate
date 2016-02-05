package Entities;

/**
 * Created by tomasz on 05.02.16.
 */
public class Cecha {
    private int id;
    private String nazwa;
    private int typ;

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

    public int getTyp() {
        return typ;
    }

    public void setTyp(int typ) {
        this.typ = typ;
    }

    public Cecha(int id, String nazwa, int typ) {

        this.id = id;
        this.nazwa = nazwa;
        this.typ = typ;
    }
}
