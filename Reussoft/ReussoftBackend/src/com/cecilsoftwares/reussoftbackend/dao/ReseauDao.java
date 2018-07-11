package com.cecilsoftwares.reussoftbackend.dao;

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

    public List<Reseau> listerTousLesReseaus() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Reseau> listeReseaus;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT code, nom, nomAbrege, active");
            scriptSQL.append(" FROM reseau");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeReseaus = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    Reseau reseau = new Reseau(res.getInt(1));
                    reseau.setNom(res.getString(2));
                    reseau.setNomAbrege(res.getString(3));
                    reseau.setActive(res.getInt(4) == 1);

                    listeReseaus.add(reseau);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeReseaus;
    }

    public Reseau selectionnerReseauParCode(int codeReseau) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT code, nom, nomAbrege, active");
            scriptSQL.append(" FROM reseau");
            scriptSQL.append(" WHERE reseau.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeReseau);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    Reseau reseau = new Reseau(res.getInt(1));
                    reseau.setNom(res.getString(2));
                    reseau.setNomAbrege(res.getString(3));
                    reseau.setActive(res.getInt(4) == 1);

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

    public boolean enregistrerReseau(Reseau reseau) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            if (reseau.getCode() == 0) {

                scriptSQL = new StringBuilder("INSERT INTO reseau(");
                scriptSQL.append(" nom, nomAbrege, active, code)");
                scriptSQL.append(" VALUES (?, ?, ?, ?)");
            } else {

                scriptSQL = new StringBuilder("UPDATE reseau");
                scriptSQL.append(" SET nom=?, nomAbrege=?, active=?");
                scriptSQL.append(" WHERE code=?");
            }
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, reseau.getNom());
            prs.setString(2, reseau.getNomAbrege());
            prs.setInt(3, reseau.isActive() ? 1 : 0);
            prs.setInt(4, reseau.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
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
