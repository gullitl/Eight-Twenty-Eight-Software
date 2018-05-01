package com.cecilsystems.mistersoftbackend.dao;

import com.cecilsystems.mistersoftbackend.model.ChaveLicenca;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class ChaveLicencaDao {

    private StringBuilder scriptSQL;
    private static ChaveLicencaDao uniqueInstance;

    public ChaveLicencaDao() {
    }

    public static synchronized ChaveLicencaDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ChaveLicencaDao();
        }
        return uniqueInstance;
    }

    public List<ChaveLicenca> lista() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ChaveLicenca> lstChavesLicenca;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            lstChavesLicenca = new ArrayList();
            scriptSQL = new StringBuilder("SELECT ChaveLicenca.cdChave, ChaveLicenca.nrChave,");
            scriptSQL.append(" ChaveLicenca.diasValidade, ChaveLicenca.dataVencimento");
            scriptSQL.append(" FROM ChaveLicenca");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {
                    ChaveLicenca chaveLicenca = new ChaveLicenca();
                    chaveLicenca.setCdChave(res.getInt(1));
                    chaveLicenca.setNrChave(res.getString(2));
                    chaveLicenca.setDiasValidade(res.getInt(3));

                    Calendar data = Calendar.getInstance();
                    data.setTime(res.getDate(4));
                    chaveLicenca.setDataVencimento(data);

                    lstChavesLicenca.add(chaveLicenca);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return lstChavesLicenca;
    }

    public ChaveLicenca seleciona(int cdChave) throws SQLException, ClassNotFoundException {
        PreparedStatement prs;
        ResultSet res;
        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT ChaveLicenca.cdChave, ChaveLicenca.nrChave,");
            scriptSQL.append(" ChaveLicenca.diasValidade, ChaveLicenca.dataVencimento");
            scriptSQL.append(" FROM ChaveLicenca");
            scriptSQL.append(" WHERE ChaveLicenca.cdChave=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, cdChave);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {
                    ChaveLicenca chaveLicenca = new ChaveLicenca();
                    chaveLicenca.setCdChave(res.getInt(1));
                    chaveLicenca.setNrChave(res.getString(2));
                    chaveLicenca.setDiasValidade(res.getInt(3));

                    Calendar data = Calendar.getInstance();
                    data.setTime(res.getDate(4));
                    chaveLicenca.setDataVencimento(data);

                    res.close();
                    prs.close();
                    conexao.close();

                    return chaveLicenca;
                }
            }
            res.close();
            prs.close();
            conexao.close();
        }
        return null;
    }

    public int selecionaCodigoChaveSubsequente() throws SQLException, ClassNotFoundException {
        PreparedStatement prs;
        ResultSet res;
        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("SELECT a.AUTO_INCREMENT");
            scriptSQL.append(" FROM information_schema.tables a");
            scriptSQL.append(" WHERE a.table_name='ChaveLicenca' AND table_schema=DATABASE()");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {
                    int cdChave = res.getInt(1);

                    res.close();
                    prs.close();
                    conexao.close();

                    return cdChave;
                }
            }
            res.close();
            prs.close();
            conexao.close();
        }
        return 0;
    }

    public boolean salva(ChaveLicenca chaveLicenca) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("INSERT INTO ChaveLicenca(");
            scriptSQL.append("cdChave, nrChave, diasValidade, dataVencimento)");
            scriptSQL.append(" VALUES (?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, chaveLicenca.getCdChave());
            prs.setString(2, chaveLicenca.getNrChave());
            prs.setInt(3, chaveLicenca.getDiasValidade());
            prs.setDate(4, new Date(chaveLicenca.getDataVencimento().getTimeInMillis()));

            prs.execute();

            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean atualiza(ChaveLicenca chaveLicenca) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("UPDATE ChaveLicenca");
            scriptSQL.append(" SET nrChave=?, diasValidade=?, dataVencimento=?");
            scriptSQL.append(" WHERE cdChave=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, chaveLicenca.getNrChave());
            prs.setInt(2, chaveLicenca.getDiasValidade());
            prs.setDate(3, new Date(chaveLicenca.getDataVencimento().getTimeInMillis()));
            prs.setInt(4, chaveLicenca.getCdChave());

            prs.execute();

            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean remove(int cdCliente) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().abreNovaConexao()) {
            scriptSQL = new StringBuilder("DELETE FROM ChaveLicenca WHERE cdChave=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, cdCliente);

            prs.execute();

            prs.close();
            conexao.close();
        }

        return true;
    }
}
