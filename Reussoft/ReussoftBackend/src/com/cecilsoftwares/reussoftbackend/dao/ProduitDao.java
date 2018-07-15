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

            scriptSQL = new StringBuilder("SELECT produit.id, produit.description, produit.active,");
            scriptSQL.append(" produit.idCategorieProduit, categorieproduit.description, categorieproduit.descriptionAbregee,");
            scriptSQL.append(" produit.idReseau, reseau.nom, reseau.nomAbrege,");
            scriptSQL.append(" prixachatproduit.id, prixachatproduit.valeurUSD, prixachatproduit.dateHeure");
            scriptSQL.append(" FROM produit");
            scriptSQL.append(" LEFT JOIN categorieproduit ON produit.idCategorieProduit = categorieproduit.id");
            scriptSQL.append(" LEFT JOIN reseau ON produit.idReseau = reseau.id");
            scriptSQL.append(" LEFT JOIN prixachatproduit ON produit.id = prixachatproduit.idProduit");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Produit produit = new Produit(res.getString(1));
                    produit.setDescription(res.getString(2));
                    produit.setActive(res.getInt(3) == 1);

                    CategorieProduit categorieProduit = new CategorieProduit(res.getString(4));
                    categorieProduit.setDescription(res.getString(5));
                    categorieProduit.setDescriptionAbregee(res.getString(6));
                    produit.setCategorieProduit(categorieProduit);

                    Reseau reseau = new Reseau(res.getString(7));
                    reseau.setNom(res.getString(8));
                    reseau.setNomAbrege(res.getString(9));
                    produit.setReseau(reseau);

                    PrixAchatProduit prixAchatProduit = new PrixAchatProduit(res.getString(10));
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

    public Produit selectionnerProduitParId(String idProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT produit.id, produit.description, produit.active,");
            scriptSQL.append(" produit.idCategorieProduit, categorieproduit.description, categorieproduit.descriptionAbregee,");
            scriptSQL.append(" produit.idReseau, reseau.nom, reseau.nomAbrege,");
            scriptSQL.append(" produit.idPrixAchatProduit, prixachatproduit.valeur, prixachatproduit.dateHeure");
            scriptSQL.append(" FROM produit");
            scriptSQL.append(" LEFT JOIN categorieproduit ON produit.idCategorieProduit = categorieproduit.id");
            scriptSQL.append(" LEFT JOIN reseau ON produit.idReseau = reseau.id");
            scriptSQL.append(" WHERE produit.id=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idProduit);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    Produit produit = new Produit(res.getString(1));
                    produit.setDescription(res.getString(2));
                    produit.setActive(res.getInt(3) == 1);

                    CategorieProduit categorieProduit = new CategorieProduit(res.getString(4));
                    categorieProduit.setDescription(res.getString(5));
                    categorieProduit.setDescriptionAbregee(res.getString(6));
                    produit.setCategorieProduit(categorieProduit);

                    Reseau reseau = new Reseau(res.getString(7));
                    reseau.setNom(res.getString(8));
                    reseau.setNomAbrege(res.getString(9));
                    produit.setReseau(reseau);

                    PrixAchatProduit prixAchatProduit = new PrixAchatProduit(res.getString(10));
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
            scriptSQL = new StringBuilder("INSERT INTO produit(");
            scriptSQL.append(" description, idCategorieProduit, idReseau, active, id )");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, produit.getDescription());
            prs.setString(2, produit.getCategorieProduit().getId());
            prs.setString(3, produit.getReseau().getId());
            prs.setInt(4, produit.isActive() ? 1 : 0);
            prs.setString(5, produit.getId());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean actualiserProduit(Produit produit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("UPDATE produit");
            scriptSQL.append(" SET description=?, idCategorieProduit=?, idReseau=?, active=?");
            scriptSQL.append(" WHERE id=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, produit.getDescription());
            prs.setString(2, produit.getCategorieProduit().getId());
            prs.setString(3, produit.getReseau().getId());
            prs.setInt(4, produit.isActive() ? 1 : 0);
            prs.setString(5, produit.getId());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean exclureProduit(String idProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM produit WHERE id=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idProduit);

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }
}
