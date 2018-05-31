package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Collaborateur;
import com.cecilsoftwares.reussoftmiddleend.model.Collaborateur.CollaborateurBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.ProfilUtilisateur;
import com.cecilsoftwares.reussoftmiddleend.model.ProfilUtilisateur.ProfilUtilisateurBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
import com.cecilsoftwares.reussoftmiddleend.model.Shop.ShopBuilder;
import com.cecilsoftwares.reussoftmiddleend.model.Utilisateur;
import com.cecilsoftwares.reussoftmiddleend.model.Utilisateur.UtilisateurBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class UtilisateurDao {

    private StringBuilder scriptSQL;
    private static UtilisateurDao uniqueInstance;

    public UtilisateurDao() {
    }

    public static synchronized UtilisateurDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new UtilisateurDao();
        }
        return uniqueInstance;
    }

    public Utilisateur login(String nomUtilisateur, String motDePasse) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT utilisateur.code, utilisateur.nom, utilisateur.motDePasse, utilisateur.observation,");
            scriptSQL.append(" utilisateur.idProfilUtilisateur, profilutilisateur.description, profilutilisateur.descriptionAbregee, profilutilisateur.observation");
            scriptSQL.append(" utilisateur.idCollaborateur, collaborateur.prenom, collaborateur.nom, collaborateur.postnom, collaborateur.surnom,");
            scriptSQL.append(" collaborateur.observation, collaborateur.active");
            scriptSQL.append(" collaborateur.idShop, shop.nom, shop.adresse, shop.observation, shop.active");
            scriptSQL.append(" FROM utilisateur");
            scriptSQL.append(" LEFT JOIN profilutilisateur");
            scriptSQL.append(" ON utilisateur.idProfilUtilisateur = profilutilisateur.code");
            scriptSQL.append(" LEFT JOIN collaborateur");
            scriptSQL.append(" ON utilisateur.idCollaborateur = collaborateur.code");
            scriptSQL.append(" LEFT JOIN shop ON collaborateur.idShop = shop.code");
            scriptSQL.append(" WHERE utilizateur.nom=? AND utilizateur.motDePasse=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, nomUtilisateur);
            prs.setString(2, motDePasse);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    Shop shop = new ShopBuilder(res.getInt(12))
                            .nom(res.getString(13))
                            .adresse(res.getString(14))
                            .build();

                    Collaborateur collaborateur = new CollaborateurBuilder(res.getInt(7))
                            .prenom(res.getString(8))
                            .nom(res.getString(9))
                            .postnom(res.getString(10))
                            .surnom(res.getString(11))
                            .shop(shop)
                            .build();

                    ProfilUtilisateur profilUtilisateur = new ProfilUtilisateurBuilder(res.getInt(4))
                            .description(res.getString(5))
                            .descriptionAbregee(res.getString(6))
                            .build();

                    Utilisateur utilisateur = new UtilisateurBuilder(res.getInt(1))
                            .nom(res.getString(2))
                            .motDePasse(res.getString(3))
                            .profilUtilisateur(profilUtilisateur)
                            .collaborateur(collaborateur)
                            .build();

                    res.close();
                    conexao.close();

                    return utilisateur;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public List<Utilisateur> listerTousLesUtilisateurs() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Utilisateur> listeUtilisateurs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT utilisateur.code, utilisateur.nom, utilisateur.motDePasse, utilisateur.observation,");
            scriptSQL.append(" utilisateur.idProfilUtilisateur, profilutilisateur.description, profilutilisateur.descriptionAbregee, profilutilisateur.observation");
            scriptSQL.append(" utilisateur.idCollaborateur, collaborateur.prenom, collaborateur.nom, collaborateur.postnom, collaborateur.surnom,");
            scriptSQL.append(" collaborateur.observation, collaborateur.active");
            scriptSQL.append(" collaborateur.idShop, shop.nom, shop.adresse, shop.observation, shop.active");
            scriptSQL.append(" FROM utilisateur");
            scriptSQL.append(" LEFT JOIN profilutilisateur");
            scriptSQL.append(" ON utilisateur.idProfilUtilisateur = profilutilisateur.code");
            scriptSQL.append(" LEFT JOIN collaborateur");
            scriptSQL.append(" ON utilisateur.idCollaborateur = collaborateur.code");
            scriptSQL.append(" LEFT JOIN shop ON collaborateur.idShop = shop.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeUtilisateurs = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    Shop shop = new ShopBuilder(res.getInt(12))
                            .nom(res.getString(13))
                            .adresse(res.getString(14))
                            .build();

                    Collaborateur collaborateur = new CollaborateurBuilder(res.getInt(7))
                            .prenom(res.getString(8))
                            .nom(res.getString(9))
                            .postnom(res.getString(10))
                            .surnom(res.getString(11))
                            .shop(shop)
                            .build();

                    ProfilUtilisateur profilUtilisateur = new ProfilUtilisateurBuilder(res.getInt(4))
                            .description(res.getString(5))
                            .descriptionAbregee(res.getString(6))
                            .build();

                    Utilisateur utilisateur = new UtilisateurBuilder(res.getInt(1))
                            .nom(res.getString(2))
                            .motDePasse(res.getString(3))
                            .profilUtilisateur(profilUtilisateur)
                            .collaborateur(collaborateur)
                            .build();

                    res.close();
                    conexao.close();

                    listeUtilisateurs.add(utilisateur);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeUtilisateurs;
    }

    public Utilisateur selectionnerUtilisateurParCode(int codeUtilisateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT utilisateur.code, utilisateur.nom, utilisateur.motDePasse, utilisateur.observation,");
            scriptSQL.append(" utilisateur.idProfilUtilisateur, profilutilisateur.description, profilutilisateur.descriptionAbregee, profilutilisateur.observation");
            scriptSQL.append(" utilisateur.idCollaborateur, collaborateur.prenom, collaborateur.nom, collaborateur.postnom, collaborateur.surnom,");
            scriptSQL.append(" collaborateur.observation, collaborateur.active");
            scriptSQL.append(" collaborateur.idShop, shop.nom, shop.adresse, shop.observation, shop.active");
            scriptSQL.append(" FROM utilisateur");
            scriptSQL.append(" LEFT JOIN profilutilisateur");
            scriptSQL.append(" ON utilisateur.idProfilUtilisateur = profilutilisateur.code");
            scriptSQL.append(" LEFT JOIN collaborateur");
            scriptSQL.append(" ON utilisateur.idCollaborateur = collaborateur.code");
            scriptSQL.append(" LEFT JOIN shop ON collaborateur.idShop = shop.code");
            scriptSQL.append(" WHERE utilizateur.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeUtilisateur);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    Shop shop = new ShopBuilder(res.getInt(12))
                            .nom(res.getString(13))
                            .adresse(res.getString(14))
                            .build();

                    Collaborateur collaborateur = new CollaborateurBuilder(res.getInt(7))
                            .prenom(res.getString(8))
                            .nom(res.getString(9))
                            .postnom(res.getString(10))
                            .surnom(res.getString(11))
                            .shop(shop)
                            .build();

                    ProfilUtilisateur profilUtilisateur = new ProfilUtilisateurBuilder(res.getInt(4))
                            .description(res.getString(5))
                            .descriptionAbregee(res.getString(6))
                            .build();

                    Utilisateur utilisateur = new UtilisateurBuilder(res.getInt(1))
                            .nom(res.getString(2))
                            .motDePasse(res.getString(3))
                            .profilUtilisateur(profilUtilisateur)
                            .collaborateur(collaborateur)
                            .build();

                    prs.close();
                    res.close();
                    conexao.close();

                    return utilisateur;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    public boolean estUtilisateurDejaExistant(Utilisateur utilisateur, boolean modeEdition) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT code FROM utilisateur");
            scriptSQL.append(" WHERE nom=?");
            if (!modeEdition) {
                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
                prs.setString(1, utilisateur.getNom());
            } else {
                scriptSQL.append(" AND code<>?");
                prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
                prs.setString(1, utilisateur.getNom());
                prs.setInt(2, utilisateur.getCode());
            }

            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {
                    conexao.close();
                    return true;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return false;
    }

    //valide
    public boolean enregistrerUtilisateur(Utilisateur utilisateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            if (utilisateur.getCode() == 0) {
                scriptSQL = new StringBuilder("INSERT INTO utilisateur(");
                scriptSQL.append(" idProfilUtilisateur, idCollaborateur, nom, motDePasse, observation, code)");
                scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?)");
            } else {
                scriptSQL = new StringBuilder("UPDATE utilisateur(");
                scriptSQL.append(" SET idProfilUtilisateur=?, idCollaborateur=?, nom=?, motDePasse=?, observation=?)");
                scriptSQL.append(" WHERE code=?)");
            }

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, utilisateur.getCode());
            prs.setInt(2, utilisateur.getProfilUtilisateur().getCode());
            prs.setInt(3, utilisateur.getCollaborateur().getCode());
            prs.setString(4, utilisateur.getNom());
            prs.setString(5, utilisateur.getMotDePasse());
            prs.setString(6, utilisateur.getObservation());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean exclureUtilisateur(int codeUtilisateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM utilisateur WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeUtilisateur);

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

}
