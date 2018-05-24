package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.PrixVenteProduitDao;
import com.cecilsoftwares.reussoftmiddleend.model.PrixVenteProduit;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class PrixVenteProduitService {

    private static PrixVenteProduitService uniqueInstance;

    public PrixVenteProduitService() {
    }

    public static synchronized PrixVenteProduitService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new PrixVenteProduitService();
        }
        return uniqueInstance;
    }

    public List<PrixVenteProduit> listerTousLesPrixVenteProduits() throws ClassNotFoundException, SQLException {
        return PrixVenteProduitDao.getInstance().listerTousLesPrixVenteProduits();
    }

    public PrixVenteProduit selectionnerPrixVenteProduitParCode(int codePrixVenteProduit) throws ClassNotFoundException, SQLException {
        return PrixVenteProduitDao.getInstance().selectionnerPrixVenteProduitParCode(codePrixVenteProduit);
    }

    public boolean enregistrerPrixVenteProduit(PrixVenteProduit prixVenteProduit) throws ClassNotFoundException, SQLException {
        return PrixVenteProduitDao.getInstance().enregistrerPrixVenteProduit(prixVenteProduit);
    }

    public boolean actualiserPrixVenteProduit(PrixVenteProduit prixVenteProduit) throws ClassNotFoundException, SQLException {
        return PrixVenteProduitDao.getInstance().actualiserPrixVenteProduit(prixVenteProduit);
    }

    public int selectionnerCodePrixVenteProduitSubsequent() throws ClassNotFoundException, SQLException {
        return PrixVenteProduitDao.getInstance().selectionnerCodePrixVenteProduitSubsequent();
    }

}
