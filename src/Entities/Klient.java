package Entities;

/**
 * Created by tomasz on 05.02.16.
 */
public class Klient {
    private int id;
    private String imie;
    private String nazwisko;
    private String plec;
    private String Obywatelstwo;
    private int adresZamieszkania;
    private String nrTelefonu;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public String getObywatelstwo() {
        return Obywatelstwo;
    }

    public void setObywatelstwo(String obywatelstwo) {
        Obywatelstwo = obywatelstwo;
    }

    public int getAdresZamieszkania() {
        return adresZamieszkania;
    }

    public void setAdresZamieszkania(int adresZamieszkania) {
        this.adresZamieszkania = adresZamieszkania;
    }

    public String getNrTelefonu() {
        return nrTelefonu;
    }

    public void setNrTelefonu(String nrTelefonu) {
        this.nrTelefonu = nrTelefonu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Klient(int id, String imie, String nazwisko, String plec, String obywatelstwo, int adresZamieszkania, String nrTelefonu, String email) {

        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.plec = plec;
        Obywatelstwo = obywatelstwo;
        this.adresZamieszkania = adresZamieszkania;
        this.nrTelefonu = nrTelefonu;
        this.email = email;
    }
}
