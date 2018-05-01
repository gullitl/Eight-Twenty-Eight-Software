package com.cecilsystems.mistersoftbackend.dao;

import com.cecilsystems.mistersoftbackend.model.PecaSimples;
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
public class PecaSimplesDao {

    private StringBuilder scriptSQL;
    private static PecaSimplesDao uniqueInstance;

    public PecaSimplesDao() {
    }

    public static synchronized PecaSimplesDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new PecaSimplesDao();
        }
        return uniqueInstance;
    }

    public List<PecaSimples> lista() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<PecaSimples> lstPecasSimples;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            lstPecasSimples = new ArrayList();
            scriptSQL = new StringBuilder("SELECT PecaSimples.cdPecaSimples, PecaSimples.vlCusto,");
            scriptSQL.append(" Peca.dsPeca, Peca.tipoPeca, Peca.markup,");
            scriptSQL.append(" UnidadeMedida.cdUnidadeMedida, UnidadeMedida.dsUnidadeMedida,");
            scriptSQL.append(" UnidadeMedida.daUnidadeMedida");
            scriptSQL.append(" FROM PecaSimples");
            scriptSQL.append(" LEFT JOIN Peca");
            scriptSQL.append(" ON PecaSimples.cdPecaSimples = Peca.cdPeca");
            scriptSQL.append(" LEFT JOIN UnidadeMedida");
            scriptSQL.append(" ON Peca.idUnidadeMedida = UnidadeMedida.cdUnidadeMedida");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {
                    PecaSimples pecaSimples = new PecaSimples();
                    pecaSimples.setCdPeca(res.getInt(1));
                    pecaSimples.setVlCusto(res.getBigDecimal(2));

                    pecaSimples.setDsPeca(res.getString(3));
                    pecaSimples.setTipoPeca(res.getString(4).charAt(0));
                    pecaSimples.setMarkup(res.getBigDecimal(5));
                    pecaSimples.setVlVenda(pecaSimples.getVlCusto().multiply(pecaSimples.getMarkup()));

                    UnidadeMedida unidadeMedida = new UnidadeMedida();
                    unidadeMedida.setCdUnidadeMedida(res.getInt(6));
                    unidadeMedida.setDsUnidadeMedida(res.getString(7));
                    unidadeMedida.setDaUnidadeMedida(res.getString(8));

                    pecaSimples.setUnidadeMedida(unidadeMedida);

                    lstPecasSimples.add(pecaSimples);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return lstPecasSimples;
    }

    public PecaSimples seleciona(int cdPecaSimples) throws SQLException, ClassNotFoundException {
        PreparedStatement prs;
        ResultSet res;
        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT PecaSimples.cdPecaSimples, PecaSimples.vlCusto,");
            scriptSQL.append(" Peca.dsPeca, Peca.tipoPeca, Peca.markup,");
            scriptSQL.append(" UnidadeMedida.cdUnidadeMedida, UnidadeMedida.dsUnidadeMedida,");
            scriptSQL.append(" UnidadeMedida.daUnidadeMedida");
            scriptSQL.append(" FROM PecaSimples");
            scriptSQL.append(" LEFT JOIN Peca");
            scriptSQL.append(" ON PecaSimples.cdPecaSimples = Peca.cdPeca");
            scriptSQL.append(" LEFT JOIN UnidadeMedida");
            scriptSQL.append(" ON Peca.idUnidadeMedida = UnidadeMedida.cdUnidadeMedida");
            scriptSQL.append(" WHERE PecaSimples.cdPecaSimples=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, cdPecaSimples);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {
                    PecaSimples pecaSimples = new PecaSimples();
                    pecaSimples.setCdPeca(res.getInt(1));
                    pecaSimples.setVlCusto(res.getBigDecimal(2));

                    pecaSimples.setDsPeca(res.getString(3));
                    pecaSimples.setTipoPeca(res.getString(4).charAt(0));
                    pecaSimples.setMarkup(res.getBigDecimal(5));
                    pecaSimples.setVlVenda(pecaSimples.getVlCusto()
                            .multiply(pecaSimples.getMarkup()));

                    UnidadeMedida unidadeMedida = new UnidadeMedida();
                    unidadeMedida.setCdUnidadeMedida(res.getInt(6));
                    unidadeMedida.setDsUnidadeMedida(res.getString(7));
                    unidadeMedida.setDaUnidadeMedida(res.getString(8));

                    pecaSimples.setUnidadeMedida(unidadeMedida);

                    res.close();
                    prs.close();
                    conexao.close();

                    return pecaSimples;
                }
            }
            res.close();
            prs.close();
            conexao.close();
        }
        return null;
    }

    public boolean salva(PecaSimples pecaSimples) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("INSERT INTO Peca(");
            scriptSQL.append("cdPeca, dsPeca, idUnidadeMedida, tipoPeca, markup)");
            scriptSQL.append(" VALUES (?, ?, ?, 'S', ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, pecaSimples.getCdPeca());
            prs.setString(2, pecaSimples.getDsPeca());
            prs.setInt(3, pecaSimples.getUnidadeMedida().getCdUnidadeMedida());
            prs.setBigDecimal(4, pecaSimples.getMarkup());

            prs.execute();

            scriptSQL = new StringBuilder("INSERT INTO PecaSimples(cdPecaSimples, vlCusto)");
            scriptSQL.append(" VALUES (?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, pecaSimples.getCdPeca());
            prs.setBigDecimal(2, pecaSimples.getVlCusto());

            prs.execute();

            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean atualiza(PecaSimples pecaSimples) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("UPDATE Peca SET dsPeca=?, idUnidadeMedida=?, markup=?");
            scriptSQL.append(" WHERE cdPeca=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, pecaSimples.getDsPeca());
            prs.setInt(2, pecaSimples.getUnidadeMedida().getCdUnidadeMedida());
            prs.setBigDecimal(3, pecaSimples.getMarkup());
            prs.setInt(4, pecaSimples.getCdPeca());

            prs.execute();

            scriptSQL = new StringBuilder("UPDATE PecaSimples SET vlCusto=?");
            scriptSQL.append(" WHERE cdPecaSimples=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setBigDecimal(1, pecaSimples.getVlCusto());
            prs.setInt(2, pecaSimples.getCdPeca());

            prs.execute();

            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean remove(int cdPecaSimples) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("DELETE FROM PecaSimples WHERE cdPecaSimples=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, cdPecaSimples);

            prs.execute();

            scriptSQL = new StringBuilder("DELETE FROM Peca WHERE cdPeca=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, cdPecaSimples);

            prs.execute();

            prs.close();
            conexao.close();
        }

        return true;
    }

}
