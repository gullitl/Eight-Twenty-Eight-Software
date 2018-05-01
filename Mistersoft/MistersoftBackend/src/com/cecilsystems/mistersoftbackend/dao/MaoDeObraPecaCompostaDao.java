package com.cecilsystems.mistersoftbackend.dao;

import com.cecilsystems.mistersoftbackend.model.MaoDeObraPecaComposta;
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
public class MaoDeObraPecaCompostaDao {

    private StringBuilder scriptSQL;
    private static MaoDeObraPecaCompostaDao uniqueInstance;

    public MaoDeObraPecaCompostaDao() {
    }

    public static synchronized MaoDeObraPecaCompostaDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MaoDeObraPecaCompostaDao();
        }
        return uniqueInstance;
    }

    public List<MaoDeObraPecaComposta> lista(int cdPecaComposta) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm;
        ResultSet res;
        List<MaoDeObraPecaComposta> lstMaodeobraPecaComposta;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            lstMaodeobraPecaComposta = new ArrayList();
            scriptSQL = new StringBuilder("SELECT MaoDeObra.cdMaoDeObra, MaoDeObra.dsMaoDeObra,");
            scriptSQL.append(" MaoDeObra.daMaoDeObra, MaoDeObra.vlCusto, MaoDeObra.observacao,");
            scriptSQL.append(" MaoDeObraPecaComposta.qtdHoras");
            scriptSQL.append(" FROM MaoDeObraPecaComposta");
            scriptSQL.append(" LEFT JOIN MaoDeObra");
            scriptSQL.append(" ON MaoDeObraPecaComposta.idMaoDeObra = MaoDeObra.cdMaoDeObra");
            scriptSQL.append(" WHERE MaoDeObraPecaComposta.idPecaComposta =?");

            pstm = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            pstm.setInt(1, cdPecaComposta);
            res = pstm.executeQuery();
            if (res != null) {
                while (res.next()) {
                    MaoDeObraPecaComposta maoDeObraPecaComposta = new MaoDeObraPecaComposta();

                    maoDeObraPecaComposta.setCdMaoDeObra(res.getInt(1));
                    maoDeObraPecaComposta.setDsMaoDeObra(res.getString(2));
                    maoDeObraPecaComposta.setDaMaoDeObra(res.getString(3));
                    maoDeObraPecaComposta.setVlCusto(res.getBigDecimal(4));
                    maoDeObraPecaComposta.setObservacao(res.getString(5));
                    maoDeObraPecaComposta.setQtHoras(res.getBigDecimal(6));
                    maoDeObraPecaComposta.setPecaComposta(PecaCompostaDao.getInstance().seleciona(cdPecaComposta));

                    lstMaodeobraPecaComposta.add(maoDeObraPecaComposta);
                }
            }
            pstm.close();
            res.close();
            conexao.close();
        }
        return lstMaodeobraPecaComposta;
    }

    public BigDecimal getVlCustoMaoDeObraPecaComposta(int cdPecaComposta)
            throws SQLException, ClassNotFoundException {
        PreparedStatement pstm;
        ResultSet res;
        BigDecimal vlCusto = new BigDecimal("0");

        try (Connection conexao = new ConnectionFactory().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT");
            scriptSQL.append(" SUM(MaoDeObra.vlCusto * MaoDeObraPecaComposta.qtdHoras)");
            scriptSQL.append(" FROM MaoDeObraPecaComposta");
            scriptSQL.append(" LEFT JOIN MaoDeObra");
            scriptSQL.append(" ON MaoDeObraPecaComposta.idMaoDeObra = MaoDeObra.cdMaoDeObra");
            scriptSQL.append(" WHERE MaoDeObraPecaComposta.idPecaComposta = ?");

            pstm = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            pstm.setInt(1, cdPecaComposta);
            res = pstm.executeQuery();

            if (res != null) {
                if (res.next()) {
                    vlCusto = res.getBigDecimal(1);
                }
            }
            res.close();
            pstm.close();
            conexao.close();
        }
        return vlCusto == null ? new BigDecimal("0") : vlCusto;
    }

}
