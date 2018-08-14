package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.StockProduitDao;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import java.math.BigDecimal;
import java.sql.SQLException;

/**
 * @author Plamedi L. Lusembo
 */
public class StockProduitService {

    private static StockProduitService uniqueInstance;

    public StockProduitService() {
    }

    public static synchronized StockProduitService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new StockProduitService();
        }
        return uniqueInstance;
    }

    public boolean actualiserEstoque(Produit produit, Shop shop, BigDecimal quantiteStockMouvemente) throws ClassNotFoundException, SQLException {
        return StockProduitDao.getInstance().actualiserEstoque(produit, shop, quantiteStockMouvemente);
    }
}
