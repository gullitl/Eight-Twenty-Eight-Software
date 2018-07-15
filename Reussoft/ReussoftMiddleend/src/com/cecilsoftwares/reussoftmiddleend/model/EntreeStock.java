package com.cecilsoftwares.reussoftmiddleend.model;

import java.util.Date;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class EntreeStock {

    private String id;
    private String numeroEntreeStock;
    private Fournisseur fournisseur;
    private TauxMonnaie tauxMonnaie;
    private Date dateHeure;
    private List<ItemEntreeStock> itemsEntreeStock;

    public EntreeStock() {

    }

    public EntreeStock(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumeroEntreeStock() {
        return numeroEntreeStock;
    }

    public void setNumeroEntreeStock(String numeroEntreeStock) {
        this.numeroEntreeStock = numeroEntreeStock;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public TauxMonnaie getTauxMonnaie() {
        return tauxMonnaie;
    }

    public void setTauxMonnaie(TauxMonnaie tauxMonnaie) {
        this.tauxMonnaie = tauxMonnaie;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Date dateHeure) {
        this.dateHeure = dateHeure;
    }

    public List<ItemEntreeStock> getItemsEntreeStock() {
        return itemsEntreeStock;
    }

    public void setItemsEntreeStock(List<ItemEntreeStock> itemsEntreeStock) {
        this.itemsEntreeStock = itemsEntreeStock;
    }

}
