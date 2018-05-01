package com.cecilsystems.mistersoftbackend.dao;

import com.cecilsystems.mistersoftbackend.model.DetalheItemPecaComposta;
import com.cecilsystems.mistersoftbackend.model.ItemPecaComposta;
import com.cecilsystems.mistersoftbackend.model.MaoDeObraPecaComposta;
import com.cecilsystems.mistersoftbackend.model.PecaComposta;
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
public class PecaCompostaDao {

    private StringBuilder scriptSQL;
    private static PecaCompostaDao uniqueInstance;

    public PecaCompostaDao() {
    }

    public static synchronized PecaCompostaDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new PecaCompostaDao();
        }
        return uniqueInstance;
    }

    public List<PecaComposta> lista() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<PecaComposta> lstPecasCompostas;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            lstPecasCompostas = new ArrayList();
            scriptSQL = new StringBuilder("SELECT PecaComposta.cdPecaComposta, Peca.markup,");
            scriptSQL.append(" Peca.dsPeca, Peca.tipoPeca, UnidadeMedida.cdUnidadeMedida,");
            scriptSQL.append(" UnidadeMedida.dsUnidadeMedida, UnidadeMedida.daUnidadeMedida");
            scriptSQL.append(" FROM PecaComposta");
            scriptSQL.append(" LEFT JOIN Peca");
            scriptSQL.append(" ON PecaComposta.cdPecaComposta = Peca.cdPeca");
            scriptSQL.append(" LEFT JOIN UnidadeMedida");
            scriptSQL.append(" ON Peca.idUnidadeMedida = UnidadeMedida.cdUnidadeMedida");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {
                    PecaComposta pecaComposta = new PecaComposta();
                    pecaComposta.setCdPeca(res.getInt(1));
                    pecaComposta.setMarkup(res.getBigDecimal(2));
                    pecaComposta.setDsPeca(res.getString(3));
                    pecaComposta.setTipoPeca(res.getString(4).charAt(0));

                    UnidadeMedida unidadeMedida = new UnidadeMedida();
                    unidadeMedida.setCdUnidadeMedida(res.getInt(5));
                    unidadeMedida.setDsUnidadeMedida(res.getString(6));
                    unidadeMedida.setDaUnidadeMedida(res.getString(7));

                    pecaComposta.setUnidadeMedida(unidadeMedida);

//                    pecaComposta.setVlCusto(ItemPecaCompostaDao.getInstance()
//                            .getVlCustoItensPecaComposta(pecaComposta.getCdPeca())
//                            .add(MaoDeObraPecaCompostaDao.getInstance()
//                                    .getVlCustoMaoDeObraPecaComposta(pecaComposta.getCdPeca())));
//
//                    pecaComposta.setVlVenda(pecaComposta.getVlCusto()
//                            .multiply(pecaComposta.getMarkup()));
                    lstPecasCompostas.add(pecaComposta);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return lstPecasCompostas;
    }

    public PecaComposta seleciona(int cdPecaComposta) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT PecaComposta.cdPecaComposta, Peca.markup,");
            scriptSQL.append(" Peca.dsPeca, Peca.tipoPeca, UnidadeMedida.cdUnidadeMedida,");
            scriptSQL.append(" UnidadeMedida.dsUnidadeMedida, UnidadeMedida.daUnidadeMedida");
            scriptSQL.append(" FROM PecaComposta");
            scriptSQL.append(" LEFT JOIN Peca");
            scriptSQL.append(" ON PecaComposta.cdPecaComposta = Peca.cdPeca");
            scriptSQL.append(" LEFT JOIN UnidadeMedida");
            scriptSQL.append(" ON Peca.idUnidadeMedida = UnidadeMedida.cdUnidadeMedida");
            scriptSQL.append(" WHERE PecaComposta.cdPecaComposta=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, cdPecaComposta);
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {
                    PecaComposta pecaComposta = new PecaComposta();
                    pecaComposta.setCdPeca(res.getInt(1));
                    pecaComposta.setMarkup(res.getBigDecimal(2));
                    pecaComposta.setDsPeca(res.getString(3));
                    pecaComposta.setTipoPeca(res.getString(4).charAt(0));

                    UnidadeMedida unidadeMedida = new UnidadeMedida();
                    unidadeMedida.setCdUnidadeMedida(res.getInt(5));
                    unidadeMedida.setDsUnidadeMedida(res.getString(6));
                    unidadeMedida.setDaUnidadeMedida(res.getString(7));

                    pecaComposta.setUnidadeMedida(unidadeMedida);

//                    pecaComposta.setVlTotalItens(ItemPecaCompostaDao.getInstance()
//                            .getVlCustoItensPecaComposta(pecaComposta.getCdPeca())
//                            .setScale(2, RoundingMode.HALF_UP));
//
//                    pecaComposta.setVlTotalMaoDeObra(MaoDeObraPecaCompostaDao.getInstance()
//                            .getVlCustoMaoDeObraPecaComposta(pecaComposta.getCdPeca())
//                            .setScale(2, RoundingMode.HALF_UP));
//
//                    pecaComposta.setVlCusto(pecaComposta.getVlTotalItens()
//                            .add(pecaComposta.getVlTotalMaoDeObra()).setScale(2, RoundingMode.HALF_UP));
//                    pecaComposta.setVlCusto(ItemPecaCompostaDao.getInstance()
//                            .getVlCustoItensPecaComposta(pecaComposta.getCdPeca())
//                            .add(MaoDeObraPecaCompostaDao.getInstance()
//                                    .getVlCustoMaoDeObraPecaComposta(pecaComposta.getCdPeca()))
//                            .setScale(2, RoundingMode.HALF_UP));
//
//                    pecaComposta.setVlVenda(pecaComposta.getVlCusto()
//                            .multiply(pecaComposta.getMarkup()).setScale(2, RoundingMode.HALF_UP));
                    pecaComposta.setLstDetalheItemPecaComposta(DetalheItemPecaCompostaDao.getInstance()
                            .lista(cdPecaComposta));

                    return pecaComposta;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean salva(PecaComposta pecaComposta) throws SQLException, ClassNotFoundException {

        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {

            scriptSQL = new StringBuilder("INSERT INTO Peca(");
            scriptSQL.append("cdPeca, dsPeca, idUnidadeMedida, tipoPeca, markup)");
            scriptSQL.append(" VALUES (?, ?, 1, 'C', ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, pecaComposta.getCdPeca());
            prs.setString(2, pecaComposta.getDsPeca());
            prs.setBigDecimal(3, pecaComposta.getMarkup());
//            prs.setInt(3, pecaComposta.getUnidadeMedida().getCdUnidadeMedida());
//            prs.setString(4, String.valueOf(pecaComposta.getTipoPeca()));

            prs.execute();

            scriptSQL = new StringBuilder("INSERT INTO PecaComposta(cdPecaComposta)");
            scriptSQL.append(" VALUES (?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, pecaComposta.getCdPeca());

            prs.execute();

            //Mão de obra
            for (MaoDeObraPecaComposta maoDeObraPecaComposta : pecaComposta.getLstMaoDeObra()) {
                scriptSQL = new StringBuilder("INSERT INTO MaoDeObraPecaComposta(");
                scriptSQL.append("idMaodeobra, idPecaComposta, qtdHoras)");
                scriptSQL.append(" VALUES (?, ?, ?)");
                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setInt(1, maoDeObraPecaComposta.getCdMaoDeObra());
                prs.setInt(2, pecaComposta.getCdPeca());
                prs.setBigDecimal(3, maoDeObraPecaComposta.getQtHoras());
                prs.execute();
            }

            //Item peça composta
            for (ItemPecaComposta itemPecaComposta : pecaComposta.getLstItensPecaComposta()) {
                scriptSQL = new StringBuilder("INSERT INTO ItemPecaComposta(");
                scriptSQL.append("idPeca, idPecaComposta, qtdItens)");
                scriptSQL.append(" VALUES (?, ?, ?)");
                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setInt(1, itemPecaComposta.getCdPeca());
                prs.setInt(2, pecaComposta.getCdPeca());
                prs.setBigDecimal(3, itemPecaComposta.getQtdItens());
                prs.execute();
            }

            for (DetalheItemPecaComposta detalheItemPecaComposta : pecaComposta.getLstDetalheItemPecaComposta()) {
                scriptSQL = new StringBuilder("INSERT INTO DetalheItemPecaComposta(");
                scriptSQL.append("idPecaComposta, idPecaSimples, idPecaCompostaMae,");
                scriptSQL.append(" idPecaMediadora, qtdNaPecaCompostaMae, qtdNaPecaMediadora)");
                scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?)");
                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setInt(1, detalheItemPecaComposta.getCdPecaComposta());
                prs.setInt(2, detalheItemPecaComposta.getCdPecaSimples());
                prs.setInt(3, detalheItemPecaComposta.getCdPecaCompostaMae());
                prs.setInt(4, detalheItemPecaComposta.getCdPecaMediadora());
                prs.setBigDecimal(5, detalheItemPecaComposta.getQtdNaPecaCompostaMae());
                prs.setBigDecimal(6, detalheItemPecaComposta.getQtdNaPecaMediadora());
                prs.execute();
            }
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean atualiza(PecaComposta pecaComposta) throws SQLException, ClassNotFoundException {

        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("UPDATE Peca SET dsPeca=?, markup=?");
            scriptSQL.append(" WHERE cdPeca=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, pecaComposta.getDsPeca());
            prs.setBigDecimal(2, pecaComposta.getMarkup());
//            prs.setInt(2, pecaComposta.getUnidadeMedida().getCdUnidadeMedida());
//            prs.setString(3, String.valueOf(pecaComposta.getTipoPeca()));
            prs.setInt(3, pecaComposta.getCdPeca());
            prs.execute();

            scriptSQL = new StringBuilder("DELETE FROM ItemPecaComposta WHERE idPecaComposta=?");
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, pecaComposta.getCdPeca());
            prs.execute();

            scriptSQL = new StringBuilder("DELETE FROM MaoDeObraPecaComposta");
            scriptSQL.append(" WHERE idPecaComposta=?");
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, pecaComposta.getCdPeca());
            prs.execute();

            scriptSQL = new StringBuilder("DELETE FROM DetalheItemPecaComposta");
            scriptSQL.append(" WHERE idPecaComposta=?");
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, pecaComposta.getCdPeca());
            prs.execute();

            for (MaoDeObraPecaComposta maoDeObraPecaComposta : pecaComposta.getLstMaoDeObra()) {
                scriptSQL = new StringBuilder("INSERT INTO MaoDeObraPecaComposta(");
                scriptSQL.append("idMaodeobra, idPecaComposta, qtdHoras)");
                scriptSQL.append(" VALUES (?, ?, ?)");
                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setInt(1, maoDeObraPecaComposta.getCdMaoDeObra());
                prs.setInt(2, pecaComposta.getCdPeca());
                prs.setBigDecimal(3, maoDeObraPecaComposta.getQtHoras());
                prs.execute();
            }

            for (ItemPecaComposta itemPecaComposta : pecaComposta.getLstItensPecaComposta()) {
                scriptSQL = new StringBuilder("INSERT INTO ItemPecaComposta(");
                scriptSQL.append("idPeca, idPecaComposta, qtdItens)");
                scriptSQL.append(" VALUES (?, ?, ?)");
                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setInt(1, itemPecaComposta.getCdPeca());
                prs.setInt(2, pecaComposta.getCdPeca());
                prs.setBigDecimal(3, itemPecaComposta.getQtdItens());
                prs.execute();
            }

            for (DetalheItemPecaComposta detalheItemPecaComposta : pecaComposta.getLstDetalheItemPecaComposta()) {
                scriptSQL = new StringBuilder("INSERT INTO DetalheItemPecaComposta(");
                scriptSQL.append(" idPecaComposta, idPecaSimples, idPecaCompostaMae,");
                scriptSQL.append(" idPecaMediadora, qtdNaPecaCompostaMae,");
                scriptSQL.append(" qtdNaPecaMediadora)");
                scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?)");
                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

                prs.setInt(1, detalheItemPecaComposta.getCdPecaComposta());
                prs.setInt(2, detalheItemPecaComposta.getCdPecaSimples());
                prs.setInt(3, detalheItemPecaComposta.getCdPecaCompostaMae());
                prs.setInt(4, detalheItemPecaComposta.getCdPecaMediadora());
                prs.setBigDecimal(5, detalheItemPecaComposta.getQtdNaPecaCompostaMae());
                prs.setBigDecimal(6, detalheItemPecaComposta.getQtdNaPecaMediadora());
                prs.execute();
            }
            prs.close();
            conexao.close();
        }

        return true;
    }

    public boolean remove(int cdPecaComposta) throws SQLException, ClassNotFoundException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("DELETE FROM ItemPecaComposta WHERE idPecaComposta=?");
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, cdPecaComposta);
            prs.execute();

            scriptSQL = new StringBuilder("DELETE FROM MaoDeObraPecaComposta");
            scriptSQL.append(" WHERE idPecaComposta=?");
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, cdPecaComposta);
            prs.execute();

            scriptSQL = new StringBuilder("DELETE FROM DetalheItemPecaComposta");
            scriptSQL.append(" WHERE idPecaComposta=?");
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, cdPecaComposta);
            prs.execute();

            scriptSQL = new StringBuilder("DELETE FROM PecaComposta WHERE cdPecaComposta=?");
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, cdPecaComposta);
            prs.execute();

            scriptSQL = new StringBuilder("DELETE FROM Peca WHERE cdPeca=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, cdPecaComposta);
            prs.execute();

            prs.close();
            conexao.close();
        }
        return true;
    }

}
