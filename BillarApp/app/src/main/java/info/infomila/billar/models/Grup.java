/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.billar.models;

import java.io.Serializable;

/**
 *
 * @author David
 */
public class Grup implements Serializable
{
    private static final long serialVersionUID = 5L;
    
    private int id;
    private Torneig torneig;
    private String descripcio;
    private int carambolesVictoria;
    private int limitEntrades;
    private boolean actiu;
    
    protected Grup() {}

    public Grup(Torneig torneig, String descripcio, int carambolesVictoria, int limitEntrades, boolean actiu)
    {
        this.id = 0;
        setTorneig(torneig);
        setDescripcio(descripcio);
        setCarambolesVictoria(carambolesVictoria);
        setLimitEntrades(limitEntrades);
        setActiu(actiu);
    }
    
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Torneig getTorneig()
    {
        return torneig;
    }

    protected void setTorneig(Torneig torneig)
    {
        this.torneig = torneig;
    }

    public String getDescripcio()
    {
        return descripcio;
    }

    public void setDescripcio(String descripcio)
    {
        if (descripcio == null || descripcio.length() < 3) {
            throw new GrupException("El nom del grup es obligatori i ha de tindre més de 2 caràcters");
        }
        
        this.descripcio = descripcio;
    }

    public int getCarambolesVictoria()
    {
        return carambolesVictoria;
    }

    public void setCarambolesVictoria(int carambolesVictoria)
    {
        if (carambolesVictoria < 1) {
            throw new GrupException("Les caramboles de victoria han de tenir un mínim de 1");
        }
        
        this.carambolesVictoria = carambolesVictoria;
    }

    public int getLimitEntrades()
    {
        return limitEntrades;
    }

    public void setLimitEntrades(int limitEntrades)
    {
        if (limitEntrades < 1) {
            throw new GrupException("El límit d'entrades han de tenir un mínim de 1");
        }
        
        this.limitEntrades = limitEntrades;
    }

    public boolean isActiu()
    {
        return actiu;
    }

    public void setActiu(boolean actiu)
    {
        this.actiu = actiu;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 53 * hash + this.id;
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
        final Grup other = (Grup) obj;
        return this.id == other.id;
    }
}
