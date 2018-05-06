package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class Fournisseur {

    private final int codeFournisseur;
    private final String entreprise;
    private final String responsable;
    private final String observation;

    public Fournisseur(FournisseurBuilder fournisseurBuilder) {
        codeFournisseur = fournisseurBuilder.codeFournisseur;
        entreprise = fournisseurBuilder.entreprise;
        responsable = fournisseurBuilder.responsable;
        observation = fournisseurBuilder.observation;
    }

    public int getCodeFournisseur() {
        return codeFournisseur;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public String getResponsable() {
        return responsable;
    }

    public String observation() {
        return observation;
    }

    public static class FournisseurBuilder {

        private int codeFournisseur;
        private String entreprise;
        private String responsable;
        private String observation;

        public FournisseurBuilder(int codeFournisseur) {
            this.codeFournisseur = codeFournisseur;
        }

        public FournisseurBuilder entreprise(String entreprise) {
            this.entreprise = entreprise;
            return this;
        }

        public FournisseurBuilder responsable(String responsable) {
            this.responsable = responsable;
            return this;
        }

        public FournisseurBuilder observartion(String observation) {
            this.observation = observation;
            return this;
        }

        public Fournisseur build() {
            return new Fournisseur(this);
        }

    }

}
