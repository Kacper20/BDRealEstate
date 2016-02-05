package Entities;

/**
 * Created by tomasz on 05.02.16.
 */
public class Preferencja {
    private int id;
    private String wartosc;
    private int od;
    private int doo;
    private int cechaId;

    public String getWartosc() {
        return wartosc;
    }

    public void setWartosc(String wartosc) {
        this.wartosc = wartosc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOd() {
        return od;
    }

    public void setOd(int od) {
        this.od = od;
    }

    public int getDo() {
        return doo;
    }

    public void setDo(int doo) {
        this.doo = doo;
    }

    public int getCechaId() {
        return cechaId;
    }

    public void setCechaId(int cechaId) {
        this.cechaId = cechaId;
    }

    public Preferencja(int id, String wartosc, int od, int doo, int cechaId) {

        this.id = id;
        this.wartosc = wartosc;
        this.od = od;
        this.doo = doo;
        this.cechaId = cechaId;
    }
}
