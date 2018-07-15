package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class TauxCarte extends Taux {

    private Shop shop;

    public TauxCarte() {
        super();
    }

    public TauxCarte(String id) {
        super(id);
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

}
