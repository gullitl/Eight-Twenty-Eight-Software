package com.cecilsystems.mistersoftbackend.dao;

import com.cecilsystems.mistersoftbackend.model.UnidadeMedida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class UnidadeMedidaDao {

    private StringBuilder scriptSQL;
    private static UnidadeMedidaDao uniqueInstance;

    public UnidadeMedidaDao() {
    }

    public static synchronized UnidadeMedidaDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new UnidadeMedidaDao();
        }
        return uniqueInstance;
    }

    public List<UnidadeMedida> lista() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<UnidadeMedida> lstUnidadesMedida;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            lstUnidadesMedida = new ArrayList();
            scriptSQL = new StringBuilder("SELECT cdUnidadeMedida, dsUnidadeMedida, daUnidadeMedida");
            scriptSQL.append(" FROM unidademedida");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {
                    UnidadeMedida unidadeMedida = new UnidadeMedida();
                    unidadeMedida.setCdUnidadeMedida(res.getInt(1));
                    unidadeMedida.setDsUnidadeMedida(res.getString(2));
                    unidadeMedida.setDaUnidadeMedida(res.getString(3));

                    lstUnidadesMedida.add(unidadeMedida);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return lstUnidadesMedida;
    }

    public UnidadeMedida seleciona(int cdUnidadeMedida) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT cdUnidadeMedida, dsUnidadeMedida, daUnidadeMedida");
            scriptSQL.append(" FROM UnidadeMedida");
            scriptSQL.append(" WHERE cdUnidadeMedida=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, cdUnidadeMedida);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {
                    UnidadeMedida unidadeMedida = new UnidadeMedida();
                    unidadeMedida.setCdUnidadeMedida(res.getInt(1));
                    unidadeMedida.setDsUnidadeMedida(res.getString(2));
                    unidadeMedida.setDaUnidadeMedida(res.getString(3));

                    prs.close();
                    res.close();
                    conexao.close();

                    return unidadeMedida;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean salva(UnidadeMedida unidadeMedida) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("INSERT INTO UnidadeMedida(");
            scriptSQL.append("cdUnidadeMedida, dsUnidadeMedida, daUnidadeMedida)");
            scriptSQL.append(" VALUES (?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, unidadeMedida.getCdUnidadeMedida());
            prs.setString(2, unidadeMedida.getDsUnidadeMedida());
            prs.setString(3, unidadeMedida.getDaUnidadeMedida());

            prs.execute();

            prs.close();
            conexao.close();
        }

        return true;
    }

    public boolean atualiza(UnidadeMedida unidadeMedida) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("UPDATE UnidadeMedida");
            scriptSQL.append(" SET dsUnidadeMedida=?, daUnidadeMedida=?");
            scriptSQL.append(" WHERE cdUnidadeMedida=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, unidadeMedida.getDsUnidadeMedida());
            prs.setString(2, unidadeMedida.getDaUnidadeMedida());
            prs.setInt(3, unidadeMedida.getCdUnidadeMedida());

            prs.execute();

            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean remove(int cdUnidadeMedida) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("DELETE FROM unidademedida WHERE cdUnidadeMedida=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, cdUnidadeMedida);

            prs.execute();

            prs.close();
            conexao.close();
        }

        return true;
    }

    public int selecionaCodigoUnidadeMedidaSubsequente() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT Max(cdUnidadeMedida)+1 FROM unidademedida");
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
