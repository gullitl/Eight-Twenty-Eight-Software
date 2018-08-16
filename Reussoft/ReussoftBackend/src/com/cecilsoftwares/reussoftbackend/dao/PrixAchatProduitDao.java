package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.PrixAchatProduit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
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
public class PrixAchatProduitDao {

    private StringBuilder scriptSQL;
    private static PrixAchatProduitDao uniqueInstance;

    public PrixAchatProduitDao() {
    }

    public static synchronized PrixAchatProduitDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new PrixAchatProduitDao();
        }
        return uniqueInstance;
    }

    public List<PrixAchatProduit> listerTousLesPrixAchatProduits() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<PrixAchatProduit> listePrixAchatProduits;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT prixachatproduit.id, prixachatproduit.valeurUSD, prixachatproduit.dateHeure");
            scriptSQL.append(" prixachatproduit.idProduit, produit.Description");
            scriptSQL.append(" FROM prixachatproduit");
            scriptSQL.append(" LEFT JOIN produit ON prixachatproduit.idProduit = produit.id");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listePrixAchatProduits = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    PrixAchatProduit prixAchatProduit = new PrixAchatProduit(res.getString(1));
                    prixAchatProduit.setValeurUSD(res.getBigDecimal(2));
                    prixAchatProduit.setDateHeure(res.getTimestamp(3));

                    Produit produit = new Produit(res.getString(4));
                    produit.setDescription(res.getString(5));
                    prixAchatProduit.setProduit(produit);

                    listePrixAchatProduits.add(prixAchatProduit);
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return listePrixAchatProduits;
    }

    public PrixAchatProduit selectionnerPrixAchatProduitParId(String idPrixAchatProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT prixachatproduit.id, prixachatproduit.valeurUSD, prixachatproduit.dateHeure");
            scriptSQL.append(" prixachatproduit.idProduit, produit.Description");
            scriptSQL.append(" FROM prixachatproduit");
            scriptSQL.append(" LEFT JOIN produit ON prixachatproduit.idProduit = produit.id");
            scriptSQL.append(" WHERE prixachatproduit.id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idPrixAchatProduit);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    PrixAchatProduit prixAchatProduit = new PrixAchatProduit(res.getString(1));
                    prixAchatProduit.setValeurUSD(res.getBigDecimal(2));
                    prixAchatProduit.setDateHeure(res.getTimestamp(3));

                    Produit produit = new Produit(res.getString(4));
                    produit.setDescription(res.getString(5));
                    prixAchatProduit.setProduit(produit);

                    prs.close();
                    res.close();
                    connection.close();

                    return prixAchatProduit;
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return null;
    }

    public boolean enregistrerPrixAchatProduit(PrixAchatProduit prixAchatProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO produit(");
            scriptSQL.append(" description, idCategorieProduit, idReseau, active, id )");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

            prs.setString(1, produit.getDescription());
            prs.setString(2, produit.getCategorieProduit().getId());
            prs.setString(3, produit.getReseau().getId());
            prs.setInt(4, produit.isActive() ? 1 : 0);
            prs.setString(5, produit.getId());

            prs.execute();

            scriptSQL = new StringBuilder("INSERT INTO prixachatproduit(");
            scriptSQL.append(" idProduit, valeurUSD, dateHeure, id)");
            scriptSQL.append(" VALUES (?, ?, ?, ?)");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

            prs.setString(1, produit.getId());
            prs.setBigDecimal(2, produit.getPrixAchatProduit().getValeurUSD());
            prs.setTimestamp(3, new Timestamp(produit.getPrixAchatProduit().getDateHeure().getTime()));
            prs.setString(4, produit.getPrixAchatProduit().getId());

            prs.execute();

            scriptSQL = new StringBuilder("UPDATE produit SET idPrixAchat=? WHERE id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

            prs.setString(1, produit.getPrixAchatProduit().getId());
            prs.setString(2, produit.getId());

            prs.execute();
            prs.close();
            connection.close();
        }
        return true;
    }

    public PrixAchatProduit selectionnerDernierPrixAchatProduit(Produit produit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT Max(id), idProduit, valeurUSD, dateHeure");
            scriptSQL.append(" FROM prixachatproduit");
            scriptSQL.append(" WHERE prixachatproduit.idProduit=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setString(1, produit.getId());
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {
                    PrixAchatProduit prixAchatProduit = new PrixAchatProduit(res.getString(1));
                    prixAchatProduit.setProduit(new Produit(res.getString(2)));
                    prixAchatProduit.setValeurUSD(new BigDecimal(res.getInt(3)));
                    prixAchatProduit.setDateHeure(new Timestamp(res.getInt(3)));

                    prs.close();
                    res.close();
                    connection.close();

                    return prixAchatProduit;
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return null;
    }

}
