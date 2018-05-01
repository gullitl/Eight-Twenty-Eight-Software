package com.cecilsystems.mistersoftbackend.dao;

import com.cecilsystems.mistersoftbackend.model.GrupoUsuario;
import com.cecilsystems.mistersoftbackend.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class UsuarioDao {

    private StringBuilder scriptSQL;
    private static UsuarioDao uniqueInstance;

    public UsuarioDao() {
    }

    public static synchronized UsuarioDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new UsuarioDao();
        }
        return uniqueInstance;
    }

    public Usuario login(String email, String senha) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT Usuario.cdUsuario, Usuario.email, Usuario.senha,");
            scriptSQL.append(" Usuario.nome, Usuario.fotoPath, GrupoUsuario.cdGrupoUsuario,");
            scriptSQL.append(" GrupoUsuario.dsGrupoUsuario, GrupoUsuario.daGrupoUsuario");
            scriptSQL.append(" FROM Usuario");
            scriptSQL.append(" LEFT JOIN GrupoUsuario");
            scriptSQL.append(" ON Usuario.idGrupoUsuario = GrupoUsuario.cdGrupoUsuario");
            scriptSQL.append(" WHERE email=? AND senha=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, email);
            prs.setString(2, senha);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setCdUsuario(res.getInt(1));
                    usuario.setEmail(res.getString(2));
                    usuario.setSenha(res.getString(3));
                    usuario.setNome(res.getString(4));
                    usuario.setFotoPath(res.getString(5));

                    GrupoUsuario grupoUsuario = new GrupoUsuario();
                    grupoUsuario.setCdGrupoUsuario(res.getInt(6));
                    grupoUsuario.setDsGrupoUsuario(res.getString(7));
                    grupoUsuario.setDaGrupoUsuario(res.getString(8));

                    usuario.setGrupoUsuario(grupoUsuario);

                    prs.close();
                    res.close();
                    conexao.close();

                    return usuario;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public List<Usuario> lista() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Usuario> lstUsuarios;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            lstUsuarios = new ArrayList();
            scriptSQL = new StringBuilder("SELECT Usuario.cdUsuario, Usuario.email,");
            scriptSQL.append(" Usuario.senha, Usuario.nome, Usuario.fotoPath,");
            scriptSQL.append(" GrupoUsuario.cdGrupoUsuario, GrupoUsuario.dsGrupoUsuario,");
            scriptSQL.append(" GrupoUsuario.daGrupoUsuario");
            scriptSQL.append(" FROM Usuario");
            scriptSQL.append(" LEFT JOIN GrupoUsuario");
            scriptSQL.append(" ON Usuario.idGrupoUsuario = GrupoUsuario.cdGrupoUsuario");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setCdUsuario(res.getInt(1));
                    usuario.setEmail(res.getString(2));
                    usuario.setSenha(res.getString(3));
                    usuario.setNome(res.getString(4));
                    usuario.setFotoPath(res.getString(5));

                    GrupoUsuario grupoUsuario = new GrupoUsuario();
                    grupoUsuario.setCdGrupoUsuario(res.getInt(6));
                    grupoUsuario.setDsGrupoUsuario(res.getString(7));
                    grupoUsuario.setDaGrupoUsuario(res.getString(8));

                    usuario.setGrupoUsuario(grupoUsuario);

                    lstUsuarios.add(usuario);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return lstUsuarios;
    }

    public Usuario seleciona(int cdUsuario) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT Usuario.cdUsuario, Usuario.email, Usuario.senha,");
            scriptSQL.append(" Usuario.nome, Usuario.fotoPath, GrupoUsuario.cdGrupoUsuario,");
            scriptSQL.append(" GrupoUsuario.dsGrupoUsuario, GrupoUsuario.daGrupoUsuario");
            scriptSQL.append(" FROM Usuario");
            scriptSQL.append(" LEFT JOIN GrupoUsuario");
            scriptSQL.append(" ON Usuario.idGrupoUsuario = GrupoUsuario.cdGrupoUsuario");
            scriptSQL.append(" WHERE cdUsuario=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, cdUsuario);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setCdUsuario(res.getInt(1));
                    usuario.setEmail(res.getString(2));
                    usuario.setSenha(res.getString(3));
                    usuario.setNome(res.getString(4));
                    usuario.setFotoPath(res.getString(5));

                    GrupoUsuario grupoUsuario = new GrupoUsuario();
                    grupoUsuario.setCdGrupoUsuario(res.getInt(6));
                    grupoUsuario.setDsGrupoUsuario(res.getString(7));
                    grupoUsuario.setDaGrupoUsuario(res.getString(8));

                    usuario.setGrupoUsuario(grupoUsuario);

                    prs.close();
                    res.close();
                    conexao.close();

                    return usuario;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean isEmailJaUtilizado(Usuario usuario, boolean modoEdicao) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT cdUsuario FROM Usuario");
            scriptSQL.append(" WHERE email=?");
            if (!modoEdicao) {
                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
                prs.setString(1, usuario.getEmail());
            } else {
                scriptSQL.append(" and cdUsuario<>?");
                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
                prs.setString(1, usuario.getEmail());
                prs.setInt(2, usuario.getCdUsuario());
            }

            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {
                    conexao.close();
                    return true;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return false;
    }

    public boolean salva(Usuario usuario) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("INSERT INTO Usuario(");
            scriptSQL.append("cdUsuario, email, senha, nome, fotoPath, idGrupoUsuario)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, usuario.getCdUsuario());
            prs.setString(2, usuario.getEmail());
            prs.setString(3, usuario.getSenha());
            prs.setString(4, usuario.getNome());
            prs.setString(5, usuario.getFotoPath());
            prs.setInt(6, usuario.getGrupoUsuario().getCdGrupoUsuario());

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

    public boolean atualiza(Usuario usuario) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("UPDATE Usuario");
            scriptSQL.append(" SET email=?, senha=?, nome=?, fotoPath=?, idGrupoUsuario=?");
            scriptSQL.append(" WHERE cdUsuario=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, usuario.getEmail());
            prs.setString(2, usuario.getSenha());
            prs.setString(3, usuario.getNome());
            prs.setString(4, usuario.getFotoPath());
            prs.setInt(5, usuario.getGrupoUsuario().getCdGrupoUsuario());
            prs.setInt(6, usuario.getCdUsuario());

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

    public boolean remove(int cdUsuario) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("DELETE FROM Usuario WHERE cdUsuario=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, cdUsuario);

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

    public int selecionaCodigoUsuarioSubsequente() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT Max(cdUsuario)+1 FROM usuario");
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {
                    int cdSubsequente = res.getInt(1);

                    prs.close();
                    res.close();
                    conexao.close();

                    return cdSubsequente;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return 0;
    }

}
