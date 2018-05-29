package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.ShopDao;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class ShopService {

    private static ShopService uniqueInstance;

    public ShopService() {
    }

    public static synchronized ShopService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ShopService();
        }
        return uniqueInstance;
    }

    public List<Shop> listerTousLesShops() throws ClassNotFoundException, SQLException {
        return ShopDao.getInstance().listerTousLesShops();
    }

    public Shop selectionnerShopParCode(int codeShop) throws ClassNotFoundException, SQLException {
        return ShopDao.getInstance().selectionnerShopParCode(codeShop);
    }

    public boolean enregistrerShop(Shop shop) throws ClassNotFoundException, SQLException {
        return ShopDao.getInstance().enregistrerShop(shop);
    }

    public boolean actualiserShop(Shop shop) throws ClassNotFoundException, SQLException {
        return ShopDao.getInstance().actualiserShop(shop);
    }

    public int selectionnerCodeShopSubsequent() throws ClassNotFoundException, SQLException {
        return ShopDao.getInstance().selectionnerCodeShopSubsequent();
    }

    public boolean exclureShop(int codeShop) throws ClassNotFoundException, SQLException {
        return ShopDao.getInstance().exclureShop(codeShop);
    }

}
