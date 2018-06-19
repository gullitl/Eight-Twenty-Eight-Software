package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class Client {

    private final int code;
    private final String nom;
    private final String entreprise;
    private final String telephone;
    private final Shop shop;

    public Client(ClientBuilder clientBuilder) {
        code = clientBuilder.code;
        nom = clientBuilder.nom;
        entreprise = clientBuilder.entreprise;
        telephone = clientBuilder.telephone;
        shop = clientBuilder.shop;
    }

    public int getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public String getTelephone() {
        return telephone;
    }

    public Shop getShop() {
        return shop;
    }

    public static class ClientBuilder {

        private int code;
        private String nom;
        private String entreprise;
        private String telephone;
        private Shop shop;

        public ClientBuilder(int code) {
            this.code = code;
        }

        public ClientBuilder nom(String nom) {
            this.nom = nom;
            return this;
        }

        public ClientBuilder entreprise(String entreprise) {
            this.entreprise = entreprise;
            return this;
        }

        public ClientBuilder telephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        public ClientBuilder shop(Shop shop) {
            this.shop = shop;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}
