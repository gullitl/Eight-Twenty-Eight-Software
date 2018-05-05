package com.cecilsoftwares.reussoftmiddleend.model;

import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class Reseau {

    private final int codeReseau;
    private final String nom;
    private final String nomAbrege;
    private final List<Produit> listeProduits;

    public Reseau(ReseauBuilder reseauBuilder) {
        codeReseau = reseauBuilder.codeReseau;
        nom = reseauBuilder.nom;
        nomAbrege = reseauBuilder.nomAbrege;
        listeProduits = reseauBuilder.listeProduits;
    }

    public int getCodeReseau() {
        return codeReseau;
    }

    public String getNom() {
        return nom;
    }

    public String getNomAbrege() {
        return nomAbrege;
    }

    public List<Produit> getListeProduits() {
        return listeProduits;
    }

    public static class ReseauBuilder {

        private int codeReseau;
        private String nom;
        private String nomAbrege;
        private List<Produit> listeProduits;

        public ReseauBuilder(int codeReseau) {
            this.codeReseau = codeReseau;
        }

        public ReseauBuilder nom(String nom) {
            this.nom = nom;
            return this;
        }

        public ReseauBuilder nomAbrege(String nomAbrege) {
            this.nomAbrege = nomAbrege;
            return this;
        }

        public ReseauBuilder listeProduits(List<Produit> listeProduits) {
            this.listeProduits = listeProduits;
            return this;
        }

        public Reseau build() {
            return new Reseau(this);
        }

    }

}
