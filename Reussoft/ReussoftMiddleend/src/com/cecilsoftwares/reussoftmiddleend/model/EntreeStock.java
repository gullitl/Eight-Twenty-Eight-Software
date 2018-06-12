package com.cecilsoftwares.reussoftmiddleend.model;

import java.util.Date;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class EntreeStock {

    private final int code;
    private final Fournisseur fournisseur;
    private final TauxCarte tauxCarte;
    private final Date dateHeure;
    private final List<ItemEntreeStock> itemsEntreeStock;

    public EntreeStock(EntreeStockBuilder entreeStockBuilder) {
        code = entreeStockBuilder.code;
        fournisseur = entreeStockBuilder.fournisseur;
        tauxCarte = entreeStockBuilder.tauxCarte;
        dateHeure = entreeStockBuilder.dateHeure;
        itemsEntreeStock = entreeStockBuilder.itemsEntreeStock;
    }

    public int getCode() {
        return code;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public TauxCarte getTauxCarte() {
        return tauxCarte;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public List<ItemEntreeStock> getItemsEntreeStock() {
        return itemsEntreeStock;
    }

    public static class EntreeStockBuilder {

        private int code;
        private Fournisseur fournisseur;
        private TauxCarte tauxCarte;
        private Date dateHeure;
        private List<ItemEntreeStock> itemsEntreeStock;

        public EntreeStockBuilder(int code) {
            this.code = code;
        }

        public EntreeStockBuilder fournisseur(Fournisseur fournisseur) {
            this.fournisseur = fournisseur;
            return this;
        }

        public EntreeStockBuilder tauxCarte(TauxCarte tauxCarte) {
            this.tauxCarte = tauxCarte;
            return this;
        }

        public EntreeStockBuilder dateHeure(Date dateHeure) {
            this.dateHeure = dateHeure;
            return this;
        }

        public EntreeStockBuilder itemsEntreeStock(List<ItemEntreeStock> itemsEntreeStock) {
            this.itemsEntreeStock = itemsEntreeStock;
            return this;
        }

        public EntreeStock build() {
            return new EntreeStock(this);
        }

    }

}
