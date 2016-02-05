package Entities;

/**
 * Created by tomasz on 05.02.16.
 */
public class TypOferty {
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

    public TypOferty(int id, String nazwa) {

        this.id = id;
        this.nazwa = nazwa;
    }
}
