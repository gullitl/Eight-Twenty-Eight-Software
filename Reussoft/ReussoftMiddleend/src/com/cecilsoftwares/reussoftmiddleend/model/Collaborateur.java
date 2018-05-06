package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class Collaborateur {

    private final int code;
    private final String prenom;
    private final String nom;
    private final String postnom;
    private final String surnom;
    private final Shop shop;
    private final Utilisateur utilisateur;
    private final boolean active;
    private final String observation;

    public Collaborateur(CollaborateurBuilder collaborateurBuilder) {
        code = collaborateurBuilder.code;
        prenom = collaborateurBuilder.prenom;
        nom = collaborateurBuilder.nom;
        postnom = collaborateurBuilder.prenom;
        surnom = collaborateurBuilder.surnom;
        utilisateur = collaborateurBuilder.utilisateur;
        shop = collaborateurBuilder.shop;
        active = collaborateurBuilder.active;
        observation = collaborateurBuilder.observation;
    }

    public int getCode() {
        return code;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getPostnom() {
        return postnom;
    }

    public String getSurnom() {
        return surnom;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Shop getShop() {
        return shop;
    }

    public boolean isActive() {
        return active;
    }

    public String getObservation() {
        return observation;
    }

    public static class CollaborateurBuilder {

        private int code;
        private String prenom;
        private String nom;
        private String postnom;
        private String surnom;
        private Utilisateur utilisateur;
        private Shop shop;
        private boolean active;
        private String observation;

        public CollaborateurBuilder(int code) {
            this.code = code;
        }

        public CollaborateurBuilder prenom(String prenom) {
            this.prenom = prenom;
            return this;
        }

        public CollaborateurBuilder nom(String nom) {
            this.nom = nom;
            return this;
        }

        public CollaborateurBuilder postnom(String postnom) {
            this.postnom = postnom;
            return this;
        }

        public CollaborateurBuilder surnom(String surnom) {
            this.surnom = surnom;
            return this;
        }

        public CollaborateurBuilder utilisateur(Utilisateur utilisateur) {
            this.utilisateur = utilisateur;
            return this;
        }

        public CollaborateurBuilder shop(Shop shop) {
            this.shop = shop;
            return this;
        }

        public CollaborateurBuilder active(boolean active) {
            this.active = active;
            return this;
        }

        public void setObservation(String observation) {
            this.observation = observation;
        }

        public Collaborateur build() {
            return new Collaborateur(this);
        }
    }
}
