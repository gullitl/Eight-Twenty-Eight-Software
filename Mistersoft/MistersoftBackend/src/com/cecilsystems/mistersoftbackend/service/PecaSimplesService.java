package com.cecilsystems.mistersoftbackend.service;

import com.cecilsystems.mistersoftbackend.dao.PecaSimplesDao;
import com.cecilsystems.mistersoftbackend.model.PecaSimples;
import java.sql.SQLException;
import java.util.List;

public class PecaSimplesService {

    private static PecaSimplesService uniqueInstance;

    public PecaSimplesService() {
    }

    public static synchronized PecaSimplesService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new PecaSimplesService();
        }
        return uniqueInstance;
    }

    public List<PecaSimples> listaPecaSimples() throws ClassNotFoundException, SQLException {
        return PecaSimplesDao.getInstance().lista();
    }

    public PecaSimples selecionaPecaSimples(int codigoPecaSimples)
            throws ClassNotFoundException, SQLException {
        return PecaSimplesDao.getInstance().seleciona(codigoPecaSimples);
    }

    public boolean salvaPecaSimples(PecaSimples pecaSimples, boolean isModoEdicao)
            throws ClassNotFoundException, SQLException {
        return !isModoEdicao
                ? PecaSimplesDao.getInstance().salva(pecaSimples)
                : PecaSimplesDao.getInstance().atualiza(pecaSimples);
    }

    public boolean excluiPecaSimples(int codigoPecaSimples) throws ClassNotFoundException, SQLException {
        return PecaSimplesDao.getInstance().remove(codigoPecaSimples);
    }

}
