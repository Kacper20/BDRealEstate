package Entities;

import java.util.Date;

/**
 * Created by tomasz on 04.02.16.
 */
public class Projekt {
    private int id;
    private int deweloperId;
    private String nazwa;
    private String opis;
    private int osiedle_id;
    private Date przewidywanaDataRealizacji;
    private Date dataRealizacji;
    private Date dataRozpoczecia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeweloperId() {
        return deweloperId;
    }

    public void setDeweloperId(int deweloperId) {
        this.deweloperId = deweloperId;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getOsiedle_id() {
        return osiedle_id;
    }

    public void setOsiedle_id(int osiedle_id) {
        this.osiedle_id = osiedle_id;
    }

    public Date getPrzewidywanaDataRealizacji() {
        return przewidywanaDataRealizacji;
    }

    public void setPrzewidywanaDataRealizacji(Date przewidywanaDataRealizacji) {
        this.przewidywanaDataRealizacji = przewidywanaDataRealizacji;
    }

    public Date getDataRealizacji() {
        return dataRealizacji;
    }

    public void setDataRealizacji(Date dataRealizacji) {
        this.dataRealizacji = dataRealizacji;
    }

    public Date getDataRozpoczecia() {
        return dataRozpoczecia;
    }

    public void setDataRozpoczecia(Date dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }

    public Projekt(int id, int deweloperId, String nazwa, String opis, int osiedle_id, Date przewidywanaDataRealizacji, Date dataRealizacji, Date dataRozpoczecia) {

        this.id = id;
        this.deweloperId = deweloperId;
        this.nazwa = nazwa;
        this.opis = opis;
        this.osiedle_id = osiedle_id;
        this.przewidywanaDataRealizacji = przewidywanaDataRealizacji;
        this.dataRealizacji = dataRealizacji;
        this.dataRozpoczecia = dataRozpoczecia;
    }
}
