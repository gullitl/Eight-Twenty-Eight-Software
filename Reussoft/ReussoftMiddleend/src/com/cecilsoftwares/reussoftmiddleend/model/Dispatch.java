package com.cecilsoftwares.reussoftmiddleend.model;

import java.util.Date;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class Dispatch {

    private int code;
    private Shop shopExpediteur;
    private Shop shopDestinataire;
    private Date dateHeure;
    private boolean active;
    private List<ItemDispatch> itemsDispatch;

    public Dispatch() {
    }

    public Dispatch(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Shop getShopExpediteur() {
        return shopExpediteur;
    }

    public void setShopExpediteur(Shop shopExpediteur) {
        this.shopExpediteur = shopExpediteur;
    }

    public Shop getShopDestinataire() {
        return shopDestinataire;
    }

    public void setShopDestinataire(Shop shopDestinataire) {
        this.shopDestinataire = shopDestinataire;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Date dateHeure) {
        this.dateHeure = dateHeure;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<ItemDispatch> getItemsDispatch() {
        return itemsDispatch;
    }

    public void setItemsDispatch(List<ItemDispatch> itemsDispatch) {
        this.itemsDispatch = itemsDispatch;
    }

}
