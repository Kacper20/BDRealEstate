package Entities;

/**
 * Created by tomasz on 05.02.16.
 */
public class WlasnoscNieruchomosci {
    private int id;
    private int cechaId;
    private int wartosc;
    private int nieruchomoscId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCechaId() {
        return cechaId;
    }

    public void setCechaId(int cechaId) {
        this.cechaId = cechaId;
    }

    public int getWartosc() {
        return wartosc;
    }

    public void setWartosc(int wartosc) {
        this.wartosc = wartosc;
    }

    public int getNieruchomoscId() {
        return nieruchomoscId;
    }

    public void setNieruchomoscId(int nieruchomoscId) {
        this.nieruchomoscId = nieruchomoscId;
    }

    public WlasnoscNieruchomosci(int id, int cechaId, int wartosc, int nieruchomoscId) {

        this.id = id;
        this.cechaId = cechaId;
        this.wartosc = wartosc;
        this.nieruchomoscId = nieruchomoscId;
    }
}
