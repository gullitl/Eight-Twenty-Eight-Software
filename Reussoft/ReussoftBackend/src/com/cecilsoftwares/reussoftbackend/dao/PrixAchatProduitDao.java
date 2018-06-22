package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.PrixAchatProduit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
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

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT prixachatproduit.code, prixachatproduit.prixUSD, prixachatproduit.prixFC, prixachatproduit.dateHeure");
            scriptSQL.append(" prixachatproduit.idProduit, produit.Description,");
            scriptSQL.append(" FROM prixachatproduit");
            scriptSQL.append(" LEFT JOIN produit ON prixachatproduit.idProduit = produit.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listePrixAchatProduits = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    PrixAchatProduit prixAchatProduit = new PrixAchatProduit(1);
                    prixAchatProduit.setPrixUSD(res.getBigDecimal(2));
                    prixAchatProduit.setPrixFC(res.getBigDecimal(3));
                    prixAchatProduit.setDateHeure(res.getTimestamp(4));

                    Produit produit = new Produit(res.getInt(4));
                    produit.setDescription(res.getString(5));

                    prixAchatProduit.setProduit(produit);

                    listePrixAchatProduits.add(prixAchatProduit);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listePrixAchatProduits;
    }

    public PrixAchatProduit selectionnerPrixAchatProduitParCode(int codePrixAchatProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT prixachatproduit.code, prixachatproduit.prixUSD, prixachatproduit.prixFC, prixachatproduit.dateHeure");
            scriptSQL.append(" prixachatproduit.idProduit, produit.Description,");
            scriptSQL.append(" FROM prixachatproduit");
            scriptSQL.append(" LEFT JOIN produit ON prixachatproduit.idProduit = produit.code");
            scriptSQL.append(" WHERE prixachatproduit.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codePrixAchatProduit);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    PrixAchatProduit prixAchatProduit = new PrixAchatProduit(1);
                    prixAchatProduit.setPrixUSD(res.getBigDecimal(2));
                    prixAchatProduit.setPrixFC(res.getBigDecimal(3));
                    prixAchatProduit.setDateHeure(res.getTimestamp(4));

                    Produit produit = new Produit(res.getInt(4));
                    produit.setDescription(res.getString(5));

                    prixAchatProduit.setProduit(produit);

                    prs.close();
                    res.close();
                    conexao.close();

                    return prixAchatProduit;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean enregistrerPrixAchatProduit(PrixAchatProduit prixAchatProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO prixAchatProduit(");
            scriptSQL.append(" idProduit, prixUSD, prixFC, dateHeure, code)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, prixAchatProduit.getProduit().getCode());
            prs.setBigDecimal(2, prixAchatProduit.getPrixUSD());
            prs.setBigDecimal(3, prixAchatProduit.getPrixFC());
            prs.setTimestamp(4, new Timestamp(prixAchatProduit.getDateHeure().getTime()));
            prs.setInt(5, prixAchatProduit.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }
}
