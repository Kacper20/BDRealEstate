package Entities;

import org.postgresql.util.PGmoney;

import java.util.Date;

/**
 * Created by tomasz on 05.02.16.
 */
public class Oferta {
    private int id;
    private int nieruchomoscId;
    private int typOfertyId;
    private int agentId;
    private String tytul;
    private String opis;
    private PGmoney cena;
    private boolean czyDostepne;
    private Date dataWprowadzenia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNieruchomoscId() {
        return nieruchomoscId;
    }

    public void setNieruchomoscId(int nieruchomoscId) {
        this.nieruchomoscId = nieruchomoscId;
    }

    public int getTypOfertyId() {
        return typOfertyId;
    }

    public void setTypOfertyId(int typOfertyId) {
        this.typOfertyId = typOfertyId;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public PGmoney getCena() {
        return cena;
    }

    public void setCena(PGmoney cena) {
        this.cena = cena;
    }

    public boolean isCzyDostepne() {
        return czyDostepne;
    }

    public void setCzyDostepne(boolean czyDostepne) {
        this.czyDostepne = czyDostepne;
    }

    public Date getDataWprowadzenia() {
        return dataWprowadzenia;
    }

    public void setDataWprowadzenia(Date dataWprowadzenia) {
        this.dataWprowadzenia = dataWprowadzenia;
    }

    public Oferta(int id, int nieruchomoscId, int typOfertyId, int agentId, String tytul, String opis, PGmoney cena, boolean czyDostepne, Date dataWprowadzenia) {

        this.id = id;
        this.nieruchomoscId = nieruchomoscId;
        this.typOfertyId = typOfertyId;
        this.agentId = agentId;
        this.tytul = tytul;
        this.opis = opis;
        this.cena = cena;
        this.czyDostepne = czyDostepne;
        this.dataWprowadzenia = dataWprowadzenia;
    }
}
