package info.infomila.billar.models;

import java.io.Serializable;
import java.util.Objects;

public class EstadisticaModalitatPK implements Serializable {
    private static final long serialVersionUID = 8L;
    private Soci soci;
    private Modalitat modalitat;

    protected EstadisticaModalitatPK() {}

    public EstadisticaModalitatPK(Soci soci, Modalitat modalitat)
    {
        setSoci(soci);
        setModalitat(modalitat);
    }

    public EstadisticaModalitatPK(EstadisticaModalitatPK emPK)
    {
        setSoci(emPK.soci);
        setModalitat(emPK.modalitat);
    }

    public Soci getSoci()
    {
        return soci;
    }

    protected final void setSoci(Soci soci)
    {
        if (soci == null) {
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
        if (modalitat == null) {
            throw new EstadisticaModalitatException("La modalitat no pot ser null");
        }

        this.modalitat = modalitat;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.soci);
        hash = 79 * hash + Objects.hashCode(this.modalitat);
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
        final EstadisticaModalitatPK other = (EstadisticaModalitatPK) obj;
        if (!Objects.equals(this.soci, other.soci)) {
            return false;
        }
        return Objects.equals(this.modalitat, other.modalitat);
    }

    @Override
    public String toString()
    {
        return modalitat.toString();
    }
}
