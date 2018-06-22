package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class Version {

    private int code;
    private String description;
    private int xmajeur;
    private int ymineur;
    private int zcorrection;
    private int versionAnterieur;
    private String observation;

    public Version() {

    }

    public Version(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
