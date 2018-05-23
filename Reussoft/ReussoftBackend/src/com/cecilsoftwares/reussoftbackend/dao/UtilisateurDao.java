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
            scriptSQL = new StringBuilder("SELECT utilisateur.code, utilisateur.nom, utilisateur.motDePasse,");
            scriptSQL.append(" profilutilisateur.code, profilutilisateur.description, profilutilisateur.descriptionAbregee,");
            scriptSQL.append(" collaborateur.code, collaborateur.prenom, collaborateur.nom, collaborateur.postnom, collaborateur.surnom,");
            scriptSQL.append(" shop.code, shop.nom, shop.adresse");
            scriptSQL.append(" FROM utilisateur");
            scriptSQL.append(" LEFT JOIN profilutilisateur");
            scriptSQL.append(" ON utilisateur.idProfilUtilisateur = profilutilisateur.code");
            scriptSQL.append(" LEFT JOIN collaborateur");
            scriptSQL.append(" ON utilisateur.idCollaborateur = collaborateur.code");
            scriptSQL.append(" LEFT JOIN shop");
            scriptSQL.append(" ON collaborateur.idShop = shop.code");
            scriptSQL.append(" WHERE utilizateur.nom=? AND utilizateur.motDePasse=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setString(1, nomUtilisateur);
            prs.setString(2, motDePasse);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    ProfilUtilisateur profilUtilisateur = new ProfilUtilisateurBuilder(res.getInt(4))
                            .description(res.getString(5))
                            .descriptionAbregee(res.getString(6))
                            .build();

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
//valide

    public List<Utilisateur> listerTousLesUtilisateurs() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Utilisateur> listeUtilisateurs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT utilisateur.code, utilisateur.nom, utilisateur.motDePasse,");
            scriptSQL.append(" profilutilisateur.code, profilutilisateur.description, profilutilisateur.descriptionAbregee,");
            scriptSQL.append(" collaborateur.code, collaborateur.prenom, collaborateur.nom, collaborateur.postnom, collaborateur.surnom,");
            scriptSQL.append(" shop.code, shop.nom, shop.adresse");
            scriptSQL.append(" FROM utilisateur");
            scriptSQL.append(" LEFT JOIN profilutilisateur");
            scriptSQL.append(" ON utilisateur.idProfilUtilisateur = profilutilisateur.code");
            scriptSQL.append(" LEFT JOIN collaborateur");
            scriptSQL.append(" ON utilisateur.idCollaborateur = collaborateur.code");
            scriptSQL.append(" LEFT JOIN shop");
            scriptSQL.append(" ON collaborateur.idShop = shop.code");
            scriptSQL.append(" WHERE utilizateur.nom=? AND utilizateur.motDePasse=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeUtilisateurs = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    ProfilUtilisateur profilUtilisateur = new ProfilUtilisateurBuilder(res.getInt(4))
                            .description(res.getString(5))
                            .descriptionAbregee(res.getString(6))
                            .build();

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

                    Utilisateur utilisateur = new UtilisateurBuilder(res.getInt(1))
                            .nom(res.getString(2))
                            .motDePasse(res.getString(3))
                            .profilUtilisateur(profilUtilisateur)
                            .collaborateur(collaborateur)
                            .build();

                    listeUtilisateurs.add(utilisateur);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeUtilisateurs;
    }

    //Valide
    public Utilisateur selectionnerUtilisateurParCode(int code) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT utilisateur.code, utilisateur.nom, utilisateur.motDePasse,");
            scriptSQL.append(" profilutilisateur.code, profilutilisateur.description, profilutilisateur.descriptionAbregee,");
            scriptSQL.append(" collaborateur.code, collaborateur.prenom, collaborateur.nom, collaborateur.postnom, collaborateur.surnom,");
            scriptSQL.append(" shop.code, shop.nom, shop.adresse");
            scriptSQL.append(" FROM utilisateur");
            scriptSQL.append(" LEFT JOIN profilutilisateur");
            scriptSQL.append(" ON utilisateur.idProfilUtilisateur = profilutilisateur.code");
            scriptSQL.append(" LEFT JOIN collaborateur");
            scriptSQL.append(" ON utilisateur.idCollaborateur = collaborateur.code");
            scriptSQL.append(" LEFT JOIN shop");
            scriptSQL.append(" ON collaborateur.idShop = shop.code");
            scriptSQL.append(" WHERE utilizateur.nom=? AND utilizateur.motDePasse=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, code);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {

                    ProfilUtilisateur profilUtilisateur = new ProfilUtilisateurBuilder(res.getInt(4))
                            .description(res.getString(5))
                            .descriptionAbregee(res.getString(6))
                            .build();

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
            scriptSQL = new StringBuilder("INSERT INTO utilisateur(");
            scriptSQL.append(" code, idProfilUtilisateur, idCollaborateur, nom, motDePasse)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, utilisateur.getCode());
            prs.setInt(2, utilisateur.getProfilUtilisateur().getCode());
            prs.setInt(3, utilisateur.getCollaborateur().getCode());
            prs.setString(4, utilisateur.getNom());
            prs.setString(5, utilisateur.getMotDePasse());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean actualiser(Collaborateur collaborateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("UPDATE utilisateur(");
            scriptSQL.append(" SET idProfilUtilisateur=?, idCollaborateur=?, nom, motDePasse)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?)");

            scriptSQL = new StringBuilder("UPDATE collaborateur");
            scriptSQL.append(" SET preNom=?, nom=?, postnom=?, surnom=?, utilisateur=?,");
            scriptSQL.append(" idProfilUtilisateur=?, motDePasse=?, idShop=?");
            scriptSQL.append(" WHERE codeCollaborateur=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, collaborateur.getCode());
            prs.setString(2, collaborateur.getPrenom());
            prs.setString(3, collaborateur.getNom());
            prs.setString(4, collaborateur.getPostnom());
            prs.setString(5, collaborateur.getSurnom());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean exclure(int codeCollaborateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM collaborateur WHERE codeCollaborateur=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeCollaborateur);

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public int selectionnerCodeCollaborateurSubsequent() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT Max(codeCollaborateur)+1 FROM collaborateur");
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
