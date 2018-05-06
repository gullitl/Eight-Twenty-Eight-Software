package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class Reseau {

    private final int code;
    private final String nom;
    private final String nomAbrege;
    private final String observation;

    public Reseau(ReseauBuilder reseauBuilder) {
        code = reseauBuilder.code;
        nom = reseauBuilder.nom;
        nomAbrege = reseauBuilder.nomAbrege;
        observation = reseauBuilder.observation;
    }

    public int getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public String getNomAbrege() {
        return nomAbrege;
    }

    public String getObservation() {
        return observation;
    }

    public static class ReseauBuilder {

        private int code;
        private String nom;
        private String nomAbrege;
        private String observation;

        public ReseauBuilder(int code) {
            this.code = code;
        }

        public ReseauBuilder nom(String nom) {
            this.nom = nom;
            return this;
        }

        public ReseauBuilder nomAbrege(String nomAbrege) {
            this.nomAbrege = nomAbrege;
            return this;
        }

        public ReseauBuilder observation(String observation) {
            this.observation = observation;
            return this;
        }

        public Reseau build() {
            return new Reseau(this);
        }

    }

}
