package com.cecilsystems.mistersoftbackend.dao;

import com.cecilsystems.mistersoftbackend.model.DetalheItemPecaComposta;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class DetalheItemPecaCompostaDao {

    private StringBuilder scriptSQL;
    private static DetalheItemPecaCompostaDao uniqueInstance;

    public DetalheItemPecaCompostaDao() {
    }

    public static synchronized DetalheItemPecaCompostaDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new DetalheItemPecaCompostaDao();
        }
        return uniqueInstance;
    }

    public List<DetalheItemPecaComposta> lista(int cdPecaComposta)
            throws SQLException, ClassNotFoundException {

        PreparedStatement pstm;
        ResultSet res;
        List<DetalheItemPecaComposta> lstDetalhesItemPecaComposta;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            lstDetalhesItemPecaComposta = new ArrayList();
            scriptSQL = new StringBuilder("SELECT idPecaComposta, idPecaSimples, idPecaMediadora,");
            scriptSQL.append(" idPecaCompostaMae, qtdNaPecaCompostaMae, qtdNaPecaMediadora");
            scriptSQL.append(" FROM DetalheItemPecaComposta");
            scriptSQL.append(" WHERE idPecaComposta=?");

            pstm = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            pstm.setInt(1, cdPecaComposta);
            res = pstm.executeQuery();
            if (res != null) {
                while (res.next()) {
                    DetalheItemPecaComposta detalheItemPecaComposta = new DetalheItemPecaComposta();

                    detalheItemPecaComposta.setCdPecaComposta(res.getInt(1));
                    detalheItemPecaComposta.setCdPecaSimples(res.getInt(2));
                    detalheItemPecaComposta.setCdPecaMediadora(res.getInt(3));
                    detalheItemPecaComposta.setCdPecaCompostaMae(res.getInt(4));
                    detalheItemPecaComposta.setQtdNaPecaCompostaMae(res.getBigDecimal(5));
                    detalheItemPecaComposta.setQtdNaPecaMediadora(res.getBigDecimal(6));

                    lstDetalhesItemPecaComposta.add(detalheItemPecaComposta);
                }
            }
            pstm.close();
            res.close();
            conexao.close();
        }

        return lstDetalhesItemPecaComposta;
    }

    // Busca e soma a mão de obra de cada Peça composta utilizada como Item
    public BigDecimal getValorMaoDeObraPecaComposta(int cdPecaComposta)
            throws SQLException, ClassNotFoundException {
        PreparedStatement pstm;
        ResultSet res;
        BigDecimal valor = new BigDecimal("0");
        try (Connection conexao = new ConnectionFactory().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT DetalheItemPecaComposta.idPecaMediadora,");
            scriptSQL.append(" DetalheItemPecaComposta.qtdNaPecaMediadora");
            scriptSQL.append(" FROM DetalheItemPecaComposta");
            scriptSQL.append(" LEFT JOIN Peca");
            scriptSQL.append(" ON DetalheItemPecaComposta.idPecaMediadora = Peca.cdPeca");
            scriptSQL.append(" WHERE DetalheItemPecaComposta.idPecaComposta=?");
            scriptSQL.append(" AND Peca.tipoPeca=?");
            scriptSQL.append(" GROUP BY DetalheItemPecaComposta.idPecaComposta,");
            scriptSQL.append(" DetalheItemPecaComposta.idPecaMediadora");

            pstm = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            pstm.setInt(1, cdPecaComposta);
            pstm.setString(2, String.valueOf('C'));
            res = pstm.executeQuery();

            if (res != null) {
                while (res.next()) {
                    valor = valor.add(MaoDeObraPecaCompostaDao.getInstance()
                            .getVlCustoMaoDeObraPecaComposta(res.getInt(1))
                            .multiply(res.getBigDecimal(2)));
                }
            }
            res.close();
            pstm.close();
            conexao.close();
        }
        return valor == null ? new BigDecimal("0") : valor;
    }

}
