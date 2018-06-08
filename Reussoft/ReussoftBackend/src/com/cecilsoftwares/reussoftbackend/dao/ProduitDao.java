package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit;
import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit.CategorieProduitBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit.ProduitBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Reseau;
import com.cecilsoftwares.reussoftmiddleend.model.Reseau.ReseauBuilder;
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
public class ProduitDao {

    private StringBuilder scriptSQL;
    private static ProduitDao uniqueInstance;

    public ProduitDao() {
    }

    public static synchronized ProduitDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ProduitDao();
        }
        return uniqueInstance;
    }

    public List<Produit> listerTousLesProduits() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Produit> listeProduits;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeProduits = new ArrayList();

            scriptSQL = new StringBuilder("SELECT produit.code, produit.description, produit.observation, produit.active,");
            scriptSQL.append(" produit.prixAchatUSD, produit.prixAchatFC,");
            scriptSQL.append(" produit.idCategorieProduit, categorieproduit.description, categorieproduit.descriptionAbregee,");
            scriptSQL.append(" categorieproduit.observation,");
            scriptSQL.append(" produit.idReseau, reseau.nom, reseau.nomAbrege, reseau.observation");
            scriptSQL.append(" FROM produit");
            scriptSQL.append(" LEFT JOIN categorieproduit ON produit.idCategorieProduit = categorieproduit.code");
            scriptSQL.append(" LEFT JOIN reseau ON produit.idReseau = reseau.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Reseau reseau = new ReseauBuilder(res.getInt(11))
                            .nom(res.getString(12))
                            .nomAbrege(res.getString(13))
                            .observation(res.getString(14))
                            .build();

                    CategorieProduit categorieProduit = new CategorieProduitBuilder(res.getInt(7))
                            .description(res.getString(8))
                            .descriptionAbregee(res.getString(9))
                            .observation(res.getString(10))
                            .build();

                    Produit produit = new ProduitBuilder(res.getInt(1))
                            .description(res.getString(2))
                            .observation(res.getString(3))
                            .active(res.getInt(4) == 1)
                            .prixAchatUSD(new BigDecimal(res.getString(5)))
                            .prixAchatFC(new BigDecimal(res.getString(6)))
                            .reseau(reseau)
                            .categorieProduit(categorieProduit)
                            .build();

                    listeProduits.add(produit);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeProduits;
    }

    public Produit selectionnerProduitParCode(int codeProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT produit.code, produit.description, produit.observation, produit.active,");
            scriptSQL.append(" produit.prixAchatUSD, produit.prixAchatFC,");
            scriptSQL.append(" produit.idCategorieProduit, categorieproduit.description, categorieproduit.descriptionAbregee,");
            scriptSQL.append("  categorieproduit.observation,");
            scriptSQL.append(" produit.idReseau, reseau.nom, reseau.nomAbrege, reseau.observation");
            scriptSQL.append(" FROM produit");
            scriptSQL.append(" LEFT JOIN categorieproduit ON produit.idCategorieProduit = categorieproduit.code");
            scriptSQL.append(" LEFT JOIN reseau ON produit.idReseau = reseau.code");
            scriptSQL.append(" WHERE produit.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeProduit);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    Reseau reseau = new ReseauBuilder(res.getInt(11))
                            .nom(res.getString(12))
                            .nomAbrege(res.getString(13))
                            .observation(res.getString(14))
                            .build();

                    CategorieProduit categorieProduit = new CategorieProduitBuilder(res.getInt(7))
                            .description(res.getString(8))
                            .descriptionAbregee(res.getString(9))
                            .observation(res.getString(10))
                            .build();

                    Produit produit = new ProduitBuilder(res.getInt(1))
                            .description(res.getString(2))
                            .observation(res.getString(3))
                            .active(res.getInt(4) == 1)
                            .prixAchatUSD(new BigDecimal(res.getString(5)))
                            .prixAchatFC(new BigDecimal(res.getString(6)))
                            .reseau(reseau)
                            .categorieProduit(categorieProduit)
                            .build();

                    prs.close();
                    res.close();
                    conexao.close();

                    return produit;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean enregistrerProduit(Produit produit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            if (produit.getCode() == 0) {

                scriptSQL = new StringBuilder("INSERT INTO produit(");
                scriptSQL.append(" description, idCategorieProduit, idReseau, prixAchatUSD, prixAchatFC, observation, active, code )");
                scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            } else {

                scriptSQL = new StringBuilder("UPDATE produit");
                scriptSQL.append(" SET description=?, idCategorieProduit=?, idReseau=?, prixAchatUSD=?, prixAchatFC=?, observation=?, active=?");
                scriptSQL.append(" WHERE code=?");
            }

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, produit.getDescription());
            prs.setInt(2, produit.getCategorieProduit().getCode());
            prs.setInt(3, produit.getReseau().getCode());
            prs.setBigDecimal(4, produit.getPrixAchatUSD());
            prs.setBigDecimal(5, produit.getPrixAchatFC());
            prs.setString(6, produit.getObservation());
            prs.setInt(7, produit.isActive() ? 1 : 0);
            prs.setInt(8, produit.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean exclureProduit(int codeProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM produit WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeProduit);

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }
}
