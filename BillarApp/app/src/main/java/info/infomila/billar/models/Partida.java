/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.billar.models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author David
 */
public class Partida implements Serializable
{
    private static final long serialVersionUID = 7L;
    
    private int id;
    private Soci sociA;
    private Soci sociB;
    private Torneig torneig;
    private Grup grup;
    private int carambolesA;
    private int carambolesB;
    private int numEntradesA;
    private int numEntradesB;
    private Date dataRealitzacio;
    private EstatPartida estatPartida;
    private ModeVictoria modeVictoria;
    private Guanyador guanyador;
    
    protected Partida() {}

    public int getId()
    {
        return id;
    }

    protected void setId(int id)
    {
        this.id = id;
    }

    public Soci getSociA()
    {
        return sociA;
    }

    protected void setSociA(Soci sociA)
    {
        this.sociA = sociA;
    }

    public Soci getSociB()
    {
        return sociB;
    }

    protected void setSociB(Soci sociB)
    {
        this.sociB = sociB;
    }

    public Torneig getTorneig()
    {
        return torneig;
    }

    protected void setTorneig(Torneig torneig)
    {
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

    public int getCarambolesA()
    {
        return carambolesA;
    }

    public void setCarambolesA(int carambolesA)
    {
        if (carambolesA < 0) {
            throw new PartidaException("Les caramboles A han de ser positives");
        }
        
        this.carambolesA = carambolesA;
    }

    public int getCarambolesB()
    {
        return carambolesB;
    }

    public void setCarambolesB(int carambolesB)
    {
        if (carambolesB < 0) {
            throw new PartidaException("Les caramboles B han de ser positives");
        }
        
        this.carambolesB = carambolesB;
    }

    public int getNumEntradesA()
    {
        return numEntradesA;
    }

    public void setNumEntradesA(int numEntradesA)
    {
        if (numEntradesA < 0) {
            throw new PartidaException("El numero de entrades A han de ser positives");
        }
        
        this.numEntradesA = numEntradesA;
    }

    public int getNumEntradesB()
    {
        return numEntradesB;
    }

    public void setNumEntradesB(int numEntradesB)
    {
        if (numEntradesB < 0) {
            throw new PartidaException("El numero de entrades B han de ser positives");
        }
                
        this.numEntradesB = numEntradesB;
    }

    public Date getDataRealitzacio()
    {
        return dataRealitzacio;
    }

    protected void setDataRealitzacio(Date dataRealitzacio)
    {
        this.dataRealitzacio = dataRealitzacio;
    }

    public EstatPartida getEstatPartida()
    {
        return estatPartida;
    }

    public void setEstatPartida(EstatPartida estatPartida)
    {
        this.estatPartida = estatPartida;
    }

    public ModeVictoria getModeVictoria()
    {
        return modeVictoria;
    }

    public void setModeVictoria(ModeVictoria modeVictoria)
    {
        this.modeVictoria = modeVictoria;
    }

    public Guanyador getGuanyador()
    {
        return guanyador;
    }

    public void setGuanyador(Guanyador guanyador)
    {
        this.guanyador = guanyador;
    }
    
    public enum EstatPartida {
        PENDENT, JUGAT
    }
    
    public enum ModeVictoria {
        PER_CARAMBOLES, ENTRADES_ASSOLIDES, ABANDONAMENT
    }    
    
    public enum Guanyador {
        A, B
    }
}
