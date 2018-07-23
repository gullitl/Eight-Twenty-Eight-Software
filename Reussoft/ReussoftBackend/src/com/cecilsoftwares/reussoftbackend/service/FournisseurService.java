package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.FournisseurDao;
import com.cecilsoftwares.reussoftbackend.util.IdGenerator;
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

    public boolean enregistrerFournisseur(Fournisseur fournisseur) throws ClassNotFoundException, SQLException, Exception {
        if (fournisseur.getId().isEmpty()) {
            fournisseur.setId(IdGenerator.generateId());
            return FournisseurDao.getInstance().enregistrerFournisseur(fournisseur);
        } else {
            return FournisseurDao.getInstance().actualiserFournisseur(fournisseur);
        }
    }

    public List<Fournisseur> listerTousLesFournisseurs() throws ClassNotFoundException, SQLException {
        return FournisseurDao.getInstance().listerTousLesFournisseurs();
    }

    public boolean exclureFournisseur(String idFournisseur) throws ClassNotFoundException, SQLException {
        return FournisseurDao.getInstance().exclureFournisseur(idFournisseur);
    }

}
