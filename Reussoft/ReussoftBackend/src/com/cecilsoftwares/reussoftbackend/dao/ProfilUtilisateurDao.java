package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.ProfilUtilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class ProfilUtilisateurDao {

    private StringBuilder scriptSQL;
    private static ProfilUtilisateurDao uniqueInstance;

    public ProfilUtilisateurDao() {
    }

    public static synchronized ProfilUtilisateurDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ProfilUtilisateurDao();
        }
        return uniqueInstance;
    }

    public List<ProfilUtilisateur> listerTousLesProfilUtilisateurs()
            throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<ProfilUtilisateur> profilUtilisateurs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            profilUtilisateurs = new ArrayList();

            scriptSQL = new StringBuilder("SELECT id, description, descriptionAbregee");
            scriptSQL.append(" FROM profilutilisateur");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    ProfilUtilisateur profilUtilisateur = new ProfilUtilisateur(res.getString(1));
                    profilUtilisateur.setDescription(res.getString(2));
                    profilUtilisateur.setDescriptionAbregee(res.getString(3));

                    profilUtilisateurs.add(profilUtilisateur);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return profilUtilisateurs;
    }

    public ProfilUtilisateur selectionnerProfilUtilisateurParId(String idProfilUtilisateur)
            throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT id, description, descriptionAbregee");
            scriptSQL.append(" FROM profilutilisateur");
            scriptSQL.append(" WHERE id=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idProfilUtilisateur);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    ProfilUtilisateur profilUtilisateur = new ProfilUtilisateur(res.getString(1));
                    profilUtilisateur.setDescription(res.getString(2));
                    profilUtilisateur.setDescriptionAbregee(res.getString(3));

                    prs.close();
                    res.close();
                    conexao.close();

                    return profilUtilisateur;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean enregistrerProfilUtilisateur(ProfilUtilisateur profilUtilisateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO profilutilisateur(");
            scriptSQL.append(" description, descriptionAbregee, id )");
            scriptSQL.append(" VALUES (?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, profilUtilisateur.getDescription());
            prs.setString(2, profilUtilisateur.getDescriptionAbregee());
            prs.setString(3, profilUtilisateur.getId());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean actualiserProfilUtilisateur(ProfilUtilisateur profilUtilisateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("UPDATE profilutilisateur");
            scriptSQL.append(" SET description=?, descriptionAbregee=?");
            scriptSQL.append(" WHERE id=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, profilUtilisateur.getDescription());
            prs.setString(2, profilUtilisateur.getDescriptionAbregee());
            prs.setString(3, profilUtilisateur.getId());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean exclureProfilUtilisateur(String idProfilUtilisateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM profilutilisateur WHERE id=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idProfilUtilisateur);

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

}
