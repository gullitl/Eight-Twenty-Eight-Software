package com.cecilsystems.mistersoftbackend.dao;

import com.cecilsystems.mistersoftbackend.model.GrupoUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class GrupoUsuarioDao {

    private StringBuilder scriptSQL;
    private static GrupoUsuarioDao uniqueInstance;

    public GrupoUsuarioDao() {
    }

    public static synchronized GrupoUsuarioDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new GrupoUsuarioDao();
        }
        return uniqueInstance;
    }

    public List<GrupoUsuario> lista() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<GrupoUsuario> lstGruposUsuario;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            lstGruposUsuario = new ArrayList();
            scriptSQL = new StringBuilder("SELECT cdGrupoUsuario, dsGrupoUsuario, daGrupoUsuario");
            scriptSQL.append(" FROM GrupoUsuario");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {
                    GrupoUsuario grupoUsuario = new GrupoUsuario();

                    grupoUsuario.setCdGrupoUsuario(res.getInt(1));
                    grupoUsuario.setDsGrupoUsuario(res.getString(2));
                    grupoUsuario.setDaGrupoUsuario(res.getString(3));

                    lstGruposUsuario.add(grupoUsuario);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return lstGruposUsuario;
    }

    public GrupoUsuario seleciona(int cdGrupoUsuario) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT cdGrupoUsuario, dsGrupoUsuario, daGrupoUsuario");
            scriptSQL.append(" FROM GrupoUsuario");
            scriptSQL.append(" WHERE cdGrupoUsuario=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, cdGrupoUsuario);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {
                    GrupoUsuario grupoUsuario = new GrupoUsuario();

                    grupoUsuario.setCdGrupoUsuario(res.getInt(1));
                    grupoUsuario.setDsGrupoUsuario(res.getString(2));
                    grupoUsuario.setDaGrupoUsuario(res.getString(3));

                    prs.close();
                    res.close();
                    conexao.close();
                    return grupoUsuario;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean salva(GrupoUsuario grupoUsuario) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("INSERT INTO GrupoUsuario(");
            scriptSQL.append("cdGrupoUsuario, dsGrupousuario, daGrupoUsuario)");
            scriptSQL.append(" VALUES (?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, grupoUsuario.getCdGrupoUsuario());
            prs.setString(2, grupoUsuario.getDsGrupoUsuario());
            prs.setString(3, grupoUsuario.getDaGrupoUsuario());

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

    public boolean atualiza(GrupoUsuario grupoUsuario) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("UPDATE GrupoUsuario");
            scriptSQL.append(" SET dsGrupoUsuario=?, daGrupoUsuario=?");
            scriptSQL.append(" WHERE cdGrupoUsuario=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, grupoUsuario.getDsGrupoUsuario());
            prs.setString(2, grupoUsuario.getDaGrupoUsuario());
            prs.setInt(3, grupoUsuario.getCdGrupoUsuario());

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

    public boolean remove(int cdGrupoUsuario) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("DELETE FROM GrupoUsuario WHERE cdGrupoUsuario=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, cdGrupoUsuario);

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

    public int selecionaCodigoGrupoUsuarioSubsequente() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT Max(cdGrupoUsuario)+1 FROM grupousuario");
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
