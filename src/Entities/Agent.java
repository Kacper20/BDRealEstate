package Entities;

/**
 * Created by tomasz on 03.02.16.
 */
public class Agent {
    private int id;
    private String imie;
    private String nazwisko;
    private int numerAgenta;
    private String pesel;

    public Agent(int id, String imie, String nazwisko, int numerAgenta, String pesel) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numerAgenta = numerAgenta;
        this.pesel = pesel;
    }

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

    public int getNumerAgenta() {
        return numerAgenta;
    }

    public void setNumerAgenta(int numerAgenta) {
        this.numerAgenta = numerAgenta;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}
