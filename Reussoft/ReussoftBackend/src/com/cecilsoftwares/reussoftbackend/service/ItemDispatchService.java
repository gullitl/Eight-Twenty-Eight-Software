package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.ItemDispatchDao;
import com.cecilsoftwares.reussoftmiddleend.model.ItemDispatch;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class ItemDispatchService {

    private static ItemDispatchService uniqueInstance;

    public ItemDispatchService() {
    }

    public static synchronized ItemDispatchService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ItemDispatchService();
        }
        return uniqueInstance;
    }

    public List<ItemDispatch> listerToutesLesItemsDispatch() throws ClassNotFoundException, SQLException {
        return ItemDispatchDao.getInstance().listerTousLesItemsDispatch();
    }

    public List<ItemDispatch> listerItemsDispatchParIdDispatch(String idDispatch) throws ClassNotFoundException, SQLException {
        return ItemDispatchDao.getInstance().listerItemsDispatchParIdDispatch(idDispatch);
    }

    public List<ItemDispatch> listerItemsDispatchParIdProduit(String idProduit) throws ClassNotFoundException, SQLException {
        return ItemDispatchDao.getInstance().listerItemsDispatchParIdProduit(idProduit);
    }

    public ItemDispatch selectionnerItemDispatchParIdDispatchEIdProduit(String idDispatch, String idProduit) throws ClassNotFoundException, SQLException {
        return ItemDispatchDao.getInstance().selectionnerItemDispatchParIdDispatchEIdProduit(idDispatch, idProduit);
    }
}
