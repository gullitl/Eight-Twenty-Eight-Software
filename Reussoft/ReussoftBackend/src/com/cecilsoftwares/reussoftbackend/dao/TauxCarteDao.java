package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.TauxCarte;
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
public class TauxCarteDao {

    private StringBuilder scriptSQL;
    private static TauxCarteDao uniqueInstance;

    public TauxCarteDao() {
    }

    public static synchronized TauxCarteDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new TauxCarteDao();
        }
        return uniqueInstance;
    }

    public List<TauxCarte> listerTousLesTauxCartes() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<TauxCarte> listeTauxCartes;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeTauxCartes = new ArrayList();

            scriptSQL = new StringBuilder("SELECT tauxcarte.id, tauxcarte.dateHeure, tauxcarte.valeur,");
            scriptSQL.append(" tauxcarte.idShop, shop.nom, shop.adresse");
            scriptSQL.append(" FROM tauxcarte");
            scriptSQL.append(" LEFT JOIN shop ON tauxcarte.idShop = shop.id");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    TauxCarte tauxCarte = new TauxCarte(res.getString(1));
                    tauxCarte.setDateHeure(res.getTimestamp(2));
                    tauxCarte.setValeur(new BigDecimal(res.getString(3)));

                    Shop shop = new Shop(res.getString(4));
                    shop.setNom(res.getString(5));
                    shop.setAdresse(res.getString(6));
                    tauxCarte.setShop(shop);

                    listeTauxCartes.add(tauxCarte);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeTauxCartes;
    }

    public TauxCarte selectionnerTauxCarteParId(String idTauxCarte) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT tauxcarte.id, tauxcarte.dateHeure, tauxcarte.valeur,");
            scriptSQL.append(" tauxcarte.idShop, shop.nom, shop.adresse");
            scriptSQL.append(" FROM tauxcarte");
            scriptSQL.append(" LEFT JOIN shop ON tauxcarte.idShop = shop.id");
            scriptSQL.append(" WHERE tauxcarte.id=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idTauxCarte);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    TauxCarte tauxCarte = new TauxCarte(res.getString(1));
                    tauxCarte.setDateHeure(res.getTimestamp(2));
                    tauxCarte.setValeur(new BigDecimal(res.getString(3)));

                    Shop shop = new Shop(res.getString(4));
                    shop.setNom(res.getString(5));
                    shop.setAdresse(res.getString(6));
                    tauxCarte.setShop(shop);

                    prs.close();
                    res.close();
                    conexao.close();

                    return tauxCarte;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean sauvegarderTauxCarte(TauxCarte tauxCarte) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("INSERT INTO tauxcarte(");
            scriptSQL.append(" dateHeure, valeur, idShop, id )");
            scriptSQL.append(" VALUES (?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setTimestamp(1, new Timestamp(tauxCarte.getDateHeure().getTime()));
            prs.setBigDecimal(2, tauxCarte.getValeur());
            prs.setString(3, tauxCarte.getShop().getId());
            prs.setString(4, tauxCarte.getId());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean actualiserTauxCarte(TauxCarte tauxCarte) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("UPDATE sessionutilisateur");
            scriptSQL.append(" SET dateHeure=?, valeur=?, idShop=?");
            scriptSQL.append(" WHERE id=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setTimestamp(1, new Timestamp(tauxCarte.getDateHeure().getTime()));
            prs.setBigDecimal(2, tauxCarte.getValeur());
            prs.setString(3, tauxCarte.getShop().getId());
            prs.setString(4, tauxCarte.getId());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean exclureTauxCarte(String idTauxCarte) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM tauxcarte WHERE id=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idTauxCarte);

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

}
