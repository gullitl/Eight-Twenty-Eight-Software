package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class SortieStock {

    private final int code;
    private final Produit produit;
    private final Shop shop;
    private final Client client;
    private final BigDecimal prixVenteUSD;
    private final BigDecimal prixVenteFC;
    private final TauxCarte tauxCarte;
    private final BigDecimal quantiteProduit;
    private final Date dateHeure;
    private final String observation;

    public SortieStock(SortieStockBuilder sortieStockBuilder) {
        code = sortieStockBuilder.code;
        produit = sortieStockBuilder.produit;
        shop = sortieStockBuilder.shop;
        client = sortieStockBuilder.client;
        prixVenteUSD = sortieStockBuilder.prixVenteUSD;
        prixVenteFC = sortieStockBuilder.prixVenteFC;
        tauxCarte = sortieStockBuilder.tauxCarte;
        quantiteProduit = sortieStockBuilder.quantiteProduit;
        dateHeure = sortieStockBuilder.dateHeure;
        observation = sortieStockBuilder.observation;
    }

    public int getCode() {
        return code;
    }

    public Produit getProduit() {
        return produit;
    }

    public Shop getShop() {
        return shop;
    }

    public Client getClient() {
        return client;
    }

    public BigDecimal getPrixVenteUSD() {
        return prixVenteUSD;
    }

    public BigDecimal getPrixVenteFC() {
        return prixVenteFC;
    }

    public TauxCarte getTauxCarte() {
        return tauxCarte;
    }

    public BigDecimal getQtdProduit() {
        return quantiteProduit;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public String getObservation() {
        return observation;
    }

    public static class SortieStockBuilder {

        private int code;
        private Produit produit;
        private Shop shop;
        private Client client;
        private BigDecimal prixVenteUSD;
        private BigDecimal prixVenteFC;
        private TauxCarte tauxCarte;
        private BigDecimal quantiteProduit;
        private Date dateHeure;
        private String observation;

        public SortieStockBuilder(int code) {
            this.code = code;
        }

        public SortieStockBuilder produit(Produit produit) {
            this.produit = produit;
            return this;
        }

        public SortieStockBuilder shop(Shop shop) {
            this.shop = shop;
            return this;
        }

        public SortieStockBuilder client(Client client) {
            this.client = client;
            return this;
        }

        public SortieStockBuilder prixVenteUSD(BigDecimal prixVenteUSD) {
            this.prixVenteUSD = prixVenteUSD;
            return this;
        }

        public SortieStockBuilder prixVenteFC(BigDecimal prixVenteFC) {
            this.prixVenteFC = prixVenteFC;
            return this;
        }

        public SortieStockBuilder tauxCarte(TauxCarte tauxCarte) {
            this.tauxCarte = tauxCarte;
            return this;
        }

        public SortieStockBuilder quantiteProduit(BigDecimal quantiteProduit) {
            this.quantiteProduit = quantiteProduit;
            return this;
        }

        public SortieStockBuilder dateHeure(Date dateHeure) {
            this.dateHeure = dateHeure;
            return this;
        }

        public SortieStockBuilder observation(String observation) {
            this.observation = observation;
            return this;
        }

        public SortieStock build() {
            return new SortieStock(this);
        }

    }

}
