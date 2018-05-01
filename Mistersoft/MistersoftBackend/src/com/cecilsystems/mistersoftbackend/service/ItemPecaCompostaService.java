package com.cecilsystems.mistersoftbackend.service;

import com.cecilsystems.mistersoftbackend.dao.ItemPecaCompostaDao;
import com.cecilsystems.mistersoftbackend.model.ItemPecaComposta;
import java.sql.SQLException;
import java.util.List;

public class ItemPecaCompostaService {

    private static ItemPecaCompostaService uniqueInstance;

    public ItemPecaCompostaService() {
    }

    public static synchronized ItemPecaCompostaService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ItemPecaCompostaService();
        }
        return uniqueInstance;
    }

    public List<ItemPecaComposta> listaItemPecaComposta(int codigoPecaComposta)
            throws ClassNotFoundException, SQLException {
        return ItemPecaCompostaDao.getInstance().lista(codigoPecaComposta);
    }

}
