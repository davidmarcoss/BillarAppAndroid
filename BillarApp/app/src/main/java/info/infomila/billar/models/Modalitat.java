package info.infomila.billar.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Modalitat implements Serializable
{
    private static final long serialVersionUID = 2L;
        
    private int id;
    private String descripcio;
    
    protected Modalitat() {}
    
    public Modalitat(int id, String descripcio)
    {
        setId(id);
        setDescripcio(descripcio);
    }
    
    public int getId()
    {
        return id;
    }

    protected final void setId(int id)
    {
        this.id = id;
    }

    public String getDescripcio()
    {
        return descripcio;
    }

    public final void setDescripcio(String descripcio)
    {
        if (descripcio == null && descripcio.length() == 0)
        {
            throw new ModalitatException("La descripció de la modalitat és obligatoria");
        }
        
        this.descripcio = descripcio;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 79 * hash + this.id;
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
        final Modalitat other = (Modalitat) obj;
        return this.id == other.id;
    }

    @Override
    public String toString()
    {
        return descripcio;
    }
    
    
}
