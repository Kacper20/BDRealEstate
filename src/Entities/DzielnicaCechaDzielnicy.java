package Entities;

/**
 * Created by tomasz on 04.02.16.
 */
public class DzielnicaCechaDzielnicy {
    private int cechaDzielnicyId;
    private int dzielnicaId;
    private int liczba;

    public int getCechaDzielnicyId() {
        return cechaDzielnicyId;
    }

    public void setCechaDzielnicyId(int cechaDzielnicyId) {
        this.cechaDzielnicyId = cechaDzielnicyId;
    }

    public int getDzielnicaId() {
        return dzielnicaId;
    }

    public void setDzielnicaId(int dzielnicaId) {
        this.dzielnicaId = dzielnicaId;
    }

    public int getLiczba() {
        return liczba;
    }

    public void setLiczba(int liczba) {
        this.liczba = liczba;
    }

    public DzielnicaCechaDzielnicy(int cechaDzielnicyId, int dzielnicaId, int liczba) {

        this.cechaDzielnicyId = cechaDzielnicyId;
        this.dzielnicaId = dzielnicaId;
        this.liczba = liczba;
    }
}
