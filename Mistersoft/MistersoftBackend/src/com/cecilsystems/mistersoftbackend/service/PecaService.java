package com.cecilsystems.mistersoftbackend.service;

import com.cecilsystems.mistersoftbackend.dao.PecaDao;
import com.cecilsystems.mistersoftbackend.model.Peca;
import java.sql.SQLException;
import java.util.List;

public class PecaService {

    private static PecaService uniqueInstance;

    public PecaService() {
    }

    public static synchronized PecaService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new PecaService();
        }
        return uniqueInstance;
    }

    public Peca selecionaPeca(int codigoPeca)
            throws ClassNotFoundException, SQLException {
        return PecaDao.getInstance().seleciona(codigoPeca);
    }

    public int selecionaCodigoPecaSubsequente() throws ClassNotFoundException, SQLException {
        return PecaDao.getInstance().selecionaCodigoPecaSubsequente();
    }

    public List<Peca> listaPeca() throws ClassNotFoundException, SQLException {
        return PecaDao.getInstance().lista();
    }

}
