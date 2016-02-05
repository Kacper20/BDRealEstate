package Entities;

/**
 * Created by tomasz on 04.02.16.
 */
public class CechaOsiedla {
    private int id;
    private String nazwa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public CechaOsiedla(int id, String nazwa) {

        this.id = id;
        this.nazwa = nazwa;
    }
}
