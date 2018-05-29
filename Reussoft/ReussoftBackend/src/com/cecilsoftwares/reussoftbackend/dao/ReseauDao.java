package com.cecilsoftwares.reussoftbackend.dao;

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
public class ReseauDao {

    private StringBuilder scriptSQL;
    private static ReseauDao uniqueInstance;

    public ReseauDao() {
    }

    public static synchronized ReseauDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ReseauDao();
        }
        return uniqueInstance;
    }
//valide = true

    public List<Reseau> listerTousLesReseaus() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Reseau> listeReseaus;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT code, nom, nomAbrege, observation");
            scriptSQL.append(" FROM reseau");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeReseaus = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    Reseau reseau = new ReseauBuilder(res.getInt(1))
                            .nom(res.getString(2))
                            .nomAbrege(res.getString(3))
                            .observation(res.getString(4))
                            .build();

                    listeReseaus.add(reseau);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeReseaus;
    }

    //valide = true
    public Reseau selectionnerReseauParCode(int codeReseau) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT code, nom, nomAbrege, observation");
            scriptSQL.append(" FROM reseau WHERE reseau.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeReseau);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    Reseau reseau = new ReseauBuilder(res.getInt(1))
                            .nom(res.getString(2))
                            .nomAbrege(res.getString(3))
                            .observation(res.getString(4))
                            .build();

                    prs.close();
                    res.close();
                    conexao.close();

                    return reseau;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    //Valide = true
    public boolean enregistrerReseau(Reseau reseau) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO reseau(");
            scriptSQL.append(" code, nom, nomAbrege, observation)");
            scriptSQL.append(" VALUES (?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, reseau.getCode());
            prs.setString(2, reseau.getNom());
            prs.setString(3, reseau.getNomAbrege());
            prs.setString(4, reseau.getObservation());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    //valide = true
    public boolean actualiserReseau(Reseau reseau) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("UPDATE reseau");
            scriptSQL.append(" SET nom=?, nomAbrege=?, observation=?");
            scriptSQL.append(" WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, reseau.getNom());
            prs.setString(2, reseau.getNomAbrege());
            prs.setString(3, reseau.getObservation());
            prs.setInt(6, reseau.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    //valide = true
    public int selectionnerCodeReseauSubsequent() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT Max(code)+1 FROM reseau");
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

    public boolean exclureReseau(int codeReseau) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM reseau WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeReseau);

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

}
