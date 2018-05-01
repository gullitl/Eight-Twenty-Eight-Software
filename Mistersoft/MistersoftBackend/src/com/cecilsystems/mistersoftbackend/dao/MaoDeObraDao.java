package com.cecilsystems.mistersoftbackend.dao;

import com.cecilsystems.mistersoftbackend.model.MaoDeObra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class MaoDeObraDao {

    private StringBuilder scriptSQL;
    private static MaoDeObraDao uniqueInstance;

    public MaoDeObraDao() {
    }

    public static synchronized MaoDeObraDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MaoDeObraDao();
        }
        return uniqueInstance;
    }

    public List<MaoDeObra> lista() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<MaoDeObra> lstMaoDeObra;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            lstMaoDeObra = new ArrayList();
            scriptSQL = new StringBuilder("SELECT cdMaoDeObra, dsMaoDeObra, daMaoDeObra,");
            scriptSQL.append("  vlCusto, observacao");
            scriptSQL.append(" FROM MaoDeObra");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {
                    MaoDeObra maoDeObra = new MaoDeObra();
                    maoDeObra.setCdMaoDeObra(res.getInt(1));
                    maoDeObra.setDsMaoDeObra(res.getString(2));
                    maoDeObra.setDaMaoDeObra(res.getString(3));
                    maoDeObra.setVlCusto(res.getBigDecimal(4));
                    maoDeObra.setObservacao(res.getString(5));

                    lstMaoDeObra.add(maoDeObra);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return lstMaoDeObra;
    }

    public MaoDeObra seleciona(int cdMaoDeObra) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT cdMaoDeObra, dsMaoDeObra, daMaoDeObra,");
            scriptSQL.append(" vlCusto, observacao");
            scriptSQL.append(" FROM MaoDeObra");
            scriptSQL.append(" WHERE cdMaoDeObra=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, cdMaoDeObra);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {
                    MaoDeObra maoDeObra = new MaoDeObra();
                    maoDeObra.setCdMaoDeObra(res.getInt(1));
                    maoDeObra.setDsMaoDeObra(res.getString(2));
                    maoDeObra.setDaMaoDeObra(res.getString(3));
                    maoDeObra.setVlCusto(res.getBigDecimal(4));
                    maoDeObra.setObservacao(res.getString(5));

                    prs.close();
                    res.close();
                    conexao.close();

                    return maoDeObra;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean salva(MaoDeObra maoDeObra) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("INSERT INTO MaoDeObra(cdMaoDeObra, dsMaoDeObra,");
            scriptSQL.append(" daMaoDeObra, vlCusto, observacao)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, maoDeObra.getCdMaoDeObra());
            prs.setString(2, maoDeObra.getDsMaoDeObra());
            prs.setString(3, maoDeObra.getDaMaoDeObra());
            prs.setBigDecimal(4, maoDeObra.getVlCusto());
            prs.setString(5, maoDeObra.getObservacao());

            prs.execute();
            prs.close();
        }

        return true;
    }

    public boolean atualiza(MaoDeObra maoDeObra) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("UPDATE MaoDeObra");
            scriptSQL.append(" SET dsMaoDeObra=?, daMaoDeObra=?, vlCusto=?, observacao=?");
            scriptSQL.append(" WHERE cdMaoDeObra=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, maoDeObra.getDsMaoDeObra());
            prs.setString(2, maoDeObra.getDaMaoDeObra());
            prs.setBigDecimal(3, maoDeObra.getVlCusto());
            prs.setString(4, maoDeObra.getObservacao());
            prs.setInt(5, maoDeObra.getCdMaoDeObra());

            prs.execute();
            prs.close();
        }

        return true;
    }

    public boolean remove(int cdMaoDeObra) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("DELETE FROM MaoDeObra WHERE cdMaoDeObra=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, cdMaoDeObra);

            prs.execute();
            prs.close();
        }

        return true;
    }

    public int selecionaCodigoMaoDeObraSubsequente() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT Max(cdMaoDeObra)+1 FROM maodeobra");
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
