package Entities;

import java.util.Date;

/**
 * Created by tomasz on 05.02.16.
 */
public class Nieruchomosc {
    private int id;
    private int numerWieczysty;
    private int klient;
    private int osiedleId;
    private int dzielnicaId;
    private int miastoId;
    private int typMieszkaniaId;
    private Date dataStworzenia;
    private String tytulNieruchomosci;
    private String opisNieruchomosci;
    private String ulica;
    private String nrDomu;
    private String nrMieszkania;
    private String kodPocztowy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumerWieczysty() {
        return numerWieczysty;
    }

    public void setNumerWieczysty(int numerWieczysty) {
        this.numerWieczysty = numerWieczysty;
    }

    public int getKlient() {
        return klient;
    }

    public void setKlient(int klient) {
        this.klient = klient;
    }

    public int getOsiedleId() {
        return osiedleId;
    }

    public void setOsiedleId(int osiedleId) {
        this.osiedleId = osiedleId;
    }

    public int getDzielnicaId() {
        return dzielnicaId;
    }

    public void setDzielnicaId(int dzielnicaId) {
        this.dzielnicaId = dzielnicaId;
    }

    public int getMiastoId() {
        return miastoId;
    }

    public void setMiastoId(int miastoId) {
        this.miastoId = miastoId;
    }

    public int getTypMieszkaniaId() {
        return typMieszkaniaId;
    }

    public void setTypMieszkaniaId(int typMieszkaniaId) {
        this.typMieszkaniaId = typMieszkaniaId;
    }

    public Date getDataStworzenia() {
        return dataStworzenia;
    }

    public void setDataStworzenia(Date dataStworzenia) {
        this.dataStworzenia = dataStworzenia;
    }

    public String getTytulNieruchomosci() {
        return tytulNieruchomosci;
    }

    public void setTytulNieruchomosci(String tytulNieruchomosci) {
        this.tytulNieruchomosci = tytulNieruchomosci;
    }

    public String getOpisNieruchomosci() {
        return opisNieruchomosci;
    }

    public void setOpisNieruchomosci(String opisNieruchomosci) {
        this.opisNieruchomosci = opisNieruchomosci;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNrDomu() {
        return nrDomu;
    }

    public void setNrDomu(String nrDomu) {
        this.nrDomu = nrDomu;
    }

    public String getNrMieszkania() {
        return nrMieszkania;
    }

    public void setNrMieszkania(String nrMieszkania) {
        this.nrMieszkania = nrMieszkania;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public Nieruchomosc(int id, int numerWieczysty, int klient, int osiedleId, int dzielnicaId, int miastoId, int typMieszkaniaId, Date dataStworzenia, String tytulNieruchomosci, String opisNieruchomosci, String ulica, String nrDomu, String nrMieszkania, String kodPocztowy) {

        this.id = id;
        this.numerWieczysty = numerWieczysty;
        this.klient = klient;
        this.osiedleId = osiedleId;
        this.dzielnicaId = dzielnicaId;
        this.miastoId = miastoId;
        this.typMieszkaniaId = typMieszkaniaId;
        this.dataStworzenia = dataStworzenia;
        this.tytulNieruchomosci = tytulNieruchomosci;
        this.opisNieruchomosci = opisNieruchomosci;
        this.ulica = ulica;
        this.nrDomu = nrDomu;
        this.nrMieszkania = nrMieszkania;
        this.kodPocztowy = kodPocztowy;
    }
}
