package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class PrixVenteProduit extends PrixProduit {

    private Shop shop;

    public PrixVenteProduit(String id) {
        super(id);
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
}
