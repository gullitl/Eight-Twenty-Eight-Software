package com.cecilsystems.mistersoftbackend.service;

import com.cecilsystems.mistersoftbackend.dao.GrupoUsuarioDao;
import com.cecilsystems.mistersoftbackend.model.GrupoUsuario;
import java.sql.SQLException;
import java.util.List;

public class GrupoUsuarioService {

    private static GrupoUsuarioService uniqueInstance;

    public GrupoUsuarioService() {
    }

    public static synchronized GrupoUsuarioService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new GrupoUsuarioService();
        }
        return uniqueInstance;
    }

    public GrupoUsuario selecionaGrupoUsuario(int codigoGrupoUsuario)
            throws ClassNotFoundException, SQLException {
        return GrupoUsuarioDao.getInstance().seleciona(codigoGrupoUsuario);
    }

    public List<GrupoUsuario> listaGrupoUsuario() throws ClassNotFoundException, SQLException {
        return GrupoUsuarioDao.getInstance().lista();
    }

    public int selecionaCodigoGrupoUsuarioSubsequente() throws ClassNotFoundException, SQLException {
        return GrupoUsuarioDao.getInstance().selecionaCodigoGrupoUsuarioSubsequente();
    }

    public boolean salvaGrupoUsuario(GrupoUsuario grupoUsuario, boolean isModoEdicao)
            throws ClassNotFoundException, SQLException {
        return !isModoEdicao
                ? GrupoUsuarioDao.getInstance().salva(grupoUsuario)
                : GrupoUsuarioDao.getInstance().atualiza(grupoUsuario);
    }

    public boolean excluiGrupoUsuario(int codigoGrupoUsuario) throws ClassNotFoundException, SQLException {
        return GrupoUsuarioDao.getInstance().remove(codigoGrupoUsuario);
    }
}
