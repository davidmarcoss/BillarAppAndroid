package info.infomila.billar.models;

import java.util.Date;

public class EntradaDetall {

    private int id;
    private int partidaId;
    private int entrada;
    private Date dataEntrada;
    private int sociId;
    private String sociTag;
    private String sociNom;
    private int caramboles;

    public EntradaDetall(int id, int partidaId, int entrada, Date dataEntrada, int sociId, String sociTag, String sociNom, int caramboles) {
        this.id = id;
        this.partidaId = partidaId;
        this.entrada = entrada;
        this.dataEntrada = dataEntrada;
        this.sociId = sociId;
        this.sociTag = sociTag;
        this.sociNom = sociNom;
        this.caramboles = caramboles;
    }

    public EntradaDetall(EntradaDetall entradaDetall) {
        this.id = entradaDetall.id;
        this.partidaId = entradaDetall.partidaId;
        this.entrada = entradaDetall.entrada;
        this.dataEntrada = entradaDetall.dataEntrada;
        this.sociId = entradaDetall.sociId;
        this.sociTag = entradaDetall.sociTag;
        this.sociNom = entradaDetall.sociNom;
        this.caramboles = entradaDetall.caramboles;
    }

    public EntradaDetall(int partidaId, int entrada, int sociId, String sociTag, String sociNom, int caramboles) {
        this.partidaId = partidaId;
        this.entrada = entrada;
        this.sociId = sociId;
        this.sociTag = sociTag;
        this.sociNom = sociNom;
        this.caramboles = caramboles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPartidaId() {
        return partidaId;
    }

    public void setPartidaId(int partidaId) {
        this.partidaId = partidaId;
    }

    public int getEntrada() {
        return entrada;
    }

    public void setEntrada(int entrada) {
        this.entrada = entrada;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEtrada) {
        this.dataEntrada = dataEtrada;
    }

    public int getSociId() {
        return sociId;
    }

    public void setSociId(int sociId) {
        this.sociId = sociId;
    }

    public String getSociTag() {
        return sociTag;
    }

    public void setSociTag(String sociTag) {
        this.sociTag = sociTag;
    }

    public String getSociNom() {
        return sociNom;
    }

    public void setSociNom(String sociNom) {
        this.sociNom = sociNom;
    }

    public int getCaramboles() {
        return caramboles;
    }

    public void setCaramboles(int caramboles) {
        this.caramboles = caramboles;
    }
}
