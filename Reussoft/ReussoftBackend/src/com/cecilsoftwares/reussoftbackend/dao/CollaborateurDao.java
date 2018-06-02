package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Collaborateur;
import com.cecilsoftwares.reussoftmiddleend.model.Collaborateur.CollaborateurBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.ProfilUtilisateur;
import com.cecilsoftwares.reussoftmiddleend.model.ProfilUtilisateur.ProfilUtilisateurBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.Shop.ShopBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public List<Collaborateur> listerTousLesCollaborateurs() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Collaborateur> listeCollaborateurs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT collaborateur.codeCollaborateur, collaborateur.active,");
            scriptSQL.append(" collaborateur.prenom, collaborateur.nom, collaborateur.postnom, collaborateur.surnom,");
            scriptSQL.append(" collaborateur.nomUtilisateur, collaborateur.motDePasse,");
            scriptSQL.append(" collaborateur.idShop, shop.nom, shop.adresse, shop.observation, shop.active,");
            scriptSQL.append(" collaborateur.idProfilUtilisateur, profilUtilisateur.description,");
            scriptSQL.append(" profilUtilisateur.descriptionAbregee, profilUtilisateur.observation");
            scriptSQL.append(" FROM collaborateur");
            scriptSQL.append(" LEFT JOIN shop ON collaborateur.idShop = shop.code");
            scriptSQL.append(" LEFT JOIN profilutilisateur ON collaborateur.idProfilUtilisateur = utilisateur.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeCollaborateurs = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    ProfilUtilisateur profilUtilisateur = new ProfilUtilisateurBuilder(res.getInt(18))
                            .description(res.getString(19))
                            .descriptionAbregee(res.getString(20))
                            .observation(res.getString(21))
                            .build();

                    Shop shop = new ShopBuilder(res.getInt(8))
                            .nom(res.getString(9))
                            .adresse(res.getString(10))
                            .observation(res.getString(11))
                            .active(res.getInt(12) == 1)
                            .build();

                    Collaborateur collaborateur = new CollaborateurBuilder(res.getInt(1))
                            .active(res.getInt(2) == 1)
                            .prenom(res.getString(3))
                            .nom(res.getString(4))
                            .postnom(res.getString(5))
                            .surnom(res.getString(6))
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
            scriptSQL = new StringBuilder("SELECT collaborateur.codeCollaborateur, collaborateur.active,");
            scriptSQL.append(" collaborateur.prenom, collaborateur.nom, collaborateur.postnom, collaborateur.surnom,");
            scriptSQL.append(" collaborateur.nomUtilisateur, collaborateur.motDePasse,");
            scriptSQL.append(" collaborateur.idShop, shop.nom, shop.adresse, shop.observation, shop.active,");
            scriptSQL.append(" collaborateur.idProfilUtilisateur, profilUtilisateur.description,");
            scriptSQL.append(" profilUtilisateur.descriptionAbregee, profilUtilisateur.observation");
            scriptSQL.append(" FROM collaborateur");
            scriptSQL.append(" LEFT JOIN shop ON collaborateur.idShop = shop.code");
            scriptSQL.append(" LEFT JOIN profilutilisateur ON collaborateur.idProfilUtilisateur = utilisateur.code");
            scriptSQL.append(" WHERE collaborateur.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeCollaborateur);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    ProfilUtilisateur profilUtilisateur = new ProfilUtilisateurBuilder(res.getInt(18))
                            .description(res.getString(19))
                            .descriptionAbregee(res.getString(20))
                            .observation(res.getString(21))
                            .build();

                    Shop shop = new ShopBuilder(res.getInt(8))
                            .nom(res.getString(9))
                            .adresse(res.getString(10))
                            .observation(res.getString(11))
                            .active(res.getInt(12) == 1)
                            .build();

                    Collaborateur collaborateur = new CollaborateurBuilder(res.getInt(1))
                            .active(res.getInt(2) == 1)
                            .prenom(res.getString(3))
                            .nom(res.getString(4))
                            .postnom(res.getString(5))
                            .surnom(res.getString(6))
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
                scriptSQL.append(" idShop, idUtilisateur, code )");
                scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            } else {

                scriptSQL = new StringBuilder("UPDATE collaborateur");
                scriptSQL.append(" SET prenom=?, nom=?, postnom=?, surnom=?, nomUtilisateur=?, motDePasse=?, active=?,");
                scriptSQL.append(" idUtilisateur=?, idShop=?");
                scriptSQL.append(" WHERE code=?");
            }

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, collaborateur.getCode());
            prs.setString(2, collaborateur.getPrenom());
            prs.setString(3, collaborateur.getNom());
            prs.setString(4, collaborateur.getPostnom());
            prs.setString(5, collaborateur.getSurnom());
            prs.setString(6, collaborateur.getNomUtilisateur());
            prs.setString(7, collaborateur.getMotDePasse());
            prs.setInt(8, collaborateur.isActive() ? 1 : 0);
            prs.setInt(9, collaborateur.getShop().getCode());
            prs.setInt(10, collaborateur.getProfilUtilisateur().getCode());

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
