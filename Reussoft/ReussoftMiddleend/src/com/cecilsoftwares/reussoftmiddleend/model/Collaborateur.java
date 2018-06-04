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
    private final ProfilUtilisateur profilUtilisateur;
    private final boolean active;
    private final String nomUtilisateur;
    private final String motDePasse;

    public Collaborateur(CollaborateurBuilder collaborateurBuilder) {
        code = collaborateurBuilder.code;
        prenom = collaborateurBuilder.prenom;
        nom = collaborateurBuilder.nom;
        postnom = collaborateurBuilder.postnom;
        surnom = collaborateurBuilder.surnom;
        profilUtilisateur = collaborateurBuilder.profilUtilisateur;
        shop = collaborateurBuilder.shop;
        active = collaborateurBuilder.active;
        nomUtilisateur = collaborateurBuilder.nomUtilisateur;
        motDePasse = collaborateurBuilder.motDePasse;
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

    public ProfilUtilisateur getProfilUtilisateur() {
        return profilUtilisateur;
    }

    public Shop getShop() {
        return shop;
    }

    public boolean isActive() {
        return active;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public static class CollaborateurBuilder {

        private int code;
        private String prenom;
        private String nom;
        private String postnom;
        private String surnom;
        private ProfilUtilisateur profilUtilisateur;
        private Shop shop;
        private boolean active;
        private String nomUtilisateur;
        private String motDePasse;

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

        public CollaborateurBuilder profilUtilisateur(ProfilUtilisateur profilUtilisateur) {
            this.profilUtilisateur = profilUtilisateur;
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

        public CollaborateurBuilder nomUtilisateur(String nomUtilisateur) {
            this.nomUtilisateur = nomUtilisateur;
            return this;
        }

        public CollaborateurBuilder motDePasse(String motDePasse) {
            this.motDePasse = motDePasse;
            return this;
        }

        public Collaborateur build() {
            return new Collaborateur(this);
        }
    }
}
