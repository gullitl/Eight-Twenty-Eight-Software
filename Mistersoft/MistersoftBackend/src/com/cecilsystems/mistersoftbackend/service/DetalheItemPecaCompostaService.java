package com.cecilsystems.mistersoftbackend.service;

import com.cecilsystems.mistersoftbackend.dao.DetalheItemPecaCompostaDao;
import com.cecilsystems.mistersoftbackend.model.DetalheItemPecaComposta;
import java.sql.SQLException;
import java.util.List;

public class DetalheItemPecaCompostaService {

    private static DetalheItemPecaCompostaService uniqueInstance;

    public DetalheItemPecaCompostaService() {
    }

    public static synchronized DetalheItemPecaCompostaService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new DetalheItemPecaCompostaService();
        }
        return uniqueInstance;
    }

    public List<DetalheItemPecaComposta> listaDetalheItemPecaComposta(int codigoPecaComposta)
            throws ClassNotFoundException, SQLException {
        return DetalheItemPecaCompostaDao.getInstance().lista(codigoPecaComposta);
    }

}
