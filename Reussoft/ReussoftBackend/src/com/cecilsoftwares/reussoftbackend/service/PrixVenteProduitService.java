package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.PrixVenteProduitDao;
import com.cecilsoftwares.reussoftmiddleend.model.PrixVenteProduit;
import com.cecilsoftwares.reussoftmiddleend.model.PrixVenteProduitShop;
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

    public PrixVenteProduit selectionnerPrixVenteProduitParId(String idPrixVenteProduit) throws ClassNotFoundException, SQLException {
        return PrixVenteProduitDao.getInstance().selectionnerPrixVenteProduitParId(idPrixVenteProduit);
    }

    public List<PrixVenteProduit> selectionnerPrixVenteProduitParIdProduit(String idProduit) throws ClassNotFoundException, SQLException {
        return PrixVenteProduitDao.getInstance().selectionnerPrixVenteProduitParIdProduit(idProduit);
    }

    public boolean enregistrerPrixVenteProduit(PrixVenteProduit prixVenteProduit) throws ClassNotFoundException, SQLException {
        return PrixVenteProduitDao.getInstance().enregistrerPrixVenteProduit(prixVenteProduit);
    }

    public boolean enregistrerPrixVenteProduitShop(PrixVenteProduit prixVenteProduit, List<PrixVenteProduitShop> prixVenteProduitShop)
            throws ClassNotFoundException, SQLException {
        return PrixVenteProduitDao.getInstance().enregistrerPrixVenteProduitShop(prixVenteProduit, prixVenteProduitShop);
    }

    public boolean exclurePrixVenteProduit(String idPrixVenteProduit) throws ClassNotFoundException, SQLException {
        return PrixVenteProduitDao.getInstance().exclurePrixVenteProduit(idPrixVenteProduit);
    }

}
