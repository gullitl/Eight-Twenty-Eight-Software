package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.CollaborateurDao;
import com.cecilsoftwares.reussoftbackend.dao.SessionUtilisateurDao;
import com.cecilsoftwares.reussoftmiddleend.ks.SessionUtilisateurKS;
import com.cecilsoftwares.reussoftmiddleend.model.Collaborateur;
import com.cecilsoftwares.reussoftmiddleend.model.SessionUtilisateur;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class CollaborateurService {

    private static CollaborateurService uniqueInstance;

    public CollaborateurService() {
    }

    public static synchronized CollaborateurService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CollaborateurService();
        }
        return uniqueInstance;
    }

    public SessionUtilisateur login(Shop shopUtilisateur, String nomUtilisateur, String motDePasse) throws ClassNotFoundException, SQLException {

        Collaborateur collaborateur = CollaborateurDao.getInstance().login(shopUtilisateur, nomUtilisateur, motDePasse);

        if (collaborateur != null) {
            SessionUtilisateur sessionUtilisateur = new SessionUtilisateur();
            sessionUtilisateur.setCode(0);
            sessionUtilisateur.setCollaborateur(collaborateur);
            sessionUtilisateur.setAction("ENTRÉE");
            sessionUtilisateur.setDateHeure(new Date());

            SessionUtilisateurKS.getInstance().setSessionUtilisateur(sessionUtilisateur);

            //Mettre dans une thread
            SessionUtilisateurDao.getInstance().sauvegarderSessionUtilisateur(sessionUtilisateur);

            return sessionUtilisateur;
        }

        return null;
    }

    public List<Collaborateur> listerTousLesCollaborateurs() throws ClassNotFoundException, SQLException {
        return CollaborateurDao.getInstance().listerTousLesCollaborateurs();
    }

    public Collaborateur selectionnerCollaborateurParCode(int codeCollaborateur) throws ClassNotFoundException, SQLException {
        return CollaborateurDao.getInstance().selectionnerCollaborateurParCode(codeCollaborateur);
    }

    public boolean enregistrerCollaborateur(Collaborateur collaborateur) throws ClassNotFoundException, SQLException {
        return CollaborateurDao.getInstance().enregistrerCollaborateur(collaborateur);
    }

    public boolean exclureCollaborateur(int codeCollaborateur) throws ClassNotFoundException, SQLException {
        return CollaborateurDao.getInstance().exclureCollaborateur(codeCollaborateur);
    }

}
