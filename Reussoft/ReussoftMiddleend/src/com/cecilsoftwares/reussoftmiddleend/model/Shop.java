package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class Shop {

    private String id;
    private String nom;
    private String adresse;
    private TauxCarte tauxCarte;
    private boolean active;

    private Shop() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public TauxCarte getTauxCarte() {
        return tauxCarte;
    }

    public void setTauxCarte(TauxCarte tauxCarte) {
        this.tauxCarte = tauxCarte;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static class ShopBuilder {

        private final String id;
        private String nom;
        private String adresse;
        private TauxCarte tauxCarte;
        private boolean active;

        public ShopBuilder(String id) {
            this.id = id;
        }

        public ShopBuilder withNom(String nom) {
            this.nom = nom;
            return this;
        }

        public ShopBuilder withAdresse(String adresse) {
            this.adresse = adresse;
            return this;
        }

        public ShopBuilder withTauxCarte(TauxCarte tauxCarte) {
            this.tauxCarte = tauxCarte;
            return this;
        }

        public ShopBuilder withActive(boolean active) {
            this.active = active;
            return this;
        }

        public Shop create() {
            Shop shop = new Shop();
            shop.setId(this.id);
            shop.setNom(this.nom);
            shop.setAdresse(this.adresse);
            shop.setTauxCarte(this.tauxCarte);
            shop.setActive(this.active);

            return shop;
        }

    }

}
