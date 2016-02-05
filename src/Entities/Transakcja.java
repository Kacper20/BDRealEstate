package Entities;

import org.postgresql.util.PGmoney;

/**
 * Created by tomasz on 05.02.16.
 */
public class Transakcja {
    private int id;
    private PGmoney prowizja;
    private int ofertaId;
    private int sprzedajacyId;
    private int kupujacyId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PGmoney getProwizja() {
        return prowizja;
    }

    public void setProwizja(PGmoney prowizja) {
        this.prowizja = prowizja;
    }

    public int getOfertaId() {
        return ofertaId;
    }

    public void setOfertaId(int ofertaId) {
        this.ofertaId = ofertaId;
    }

    public int getSprzedajacyId() {
        return sprzedajacyId;
    }

    public void setSprzedajacyId(int sprzedajacyId) {
        this.sprzedajacyId = sprzedajacyId;
    }

    public int getKupujacyId() {
        return kupujacyId;
    }

    public void setKupujacyId(int kupujacyId) {
        this.kupujacyId = kupujacyId;
    }

    public Transakcja(int id, PGmoney prowizja, int ofertaId, int sprzedajacyId, int kupujacyId) {

        this.id = id;
        this.prowizja = prowizja;
        this.ofertaId = ofertaId;
        this.sprzedajacyId = sprzedajacyId;
        this.kupujacyId = kupujacyId;
    }
}
