package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit;
import com.cecilsoftwares.reussoftmiddleend.model.CategorieProduit.CategorieProduitBuilder;
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

    public List<CategorieProduit> lister() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<CategorieProduit> listeCategoriesProduit;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT code, description, descriptionAbregee");
            scriptSQL.append(" FROM categorieproduit");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeCategoriesProduit = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    CategorieProduit categorieProduit = new CategorieProduitBuilder(res.getInt(1))
                            .description(res.getString(2))
                            .descriptionAbregee(res.getString(3))
                            .build();

                    listeCategoriesProduit.add(categorieProduit);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeCategoriesProduit;
    }

    public CategorieProduit selectionner(int code) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT code, description, descriptionAbregee");
            scriptSQL.append(" WHERE codeCategorieProduit=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, code);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    CategorieProduit categorieProduit = new CategorieProduitBuilder(res.getInt(1))
                            .description(res.getString(2))
                            .descriptionAbregee(res.getString(3))
                            .build();

                    prs.close();
                    res.close();
                    conexao.close();

                    return categorieProduit;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    //Valide
    public boolean enregistrerCategorieProduit(CategorieProduit categorieProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO categorieproduit(");
            scriptSQL.append(" codeCategorieProduit, description, descriptionAbregee");
            scriptSQL.append(" VALUES (?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, categorieProduit.getCode());
            prs.setString(2, categorieProduit.getDescription());
            prs.setString(3, categorieProduit.getDescriptionAbregee());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean actualiser(CategorieProduit categorieProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("UPDATE categorieproduit");
            scriptSQL.append(" SET description=?, descriptionAbregee=?");
            scriptSQL.append(" WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, categorieProduit.getDescription());
            prs.setString(2, categorieProduit.getDescriptionAbregee());
            prs.setInt(3, categorieProduit.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean exclure(int code) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM categorieproduit WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, code);

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public int selectionnerCodeCategorieProduitSubsequent()
            throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT Max(code)+1 FROM categorieproduit");
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
