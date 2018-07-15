package com.cecilsoftwares.reussoftmiddleend.model;

import java.util.Date;

/**
 * @author Plamedi L. Lusembo
 */
public class SessionUtilisateur {

    private String id;
    private Collaborateur collaborateur;
    private boolean actionEntree;
    private Date dateHeure;

    public SessionUtilisateur() {

    }

    public SessionUtilisateur(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Collaborateur getCollaborateur() {
        return collaborateur;
    }

    public void setCollaborateur(Collaborateur collaborateur) {
        this.collaborateur = collaborateur;
    }

    public boolean isActionEntree() {
        return actionEntree;
    }

    public void setActionEntree(boolean actionEntree) {
        this.actionEntree = actionEntree;
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Date dateHeure) {
        this.dateHeure = dateHeure;
    }

}
