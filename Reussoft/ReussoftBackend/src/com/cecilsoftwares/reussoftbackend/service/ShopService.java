package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.ShopDao;
import static com.cecilsoftwares.reussoftbackend.util.IdGenerator.generateId;
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

    public Shop selectionnerShopParId(String idShop) throws ClassNotFoundException, SQLException {
        return ShopDao.getInstance().selectionnerShopParId(idShop);
    }

    public boolean enregistrerShop(Shop shop) throws ClassNotFoundException, SQLException, Exception {
        if (shop.getId().isEmpty()) {
            shop.setId(generateId());
            return ShopDao.getInstance().enregistrerShop(shop);
        } else {
            return ShopDao.getInstance().actualiserShop(shop);
        }
    }

    public boolean exclureShop(String idShop) throws ClassNotFoundException, SQLException {
        return ShopDao.getInstance().exclureShop(idShop);
    }

}
