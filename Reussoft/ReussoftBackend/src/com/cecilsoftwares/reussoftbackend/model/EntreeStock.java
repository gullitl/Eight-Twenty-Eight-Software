package com.cecilsoftwares.reussoftbackend.model;

import com.cecilsoftwares.reussoft.enumarable.DispatchEnum;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class EntreeStock {

    private int codeEntreeStock;
    private Produit idProduit;
    private Shop idShop;
    private Fournisseur idFournisseur;
    private DispatchEnum dispatchEnum;
    private BigDecimal prixUSD;
    private BigDecimal prixFC;
    private TauxCarte idTauxCarte;
    private int qtdProduit;
    private Date dateHeure;

    public EntreeStock(EntreeStockBuilder entreeStockBuilder) {
        codeEntreeStock = entreeStockBuilder.codeEntreeStock;
        idProduit = entreeStockBuilder.idProduit;
        idShop = entreeStockBuilder.idShop;
        idFournisseur = entreeStockBuilder.idFournisseur;
        dispatchEnum = entreeStockBuilder.dispatchEnum;
        prixUSD = entreeStockBuilder.prixUSD;
        prixFC = entreeStockBuilder.prixFC;
        idTauxCarte = entreeStockBuilder.idTauxCarte;
        qtdProduit = entreeStockBuilder.qtdProduit;
        dateHeure = entreeStockBuilder.dateHeure;
    }

    public int getCodeEntreeStock() {
        return codeEntreeStock;
    }

    public Produit getIdProduit() {
        return idProduit;
    }

    public Shop getIdShop() {
        return idShop;
    }

    public Fournisseur getIdFournisseur() {
        return idFournisseur;
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

    public TauxCarte getIdTauxCarte() {
        return idTauxCarte;
    }

    public int getQtdProduit() {
        return qtdProduit;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public static class EntreeStockBuilder {

        private int codeEntreeStock;
        private Produit idProduit;
        private Shop idShop;
        private Fournisseur idFournisseur;
        private DispatchEnum dispatchEnum;
        private BigDecimal prixUSD;
        private BigDecimal prixFC;
        private TauxCarte idTauxCarte;
        private int qtdProduit;
        private Date dateHeure;

        public EntreeStockBuilder(int codeEntreeStock) {
            this.codeEntreeStock = codeEntreeStock;
        }

        public EntreeStockBuilder idProduit(Produit idProduit) {
            this.idProduit = idProduit;
            return this;
        }

        public EntreeStockBuilder idShop(Shop idShop) {
            this.idShop = idShop;
            return this;
        }

        public EntreeStockBuilder idFournisseur(Fournisseur idFournisseur) {
            this.idFournisseur = idFournisseur;
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

        public EntreeStockBuilder idTauxCarte(TauxCarte idTauxCarte) {
            this.idTauxCarte = idTauxCarte;
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

    }

}
