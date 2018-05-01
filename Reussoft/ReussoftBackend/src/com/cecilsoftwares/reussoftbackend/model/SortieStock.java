package com.cecilsoftwares.reussoftbackend.model;

import com.cecilsoftwares.reussoft.enumarable.DispatchEnum;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class SortieStock {

    private int codeSortieStock;
    private Produit idProduit;
    private Shop idShop;
    private Client idClient;
    private DispatchEnum dispatchEnum;
    private BigDecimal prixUSD;
    private BigDecimal prixFC;
    private TauxCarte idTauxCarte;
    private int qtdProduit;
    private Date dateHeure;

    public SortieStock(SortieStockBuilder sortieStockBuilder) {
        codeSortieStock = sortieStockBuilder.codeSortieStock;
        idProduit = sortieStockBuilder.idProduit;
        idShop = sortieStockBuilder.idShop;
        idClient = sortieStockBuilder.idClient;
        dispatchEnum = sortieStockBuilder.dispatchEnum;
        prixUSD = sortieStockBuilder.prixUSD;
        prixFC = sortieStockBuilder.prixFC;
        idTauxCarte = sortieStockBuilder.idTauxCarte;
        qtdProduit = sortieStockBuilder.qtdProduit;
        dateHeure = sortieStockBuilder.dateHeure;
    }

    public int getCodeSortieStock() {
        return codeSortieStock;
    }

    public Produit getIdProduit() {
        return idProduit;
    }

    public Shop getIdShop() {
        return idShop;
    }

    public Client getIdClient() {
        return idClient;
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

    public static class SortieStockBuilder {

        private int codeSortieStock;
        private Produit idProduit;
        private Shop idShop;
        private Client idClient;
        private DispatchEnum dispatchEnum;
        private BigDecimal prixUSD;
        private BigDecimal prixFC;
        private TauxCarte idTauxCarte;
        private int qtdProduit;
        private Date dateHeure;

        public SortieStockBuilder(int codeSortieStock) {
            this.codeSortieStock = codeSortieStock;
        }

        public SortieStockBuilder idProduit(Produit idProduit) {
            this.idProduit = idProduit;
            return this;
        }

        public SortieStockBuilder idShop(Shop idShop) {
            this.idShop = idShop;
            return this;
        }

        public SortieStockBuilder idClient(Client idClient) {
            this.idClient = idClient;
            return this;
        }

        public SortieStockBuilder dispatchEnum(DispatchEnum dispatchEnum) {
            this.dispatchEnum = dispatchEnum;
            return this;
        }

        public SortieStockBuilder prixUSD(BigDecimal prixUSD) {
            this.prixUSD = prixUSD;
            return this;
        }

        public SortieStockBuilder prixFC(BigDecimal prixFC) {
            this.prixFC = prixFC;
            return this;
        }

        public SortieStockBuilder idTauxCarte(TauxCarte idTauxCarte) {
            this.idTauxCarte = idTauxCarte;
            return this;
        }

        public SortieStockBuilder qtdProduit(int qtdProduit) {
            this.qtdProduit = qtdProduit;
            return this;
        }

        public SortieStockBuilder dateHeure(Date dateHeure) {
            this.dateHeure = dateHeure;
            return this;
        }

    }

}
