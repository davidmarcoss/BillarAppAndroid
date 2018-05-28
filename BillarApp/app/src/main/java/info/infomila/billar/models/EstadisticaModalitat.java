package info.infomila.billar.models;

import java.io.Serializable;
import java.util.Objects;

public class EstadisticaModalitat implements Serializable
{

    private static final long serialVersionUID = 3L;
    private Soci soci;
    private Modalitat modalitat;
    private double coeficientBase;
    private int carambolesTemporadaActual;
    private int entradesTemporadaActual;

    protected EstadisticaModalitat()
    {
    }

    public EstadisticaModalitat(Soci soci, Modalitat modalitat, double coeficientBase, int carambolesTemporadaActual, int entradesTemporadaActual)
    {
        setSoci(soci);
        setModalitat(modalitat);
        setCoeficientBase(coeficientBase);
        setCarambolesTemporadaActual(carambolesTemporadaActual);
        setEntradesTemporadaActual(entradesTemporadaActual);
    }

    public Soci getSoci()
    {
        return soci;
    }

    protected final void setSoci(Soci soci)
    {
        if (this.soci == null) {
            throw new EstadisticaModalitatException("El soci no pot ser null");
        }
        
        this.soci = soci;
    }

    public Modalitat getModalitat()
    {
        return modalitat;
    }

    protected final void setModalitat(Modalitat modalitat)
    {
        if (this.modalitat == null) {
            throw new EstadisticaModalitatException("La modalitat no pot ser null");
        }
        
        this.modalitat = modalitat;
    }

    public double getCoeficientBase()
    {
        return coeficientBase;
    }

    public final void setCoeficientBase(double coeficientBase)
    {
        if (coeficientBase < 0 || coeficientBase > 1) {
            throw new EstadisticaModalitatException("El coeficient base ha de ser un valor entre 0 i 1 ");
        }

        this.coeficientBase = coeficientBase;
    }

    public int getCarambolesTemporadaActual()
    {
        return carambolesTemporadaActual;
    }

    public final void setCarambolesTemporadaActual(int carambolesTemporadaActual)
    {
        if (carambolesTemporadaActual < 0) {
            throw new EstadisticaModalitatException("Les caramboles de la temporada no poden ser negatives");
        }

        this.carambolesTemporadaActual = carambolesTemporadaActual;
    }

    public int getEntradesTemporadaActual()
    {
        return entradesTemporadaActual;
    }

    public final void setEntradesTemporadaActual(int entradesTemporadaActual)
    {
        if (entradesTemporadaActual < 0) {
            throw new EstadisticaModalitatException("Les entrades de la temporada no poden ser negatives");
        }

        this.entradesTemporadaActual = entradesTemporadaActual;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.soci);
        hash = 67 * hash + Objects.hashCode(this.modalitat);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EstadisticaModalitat other = (EstadisticaModalitat) obj;
        if (!Objects.equals(this.soci, other.soci)) {
            return false;
        }
        if (!Objects.equals(this.modalitat, other.modalitat)) {
            return false;
        }
        return true;
    }

}
