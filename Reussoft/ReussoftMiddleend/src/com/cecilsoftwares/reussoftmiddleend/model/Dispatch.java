package com.cecilsoftwares.reussoftmiddleend.model;

import java.util.Date;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class Dispatch {

    private String id;
    private Shop shop;
    private Date dateHeure;
    private boolean valide;
    private List<ItemDispatch> itemsDispatch;

    public Dispatch() {
    }

    public Dispatch(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Date dateHeure) {
        this.dateHeure = dateHeure;
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public List<ItemDispatch> getItemsDispatch() {
        return itemsDispatch;
    }

    public void setItemsDispatch(List<ItemDispatch> itemsDispatch) {
        this.itemsDispatch = itemsDispatch;
    }

}
