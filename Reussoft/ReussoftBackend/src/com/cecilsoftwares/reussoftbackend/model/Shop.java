package com.cecilsoftwares.reussoftbackend.model;

import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class Shop {

    private final int code;
    private final String nom;
    private final String adresse;
    private final List<Collaborateur> listeCollaborateurs;

    public Shop(ShopBuilder shopBuilder) {
        code = shopBuilder.code;
        nom = shopBuilder.nom;
        adresse = shopBuilder.adresse;
        listeCollaborateurs = shopBuilder.listeCollaborateurs;
    }

    public int getCodeShop() {
        return code;
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

        private int code;
        private String nom;
        private String adresse;
        private List<Collaborateur> listeCollaborateurs;

        public ShopBuilder(int code) {
            this.code = code;
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
