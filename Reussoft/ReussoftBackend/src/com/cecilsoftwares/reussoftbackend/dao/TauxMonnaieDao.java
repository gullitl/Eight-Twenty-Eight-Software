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

            scriptSQL = new StringBuilder("SELECT tauxmonnaie.id, tauxmonnaie.dateHeure, tauxmonnaie.valeur,");
            scriptSQL.append(" FROM tauxmonnaie");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    TauxMonnaie tauxMonnaie = new TauxMonnaie(res.getString(1));
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

    public TauxMonnaie selectionnerTauxMonnaieParId(String idTauxMonnaie) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT tauxmonnaie.id, tauxmonnaie.dateHeure, tauxmonnaie.valeur,");
            scriptSQL.append(" FROM tauxmonnaie");
            scriptSQL.append(" WHERE tauxmonnaie.id=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idTauxMonnaie);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    TauxMonnaie tauxMonnaie = new TauxMonnaie(res.getString(1));
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
            scriptSQL = new StringBuilder("INSERT INTO tauxcarte(");
            scriptSQL.append(" dateHeure, valeur, id )");
            scriptSQL.append(" VALUES (?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setTimestamp(1, new Timestamp(tauxMonnaie.getDateHeure().getTime()));
            prs.setBigDecimal(2, tauxMonnaie.getValeur());
            prs.setString(3, tauxMonnaie.getId());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean actualiserTauxMonnaie(TauxMonnaie tauxMonnaie) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("UPDATE sessionutilisateur");
            scriptSQL.append(" SET dateHeure=?, valeur=?");
            scriptSQL.append(" WHERE id=?");
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setTimestamp(1, new Timestamp(tauxMonnaie.getDateHeure().getTime()));
            prs.setBigDecimal(2, tauxMonnaie.getValeur());
            prs.setString(3, tauxMonnaie.getId());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean exclureTauxMonnaie(String idTauxMonnaie) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM tauxmonnaie WHERE id=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idTauxMonnaie);

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

}
