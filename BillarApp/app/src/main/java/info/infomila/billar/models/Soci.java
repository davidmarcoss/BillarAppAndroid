package info.infomila.billar.models;

import java.io.Serializable;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Soci implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String nif;
    private String nom;
    private String cognom1;
    private String cognom2;
    private Date dataAlta;
    private String passwordHash;
    private transient Blob foto;
    private List<EstadisticaModalitat> estadistiques = new ArrayList<>();
    private List<Inscripcio> inscripcions = new ArrayList();
    private boolean actiu;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    protected Soci()
    {
    }

    public Soci(String nif, String nom, String cognom1, String cognom2, String passwordHash, Blob foto, boolean actiu)
    {
        id = 0;
        setNif(nif);
        setNom(nom);
        setCognom1(cognom1);
        setCognom2(cognom2);
        setDataAlta(new Date());
        setPasswordHash(passwordHash);
        setActiu(actiu);
    }

    public int getId()
    {
        return id;
    }

    protected final void setId(int id)
    {
        this.id = id;
    }

    public String getNif()
    {
        return nif;
    }

    public final void setNif(String nif)
    {
        if (!validarNIF(nif)) {
            throw new SociException("El NIF es obligatori i ha de tindre una longitud de 8 dígits i 1 caràcter.");
        }
        this.nif = nif;
    }

    public String getNom()
    {
        return nom;
    }

    public final void setNom(String nom)
    {
        if (nom == null || nom.length() < 3) {
            throw new SociException("El nom es obligatori i ha de tindre un mínim de 2 caràcters.");
        }
        this.nom = nom;
    }

    public String getCognom1()
    {
        return cognom1;
    }

    public final void setCognom1(String cognom1)
    {
        this.cognom1 = cognom1;
    }

    public String getCognom2()
    {
        return cognom2;
    }

    public final void setCognom2(String cognom2)
    {
        this.cognom2 = cognom2;
    }

    public Date getDataAlta()
    {
        return dataAlta;
    }

    protected final void setDataAlta(Date dataAlta)
    {
        this.dataAlta = dataAlta;
    }

    public String getDataAltaString()
    {
        return sdf.format(dataAlta);
    }

    public String getPasswordHash()
    {
        return passwordHash;
    }

    public final void setPasswordHash(String passwordHash)
    {
        this.passwordHash = passwordHash;
    }

    public Blob getFoto()
    {
        return foto;
    }

    public final void setFoto(Blob foto)
    {
        this.foto = foto;
    }

    public List<EstadisticaModalitat> getEstadistiques()
    {
        return estadistiques;
    }

    public Iterator<EstadisticaModalitat> iteEstadistiques()
    {
        return estadistiques.iterator();
    }

    public EstadisticaModalitat getEstadisticaByIndex(int index)
    {
        return estadistiques.get(index);
    }

    private final void setEstadistiques(List<EstadisticaModalitat> estadistiques)
    {
        this.estadistiques = estadistiques;
    }

    public boolean isActiu()
    {
        return actiu;
    }

    public void setActiu(boolean actiu)
    {
        this.actiu = actiu;
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
            throw new SociException("No es pot afegir una inscripció null a un soci");
        }
        
        if (!this.inscripcions.contains(inscripcio)) {
            this.inscripcions.add(inscripcio);
            inscripcio.setSoci(this);
        }
    }
    
    @Override
    public String toString()
    {
        return "Soci{" + "id=" + id + ", nif=" + nif + ", nom=" + nom + ", cognom1=" + cognom1 + ", cognom2=" + cognom2 + ", actiu=" + actiu + '}';
    }

    private boolean validarNIF(String nif)
    {
        boolean correcto = false;
        Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
        Matcher matcher = pattern.matcher(nif);

        if (matcher.matches()) {

            String letra = matcher.group(2);
            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

            int index = Integer.parseInt(matcher.group(1));
            index = index % 23;

            String reference = letras.substring(index, index + 1);
            correcto = reference.equalsIgnoreCase(letra);

        } else {
            correcto = false;
        }

        return correcto;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + this.id;
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
        final Soci other = (Soci) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public String getNomComplet()
    {
        String nomComplet = this.nom;

        if (this.cognom1 != null) {
            nomComplet += " " + this.cognom1;
        }
        if (this.cognom2 != null) {
            nomComplet += " " + this.cognom2;
        }

        return nomComplet;
    }
}
