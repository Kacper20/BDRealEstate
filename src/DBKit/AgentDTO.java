package DBKit;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by kacper on 02.02.2016.
 */
public class AgentDTO {
    private Integer id;
    private String imie;
    private String nazwisko;
    private Integer nrAgenta;
    private String PESEL;


    public AgentDTO(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.imie = rs.getString("imie");
        this.nazwisko = rs.getString("nazwisko");
        this.PESEL = rs.getString("PESEL");
        this.nrAgenta = rs.getInt("nr_agenta");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getNrAgenta() {
        return nrAgenta;
    }

    public void setNrAgenta(Integer nrAgenta) {
        this.nrAgenta = nrAgenta;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }
}
