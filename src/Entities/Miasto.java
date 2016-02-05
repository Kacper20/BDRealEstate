package Entities;

/**
 * Created by tomasz on 04.02.16.
 */
public class Miasto {
    private int id;
    private String nazwa;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Miasto(int id, String nazwa) {

        this.id = id;
        this.nazwa = nazwa;
    }
}
