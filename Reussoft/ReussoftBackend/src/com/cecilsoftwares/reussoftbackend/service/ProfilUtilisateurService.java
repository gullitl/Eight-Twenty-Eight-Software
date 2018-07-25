package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.ProfilUtilisateurDao;
import static com.cecilsoftwares.reussoftbackend.util.IdGenerator.generateId;
import com.cecilsoftwares.reussoftmiddleend.model.ProfilUtilisateur;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class ProfilUtilisateurService {

    private static ProfilUtilisateurService uniqueInstance;

    public ProfilUtilisateurService() {
    }

    public static synchronized ProfilUtilisateurService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ProfilUtilisateurService();
        }
        return uniqueInstance;
    }

    public List<ProfilUtilisateur> listerTousLesProfilUtilisateurs() throws ClassNotFoundException, SQLException {
        return ProfilUtilisateurDao.getInstance().listerTousLesProfilUtilisateurs();
    }

    public ProfilUtilisateur selectionnerProfilUtilisateurParId(String idProfilUtilisateur) throws ClassNotFoundException, SQLException {
        return ProfilUtilisateurDao.getInstance().selectionnerProfilUtilisateurParId(idProfilUtilisateur);
    }

    public boolean enregistrerProfilUtilisateur(ProfilUtilisateur profilUtilisateur) throws ClassNotFoundException, SQLException, Exception {

        if (profilUtilisateur.getId().isEmpty()) {
            profilUtilisateur.setId(generateId());
            return ProfilUtilisateurDao.getInstance().enregistrerProfilUtilisateur(profilUtilisateur);
        } else {
            return ProfilUtilisateurDao.getInstance().actualiserProfilUtilisateur(profilUtilisateur);
        }

    }

    public boolean exclureProfilUtilisateur(String idProfilUtilisateur) throws ClassNotFoundException, SQLException {
        return ProfilUtilisateurDao.getInstance().exclureProfilUtilisateur(idProfilUtilisateur);
    }
}
