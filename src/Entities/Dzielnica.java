package Entities;

/**
 * Created by tomasz on 04.02.16.
 */
public class Dzielnica {
    private int id;
    private String nazwa;

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

    public int getMiastoId() {
        return miastoId;
    }

    public void setMiastoId(int miastoId) {
        this.miastoId = miastoId;
    }

    public Dzielnica(int id, String nazwa, int miastoId) {

        this.id = id;
        this.nazwa = nazwa;
        this.miastoId = miastoId;
    }

    private int miastoId;


}
