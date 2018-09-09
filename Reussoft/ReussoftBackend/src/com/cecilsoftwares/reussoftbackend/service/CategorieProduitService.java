package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.CategorieProduitDao;
import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit;
import com.cecilsoftwares.reussoftmiddleend.util.IdGenerator;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class CategorieProduitService {

    private static CategorieProduitService uniqueInstance;

    public CategorieProduitService() {
    }

    public static synchronized CategorieProduitService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CategorieProduitService();
        }
        return uniqueInstance;
    }

    public List<CategorieProduit> listerTousLesCategorieProduits() throws ClassNotFoundException, SQLException {
        return CategorieProduitDao.getInstance().listerTousLesCategorieProduits();
    }

    public CategorieProduit selectionnerCategorieProduitParId(String idCategorieProduit) throws ClassNotFoundException, SQLException {
        return CategorieProduitDao.getInstance().selectionnerCategorieProduitParId(idCategorieProduit);
    }

    public boolean enregistrerCategorieProduit(CategorieProduit categorieProduit)
            throws ClassNotFoundException, SQLException, Exception {
        if (categorieProduit.getId().isEmpty()) {
            categorieProduit.setId(IdGenerator.generateId());
            return CategorieProduitDao.getInstance().enregistrerCategorieProduit(categorieProduit);
        } else {
            return CategorieProduitDao.getInstance().actualiserCategorieProduit(categorieProduit);
        }
    }

    public boolean exclureCategorieProduit(String idCategorieProduit) throws ClassNotFoundException, SQLException {
        return CategorieProduitDao.getInstance().exclureCategorieProduit(idCategorieProduit);
    }

}
