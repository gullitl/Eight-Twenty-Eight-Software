package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.SessionUtilisateurDao;
import com.cecilsoftwares.reussoftmiddleend.model.SessionUtilisateur;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class SessionUtilisateurService {

    private static SessionUtilisateurService uniqueInstance;

    public SessionUtilisateurService() {
    }

    public static synchronized SessionUtilisateurService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SessionUtilisateurService();
        }
        return uniqueInstance;
    }

    public List<SessionUtilisateur> listerToutesLesSessionsUtilisateur() throws ClassNotFoundException, SQLException {
        return SessionUtilisateurDao.getInstance().listerToutesLesSessionsUtilisateur();
    }

    public SessionUtilisateur selectionnerSessionUtilisateurParCode(int codeSessionUtilisateur) throws ClassNotFoundException, SQLException {
        return SessionUtilisateurDao.getInstance().selectionnerSessionUtilisateurParCode(codeSessionUtilisateur);
    }

    public boolean sauvegarderSessionUtilisateur(SessionUtilisateur sessionUtilisateur) throws ClassNotFoundException, SQLException {
        return SessionUtilisateurDao.getInstance().sauvegarderSessionUtilisateur(sessionUtilisateur);
    }

}
