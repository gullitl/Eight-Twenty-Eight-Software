package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class EntreeStock {

    private String id;
    private String numeroEntreeStock;
    private Fournisseur fournisseur;
    private BigDecimal valeurTotalCoutUSD;
    private BigDecimal valeurTotalCoutFC;
    private BigDecimal valeurTauxCarte;
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

    public BigDecimal getValeurTotalCoutUSD() {
        return valeurTotalCoutUSD;
    }

    public void setValeurTotalCoutUSD(BigDecimal valeurTotalCoutUSD) {
        this.valeurTotalCoutUSD = valeurTotalCoutUSD;
    }

    public BigDecimal getValeurTotalCoutFC() {
        return valeurTotalCoutFC;
    }

    public void setValeurTotalCoutFC(BigDecimal valeurTotalCoutFC) {
        this.valeurTotalCoutFC = valeurTotalCoutFC;
    }

    public BigDecimal getValeurTauxCarte() {
        return valeurTauxCarte;
    }

    public void setValeurTauxCarte(BigDecimal valeurTauxCarte) {
        this.valeurTauxCarte = valeurTauxCarte;
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
