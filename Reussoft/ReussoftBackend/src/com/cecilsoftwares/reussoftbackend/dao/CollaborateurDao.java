package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Collaborateur;
import com.cecilsoftwares.reussoftmiddleend.model.Collaborateur.CollaborateurBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.ProfilUtilisateur;
import com.cecilsoftwares.reussoftmiddleend.model.ProfilUtilisateur.ProfilUtilisateurBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.SessionUtilisateur;
import com.cecilsoftwares.reussoftmiddleend.model.SessionUtilisateur.SessionUtilisateurBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.Shop.ShopBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class CollaborateurDao {

    private StringBuilder scriptSQL;
    private static CollaborateurDao uniqueInstance;

    public CollaborateurDao() {
    }

    public static synchronized CollaborateurDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CollaborateurDao();
        }
        return uniqueInstance;
    }

    public SessionUtilisateur login(String nomUtilisateur, String motDePasse) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT collaborateur.code, collaborateur.active,");
            scriptSQL.append(" collaborateur.prenom, collaborateur.nom, collaborateur.postnom, collaborateur.surnom,");
            scriptSQL.append(" collaborateur.nomUtilisateur, collaborateur.motDePasse,");
            scriptSQL.append(" collaborateur.idShop, shop.nom, shop.adresse, shop.active,");
            scriptSQL.append(" collaborateur.idProfilUtilisateur, profilutilisateur.description,");
            scriptSQL.append(" profilutilisateur.descriptionAbregee, profilutilisateur.observation");
            scriptSQL.append(" FROM collaborateur");
            scriptSQL.append(" LEFT JOIN shop ON collaborateur.idShop = shop.code");
            scriptSQL.append(" LEFT JOIN profilutilisateur ON collaborateur.idProfilUtilisateur = profilutilisateur.code");
            scriptSQL.append(" WHERE collaborateur.nomUtilisateur=? AND collaborateur.motDePasse=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, nomUtilisateur);
            prs.setString(2, motDePasse);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {
                    ProfilUtilisateur profilUtilisateur = new ProfilUtilisateurBuilder(res.getInt(13))
                            .description(res.getString(14))
                            .descriptionAbregee(res.getString(15))
                            .observation(res.getString(16))
                            .build();

                    Shop shop = new ShopBuilder(res.getInt(9))
                            .nom(res.getString(10))
                            .adresse(res.getString(11))
                            .active(res.getInt(12) == 1)
                            .build();

                    Collaborateur collaborateur = new CollaborateurBuilder(res.getInt(1))
                            .active(res.getInt(2) == 1)
                            .prenom(res.getString(3))
                            .nom(res.getString(4))
                            .postnom(res.getString(5))
                            .surnom(res.getString(6))
                            .nomUtilisateur(res.getString(7))
                            .motDePasse(res.getString(8))
                            .shop(shop)
                            .profilUtilisateur(profilUtilisateur)
                            .build();

                    SessionUtilisateur sessionUtilisateur = new SessionUtilisateurBuilder(0)
                            .collaborateur(collaborateur)
                            .action("ENTRÉE")
                            .dateHeure(new Date())
                            .observation("")
                            .build();

                    prs.close();
                    res.close();
                    conexao.close();

                    return sessionUtilisateur;
                }
            }

            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public List<Collaborateur> listerTousLesCollaborateurs() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Collaborateur> listeCollaborateurs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT collaborateur.code, collaborateur.active,");
            scriptSQL.append(" collaborateur.prenom, collaborateur.nom, collaborateur.postnom, collaborateur.surnom,");
            scriptSQL.append(" collaborateur.nomUtilisateur, collaborateur.motDePasse,");
            scriptSQL.append(" collaborateur.idShop, shop.nom, shop.adresse, shop.active,");
            scriptSQL.append(" collaborateur.idProfilUtilisateur, profilutilisateur.description,");
            scriptSQL.append(" profilutilisateur.descriptionAbregee, profilutilisateur.observation");
            scriptSQL.append(" FROM collaborateur");
            scriptSQL.append(" LEFT JOIN shop ON collaborateur.idShop = shop.code");
            scriptSQL.append(" LEFT JOIN profilutilisateur ON collaborateur.idProfilUtilisateur = profilutilisateur.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeCollaborateurs = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    ProfilUtilisateur profilUtilisateur = new ProfilUtilisateurBuilder(res.getInt(13))
                            .description(res.getString(14))
                            .descriptionAbregee(res.getString(15))
                            .observation(res.getString(16))
                            .build();

                    Shop shop = new ShopBuilder(res.getInt(9))
                            .nom(res.getString(10))
                            .adresse(res.getString(11))
                            .active(res.getInt(12) == 1)
                            .build();

                    Collaborateur collaborateur = new CollaborateurBuilder(res.getInt(1))
                            .active(res.getInt(2) == 1)
                            .prenom(res.getString(3))
                            .nom(res.getString(4))
                            .postnom(res.getString(5))
                            .surnom(res.getString(6))
                            .nomUtilisateur(res.getString(7))
                            .motDePasse(res.getString(8))
                            .shop(shop)
                            .profilUtilisateur(profilUtilisateur)
                            .build();

                    listeCollaborateurs.add(collaborateur);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeCollaborateurs;
    }

    public Collaborateur selectionnerCollaborateurParCode(int codeCollaborateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT collaborateur.code, collaborateur.active,");
            scriptSQL.append(" collaborateur.prenom, collaborateur.nom, collaborateur.postnom, collaborateur.surnom,");
            scriptSQL.append(" collaborateur.nomUtilisateur, collaborateur.motDePasse,");
            scriptSQL.append(" collaborateur.idShop, shop.nom, shop.adresse, shop.active,");
            scriptSQL.append(" collaborateur.idProfilUtilisateur, profilutilisateur.description,");
            scriptSQL.append(" profilutilisateur.descriptionAbregee, profilutilisateur.observation");
            scriptSQL.append(" FROM collaborateur");
            scriptSQL.append(" LEFT JOIN shop ON collaborateur.idShop = shop.code");
            scriptSQL.append(" LEFT JOIN profilutilisateur ON collaborateur.idProfilUtilisateur = profilutilisateur.code");
            scriptSQL.append(" WHERE collaborateur.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeCollaborateur);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    ProfilUtilisateur profilUtilisateur = new ProfilUtilisateurBuilder(res.getInt(13))
                            .description(res.getString(14))
                            .descriptionAbregee(res.getString(15))
                            .observation(res.getString(16))
                            .build();

                    Shop shop = new ShopBuilder(res.getInt(9))
                            .nom(res.getString(10))
                            .adresse(res.getString(11))
                            .active(res.getInt(12) == 1)
                            .build();

                    Collaborateur collaborateur = new CollaborateurBuilder(res.getInt(1))
                            .active(res.getInt(2) == 1)
                            .prenom(res.getString(3))
                            .nom(res.getString(4))
                            .postnom(res.getString(5))
                            .surnom(res.getString(6))
                            .nomUtilisateur(res.getString(7))
                            .motDePasse(res.getString(8))
                            .shop(shop)
                            .profilUtilisateur(profilUtilisateur)
                            .build();

                    prs.close();
                    res.close();
                    conexao.close();

                    return collaborateur;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean enregistrerCollaborateur(Collaborateur collaborateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            if (collaborateur.getCode() == 0) {
                scriptSQL = new StringBuilder("INSERT INTO collaborateur(");
                scriptSQL.append(" prenom, nom, postnom, surnom, nomUtilisateur, motDePasse, active,");
                scriptSQL.append(" idProfilUtilisateur, idShop, code )");
                scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            } else {

                scriptSQL = new StringBuilder("UPDATE collaborateur");
                scriptSQL.append(" SET prenom=?, nom=?, postnom=?, surnom=?, nomUtilisateur=?, motDePasse=?, active=?,");
                scriptSQL.append(" idProfilUtilisateur=?, idShop=?");
                scriptSQL.append(" WHERE code=?");
            }

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, collaborateur.getPrenom());
            prs.setString(2, collaborateur.getNom());
            prs.setString(3, collaborateur.getPostnom());
            prs.setString(4, collaborateur.getSurnom());
            prs.setString(5, collaborateur.getNomUtilisateur());
            prs.setString(6, collaborateur.getMotDePasse());
            prs.setInt(7, collaborateur.isActive() ? 1 : 0);
            prs.setInt(8, collaborateur.getProfilUtilisateur().getCode());
            prs.setInt(9, collaborateur.getShop().getCode());
            prs.setInt(10, collaborateur.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean exclureCollaborateur(int codeCollaborateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM collaborateur WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeCollaborateur);

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

}
