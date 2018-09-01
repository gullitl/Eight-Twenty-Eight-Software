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

    public List<StockProduit> listerTousLesStockProduitAvecDetail() throws ClassNotFoundException, SQLException {
        return StockProduitDao.getInstance().listerTousLesStockProduitAvecDetail();
    }

    public List<StockProduit> listerTousLesStockProduitSansDetail() throws ClassNotFoundException, SQLException {
        return StockProduitDao.getInstance().listerTousLesStockProduitSansDetail();
    }

    public List<StockProduit> listerTotalStockProduit() throws ClassNotFoundException, SQLException {
        return StockProduitDao.getInstance().listerTotalStockProduit();
    }

    public StockProduit selectionnerStockProduitParIdProduitEIdShopAvecDetail(String idProduit, String idShop)
            throws ClassNotFoundException, SQLException {
        return StockProduitDao.getInstance().selectionnerStockProduitParIdProduitEIdShopAvecDetail(idProduit, idShop);
    }

    public StockProduit selectionnerStockProduitParIdProduitEIdShopSansDetail(String idProduit, String idShop)
            throws ClassNotFoundException, SQLException {
        return StockProduitDao.getInstance().selectionnerStockProduitParIdProduitEIdShopSansDetail(idProduit, idShop);
    }

    public StockProduit selectionnerStockProduitTousLesShopsParIdProduitAvecDetail(String idProduit)
            throws ClassNotFoundException, SQLException {
        return StockProduitDao.getInstance().selectionnerStockProduitTousLesShopsParIdProduitAvecDetail(idProduit);
    }

    public StockProduit selectionnerStockProduitTousLesShopsParIdProduitSansDetail(String idProduit)
            throws ClassNotFoundException, SQLException {
        return StockProduitDao.getInstance().selectionnerStockProduitTousLesShopsParIdProduitSansDetail(idProduit);
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
