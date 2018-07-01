package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class PrixVenteProduitShop {

    private Shop shop;
    private BigDecimal valeurUSD;

    public PrixVenteProduitShop() {
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public BigDecimal getValeurUSD() {
        return valeurUSD;
    }

    public void setValeurUSD(BigDecimal valeurUSD) {
        this.valeurUSD = valeurUSD;
    }

}
