package Entities;

/**
 * Created by tomasz on 05.02.16.
 */
public class KlientPreferencje {
    private int klientId;
    private int preferencjaId;

    public int getKlientId() {
        return klientId;
    }

    public void setKlientId(int klientId) {
        this.klientId = klientId;
    }

    public int getPreferencjaId() {
        return preferencjaId;
    }

    public void setPreferencjaId(int preferencjaId) {
        this.preferencjaId = preferencjaId;
    }

    public KlientPreferencje(int klientId, int preferencjaId) {

        this.klientId = klientId;
        this.preferencjaId = preferencjaId;
    }
}
