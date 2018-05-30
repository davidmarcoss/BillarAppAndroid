package info.infomila.billar.models;

import java.io.Serializable;

public class Classificacio implements Serializable
{
    private static final long serialVersionUID = 9L;
    private String soci;
    private long patidesGuanyades;
    private long partidesPerdudes;

    public Classificacio(String soci, long patidesGuanyades, long partidesPerdudes)
    {
        this.soci = soci;
        this.patidesGuanyades = patidesGuanyades;
        this.partidesPerdudes = partidesPerdudes;
    }

    public String getSoci()
    {
        return soci;
    }

    public void setSoci(String soci)
    {
        this.soci = soci;
    }

    public long getPatidesGuanyades()
    {
        return patidesGuanyades;
    }

    public void setPatidesGuanyades(long patidesGuanyades)
    {
        this.patidesGuanyades = patidesGuanyades;
    }

    public long getPartidesPerdudes()
    {
        return partidesPerdudes;
    }

    public void setPartidesPerdudes(long partidesPerdudes)
    {
        this.partidesPerdudes = partidesPerdudes;
    }

}
