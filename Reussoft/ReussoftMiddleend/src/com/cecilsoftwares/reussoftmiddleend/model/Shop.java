package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class Shop {

    private String id;
    private String nom;
    private String adresse;
    private TauxCarte tauxCarte;
    private boolean active;

    public Shop() {

    }

    public Shop(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public TauxCarte getTauxCarte() {
        return tauxCarte;
    }

    public void setTauxCarte(TauxCarte tauxCarte) {
        this.tauxCarte = tauxCarte;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
