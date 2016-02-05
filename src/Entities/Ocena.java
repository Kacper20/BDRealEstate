package Entities;

/**
 * Created by tomasz on 05.02.16.
 */
public class Ocena {
    private int id;
    private int wartosc;
    private int agentId;
    private int transakcjaId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWartosc() {
        return wartosc;
    }

    public void setWartosc(int wartosc) {
        this.wartosc = wartosc;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public int getTransakcjaId() {
        return transakcjaId;
    }

    public void setTransakcjaId(int transakcjaId) {
        this.transakcjaId = transakcjaId;
    }

    public Ocena(int id, int wartosc, int agentId, int transakcjaId) {

        this.id = id;
        this.wartosc = wartosc;
        this.agentId = agentId;
        this.transakcjaId = transakcjaId;
    }
}
