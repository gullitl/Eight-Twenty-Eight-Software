package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.ProduitDao;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import static com.cecilsoftwares.reussoftmiddleend.util.IdGenerator.generateId;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class ProduitService {

    private static ProduitService uniqueInstance;

    public ProduitService() {
    }

    public static synchronized ProduitService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ProduitService();
        }
        return uniqueInstance;
    }

    public List<Produit> listerTousLesProduits() throws ClassNotFoundException, SQLException {
        return ProduitDao.getInstance().listerTousLesProduits();
    }

    public Produit selectionnerProduitParId(String idProduit) throws ClassNotFoundException, SQLException {
        return ProduitDao.getInstance().selectionnerProduitParId(idProduit);
    }

    public boolean enregistrerProduit(Produit produit) throws ClassNotFoundException, SQLException, Exception {
        if (produit.getId().isEmpty()) {
            produit.setId(generateId());
            produit.getPrixAchatProduit().setId(generateId());

            return ProduitDao.getInstance().enregistrerProduit(produit);
        } else {

            if (produit.getPrixAchatProduit() != null) {
                produit.getPrixAchatProduit().setId(generateId());
            }
            return ProduitDao.getInstance().actualiserProduit(produit);
        }

    }

    public boolean exclureProduit(String idProduit) throws ClassNotFoundException, SQLException {
        return ProduitDao.getInstance().exclureProduit(idProduit);
    }

}
