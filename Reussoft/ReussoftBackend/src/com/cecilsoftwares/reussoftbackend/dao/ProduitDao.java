package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit;
import com.cecilsoftwares.reussoftmiddleend.model.PrixAchatProduit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Reseau;
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

            scriptSQL = new StringBuilder("SELECT produit.code, produit.description, produit.active,");
            scriptSQL.append(" produit.idCategorieProduit, categorieproduit.description, categorieproduit.descriptionAbregee,");
            scriptSQL.append(" produit.idReseau, reseau.nom, reseau.nomAbrege,");
            scriptSQL.append(" produit.idPrixAchatProduit, prixachatproduit.valeur, prixachatproduit.dateHeure");
            scriptSQL.append(" FROM produit");
            scriptSQL.append(" LEFT JOIN categorieproduit ON produit.idCategorieProduit = categorieproduit.code");
            scriptSQL.append(" LEFT JOIN reseau ON produit.idReseau = reseau.code");
            scriptSQL.append(" LEFT JOIN prixachatproduit ON produit.idPrixAchatProduit = prixachatproduit.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Produit produit = new Produit(res.getInt(1));
                    produit.setDescription(res.getString(2));
                    produit.setActive(res.getInt(3) == 1);

                    CategorieProduit categorieProduit = new CategorieProduit(res.getInt(4));
                    categorieProduit.setDescription(res.getString(5));
                    categorieProduit.setDescriptionAbregee(res.getString(6));
                    produit.setCategorieProduit(categorieProduit);

                    Reseau reseau = new Reseau(res.getInt(7));
                    reseau.setNom(res.getString(8));
                    reseau.setNomAbrege(res.getString(9));
                    produit.setReseau(reseau);

                    PrixAchatProduit prixAchatProduit = new PrixAchatProduit(res.getInt(10));
                    prixAchatProduit.setValeurUSD(res.getBigDecimal(11));
                    prixAchatProduit.setDateHeure(res.getTimestamp(12));
                    produit.setPrixAchatProduit(prixAchatProduit);

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

            scriptSQL = new StringBuilder("SELECT produit.code, produit.description, produit.active,");
            scriptSQL.append(" produit.idCategorieProduit, categorieproduit.description, categorieproduit.descriptionAbregee,");
            scriptSQL.append(" produit.idReseau, reseau.nom, reseau.nomAbrege,");
            scriptSQL.append(" produit.idPrixAchatProduit, prixachatproduit.valeur, prixachatproduit.dateHeure");
            scriptSQL.append(" FROM produit");
            scriptSQL.append(" LEFT JOIN categorieproduit ON produit.idCategorieProduit = categorieproduit.code");
            scriptSQL.append(" LEFT JOIN reseau ON produit.idReseau = reseau.code");
            scriptSQL.append(" WHERE produit.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeProduit);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    Produit produit = new Produit(res.getInt(1));
                    produit.setDescription(res.getString(2));
                    produit.setActive(res.getInt(3) == 1);

                    CategorieProduit categorieProduit = new CategorieProduit(res.getInt(4));
                    categorieProduit.setDescription(res.getString(5));
                    categorieProduit.setDescriptionAbregee(res.getString(6));
                    produit.setCategorieProduit(categorieProduit);

                    Reseau reseau = new Reseau(res.getInt(7));
                    reseau.setNom(res.getString(8));
                    reseau.setNomAbrege(res.getString(9));
                    produit.setReseau(reseau);

                    PrixAchatProduit prixAchatProduit = new PrixAchatProduit(res.getInt(10));
                    prixAchatProduit.setValeurUSD(res.getBigDecimal(11));
                    prixAchatProduit.setDateHeure(res.getTimestamp(12));
                    produit.setPrixAchatProduit(prixAchatProduit);

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
                scriptSQL.append(" description, idCategorieProduit, idReseau, active, code )");
                scriptSQL.append(" VALUES (?, ?, ?, ?, ?)");

            } else {

                scriptSQL = new StringBuilder("UPDATE produit");
                scriptSQL.append(" SET description=?, idCategorieProduit=?, idReseau=?, active=?");
                scriptSQL.append(" WHERE code=?");
            }

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, produit.getDescription());
            prs.setInt(2, produit.getCategorieProduit().getCode());
            prs.setInt(3, produit.getReseau().getCode());
            prs.setInt(4, produit.isActive() ? 1 : 0);
            prs.setInt(5, produit.getCode());

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
