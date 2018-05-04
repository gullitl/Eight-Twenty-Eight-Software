package com.cecilsoftwares.reussoftbackend.model;

import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class ProfilUtilisateur {

    private final int code;
    private final String description;
    private final String descriptionAbregee;
    private final List<Collaborateur> listeCollaborateurs;

    public ProfilUtilisateur(ProfilUtilisateurBuilder profilUtilisateurBuilder) {
        code = profilUtilisateurBuilder.code;
        description = profilUtilisateurBuilder.description;
        descriptionAbregee = profilUtilisateurBuilder.descriptionAbregee;
        listeCollaborateurs = profilUtilisateurBuilder.listeCollaborateurs;
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

    public List<Collaborateur> getListeCollaborateurs() {
        return listeCollaborateurs;
    }

    public static class ProfilUtilisateurBuilder {

        private int code;
        private String description;
        private String descriptionAbregee;
        private List<Collaborateur> listeCollaborateurs;

        public ProfilUtilisateurBuilder(int code) {
            this.code = code;
        }

        public ProfilUtilisateurBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProfilUtilisateurBuilder descriptionAbregee(String descriptionAbregee) {
            this.descriptionAbregee = descriptionAbregee;
            return this;
        }

        public ProfilUtilisateurBuilder listeCollaborateurs(List<Collaborateur> listeCollaborateurs) {
            this.listeCollaborateurs = listeCollaborateurs;
            return this;
        }

        public ProfilUtilisateur build() {
            return new ProfilUtilisateur(this);
        }
    }
}
