package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.FournisseurDao;
import com.cecilsoftwares.reussoftmiddleend.model.Fournisseur;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class FournisseurService {

    private static FournisseurService uniqueInstance;

    public FournisseurService() {
    }

    public static synchronized FournisseurService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new FournisseurService();
        }
        return uniqueInstance;
    }

    public Fournisseur selectionnerFournisseurParId(String idFournisseur) throws ClassNotFoundException, SQLException {
        return FournisseurDao.getInstance().selectionnerFournisseurParId(idFournisseur);
    }

    public boolean enregistrerFournisseur(Fournisseur fournisseur) throws ClassNotFoundException, SQLException {
        return FournisseurDao.getInstance().enregistrerFournisseur(fournisseur);
    }

    public boolean actualiserFournisseur(Fournisseur fournisseur) throws ClassNotFoundException, SQLException {
        return FournisseurDao.getInstance().actualiserFournisseur(fournisseur);
    }

    public List<Fournisseur> listerTousLesFournisseurs() throws ClassNotFoundException, SQLException {
        return FournisseurDao.getInstance().listerTousLesFournisseurs();
    }

    public boolean exclureFournisseur(String idFournisseur) throws ClassNotFoundException, SQLException {
        return FournisseurDao.getInstance().exclureFournisseur(idFournisseur);
    }

}
