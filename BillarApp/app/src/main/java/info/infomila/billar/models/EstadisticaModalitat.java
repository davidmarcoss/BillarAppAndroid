package info.infomila.billar.models;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Objects;

public class EstadisticaModalitat implements Serializable
{

    private static final long serialVersionUID = 3L;
    private EstadisticaModalitatPK emPK;
    private double coeficientBase;
    private int carambolesTemporadaActual;
    private int entradesTemporadaActual;

    protected EstadisticaModalitat()
    {
    }

    public EstadisticaModalitat(Soci soci, Modalitat modalitat, double coeficientBase, int carambolesTemporadaActual, int entradesTemporadaActual)
    {
        emPK = new EstadisticaModalitatPK(soci, modalitat);
        setCoeficientBase(coeficientBase);
        setCarambolesTemporadaActual(carambolesTemporadaActual);
        setEntradesTemporadaActual(entradesTemporadaActual);
    }

    public Soci getSoci()
    {
        return emPK.getSoci();
    }

    protected final void setSoci(Soci soci)
    {
        emPK.setSoci(soci);
    }

    public Modalitat getModalitat()
    {
        return emPK.getModalitat();
    }

    protected final void setModalitat(Modalitat modalitat)
    {
        emPK.setModalitat(modalitat);
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.emPK);
        return hash;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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
        return Objects.equals(this.emPK, other.emPK);
    }
}
