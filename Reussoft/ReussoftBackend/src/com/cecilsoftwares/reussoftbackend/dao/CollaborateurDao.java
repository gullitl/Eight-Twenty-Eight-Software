package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Collaborateur;
import com.cecilsoftwares.reussoftmiddleend.model.ProfilUtilisateur;
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

    public Collaborateur selectionnerUtilisateur(String nomUtilisateur, String motDePasse) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT collaborateur.id, collaborateur.active,");
            scriptSQL.append(" collaborateur.prenom, collaborateur.nom, collaborateur.postnom, collaborateur.surnom,");
            scriptSQL.append(" collaborateur.nomUtilisateur, collaborateur.motDePasse,");
            scriptSQL.append(" collaborateur.idShop, shop.nom, shop.adresse, shop.active,");
            scriptSQL.append(" collaborateur.idProfilUtilisateur, profilutilisateur.description, profilutilisateur.descriptionAbregee");
            scriptSQL.append(" FROM collaborateur");
            scriptSQL.append(" LEFT JOIN shop ON collaborateur.idShop = shop.id");
            scriptSQL.append(" LEFT JOIN profilutilisateur ON collaborateur.idProfilUtilisateur = profilutilisateur.id");
            scriptSQL.append(" WHERE collaborateur.nomUtilisateur COLLATE latin1_general_cs=? AND collaborateur.motDePasse COLLATE latin1_general_cs=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setString(1, nomUtilisateur);
            prs.setString(2, motDePasse);
            res = prs.executeQuery();
            if (res != null) {
                if (res.next()) {
                    Collaborateur collaborateur = new Collaborateur(res.getString(1));
                    collaborateur.setActive(res.getInt(2) == 1);
                    collaborateur.setPrenom(res.getString(3));
                    collaborateur.setNom(res.getString(4));
                    collaborateur.setPostnom(res.getString(5));
                    collaborateur.setSurnom(res.getString(6));
                    collaborateur.setNomUtilisateur(res.getString(7));
                    collaborateur.setMotDePasse(res.getString(8));

                    Shop shop = new ShopBuilder(res.getString(9))
                            .withNom(res.getString(10))
                            .withAdresse(res.getString(11))
                            .withActive(res.getInt(12) == 1).create();

                    collaborateur.setShop(shop);

                    ProfilUtilisateur profilUtilisateur = new ProfilUtilisateur(res.getString(13));
                    profilUtilisateur.setDescription(res.getString(14));
                    profilUtilisateur.setDescriptionAbregee(res.getString(15));
                    collaborateur.setProfilUtilisateur(profilUtilisateur);

                    prs.close();
                    res.close();
                    connection.close();

                    return collaborateur;
                }
            }

            prs.close();
            res.close();
            connection.close();
        }
        return null;
    }

    public List<Collaborateur> listerTousLesCollaborateurs() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Collaborateur> listeCollaborateurs;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT collaborateur.id, collaborateur.active,");
            scriptSQL.append(" collaborateur.prenom, collaborateur.nom, collaborateur.postnom, collaborateur.surnom,");
            scriptSQL.append(" collaborateur.nomUtilisateur, collaborateur.motDePasse,");
            scriptSQL.append(" collaborateur.idShop, shop.nom, shop.adresse, shop.active,");
            scriptSQL.append(" collaborateur.idProfilUtilisateur, profilutilisateur.description,");
            scriptSQL.append(" profilutilisateur.descriptionAbregee");
            scriptSQL.append(" FROM collaborateur");
            scriptSQL.append(" LEFT JOIN shop ON collaborateur.idShop = shop.id");
            scriptSQL.append(" LEFT JOIN profilutilisateur ON collaborateur.idProfilUtilisateur = profilutilisateur.id");
            scriptSQL.append(" ORDER BY collaborateur.prenom");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeCollaborateurs = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    Collaborateur collaborateur = new Collaborateur(res.getString(1));
                    collaborateur.setActive(res.getInt(2) == 1);
                    collaborateur.setPrenom(res.getString(3));
                    collaborateur.setNom(res.getString(4));
                    collaborateur.setPostnom(res.getString(5));
                    collaborateur.setSurnom(res.getString(6));
                    collaborateur.setNomUtilisateur(res.getString(7));
                    collaborateur.setMotDePasse(res.getString(8));

                    Shop shop = new ShopBuilder(res.getString(9))
                            .withNom(res.getString(10))
                            .withAdresse(res.getString(11))
                            .withActive(res.getInt(12) == 1).create();

                    collaborateur.setShop(shop);

                    ProfilUtilisateur profilUtilisateur = new ProfilUtilisateur(res.getString(13));
                    profilUtilisateur.setDescription(res.getString(14));
                    profilUtilisateur.setDescriptionAbregee(res.getString(15));
                    collaborateur.setProfilUtilisateur(profilUtilisateur);

                    listeCollaborateurs.add(collaborateur);
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return listeCollaborateurs;
    }

    public Collaborateur selectionnerCollaborateurParId(int idCollaborateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT collaborateur.id, collaborateur.active,");
            scriptSQL.append(" collaborateur.prenom, collaborateur.nom, collaborateur.postnom, collaborateur.surnom,");
            scriptSQL.append(" collaborateur.nomUtilisateur, collaborateur.motDePasse,");
            scriptSQL.append(" collaborateur.idShop, shop.nom, shop.adresse, shop.active,");
            scriptSQL.append(" collaborateur.idProfilUtilisateur, profilutilisateur.description,");
            scriptSQL.append(" profilutilisateur.descriptionAbregee");
            scriptSQL.append(" FROM collaborateur");
            scriptSQL.append(" LEFT JOIN shop ON collaborateur.idShop = shop.id");
            scriptSQL.append(" LEFT JOIN profilutilisateur ON collaborateur.idProfilUtilisateur = profilutilisateur.id");
            scriptSQL.append(" WHERE collaborateur.id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, idCollaborateur);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    Collaborateur collaborateur = new Collaborateur(res.getString(1));
                    collaborateur.setActive(res.getInt(2) == 1);
                    collaborateur.setPrenom(res.getString(3));
                    collaborateur.setNom(res.getString(4));
                    collaborateur.setPostnom(res.getString(5));
                    collaborateur.setSurnom(res.getString(6));
                    collaborateur.setNomUtilisateur(res.getString(7));
                    collaborateur.setMotDePasse(res.getString(8));

                    Shop shop = new ShopBuilder(res.getString(9))
                            .withNom(res.getString(10))
                            .withAdresse(res.getString(11))
                            .withActive(res.getInt(12) == 1).create();

                    collaborateur.setShop(shop);

                    ProfilUtilisateur profilUtilisateur = new ProfilUtilisateur(res.getString(13));
                    profilUtilisateur.setDescription(res.getString(14));
                    profilUtilisateur.setDescriptionAbregee(res.getString(15));
                    collaborateur.setProfilUtilisateur(profilUtilisateur);

                    prs.close();
                    res.close();
                    connection.close();

                    return collaborateur;
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return null;
    }

    public boolean enregistrerCollaborateur(Collaborateur collaborateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("INSERT INTO collaborateur(");
            scriptSQL.append(" prenom, nom, postnom, surnom, nomUtilisateur, motDePasse, active,");
            scriptSQL.append(" idProfilUtilisateur,");
            scriptSQL.append(collaborateur.getShop() == null ? " id)" : " idShop, id)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?,");
            scriptSQL.append(collaborateur.getShop() == null ? " ?)" : " ?, ?)");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

            prs.setString(1, collaborateur.getPrenom());
            prs.setString(2, collaborateur.getNom());
            prs.setString(3, collaborateur.getPostnom());
            prs.setString(4, collaborateur.getSurnom());
            prs.setString(5, collaborateur.getNomUtilisateur());
            prs.setString(6, collaborateur.getMotDePasse());
            prs.setInt(7, collaborateur.isActive() ? 1 : 0);
            prs.setString(8, collaborateur.getProfilUtilisateur().getId());

            if (collaborateur.getShop() == null) {
                prs.setString(9, collaborateur.getId());
            } else {
                prs.setString(9, collaborateur.getShop().getId());
                prs.setString(10, collaborateur.getId());
            }
            prs.execute();
            prs.close();
            connection.close();
        }
        return true;
    }

    public boolean actualiserCollaborateur(Collaborateur collaborateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {

            scriptSQL = new StringBuilder("UPDATE collaborateur");
            scriptSQL.append(" SET prenom=?, nom=?, postnom=?, surnom=?, nomUtilisateur=?, motDePasse=?, active=?,");
            scriptSQL.append(" idProfilUtilisateur=?");
            scriptSQL.append(collaborateur.getShop() == null ? "" : ", idShop=?");
            scriptSQL.append(" WHERE id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

            prs.setString(1, collaborateur.getPrenom());
            prs.setString(2, collaborateur.getNom());
            prs.setString(3, collaborateur.getPostnom());
            prs.setString(4, collaborateur.getSurnom());
            prs.setString(5, collaborateur.getNomUtilisateur());
            prs.setString(6, collaborateur.getMotDePasse());
            prs.setInt(7, collaborateur.isActive() ? 1 : 0);
            prs.setString(8, collaborateur.getProfilUtilisateur().getId());

            if (collaborateur.getShop() == null) {
                prs.setString(9, collaborateur.getId());
            } else {
                prs.setString(9, collaborateur.getShop().getId());
                prs.setString(10, collaborateur.getId());
            }
            prs.execute();
            prs.close();
            connection.close();
        }
        return true;
    }

    public boolean exclureCollaborateur(String idCollaborateur) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM collaborateur WHERE id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idCollaborateur);

            prs.execute();
            prs.close();
            connection.close();
        }

        return true;
    }

}
