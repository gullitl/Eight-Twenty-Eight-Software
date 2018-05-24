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

    public Utilisateur login(String nomUtilisateur, String motDePasse) throws ClassNotFoundException, SQLException {
        return UtilisateurDao.getInstance().login(nomUtilisateur, motDePasse);
    }

    public List<Utilisateur> listerTousLesUtilisateurs() throws ClassNotFoundException, SQLException {
        return UtilisateurDao.getInstance().listerTousLesUtilisateurs();
    }

    public Utilisateur selectionnerUtilisateurParCode(int codeUtilisateur) throws ClassNotFoundException, SQLException {
        return UtilisateurDao.getInstance().selectionnerUtilisateurParCode(codeUtilisateur);
    }

    public boolean estUtilisateurDejaExistant(Utilisateur utilisateur, boolean modeEdition) throws ClassNotFoundException, SQLException {
        return UtilisateurDao.getInstance().estUtilisateurDejaExistant(utilisateur, modeEdition);
    }

    public boolean enregistrerUtilisateur(Utilisateur utilisateur) throws ClassNotFoundException, SQLException {
        return UtilisateurDao.getInstance().enregistrerUtilisateur(utilisateur);
    }

    public boolean actualiserUtilisateur(Utilisateur utilisateur) throws ClassNotFoundException, SQLException {
        return UtilisateurDao.getInstance().actualiserUtilisateur(utilisateur);
    }

    public int selectionnerCodeUtilisateurSubsequent() throws ClassNotFoundException, SQLException {
        return UtilisateurDao.getInstance().selectionnerCodeUtilisateurSubsequent();
    }

}
