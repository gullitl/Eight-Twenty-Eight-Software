package com.cecilsystems.mistersoftbackend.service;

import com.cecilsystems.mistersoftbackend.dao.UsuarioDao;
import com.cecilsystems.mistersoftbackend.model.Usuario;
import com.cecilsystems.mistersoftbackend.model.UsuarioLogado;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;

/**
 * @author Plamedi L. Lusembo
 */
public class LoginService {

    private static LoginService uniqueInstance;

    public LoginService() {
    }

    public static synchronized LoginService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new LoginService();
        }
        return uniqueInstance;
    }

    public Usuario lembraDoUsuario()
            throws FileNotFoundException, UnknownHostException, SocketException {
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader(MainService.getInstance()
                .getMacAddress() + ".msst"));

        Usuario usuario = gson.fromJson(br, Usuario.class);

        return usuario;
    }

    public boolean entrar(Usuario usuarioLogin, boolean lembrarDeMim)
            throws ClassNotFoundException, SQLException, IOException {
        Usuario usuario = UsuarioDao.getInstance()
                .login(usuarioLogin.getEmail(), usuarioLogin.getSenha());
        if (usuario != null) {
            UsuarioLogado.getInstance().setUsuario(usuario);
            UsuarioLogado.getInstance().setPrecisaFazerLogout(false);

            if (lembrarDeMim) {
                Gson gson = new Gson();
                String json = gson.toJson(usuario);

                try (FileWriter writer = new FileWriter(MainService.getInstance()
                        .getMacAddress() + ".msst")) {
                    writer.write(json);
                }
            } else {
                new File(MainService.getInstance().getMacAddress() + ".msst").delete();
            }
            return true;
        }
        return false;
    }

}
