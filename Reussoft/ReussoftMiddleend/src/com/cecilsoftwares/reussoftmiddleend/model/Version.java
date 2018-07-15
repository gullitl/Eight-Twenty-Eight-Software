package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class Version {

    private String id;
    private String description;
    private int xmajeur;
    private int ymineur;
    private int zcorrection;
    private int versionAnterieur;
    private String observation;

    public Version() {

    }

    public Version(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getXmajeur() {
        return xmajeur;
    }

    public void setXmajeur(int xmajeur) {
        this.xmajeur = xmajeur;
    }

    public int getYmineur() {
        return ymineur;
    }

    public void setYmineur(int ymineur) {
        this.ymineur = ymineur;
    }

    public int getZcorrection() {
        return zcorrection;
    }

    public void setZcorrection(int zcorrection) {
        this.zcorrection = zcorrection;
    }

    public int getVersionAnterieur() {
        return versionAnterieur;
    }

    public void setVersionAnterieur(int versionAnterieur) {
        this.versionAnterieur = versionAnterieur;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

}
