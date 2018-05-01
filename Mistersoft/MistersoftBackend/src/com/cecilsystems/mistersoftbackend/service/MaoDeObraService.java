package com.cecilsystems.mistersoftbackend.service;

import com.cecilsystems.mistersoftbackend.dao.MaoDeObraDao;
import com.cecilsystems.mistersoftbackend.model.MaoDeObra;
import java.sql.SQLException;
import java.util.List;

public class MaoDeObraService {

    private static MaoDeObraService uniqueInstance;

    public MaoDeObraService() {
    }

    public static synchronized MaoDeObraService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MaoDeObraService();
        }
        return uniqueInstance;
    }

    public List<MaoDeObra> listaMaoDeObra() throws ClassNotFoundException, SQLException {
        return MaoDeObraDao.getInstance().lista();
    }

    public MaoDeObra selecionaMaoDeObra(int codigoMaoDeObra)
            throws ClassNotFoundException, SQLException {
        return MaoDeObraDao.getInstance().seleciona(codigoMaoDeObra);
    }

    public int selecionaCodigoMaoDeObraSubsequente() throws ClassNotFoundException, SQLException {
        return MaoDeObraDao.getInstance().selecionaCodigoMaoDeObraSubsequente();
    }

    public boolean salvaMaoDeObra(MaoDeObra maoDeObra, boolean isModoEdicao)
            throws ClassNotFoundException, SQLException {
        return !isModoEdicao
                ? MaoDeObraDao.getInstance().salva(maoDeObra)
                : MaoDeObraDao.getInstance().atualiza(maoDeObra);
    }

    public boolean excluiMaoDeObra(int codigoMaoDeObra) throws ClassNotFoundException, SQLException {
        return MaoDeObraDao.getInstance().remove(codigoMaoDeObra);
    }
}
