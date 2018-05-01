package com.cecilsystems.mistersoftbackend.dao;

import com.cecilsystems.mistersoftbackend.model.ItemPecaComposta;
import com.cecilsystems.mistersoftbackend.model.UnidadeMedida;
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
public class ItemPecaCompostaDao {

    private StringBuilder scriptSQL;
    private static ItemPecaCompostaDao uniqueInstance;

    public ItemPecaCompostaDao() {
    }

    public static synchronized ItemPecaCompostaDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ItemPecaCompostaDao();
        }
        return uniqueInstance;
    }

    public List<ItemPecaComposta> lista(int cdPecaComposta)
            throws SQLException, ClassNotFoundException {

        PreparedStatement pstm;
        ResultSet res;
        List<ItemPecaComposta> lstItensPecaComposta;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            lstItensPecaComposta = new ArrayList();
            scriptSQL = new StringBuilder("SELECT Peca.cdPeca, Peca.dsPeca, Peca.tipoPeca,");
            scriptSQL.append(" UnidadeMedida.cdUnidadeMedida, UnidadeMedida.dsUnidadeMedida,");
            scriptSQL.append(" UnidadeMedida.daUnidadeMedida, PecaSimples.vlCusto,");
            scriptSQL.append(" ItemPecaComposta.qtdItens");
            scriptSQL.append(" FROM ItemPecaComposta");
            scriptSQL.append(" LEFT JOIN Peca");
            scriptSQL.append(" ON ItemPecaComposta.idPeca = Peca.cdPeca");
            scriptSQL.append(" LEFT JOIN UnidadeMedida");
            scriptSQL.append(" ON Peca.idUnidadeMedida = UnidadeMedida.cdUnidadeMedida");
            scriptSQL.append(" LEFT JOIN PecaSimples");
            scriptSQL.append(" ON Peca.cdPeca = PecaSimples.cdPecaSimples");
            scriptSQL.append(" WHERE itempecacomposta.idPecaComposta=?");

            pstm = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            pstm.setInt(1, cdPecaComposta);
            res = pstm.executeQuery();
            if (res != null) {
                while (res.next()) {
                    ItemPecaComposta itemPecaComposta = new ItemPecaComposta();

                    itemPecaComposta.setCdPeca(res.getInt(1));
                    itemPecaComposta.setDsPeca(res.getString(2));
                    itemPecaComposta.setTipoPeca(res.getString(3).charAt(0));

                    UnidadeMedida medida = new UnidadeMedida();
                    medida.setCdUnidadeMedida(res.getInt(4));
                    medida.setDsUnidadeMedida(res.getString(5));
                    medida.setDaUnidadeMedida(res.getString(6));
                    itemPecaComposta.setUnidadeMedida(medida);

                    if (itemPecaComposta.getTipoPeca() == 'S') {
                        itemPecaComposta.setVlCusto(res.getBigDecimal(7));
                    } else if (itemPecaComposta.getTipoPeca() == 'C') {

                        itemPecaComposta.setVlCusto(getVlCustoItensPecaComposta(itemPecaComposta.getCdPeca())
                                .add(MaoDeObraPecaCompostaDao.getInstance()
                                        .getVlCustoMaoDeObraPecaComposta(itemPecaComposta.getCdPeca())));
                    }

                    itemPecaComposta.setPecaComposta(PecaCompostaDao.getInstance().seleciona(cdPecaComposta));
                    itemPecaComposta.setQtdItens(res.getBigDecimal(8));

                    lstItensPecaComposta.add(itemPecaComposta);
                }
            }
            pstm.close();
            res.close();
            conexao.close();
        }
        return lstItensPecaComposta;
    }

    public BigDecimal getVlCustoItensPecaComposta(int cdPecaCompotsa)
            throws SQLException, ClassNotFoundException {

        PreparedStatement pstm;
        ResultSet res;
        BigDecimal vlCusto = new BigDecimal("0");

        try (Connection conexao = new ConnectionFactory().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT SUM(PecaSimples.vlCusto");
            scriptSQL.append(" * DetalheItemPecaComposta.qtdNaPecaCompostaMae");
            scriptSQL.append(" * DetalheItemPecaComposta.qtdNaPecaMediadora)");
            scriptSQL.append(" FROM DetalheItemPecaComposta");
            scriptSQL.append(" LEFT JOIN PecaSimples");
            scriptSQL.append(" ON DetalheItemPecaComposta.idPecaSimples = PecaSimples.cdPecaSimples");
            scriptSQL.append(" WHERE DetalheItemPecaComposta.idPecaComposta=?");

            pstm = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            pstm.setInt(1, cdPecaCompotsa);
            res = pstm.executeQuery();

            if (res != null) {
                if (res.next()) {
                    vlCusto = res.getBigDecimal(1)
                            .add(DetalheItemPecaCompostaDao.getInstance()
                                    .getValorMaoDeObraPecaComposta(cdPecaCompotsa));
                }
            }
            res.close();
            pstm.close();
            conexao.close();
        }
        return vlCusto == null ? new BigDecimal("0") : vlCusto;
    }

}
