package com.cecilsoftwares.reussoftmiddleend.model;

import java.util.Date;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class Dispatch {

    private final int code;
    private final Shop shopExpediteur;
    private final Shop shopDestinataire;
    private final Date dateHeure;
    private final boolean active;
    private final List<ItemDispatch> dispatchs;

    public Dispatch(DispatchBuilder dispatchBuilder) {
        code = dispatchBuilder.code;
        shopExpediteur = dispatchBuilder.shopExpediteur;
        shopDestinataire = dispatchBuilder.shopDestinataire;
        dateHeure = dispatchBuilder.dateHeure;
        active = dispatchBuilder.active;
        dispatchs = dispatchBuilder.dispatchs;
    }

    public int getCode() {
        return code;
    }

    public Shop getShopExpediteur() {
        return shopExpediteur;
    }

    public Shop getShopDestinataire() {
        return shopDestinataire;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public boolean isActive() {
        return active;
    }

    public List<ItemDispatch> getDispatchs() {
        return dispatchs;
    }

    public class DispatchBuilder {

        private int code;
        private Shop shopExpediteur;
        private Shop shopDestinataire;
        private Date dateHeure;
        private boolean active;
        private List<ItemDispatch> dispatchs;

        public DispatchBuilder(int code) {
            this.code = code;
        }

        public DispatchBuilder produit(int code) {
            this.code = code;
            return this;
        }

        public DispatchBuilder shopExpediteur(Shop shopExpediteur) {
            this.shopExpediteur = shopExpediteur;
            return this;
        }

        public DispatchBuilder shopDestinataire(Shop shopDestinataire) {
            this.shopDestinataire = shopDestinataire;
            return this;
        }

        public DispatchBuilder dateHeure(Date dateHeure) {
            this.dateHeure = dateHeure;
            return this;
        }

        public DispatchBuilder active(boolean active) {
            this.active = active;
            return this;
        }

        public DispatchBuilder dispatchs(List<ItemDispatch> dispatch) {
            this.dispatchs = dispatch;
            return this;
        }

        public Dispatch build() {
            return new Dispatch(this);
        }

    }

}
