package com.cecilsystems.mistersoftbackend.service;

import com.cecilsystems.mistersoftbackend.dao.UnidadeMedidaDao;
import com.cecilsystems.mistersoftbackend.model.UnidadeMedida;
import java.sql.SQLException;
import java.util.List;

public class UnidadeMedidaService {

    private static UnidadeMedidaService uniqueInstance;

    public UnidadeMedidaService() {
    }

    public static synchronized UnidadeMedidaService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new UnidadeMedidaService();
        }
        return uniqueInstance;
    }

    public List<UnidadeMedida> listaUnidadeMedida() throws ClassNotFoundException, SQLException {
        return UnidadeMedidaDao.getInstance().lista();
    }

    public UnidadeMedida selecionaUnidadeMedida(int codigoUnidadeMedida)
            throws ClassNotFoundException, SQLException {
        return UnidadeMedidaDao.getInstance().seleciona(codigoUnidadeMedida);
    }

    public int selecionaCodigoUnidadeMedidaSubsequente() throws ClassNotFoundException, SQLException {
        return UnidadeMedidaDao.getInstance().selecionaCodigoUnidadeMedidaSubsequente();
    }

    public boolean salvaUnidadeMedida(UnidadeMedida unidadeMedida, boolean isModoEdicao)
            throws ClassNotFoundException, SQLException {
        return !isModoEdicao
                ? UnidadeMedidaDao.getInstance().salva(unidadeMedida)
                : UnidadeMedidaDao.getInstance().atualiza(unidadeMedida);
    }

    public boolean excluiUnidadeMedida(int codigoUnidadeMedida) throws ClassNotFoundException, SQLException {
        return UnidadeMedidaDao.getInstance().remove(codigoUnidadeMedida);
    }

}
