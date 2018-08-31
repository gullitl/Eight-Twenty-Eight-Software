package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class CategorieProduitDao {

    private StringBuilder scriptSQL;
    private static CategorieProduitDao uniqueInstance;

    public CategorieProduitDao() {
    }

    public static synchronized CategorieProduitDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CategorieProduitDao();
        }
        return uniqueInstance;
    }

    public List<CategorieProduit> listerTousLesCategorieProduits() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<CategorieProduit> listeCategoriesProduit;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT id, description, descriptionAbregee");
            scriptSQL.append(" FROM categorieproduit");
            scriptSQL.append(" ORDER BY description");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeCategoriesProduit = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    CategorieProduit categorieProduit = new CategorieProduit(res.getString(1));
                    categorieProduit.setDescription(res.getString(2));
                    categorieProduit.setDescriptionAbregee(res.getString(3));

                    listeCategoriesProduit.add(categorieProduit);
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return listeCategoriesProduit;
    }

    public CategorieProduit selectionnerCategorieProduitParId(String idProduitParId) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT id, description, descriptionAbregee");
            scriptSQL.append(" FROM categorieproduit WHERE id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idProduitParId);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    CategorieProduit categorieProduit = new CategorieProduit(res.getString(1));
                    categorieProduit.setDescription(res.getString(2));
                    categorieProduit.setDescriptionAbregee(res.getString(3));

                    prs.close();
                    res.close();
                    connection.close();

                    return categorieProduit;
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return null;
    }

    public boolean enregistrerCategorieProduit(CategorieProduit categorieProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("INSERT INTO categorieproduit(");
            scriptSQL.append(" description, descriptionAbregee, id )");
            scriptSQL.append(" VALUES (?, ?, ?)");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

            prs.setString(1, categorieProduit.getDescription());
            prs.setString(2, categorieProduit.getDescriptionAbregee());
            prs.setString(3, categorieProduit.getId());

            prs.execute();
            prs.close();
            connection.close();
        }
        return true;
    }

    public boolean actualiserCategorieProduit(CategorieProduit categorieProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("UPDATE categorieproduit");
            scriptSQL.append(" SET description=?, descriptionAbregee=?");
            scriptSQL.append(" WHERE id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

            prs.setString(1, categorieProduit.getDescription());
            prs.setString(2, categorieProduit.getDescriptionAbregee());
            prs.setString(3, categorieProduit.getId());

            prs.execute();
            prs.close();
            connection.close();
        }
        return true;
    }

    public boolean exclureCategorieProduit(String idCategorieProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM categorieproduit WHERE id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idCategorieProduit);

            prs.execute();
            prs.close();
            connection.close();
        }

        return true;
    }

}
