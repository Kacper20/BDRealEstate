package Entities;

/**
 * Created by tomasz on 05.02.16.
 */
public class OsiedleCechaOsiedla {
    private int osiedleCechaId;
    private int osiedleId;
    private int liczba;

    public int getOsiedleCechaId() {
        return osiedleCechaId;
    }

    public void setOsiedleCechaId(int osiedleCechaId) {
        this.osiedleCechaId = osiedleCechaId;
    }

    public int getOsiedleId() {
        return osiedleId;
    }

    public void setOsiedleId(int osiedleId) {
        this.osiedleId = osiedleId;
    }

    public int getLiczba() {
        return liczba;
    }

    public void setLiczba(int liczba) {
        this.liczba = liczba;
    }

    public OsiedleCechaOsiedla(int osiedleCechaId, int osiedleId, int liczba) {
        this.osiedleCechaId = osiedleCechaId;
        this.osiedleId = osiedleId;
        this.liczba = liczba;
    }
}
