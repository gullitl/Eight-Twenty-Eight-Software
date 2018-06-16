package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class ItemDispatch {

    private final Dispatch dispatch;
    private final Produit produit;
    private final BigDecimal quantiteProduit;

    public ItemDispatch(ItemDispatchBuilder itemDispatchBuilder) {
        dispatch = itemDispatchBuilder.dispatch;
        produit = itemDispatchBuilder.produit;
        quantiteProduit = itemDispatchBuilder.quantiteProduit;
    }

    public Dispatch getDispatch() {
        return dispatch;
    }

    public Produit getProduit() {
        return produit;
    }

    public BigDecimal getQuantiteProduit() {
        return quantiteProduit;
    }

    public static class ItemDispatchBuilder {

        private Dispatch dispatch;
        private Produit produit;
        private BigDecimal quantiteProduit;

        public ItemDispatchBuilder(Dispatch dispatch, Produit produit) {
            this.dispatch = dispatch;
            this.produit = produit;
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
