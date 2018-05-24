package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit;
import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit.CategorieProduitBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
import com.cecilsoftwares.reussoftmiddleend.model.Produit.ProduitBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Reseau;
import com.cecilsoftwares.reussoftmiddleend.model.Reseau.ReseauBuilder;
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

    //valide = true
    public List<Produit> listerTousLesProduits() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Produit> listeProduits;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeProduits = new ArrayList();

            scriptSQL = new StringBuilder("SELECT produit.code, produit.description, produit.observation,");
            scriptSQL.append(" produit.idCategorieProduit, categorieProduit.description, categorieProduit.descriptionAbregee, categorieProduit.observation,");
            scriptSQL.append(" produit.idReseau, reseau.nom, reseau.nomAbrege, reseau.observation,");
            scriptSQL.append(" FROM produit");
            scriptSQL.append(" LEFT JOIN categorieproduit ON produit.idCategorieProduit = categorieproduit.code");
            scriptSQL.append(" LEFT JOIN reseau ON produit.idReseau = reseau.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Reseau reseau = new ReseauBuilder(res.getInt(8))
                            .nom(res.getString(9))
                            .nomAbrege(res.getString(10))
                            .observation(res.getString(11))
                            .build();

                    CategorieProduit categorieProduit = new CategorieProduitBuilder(res.getInt(4))
                            .description(res.getString(5))
                            .descriptionAbregee(res.getString(6))
                            .observation(res.getString(7))
                            .build();

                    Produit produit = new ProduitBuilder(res.getInt(1))
                            .description(res.getString(2))
                            .observation(res.getString(3))
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

    //valide = true
    public Produit selectionnerProduitParCode(int codeProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT produit.code, produit.description, produit.observation,");
            scriptSQL.append(" produit.idCategorieProduit, categorieProduit.description, categorieProduit.descriptionAbregee, categorieProduit.observation,");
            scriptSQL.append(" produit.idReseau, reseau.nom, reseau.nomAbrege, reseau.observation,");
            scriptSQL.append(" FROM produit");
            scriptSQL.append(" LEFT JOIN categorieproduit ON produit.idCategorieProduit = categorieproduit.code");
            scriptSQL.append(" LEFT JOIN reseau ON produit.idReseau = reseau.code");
            scriptSQL.append(" WHERE produit.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeProduit);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    Reseau reseau = new ReseauBuilder(res.getInt(8))
                            .nom(res.getString(9))
                            .nomAbrege(res.getString(10))
                            .observation(res.getString(11))
                            .build();

                    CategorieProduit categorieProduit = new CategorieProduitBuilder(res.getInt(4))
                            .description(res.getString(5))
                            .descriptionAbregee(res.getString(6))
                            .observation(res.getString(7))
                            .build();

                    Produit produit = new ProduitBuilder(res.getInt(1))
                            .description(res.getString(2))
                            .observation(res.getString(3))
                            .reseau(reseau)
                            .categorieProduit(categorieProduit)
                            .build();

                    return produit;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    //valide = true
    public boolean enregistrerProduit(Produit produit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO produit(");
            scriptSQL.append(" code, description, idCategorieProduit, idReseau, observation");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, produit.getCode());
            prs.setString(2, produit.getDescription());
            prs.setInt(3, produit.getCategorieProduit().getCode());
            prs.setInt(4, produit.getReseau().getCode());
            prs.setString(5, produit.getObservation());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    //valide = true
    public boolean actualiserProduit(Produit produit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("UPDATE produit");
            scriptSQL.append(" SET description=?, idCategorieProduit=?, idReseau=?, observation=?");
            scriptSQL.append(" WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, produit.getDescription());
            prs.setInt(2, produit.getCategorieProduit().getCode());
            prs.setInt(3, produit.getReseau().getCode());
            prs.setString(4, produit.getObservation());
            prs.setInt(5, produit.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public int selectionnerCodeProduitSubsequent() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT Max(codeProduit)+1 FROM produit");
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {
                    int cdSubsequente = res.getInt(1);

                    prs.close();
                    res.close();
                    conexao.close();

                    return cdSubsequente;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return 0;
    }
}
