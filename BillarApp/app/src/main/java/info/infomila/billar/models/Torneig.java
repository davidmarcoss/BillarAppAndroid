package info.infomila.billar.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Torneig implements Serializable
{

    private static final long serialVersionUID = 4L;

    private int id;
    private Modalitat modalitat;
    private String nom;
    private Date dataInici;
    private Date dataFi;
    private boolean preinscripcioOberta;
    private boolean actiu;
    private List<Grup> grups;
    private List<Inscripcio> inscripcions = new ArrayList();

    protected Torneig()
    {
    }

    public Torneig(Modalitat modalitat, String nom, Date dataInici, Date dataFi, boolean preinscripcioOberta, boolean actiu)
    {
        this.id = 0;
        setModalitat(modalitat);
        setNom(nom);
        setDataInici(dataInici);
        setDataFi(dataFi);
        setPreinscripcioOberta(preinscripcioOberta);
        setActiu(actiu);
    }

    public int getId()
    {
        return id;
    }

    protected void setId(int id)
    {
        this.id = id;
    }

    public Modalitat getModalitat()
    {
        return modalitat;
    }

    public void setModalitat(Modalitat modalitat)
    {
        if (modalitat == null) {
            throw new TorneigException("No es pot crear un torneig sense modalitat");
        }
        
        this.modalitat = modalitat;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        if (nom == null || nom.length() < 3) {
            throw new TorneigException("El nom del torneig es obligatori i ha de tindre més de 2 caràcters");
        }
        
        this.nom = nom;
    }

    public Date getDataInici()
    {
        return dataInici;
    }

    public void setDataInici(Date dataInici)
    {
        if (dataInici == null || dataInici.before(new Date())) {
            throw new TorneigException("La data de inici ha de ser major a avui");
        }
        if (dataFi != null && dataInici.after(dataFi)) {
            throw new TorneigException("La data de inici ha de ser mejor a la data de fi");
        }
        
        this.dataInici = dataInici;
    }

    public Date getDataFi()
    {
        return dataFi;
    }

    public void setDataFi(Date dataFi)
    {
        if (dataFi == null || dataFi.before(new Date()) || dataFi.before(dataInici)) {
            throw new TorneigException("La data de inici ha de ser major a avui i que la data de inici");
        }
        this.dataFi = dataFi;
    }

    public boolean isPreinscripcioOberta()
    {
        return preinscripcioOberta;
    }

    public void setPreinscripcioOberta(boolean preinscripcioOberta)
    {
        this.preinscripcioOberta = preinscripcioOberta;
    }

    public boolean isActiu()
    {
        return actiu;
    }

    public void setActiu(boolean actiu)
    {
        this.actiu = actiu;
    }

    protected List<Grup> getGrups()
    {
        return grups;
    }
        
    public Iterator<Grup> iteGrups()
    {
        return grups.iterator();
    }

    protected void setGrups(List<Grup> grups)
    {
        this.grups = grups;
    }
    
    protected List<Inscripcio> getInscripcions()
    {
        return inscripcions;
    }
    
    public Iterator<Inscripcio> iteInscripcions()
    {
        return inscripcions.iterator();
    }

    protected void setInscripcions(List<Inscripcio> inscripcions)
    {
        this.inscripcions = inscripcions;
    }
    
    public void addInscripcio(Inscripcio inscripcio) {
        if (inscripcio == null) {
            throw new TorneigException("No es pot afegir una inscripció null a un torneig");
        }
        
        if (!this.inscripcions.contains(inscripcio)) {
            this.inscripcions.add(inscripcio);
            inscripcio.setTorneig(this);
        }
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
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
        final Torneig other = (Torneig) obj;
        return this.id == other.id;
    }
}
