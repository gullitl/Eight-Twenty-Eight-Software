package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.PrixAchatProduitDao;
import com.cecilsoftwares.reussoftmiddleend.model.PrixAchatProduit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import static com.cecilsoftwares.reussoftmiddleend.util.IdGenerator.generateId;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class PrixAchatProduitService {

    private static PrixAchatProduitService uniqueInstance;

    public PrixAchatProduitService() {
    }

    public static synchronized PrixAchatProduitService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new PrixAchatProduitService();
        }
        return uniqueInstance;
    }

    public List<PrixAchatProduit> listerTousLesPrixAchatProduits() throws ClassNotFoundException, SQLException {
        return PrixAchatProduitDao.getInstance().listerTousLesPrixAchatProduits();
    }

    public PrixAchatProduit selectionnerPrixAchatProduitParId(String idPrixAchatProduit) throws ClassNotFoundException, SQLException {
        return PrixAchatProduitDao.getInstance().selectionnerPrixAchatProduitParId(idPrixAchatProduit);
    }

    public String enregistrerPrixAchatProduit(Produit produit) throws ClassNotFoundException, SQLException, Exception {

        String idPrixAchatProduit = generateId();

        if (produit.getPrixAchatProduit().getId().isEmpty()) {
            produit.getPrixAchatProduit().setId(idPrixAchatProduit);
        }

        produit.getPrixAchatProduit().setDateHeure(new Date());

        if (PrixAchatProduitDao.getInstance().enregistrerPrixAchatProduit(produit)) {
            return idPrixAchatProduit;
        } else {
            return "";
        }

    }

    public PrixAchatProduit selectionnerDernierPrixAchatProduit(Produit produit) throws ClassNotFoundException, SQLException {
        return PrixAchatProduitDao.getInstance().selectionnerDernierPrixAchatProduit(produit);
    }

}
