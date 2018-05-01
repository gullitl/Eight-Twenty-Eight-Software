package com.cecilsystems.mistersoftbackend.service;

import com.cecilsystems.mistersoftbackend.dao.UsuarioDao;
import com.cecilsystems.mistersoftbackend.model.Usuario;
import com.cecilsystems.mistersoftbackend.model.UsuarioLogado;
import java.io.File;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;

public class ConfiguracaoContaUsuarioService {

    private static ConfiguracaoContaUsuarioService uniqueInstance;

    public ConfiguracaoContaUsuarioService() {
    }

    public static synchronized ConfiguracaoContaUsuarioService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ConfiguracaoContaUsuarioService();
        }
        return uniqueInstance;
    }

    public boolean salvaConfiguracaoContaUsuario(Usuario usuario)
            throws ClassNotFoundException, SQLException, UnknownHostException, SocketException {
        if (UsuarioDao.getInstance().atualiza(UsuarioLogado.getInstance().getUsuario())) {
            new File(MainService.getInstance().getMacAddress() + ".msst").delete();
            return true;
        }
        return false;
    }

}
