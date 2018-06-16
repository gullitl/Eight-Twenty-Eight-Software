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

    public List<ItemSortieStock> listerItemsSortieStockParCodeSortieStock(int codeSortieStock) throws ClassNotFoundException, SQLException {
        return ItemSortieStockDao.getInstance().listerItemsSortieStockParCodeSortieStock(codeSortieStock);
    }

    public List<ItemSortieStock> listerItemsSortieStockParCodeProduit(int codeProduit) throws ClassNotFoundException, SQLException {
        return ItemSortieStockDao.getInstance().listerItemsSortieStockParCodeProduit(codeProduit);
    }

    public ItemSortieStock selectionnerItemSortieStockParCodeSortieStockECodeProduit(int codeSortieStock, int codeProduit) throws ClassNotFoundException, SQLException {
        return ItemSortieStockDao.getInstance().selectionnerItemSortieStockParCodeSortieStockECodeProduit(codeSortieStock, codeProduit);
    }

    public boolean enregistrerItemSortieStock(ItemSortieStock itemSortieStock) throws ClassNotFoundException, SQLException {
        return ItemSortieStockDao.getInstance().enregistrerItemSortieStock(itemSortieStock);
    }
}
