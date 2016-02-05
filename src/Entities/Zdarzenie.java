package Entities;

/**
 * Created by tomasz on 05.02.16.
 */
public class Zdarzenie {
    private int id;
    private String opiniaKlienta;
    private int agentId;
    private int ofertaId;
    private int transakcjaId;
    private int typZdarzeniaId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpiniaKlienta() {
        return opiniaKlienta;
    }

    public void setOpiniaKlienta(String opiniaKlienta) {
        this.opiniaKlienta = opiniaKlienta;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public int getOfertaId() {
        return ofertaId;
    }

    public void setOfertaId(int ofertaId) {
        this.ofertaId = ofertaId;
    }

    public int getTransakcjaId() {
        return transakcjaId;
    }

    public void setTransakcjaId(int transakcjaId) {
        this.transakcjaId = transakcjaId;
    }

    public int getTypZdarzeniaId() {
        return typZdarzeniaId;
    }

    public void setTypZdarzeniaId(int typZdarzeniaId) {
        this.typZdarzeniaId = typZdarzeniaId;
    }

    public Zdarzenie(int id, String opiniaKlienta, int agentId, int ofertaId, int transakcjaId, int typZdarzeniaId) {

        this.id = id;
        this.opiniaKlienta = opiniaKlienta;
        this.agentId = agentId;
        this.ofertaId = ofertaId;
        this.transakcjaId = transakcjaId;
        this.typZdarzeniaId = typZdarzeniaId;
    }
}
