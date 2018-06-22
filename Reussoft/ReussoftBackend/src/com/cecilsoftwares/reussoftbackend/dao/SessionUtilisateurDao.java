package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Collaborateur;
import com.cecilsoftwares.reussoftmiddleend.model.SessionUtilisateur;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
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
public class SessionUtilisateurDao {

    private StringBuilder scriptSQL;
    private static SessionUtilisateurDao uniqueInstance;

    public SessionUtilisateurDao() {
    }

    public static synchronized SessionUtilisateurDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SessionUtilisateurDao();
        }
        return uniqueInstance;
    }

    public List<SessionUtilisateur> listerToutesLesSessionsUtilisateur() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<SessionUtilisateur> listeSessionUtilisateurs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            listeSessionUtilisateurs = new ArrayList();

            scriptSQL = new StringBuilder("SELECT sessionutilisateur.code, sessionutilisateur.dateheure, sessionutilisateur.log, sessionutilisateur.observation,");
            scriptSQL.append(" sessionutilisateur.idCollaborateur, Collaborateur.prenom, Collaborateur.nom, Collaborateur.postnom, Collaborateur.surnom,");
            scriptSQL.append(" sessionutilisateur.idShop, shop.nom, shop.adresse");
            scriptSQL.append(" FROM sessionutilisateur");
            scriptSQL.append(" LEFT JOIN Collaborateur ON sessionutilisateur.idSessionUtilisateur = Collaborateur.code");
            scriptSQL.append(" LEFT JOIN shop ON sessionutilisateur.idShop = shop.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    Shop shop = new Shop(res.getInt(8));
                    shop.setNom(res.getString(9));
                    shop.setAdresse(res.getString(10));

                    Collaborateur collaborateur = new Collaborateur(res.getInt(4));
                    collaborateur.setNom(res.getString(5));
                    collaborateur.setPostnom(res.getString(6));
                    collaborateur.setSurnom(res.getString(7));

                    SessionUtilisateur sessionutilisateur = new SessionUtilisateur(res.getInt(1));
                    sessionutilisateur.setDateHeure(res.getTimestamp(2));
                    sessionutilisateur.setAction(res.getString(3));
                    sessionutilisateur.setCollaborateur(collaborateur);

                    listeSessionUtilisateurs.add(sessionutilisateur);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeSessionUtilisateurs;
    }

    public SessionUtilisateur selectionnerSessionUtilisateurParCode(int codeSessionUtilisateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT sessionutilisateur.code, sessionutilisateur.dateheure, sessionutilisateur.log,");
            scriptSQL.append(" sessionutilisateur.idCollaborateur, Collaborateur.prenom, Collaborateur.nom, Collaborateur.postnom, Collaborateur.surnom,");
            scriptSQL.append(" sessionutilisateur.idShop, shop.nom, shop.adresse");
            scriptSQL.append(" FROM sessionutilisateur");
            scriptSQL.append(" LEFT JOIN Collaborateur ON sessionutilisateur.idSessionUtilisateur = Collaborateur.code");
            scriptSQL.append(" LEFT JOIN shop ON sessionutilisateur.idShop = shop.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeSessionUtilisateur);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    Shop shop = new Shop(res.getInt(8));
                    shop.setNom(res.getString(9));
                    shop.setAdresse(res.getString(10));

                    Collaborateur collaborateur = new Collaborateur(res.getInt(4));
                    collaborateur.setNom(res.getString(5));
                    collaborateur.setPostnom(res.getString(6));
                    collaborateur.setSurnom(res.getString(7));

                    SessionUtilisateur sessionutilisateur = new SessionUtilisateur(res.getInt(1));
                    sessionutilisateur.setDateHeure(res.getTimestamp(2));
                    sessionutilisateur.setAction(res.getString(3));
                    sessionutilisateur.setCollaborateur(collaborateur);

                    prs.close();
                    res.close();
                    conexao.close();

                    return sessionutilisateur;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean sauvegarderSessionUtilisateur(SessionUtilisateur sessionUtilisateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO sessionutilisateur(");
            scriptSQL.append(" code, dateheure, log");
            scriptSQL.append(" VALUES (?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, sessionUtilisateur.getCode());
            prs.setTimestamp(2, new Timestamp(sessionUtilisateur.getDateHeure().getTime()));
            prs.setString(3, sessionUtilisateur.getAction());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

}
