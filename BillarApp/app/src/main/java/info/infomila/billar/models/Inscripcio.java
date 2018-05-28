/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.billar.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author David
 */
public class Inscripcio implements Serializable
{
    private static final long serialVersionUID = 6L;
    
    private int id;
    private Soci soci;
    private Torneig torneig;
    private Grup grup;
    private Date dataCreacio;

    protected Inscripcio() {}

    public Inscripcio(Soci soci, Torneig torneig, Grup grup)
    {
        this.id = 0;
        setSoci(soci);
        setTorneig(torneig);
        setGrup(grup);
        this.dataCreacio = new Date();
    }
    
    public int getId()
    {
        return id;
    }

    protected void setId(int id)
    {
        this.id = id;
    }

    public Soci getSoci()
    {
        return soci;
    }

    public void setSoci(Soci soci)
    {
        if (soci == null) {
            throw new InscripcioException("El soci de una inscripció no pot ser null");
        }
        
        if (!this.soci.equals(soci)) {
            this.soci = soci;
            soci.addInscripcio(this);
        }
    }

    public Torneig getTorneig()
    {
        return torneig;
    }

    public void setTorneig(Torneig torneig)
    {
        if (torneig == null) {
            throw new InscripcioException("El torneig de una inscripció no pot ser null");
        }
        
        this.torneig = torneig;
    }

    public Grup getGrup()
    {
        return grup;
    }

    protected void setGrup(Grup grup)
    {
        this.grup = grup;
    }

    public Date getDataCreacio()
    {
        return dataCreacio;
    }

    protected void setDataCreacio(Date dataCreacio)
    {
        this.dataCreacio = dataCreacio;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.soci);
        hash = 97 * hash + Objects.hashCode(this.torneig);
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
        final Inscripcio other = (Inscripcio) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.soci, other.soci)) {
            return false;
        }
        return true;
    }
    
    
}
