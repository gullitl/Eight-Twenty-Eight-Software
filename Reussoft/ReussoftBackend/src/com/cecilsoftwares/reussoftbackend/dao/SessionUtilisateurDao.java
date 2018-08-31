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

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            listeSessionUtilisateurs = new ArrayList();

            scriptSQL = new StringBuilder("SELECT sessionutilisateur.id, sessionutilisateur.dateHeure, sessionutilisateur.action,");
            scriptSQL.append(" sessionutilisateur.idCollaborateur, collaborateur.prenom, collaborateur.nom, collaborateur.postnom, collaborateur.surnom,");
            scriptSQL.append(" sessionutilisateur.idShop, shop.nom, shop.adresse");
            scriptSQL.append(" FROM sessionutilisateur");
            scriptSQL.append(" LEFT JOIN collaborateur ON sessionutilisateur.idSessionUtilisateur = collaborateur.id");
            scriptSQL.append(" LEFT JOIN shop ON sessionutilisateur.idShop = shop.id");
            scriptSQL.append(" ORDER BY sessionutilisateur.dateHeure, sessionutilisateur.action");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();
            if (res != null) {
                while (res.next()) {

                    SessionUtilisateur sessionutilisateur = new SessionUtilisateur(res.getString(1));
                    sessionutilisateur.setDateHeure(res.getTimestamp(2));
                    sessionutilisateur.setActionEntree(res.getInt(3) == 1);

                    Collaborateur collaborateur = new Collaborateur(res.getString(4));
                    collaborateur.setPrenom(res.getString(5));
                    collaborateur.setNom(res.getString(6));
                    collaborateur.setPostnom(res.getString(7));
                    collaborateur.setSurnom(res.getString(8));

                    Shop shop = new Shop(res.getString(9));
                    shop.setNom(res.getString(10));
                    shop.setAdresse(res.getString(11));
                    collaborateur.setShop(shop);

                    sessionutilisateur.setCollaborateur(collaborateur);

                    listeSessionUtilisateurs.add(sessionutilisateur);
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return listeSessionUtilisateurs;
    }

    public SessionUtilisateur selectionnerSessionUtilisateurParId(String idSessionUtilisateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("SELECT sessionutilisateur.id, sessionutilisateur.dateHeure, sessionutilisateur.action,");
            scriptSQL.append(" sessionutilisateur.idCollaborateur, collaborateur.prenom, collaborateur.nom, collaborateur.postnom, collaborateur.surnom,");
            scriptSQL.append(" sessionutilisateur.idShop, shop.nom, shop.adresse");
            scriptSQL.append(" FROM sessionutilisateur");
            scriptSQL.append(" LEFT JOIN collaborateur ON sessionutilisateur.idSessionUtilisateur = collaborateur.id");
            scriptSQL.append(" LEFT JOIN shop ON sessionutilisateur.idShop = shop.id");
            scriptSQL.append(" WHERE sessionutilisateur.id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idSessionUtilisateur);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    SessionUtilisateur sessionutilisateur = new SessionUtilisateur(res.getString(1));
                    sessionutilisateur.setDateHeure(res.getTimestamp(2));
                    sessionutilisateur.setActionEntree(res.getInt(3) == 1);

                    Collaborateur collaborateur = new Collaborateur(res.getString(4));
                    collaborateur.setPrenom(res.getString(5));
                    collaborateur.setNom(res.getString(6));
                    collaborateur.setPostnom(res.getString(7));
                    collaborateur.setSurnom(res.getString(8));

                    Shop shop = new Shop(res.getString(9));
                    shop.setNom(res.getString(10));
                    shop.setAdresse(res.getString(11));
                    collaborateur.setShop(shop);

                    sessionutilisateur.setCollaborateur(collaborateur);

                    prs.close();
                    res.close();
                    connection.close();

                    return sessionutilisateur;
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return null;
    }

    public boolean sauvegarderSessionUtilisateur(SessionUtilisateur sessionUtilisateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO sessionutilisateur(");
            scriptSQL.append(" id, idCollaborateur, idShop, dateHeure, actionEntree )");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

            prs.setString(1, sessionUtilisateur.getId());
            prs.setString(2, sessionUtilisateur.getCollaborateur().getId());
            prs.setString(3, sessionUtilisateur.getShop().getId());
            prs.setTimestamp(4, new Timestamp(sessionUtilisateur.getDateHeure().getTime()));
            prs.setInt(5, sessionUtilisateur.isActionEntree() ? 1 : 0);

            prs.execute();
            prs.close();
            connection.close();
        }
        return true;
    }

}
