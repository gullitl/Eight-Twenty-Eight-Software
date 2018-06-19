package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class ProfilUtilisateur {

    private final int code;
    private final String description;
    private final String descriptionAbregee;

    public ProfilUtilisateur(ProfilUtilisateurBuilder profilUtilisateurBuilder) {
        code = profilUtilisateurBuilder.codeProfilUtilizateur;
        description = profilUtilisateurBuilder.description;
        descriptionAbregee = profilUtilisateurBuilder.descriptionAbregee;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionAbregee() {
        return descriptionAbregee;
    }

    public static class ProfilUtilisateurBuilder {

        private int codeProfilUtilizateur;
        private String description;
        private String descriptionAbregee;

        public ProfilUtilisateurBuilder(int codeProfilUtilizateur) {
            this.codeProfilUtilizateur = codeProfilUtilizateur;
        }

        public ProfilUtilisateurBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProfilUtilisateurBuilder descriptionAbregee(String descriptionAbregee) {
            this.descriptionAbregee = descriptionAbregee;
            return this;
        }

        public ProfilUtilisateur build() {
            return new ProfilUtilisateur(this);
        }
    }
}
