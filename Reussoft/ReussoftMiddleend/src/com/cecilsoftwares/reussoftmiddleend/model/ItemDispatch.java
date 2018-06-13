package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class ItemDispatch {

    private final Dispatch dispatch;
    private final Produit produit;
    private final BigDecimal prixVenteUSD;
    private final BigDecimal prixVenteFC;
    private final BigDecimal quantiteProduit;

    public ItemDispatch(ItemDispatchBuilder itemDispatchBuilder) {
        dispatch = itemDispatchBuilder.dispatch;
        produit = itemDispatchBuilder.produit;
        prixVenteUSD = itemDispatchBuilder.prixVenteUSD;
        prixVenteFC = itemDispatchBuilder.prixVenteFC;
        quantiteProduit = itemDispatchBuilder.quantiteProduit;
    }

    public Dispatch getDispatch() {
        return dispatch;
    }

    public Produit getProduit() {
        return produit;
    }

    public BigDecimal getPrixVenteUSD() {
        return prixVenteUSD;
    }

    public BigDecimal getPrixVenteFC() {
        return prixVenteFC;
    }

    public BigDecimal getQuantiteProduit() {
        return quantiteProduit;
    }

    public class ItemDispatchBuilder {

        private Dispatch dispatch;
        private Produit produit;
        private BigDecimal prixVenteUSD;
        private BigDecimal prixVenteFC;
        private BigDecimal quantiteProduit;

        public ItemDispatchBuilder(Dispatch dispatch, Produit produit) {
            this.dispatch = dispatch;
            this.produit = produit;
        }

        public ItemDispatchBuilder prixVenteUSD(BigDecimal prixVenteUSD) {
            this.prixVenteUSD = prixVenteUSD;
            return this;
        }

        public ItemDispatchBuilder prixVenteFC(BigDecimal prixVenteFC) {
            this.prixVenteFC = prixVenteFC;
            return this;
        }

        public ItemDispatchBuilder quantiteProduit(BigDecimal quantiteProduit) {
            this.quantiteProduit = quantiteProduit;
            return this;
        }

        public ItemDispatch build() {
            return new ItemDispatch(this);
        }

    }

}
