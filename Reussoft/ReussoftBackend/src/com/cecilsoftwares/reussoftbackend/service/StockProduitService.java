package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.StockProduitDao;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.StockProduit;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

    public List<StockProduit> listerTousLesStockProduit() throws ClassNotFoundException, SQLException {
        return StockProduitDao.getInstance().listerTousLesStockProduit();
    }

    public StockProduit selectionnerStockProduitParIdProduitEIdShop(String idProduit, String idShop)
            throws ClassNotFoundException, SQLException {
        return StockProduitDao.getInstance().selectionnerStockProduitParIdProduitEIdShop(idProduit, idShop);
    }

    public BigDecimal selectionnerQuantiteStockProduitTousLesShopsParIdProduit(String idProduit)
            throws ClassNotFoundException, SQLException {
        return StockProduitDao.getInstance().selectionnerQuantiteStockProduitTousLesShopsParIdProduit(idProduit);
    }

    public boolean entrerStock(Produit produit, Shop shop, BigDecimal quantiteMouvement, Connection connection)
            throws ClassNotFoundException, SQLException {
        return StockProduitDao.getInstance().entrerStock(produit, shop, quantiteMouvement, connection);
    }

    public boolean sortirStock(Produit produit, Shop shop, BigDecimal quantiteMouvement, Connection connection)
            throws ClassNotFoundException, SQLException {
        return StockProduitDao.getInstance().sortirStock(produit, shop, quantiteMouvement, connection);
    }
}
