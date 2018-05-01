package com.cecilsystems.mistersoftbackend.service;

import com.cecilsystems.mistersoftbackend.dao.MaoDeObraPecaCompostaDao;
import com.cecilsystems.mistersoftbackend.model.MaoDeObraPecaComposta;
import java.sql.SQLException;
import java.util.List;

public class MaoDeObraPecaCompostaService {

    private static MaoDeObraPecaCompostaService uniqueInstance;

    public MaoDeObraPecaCompostaService() {
    }

    public static synchronized MaoDeObraPecaCompostaService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MaoDeObraPecaCompostaService();
        }
        return uniqueInstance;
    }

    public List<MaoDeObraPecaComposta> listaMaoDeObraPecaComposta(int codigoPecaComposta)
            throws ClassNotFoundException, SQLException {
        return MaoDeObraPecaCompostaDao.getInstance().lista(codigoPecaComposta);
    }

}
