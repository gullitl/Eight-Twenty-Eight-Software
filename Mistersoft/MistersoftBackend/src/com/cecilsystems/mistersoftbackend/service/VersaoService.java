package com.cecilsystems.mistersoftbackend.service;

import com.cecilsystems.mistersoftbackend.dao.VersaoDao;
import com.cecilsystems.mistersoftbackend.model.Versao;
import java.sql.SQLException;

public class VersaoService {

    private static VersaoService uniqueInstance;

    public VersaoService() {
    }

    public static synchronized VersaoService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new VersaoService();
        }
        return uniqueInstance;
    }

    public Versao selecionaUltimaVersao()
            throws ClassNotFoundException, SQLException {
        return VersaoDao.getInstance().getUltimaVersao();
    }

    public void atualizaEsquema() throws ClassNotFoundException, SQLException {
        VersaoDao.getInstance().atualizaEsquema();
    }

    public boolean temNovaVersao() throws ClassNotFoundException, SQLException {
        return VersaoDao.getInstance().temNovaVersao();
    }

}
