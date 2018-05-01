package com.cecilsoftwares.reussoftbackend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class Fournisseur {

    private int codeFournisseur;
    private String entreprise;
    private String responsable;

    public Fournisseur(FournisseurBuilder fournisseurBuilder) {
        codeFournisseur = fournisseurBuilder.codeFournisseur;
        entreprise = fournisseurBuilder.entreprise;
        responsable = fournisseurBuilder.responsable;
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

    public static class FournisseurBuilder {

        private int codeFournisseur;
        private String entreprise;
        private String responsable;

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

        public Fournisseur build() {
            return new Fournisseur(this);
        }

    }

}
