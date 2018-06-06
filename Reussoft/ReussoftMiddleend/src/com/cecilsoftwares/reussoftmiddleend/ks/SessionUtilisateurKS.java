package com.cecilsoftwares.reussoftmiddleend.ks;

import com.cecilsoftwares.reussoftmiddleend.model.SessionUtilisateur;

/**
 * @author Plamedi L. Lusembo
 */
public class SessionUtilisateurKS {

    private SessionUtilisateur sessionUtilisateur;
    private static SessionUtilisateurKS uniqueInstance;

    public SessionUtilisateurKS() {
    }

    public static synchronized SessionUtilisateurKS getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SessionUtilisateurKS();
        }
        return uniqueInstance;
    }

    public SessionUtilisateur getSessionUtilisateur() {
        return sessionUtilisateur;
    }

    public void setSessionUtilisateur(SessionUtilisateur sessionUtilisateur) {
        this.sessionUtilisateur = sessionUtilisateur;
    }

}
