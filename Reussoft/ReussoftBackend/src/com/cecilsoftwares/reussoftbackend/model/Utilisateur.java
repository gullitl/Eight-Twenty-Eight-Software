package com.cecilsoftwares.reussoftbackend.model;

/**
 * @author plamedi.lusembo
 */
public class Utilisateur {

    private final int code;
    private final ProfilUtilisateur profilUtilisateur;
    private final Collaborateur collaborateur;
    private final String nom;
    private final String motDePasse;

    public Utilisateur(UtilisateurBuilder utilisateurBuilder) {
        code = utilisateurBuilder.code;
        profilUtilisateur = utilisateurBuilder.profilUtilisateur;
        collaborateur = utilisateurBuilder.collaborateur;
        nom = utilisateurBuilder.nom;
        motDePasse = utilisateurBuilder.motDePasse;
    }

    public int getCode() {
        return code;
    }

    public ProfilUtilisateur getProfilUtilisateur() {
        return profilUtilisateur;
    }

    public Collaborateur getCollaborateur() {
        return collaborateur;
    }

    public String getNom() {
        return nom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public static class UtilisateurBuilder {

        private int code;
        private ProfilUtilisateur profilUtilisateur;
        private Collaborateur collaborateur;
        private String nom;
        private String motDePasse;

        public UtilisateurBuilder(int code) {
            this.code = code;
        }

        public UtilisateurBuilder profilUtilisateur(ProfilUtilisateur profilUtilisateur) {
            this.profilUtilisateur = profilUtilisateur;
            return this;
        }

        public UtilisateurBuilder collaborateur(Collaborateur collaborateur) {
            this.collaborateur = collaborateur;
            return this;
        }

        public UtilisateurBuilder nom(String nom) {
            this.nom = nom;
            return this;
        }

        public UtilisateurBuilder motDePasse(String motDePasse) {
            this.motDePasse = motDePasse;
            return this;
        }

        public Utilisateur build() {
            return new Utilisateur(this);
        }

    }

}
