package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.TauxMonnaie;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class TauxMonnaieDao {

    private StringBuilder scriptSQL;
    private static TauxMonnaieDao uniqueInstance;

    public TauxMonnaieDao() {
    }

    public static synchronized TauxMonnaieDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new TauxMonnaieDao();
        }
        return uniqueInstance;
    }

    public List<TauxMonnaie> listerTousLesTauxMonnaies() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<TauxMonnaie> listeTauxMonnaies;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeTauxMonnaies = new ArrayList();

            scriptSQL = new StringBuilder("SELECT tauxmonnaie.code, tauxmonnaie.dateheure, tauxmonnaie.valeur,");
            scriptSQL.append(" FROM tauxmonnaie");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    TauxMonnaie tauxMonnaie = new TauxMonnaie(res.getInt(1));
                    tauxMonnaie.setDateHeure(res.getTimestamp(2));
                    tauxMonnaie.setValeur(new BigDecimal(res.getString(3)));

                    listeTauxMonnaies.add(tauxMonnaie);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeTauxMonnaies;
    }

    public TauxMonnaie selectionnerTauxMonnaieParCode(int codeTauxMonnaie) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT tauxmonnaie.code, tauxmonnaie.dateheure, tauxmonnaie.valeur,");
            scriptSQL.append(" FROM tauxmonnaie");
            scriptSQL.append(" WHERE tauxmonnaie.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeTauxMonnaie);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    TauxMonnaie tauxMonnaie = new TauxMonnaie(res.getInt(1));
                    tauxMonnaie.setDateHeure(res.getTimestamp(2));
                    tauxMonnaie.setValeur(new BigDecimal(res.getString(3)));

                    prs.close();
                    res.close();
                    conexao.close();

                    return tauxMonnaie;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean sauvegarderTauxMonnaie(TauxMonnaie tauxMonnaie) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            if (tauxMonnaie.getCode() == 0) {
                scriptSQL = new StringBuilder("INSERT INTO tauxcarte(");
                scriptSQL.append(" dateheure, valeur, code )");
                scriptSQL.append(" VALUES (?, ?, ?, ?)");
            } else {
                scriptSQL = new StringBuilder("UPDATE sessionutilisateur");
                scriptSQL.append(" SET dateheure=?, valeur=?");
                scriptSQL.append(" WHERE code=?");
            }
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setTimestamp(1, new Timestamp(tauxMonnaie.getDateHeure().getTime()));
            prs.setBigDecimal(2, tauxMonnaie.getValeur());
            prs.setInt(3, tauxMonnaie.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean exclureTauxMonnaie(int codeTauxMonnaie) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM tauxmonnaie WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeTauxMonnaie);

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

}
