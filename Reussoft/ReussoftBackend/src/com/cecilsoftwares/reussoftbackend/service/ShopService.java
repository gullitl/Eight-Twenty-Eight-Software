package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.ShopDao;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.Shop.ShopBuilder;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public boolean enregistrerShop(Shop shop) throws ClassNotFoundException, SQLException {
        return ShopDao.getInstance().enregistrerShop(shop);
    }

//    public List<Shop> listerTousLesShops() throws ClassNotFoundException, SQLException {
//        return ShopDao.getInstance().listerTousLesShops();
//    }
//    public Shop selectionnerShop(int codeShop) throws ClassNotFoundException, SQLException {
//        return ShopDao.getInstance().selectionnerShop(codeShop);
//    }
    public Shop selectionnerShop(int codeShop) throws ClassNotFoundException, SQLException {
        return new ShopBuilder(1)
                .nom("Teste 1")
                .adresse("Teste aveenue#456#jhgfsjk#hbsdjkbjk#kljsdflgnl#kjflkjljlj")
                .active(true).build();
    }

    public List<Shop> listerTousLesShops() throws ClassNotFoundException, SQLException {

        List<Shop> shops = new ArrayList();
        shops.add(new ShopBuilder(1)
                .nom("Teste 1")
                .adresse("Teste aveenue#456#jhgfsjk#hbsdjkbjk#kljsdflgnl#kjflkjljlj")
                .active(true)
                .build());
        shops.add(new ShopBuilder(2)
                .nom("Teste 2")
                .adresse("Teste aveenue#456#jhgfsjk#hbsdjkbjk#kljsdflgnl#kjflkjljlj")
                .active(true).build());
        shops.add(new ShopBuilder(3)
                .nom("Teste 3")
                .adresse("Teste aveenue#456#jhgfsjk#hbsdjkbjk#kljsdflgnl#kjflkjljlj")
                .active(true).build());
        shops.add(new ShopBuilder(4)
                .nom("Teste 4")
                .adresse("Teste aveenue#456#jhgfsjk#hbsdjkbjk#kljsdflgnl#kjflkjljlj")
                .active(true).build());
        shops.add(new ShopBuilder(5)
                .nom("Teste 5")
                .adresse("Teste aveenue#456#jhgfsjk#hbsdjkbjk#kljsdflgnl#kjflkjljlj")
                .active(true).build());
        shops.add(new ShopBuilder(6)
                .nom("Teste 6")
                .adresse("Teste aveenue#456#jhgfsjk#hbsdjkbjk#kljsdflgnl#kjflkjljlj")
                .active(true).build());

        return shops;
    }

}
