package com.cecilsoftwares.reussoftmiddleend.model;

import java.util.Date;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class EntreeStock {

    private int code;
    private Fournisseur fournisseur;
    private TauxMonnaie tauxMonnaie;
    private Date dateHeure;
    private List<ItemEntreeStock> itemsEntreeStock;

    public EntreeStock() {

    }

    public EntreeStock(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
