package com.cecilsystems.mistersoftbackend.service;

import com.cecilsystems.mistersoftbackend.dao.UsuarioDao;
import com.cecilsystems.mistersoftbackend.model.Usuario;
import java.sql.SQLException;
import java.util.List;

public class UsuarioService {

    private static UsuarioService uniqueInstance;

    public UsuarioService() {
    }

    public static synchronized UsuarioService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new UsuarioService();
        }
        return uniqueInstance;
    }

    public List<Usuario> listaUsuario() throws ClassNotFoundException, SQLException {
        return UsuarioDao.getInstance().lista();
    }

    public Usuario selecionaUsuario(int codigoUsuario)
            throws ClassNotFoundException, SQLException {
        return UsuarioDao.getInstance().seleciona(codigoUsuario);
    }

    public int selecionaCodigoUsuarioSubsequente() throws ClassNotFoundException, SQLException {
        return UsuarioDao.getInstance().selecionaCodigoUsuarioSubsequente();
    }

    public boolean isEmailJaUtilizado(Usuario usuario, boolean isModoEdicao)
            throws ClassNotFoundException, SQLException {
        return UsuarioDao.getInstance().isEmailJaUtilizado(usuario, isModoEdicao);
    }

    public boolean salvaUsuario(Usuario usuario, boolean isModoEdicao)
            throws ClassNotFoundException, SQLException {
        return !isModoEdicao
                ? UsuarioDao.getInstance().salva(usuario)
                : UsuarioDao.getInstance().atualiza(usuario);
    }

    public boolean excluiUsuario(int codigoUsuario) throws ClassNotFoundException, SQLException {
        return UsuarioDao.getInstance().remove(codigoUsuario);
    }

}
