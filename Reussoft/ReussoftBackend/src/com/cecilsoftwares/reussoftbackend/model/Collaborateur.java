package com.cecilsoftwares.reussoftbackend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class Collaborateur {

    private int codeCollaborateur;
    private String preNom;
    private String nom;
    private String postnom;
    private String surnom;
    private String utilizateur;
    private GroupeUtilisateur idGroupeUtilisateur;
    private String motDePasse;
    private Shop shop;

    public Collaborateur(CollaborateurBuilder collaborateurBuilder) {
        codeCollaborateur = collaborateurBuilder.codeCollaborateur;
        preNom = collaborateurBuilder.preNom;
        nom = collaborateurBuilder.nom;
        postnom = collaborateurBuilder.preNom;
        surnom = collaborateurBuilder.surnom;
        utilizateur = collaborateurBuilder.utilizateur;
        idGroupeUtilisateur = collaborateurBuilder.idGroupeUtilisateur;
        motDePasse = collaborateurBuilder.motDePasse;
        shop = collaborateurBuilder.shop;
    }

    public int getCodeCollaborateur() {
        return codeCollaborateur;
    }

    public String getPreNom() {
        return preNom;
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

    public String getUtilizateur() {
        return utilizateur;
    }

    public GroupeUtilisateur getIdGroupeUtilisateur() {
        return idGroupeUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public Shop getShop() {
        return shop;
    }

    public static class CollaborateurBuilder {

        private int codeCollaborateur;
        private String preNom;
        private String nom;
        private String postnom;
        private String surnom;
        private String utilizateur;
        private GroupeUtilisateur idGroupeUtilisateur;
        private String motDePasse;
        private Shop shop;

        public CollaborateurBuilder(int codeCollaborateur) {
            this.codeCollaborateur = codeCollaborateur;
        }

        public CollaborateurBuilder preNom(String preNom) {
            this.preNom = preNom;
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

        public CollaborateurBuilder utilizateur(String utilizateur) {
            this.utilizateur = utilizateur;
            return this;
        }

        public CollaborateurBuilder idGroupeUtilisateur(GroupeUtilisateur idGroupeUtilisateur) {
            this.idGroupeUtilisateur = idGroupeUtilisateur;
            return this;
        }

        public CollaborateurBuilder motDePasse(String motDePasse) {
            this.motDePasse = motDePasse;
            return this;
        }

        public CollaborateurBuilder shop(Shop shop) {
            this.shop = shop;
            return this;
        }

    }

}
