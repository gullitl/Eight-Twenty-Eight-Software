package com.cecilsoftwares.reussoftbackend.model;

import com.cecilsoftwares.reussoft.enumarable.DispatchEnum;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class EntreeStock {

    private final int codeEntreeStock;
    private final Produit produit;
    private final Shop shop;
    private final Fournisseur fournisseur;
    private final DispatchEnum dispatchEnum;
    private final BigDecimal prixUSD;
    private final BigDecimal prixFC;
    private final TauxCarte tauxCarte;
    private final int qtdProduit;
    private final Date dateHeure;

    public EntreeStock(EntreeStockBuilder entreeStockBuilder) {
        codeEntreeStock = entreeStockBuilder.codeEntreeStock;
        produit = entreeStockBuilder.produit;
        shop = entreeStockBuilder.shop;
        fournisseur = entreeStockBuilder.fournisseur;
        dispatchEnum = entreeStockBuilder.dispatchEnum;
        prixUSD = entreeStockBuilder.prixUSD;
        prixFC = entreeStockBuilder.prixFC;
        tauxCarte = entreeStockBuilder.tauxCarte;
        qtdProduit = entreeStockBuilder.qtdProduit;
        dateHeure = entreeStockBuilder.dateHeure;
    }

    public int getCodeEntreeStock() {
        return codeEntreeStock;
    }

    public Produit getProduit() {
        return produit;
    }

    public Shop getShop() {
        return shop;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public DispatchEnum getDispatchEnum() {
        return dispatchEnum;
    }

    public BigDecimal getPrixUSD() {
        return prixUSD;
    }

    public BigDecimal getPrixFC() {
        return prixFC;
    }

    public TauxCarte getTauxCarte() {
        return tauxCarte;
    }

    public int getQtdProduit() {
        return qtdProduit;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public static class EntreeStockBuilder {

        private int codeEntreeStock;
        private Produit produit;
        private Shop shop;
        private Fournisseur fournisseur;
        private DispatchEnum dispatchEnum;
        private BigDecimal prixUSD;
        private BigDecimal prixFC;
        private TauxCarte tauxCarte;
        private int qtdProduit;
        private Date dateHeure;

        public EntreeStockBuilder(int codeEntreeStock) {
            this.codeEntreeStock = codeEntreeStock;
        }

        public EntreeStockBuilder produit(Produit produit) {
            this.produit = produit;
            return this;
        }

        public EntreeStockBuilder shop(Shop shop) {
            this.shop = shop;
            return this;
        }

        public EntreeStockBuilder fournisseur(Fournisseur fournisseur) {
            this.fournisseur = fournisseur;
            return this;
        }

        public EntreeStockBuilder dispatchEnum(DispatchEnum dispatchEnum) {
            this.dispatchEnum = dispatchEnum;
            return this;
        }

        public EntreeStockBuilder prixUSD(BigDecimal prixUSD) {
            this.prixUSD = prixUSD;
            return this;
        }

        public EntreeStockBuilder prixFC(BigDecimal prixFC) {
            this.prixFC = prixFC;
            return this;
        }

        public EntreeStockBuilder tauxCarte(TauxCarte tauxCarte) {
            this.tauxCarte = tauxCarte;
            return this;
        }

        public EntreeStockBuilder qtdProduit(int qtdProduit) {
            this.qtdProduit = qtdProduit;
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
