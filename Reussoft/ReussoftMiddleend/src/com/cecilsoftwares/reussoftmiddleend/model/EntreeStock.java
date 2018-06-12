package com.cecilsoftwares.reussoftmiddleend.model;

import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class EntreeStock {

    private final int code;
    private final Fournisseur fournisseur;
    private final TauxCarte tauxCarte;
    private final Date dateHeure;

    public EntreeStock(EntreeStockBuilder mouvementStockBuilder) {
        code = mouvementStockBuilder.code;
        fournisseur = mouvementStockBuilder.fournisseur;
        tauxCarte = mouvementStockBuilder.tauxCarte;
        dateHeure = mouvementStockBuilder.dateHeure;
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

    public static class EntreeStockBuilder {

        private int code;
        private Fournisseur fournisseur;
        private TauxCarte tauxCarte;
        private Date dateHeure;

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

        public EntreeStock build() {
            return new EntreeStock(this);
        }

    }

}
