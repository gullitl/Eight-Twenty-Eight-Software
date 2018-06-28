package com.cecilsoftwares.reussoftmiddleend.model;

import java.math.BigDecimal;

/**
 * @author Plamedi L. Lusembo
 */
public class PrixVenteProduit extends PrixProduit {

    private Shop shop;
    private BigDecimal valeurUSD;
    private BigDecimal valeurFC;

    public PrixVenteProduit(int code) {
        super(code);
    }

    public PrixVenteProduit() {
        super();
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

    public BigDecimal getValeurFC() {
        return valeurFC;
    }

    public void setValeurFC(BigDecimal valeurFC) {
        this.valeurFC = valeurFC;
    }

}
