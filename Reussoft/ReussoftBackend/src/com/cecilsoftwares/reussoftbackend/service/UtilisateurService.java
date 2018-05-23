package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.UtilisateurDao;
import com.cecilsoftwares.reussoftmiddleend.model.Utilisateur;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class UtilisateurService {

    private static UtilisateurService uniqueInstance;

    public UtilisateurService() {
    }

    public static synchronized UtilisateurService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new UtilisateurService();
        }
        return uniqueInstance;
    }

    public boolean enregistrerUtilisateur(Utilisateur utilisateur)
            throws ClassNotFoundException, SQLException {
        return UtilisateurDao.getInstance().enregistrerUtilisateur(utilisateur);
    }

    public List<Utilisateur> listerTousLesUtilisateurs() throws ClassNotFoundException, SQLException {
        return UtilisateurDao.getInstance().listerTousLesUtilisateurs();
    }

    public Utilisateur selectionnerUtilisateurParCode(int codeUtilisateur) throws ClassNotFoundException, SQLException {
        return UtilisateurDao.getInstance().selectionnerUtilisateurParCode(codeUtilisateur);
    }

}
