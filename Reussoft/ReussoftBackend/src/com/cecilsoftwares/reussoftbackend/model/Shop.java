package com.cecilsoftwares.reussoftbackend.model;

import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class Shop {

    private int codeShop;
    private String nom;
    private String adresse;
    private List<Collaborateur> listeCollaborateurs;

    public Shop(ShopBuilder shopBuilder) {
        codeShop = shopBuilder.codeShop;
        nom = shopBuilder.nom;
        adresse = shopBuilder.adresse;
        listeCollaborateurs = shopBuilder.listeCollaborateurs;
    }

    public int getCodeShop() {
        return codeShop;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public List<Collaborateur> getListeCollaborateurs() {
        return listeCollaborateurs;
    }

    public static class ShopBuilder {

        private int codeShop;
        private String nom;
        private String adresse;
        private List<Collaborateur> listeCollaborateurs;

        public ShopBuilder(int codeShop) {
            this.codeShop = codeShop;
        }

        public ShopBuilder nom(String nom) {
            this.nom = nom;
            return this;
        }

        public ShopBuilder adresse(String adresse) {
            this.adresse = adresse;
            return this;
        }

        public ShopBuilder listeCollaborateurs(List<Collaborateur> listeCollaborateurs) {
            this.listeCollaborateurs = listeCollaborateurs;
            return this;
        }

        public Shop build() {
            return new Shop(this);
        }

    }

}
