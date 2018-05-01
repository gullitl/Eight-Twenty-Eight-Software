package com.cecilsystems.mistersoftbackend.dao;

import com.cecilsystems.mistersoftbackend.model.Peca;
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
public class PecaDao {

    private StringBuilder scriptSQL;
    private static PecaDao uniqueInstance;

    public PecaDao() {
    }

    public static synchronized PecaDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new PecaDao();
        }
        return uniqueInstance;
    }

    public List<Peca> lista() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Peca> lstPecas;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            lstPecas = new ArrayList();
            scriptSQL = new StringBuilder("SELECT Peca.cdPeca, Peca.dsPeca, Peca.tipoPeca, Peca.markup,");
            scriptSQL.append(" UnidadeMedida.cdUnidadeMedida, UnidadeMedida.dsUnidadeMedida,");
            scriptSQL.append(" UnidadeMedida.daUnidadeMedida");
            scriptSQL.append(" FROM Peca");
            scriptSQL.append(" LEFT JOIN UnidadeMedida");
            scriptSQL.append(" ON Peca.idUnidadeMedida = UnidadeMedida.cdUnidadeMedida");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {
                    Peca peca = new Peca();
                    peca.setCdPeca(res.getInt(1));
                    peca.setDsPeca(res.getString(2));
                    peca.setTipoPeca(res.getString(3).charAt(0));
                    peca.setMarkup(res.getBigDecimal(4));

                    UnidadeMedida unidadeMedida = new UnidadeMedida();
                    unidadeMedida.setCdUnidadeMedida(res.getInt(5));
                    unidadeMedida.setDsUnidadeMedida(res.getString(6));
                    unidadeMedida.setDaUnidadeMedida(res.getString(7));

                    peca.setUnidadeMedida(unidadeMedida);

                    lstPecas.add(peca);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return lstPecas;
    }

    public Peca seleciona(int cdPeca) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT Peca.cdPeca, Peca.dsPeca, Peca.tipoPeca, Peca.markup,");
            scriptSQL.append(" UnidadeMedida.cdUnidadeMedida, UnidadeMedida.dsUnidadeMedida,");
            scriptSQL.append(" UnidadeMedida.daUnidadeMedida");
            scriptSQL.append(" FROM Peca");
            scriptSQL.append(" LEFT JOIN UnidadeMedida");
            scriptSQL.append(" ON Peca.idUnidadeMedida = UnidadeMedida.cdUnidadeMedida");
            scriptSQL.append(" WHERE cdPeca=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, cdPeca);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {
                    Peca peca = new Peca();
                    peca.setCdPeca(res.getInt(1));
                    peca.setDsPeca(res.getString(2));
                    peca.setTipoPeca(res.getString(3).charAt(0));
                    peca.setMarkup(res.getBigDecimal(4));

                    UnidadeMedida unidadeMedida = new UnidadeMedida();
                    unidadeMedida.setCdUnidadeMedida(res.getInt(5));
                    unidadeMedida.setDsUnidadeMedida(res.getString(6));
                    unidadeMedida.setDaUnidadeMedida(res.getString(7));

                    peca.setUnidadeMedida(unidadeMedida);

                    prs.close();
                    res.close();
                    conexao.close();

                    return peca;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public int selecionaCodigoPecaSubsequente() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT Max(cdPeca)+1 FROM peca");
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
