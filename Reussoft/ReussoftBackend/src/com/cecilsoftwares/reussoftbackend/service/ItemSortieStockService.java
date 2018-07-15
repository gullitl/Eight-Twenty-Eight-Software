package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.ItemSortieStockDao;
import com.cecilsoftwares.reussoftmiddleend.model.ItemSortieStock;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class ItemSortieStockService {

    private static ItemSortieStockService uniqueInstance;

    public ItemSortieStockService() {
    }

    public static synchronized ItemSortieStockService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ItemSortieStockService();
        }
        return uniqueInstance;
    }

    public List<ItemSortieStock> listerToutesLesItemsSortieStock() throws ClassNotFoundException, SQLException {
        return ItemSortieStockDao.getInstance().listerTousLesItemsSortieStock();
    }

    public List<ItemSortieStock> listerItemsSortieStockParIdSortieStock(String idSortieStock) throws ClassNotFoundException, SQLException {
        return ItemSortieStockDao.getInstance().listerItemsSortieStockParIdSortieStock(idSortieStock);
    }

    public List<ItemSortieStock> listerItemsSortieStockParIdProduit(String idProduit) throws ClassNotFoundException, SQLException {
        return ItemSortieStockDao.getInstance().listerItemsSortieStockParIdProduit(idProduit);
    }

    public ItemSortieStock selectionnerItemSortieStockParIdSortieStockEIdProduit(String idSortieStock, String idProduit)
            throws ClassNotFoundException, SQLException {
        return ItemSortieStockDao.getInstance().selectionnerItemSortieStockParIdSortieStockEIdProduit(idSortieStock, idProduit);
    }

}
