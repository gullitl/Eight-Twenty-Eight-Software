package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.PrixVenteProduit;
import com.cecilsoftwares.reussoftmiddleend.model.PrixVenteProduitShop;
import com.cecilsoftwares.reussoftmiddleend.model.Produit;
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
public class PrixVenteProduitDao {

    private StringBuilder scriptSQL;
    private static PrixVenteProduitDao uniqueInstance;

    public PrixVenteProduitDao() {
    }

    public static synchronized PrixVenteProduitDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new PrixVenteProduitDao();
        }
        return uniqueInstance;
    }

    public List<PrixVenteProduit> listerTousLesPrixVenteProduits() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<PrixVenteProduit> listePrixVenteProduits;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT prixventeproduit.id, prixventeproduit.valeurUSD, prixventeproduit.dateHeure");
            scriptSQL.append(" prixventeproduit.idProduit, produit.Description,");
            scriptSQL.append(" prixventeproduit.idShop, shop.nom, shop.adresse, shop.active");
            scriptSQL.append(" FROM prixventeproduit");
            scriptSQL.append(" LEFT JOIN produit ON prixventeproduit.idProduit = produit.id");
            scriptSQL.append(" LEFT JOIN shop ON prixventeproduit.idShop = shop.id");
            scriptSQL.append(" ORDER BY shop.nom, produit.Description");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listePrixVenteProduits = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    PrixVenteProduit prixVenteProduit = new PrixVenteProduit(res.getString(1));
                    prixVenteProduit.setValeurUSD(res.getBigDecimal(2));
                    prixVenteProduit.setDateHeure(res.getTimestamp(3));

                    Produit produit = new Produit(res.getString(4));
                    produit.setDescription(res.getString(5));
                    prixVenteProduit.setProduit(produit);

                    Shop shop = new Shop(res.getString(6));
                    shop.setNom(res.getString(7));
                    shop.setAdresse(res.getString(8));
                    shop.setActive(res.getInt(9) == 0);
                    prixVenteProduit.setShop(shop);

                    listePrixVenteProduits.add(prixVenteProduit);
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return listePrixVenteProduits;
    }

    public List<PrixVenteProduit> selectionnerPrixVenteProduitParIdProduit(String idProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<PrixVenteProduit> listePrixVenteProduits;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT prixventeproduit.id, prixventeproduit.valeurUSD, prixventeproduit.dateHeure");
            scriptSQL.append(" prixventeproduit.idProduit, produit.Description,");
            scriptSQL.append(" prixventeproduit.idShop, shop.nom, shop.adresse, shop.active");
            scriptSQL.append(" FROM prixventeproduit");
            scriptSQL.append(" LEFT JOIN produit ON prixventeproduit.idProduit = produit.id");
            scriptSQL.append(" LEFT JOIN shop ON prixventeproduit.idShop = shop.id");
            scriptSQL.append(" WHERE prixventeproduit.id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idProduit);
            res = prs.executeQuery();

            listePrixVenteProduits = new ArrayList();
            if (res != null) {
                if (res.next()) {

                    PrixVenteProduit prixVenteProduit = new PrixVenteProduit(res.getString(1));
                    prixVenteProduit.setValeurUSD(res.getBigDecimal(2));
                    prixVenteProduit.setDateHeure(res.getTimestamp(3));

                    Produit produit = new Produit(res.getString(4));
                    produit.setDescription(res.getString(5));
                    prixVenteProduit.setProduit(produit);

                    Shop shop = new Shop(res.getString(6));
                    shop.setNom(res.getString(7));
                    shop.setAdresse(res.getString(8));
                    shop.setActive(res.getInt(9) == 0);
                    prixVenteProduit.setShop(shop);

                    listePrixVenteProduits.add(prixVenteProduit);

                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return listePrixVenteProduits;
    }

    public PrixVenteProduit selectionnerPrixVenteProduitParId(String idPrixVenteProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT prixventeproduit.id, prixventeproduit.valeurUSD, prixventeproduit.dateHeure");
            scriptSQL.append(" prixventeproduit.idProduit, produit.Description,");
            scriptSQL.append(" prixventeproduit.idShop, shop.nom, shop.adresse, shop.active");
            scriptSQL.append(" FROM prixventeproduit");
            scriptSQL.append(" LEFT JOIN produit ON prixventeproduit.idProduit = produit.id");
            scriptSQL.append(" LEFT JOIN shop ON prixventeproduit.idShop = shop.id");
            scriptSQL.append(" WHERE prixventeproduit.id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idPrixVenteProduit);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    PrixVenteProduit prixVenteProduit = new PrixVenteProduit(res.getString(1));
                    prixVenteProduit.setValeurUSD(res.getBigDecimal(2));
                    prixVenteProduit.setDateHeure(res.getTimestamp(3));

                    Produit produit = new Produit(res.getString(4));
                    produit.setDescription(res.getString(5));
                    prixVenteProduit.setProduit(produit);

                    Shop shop = new Shop(res.getString(6));
                    shop.setNom(res.getString(7));
                    shop.setAdresse(res.getString(8));
                    shop.setActive(res.getInt(9) == 0);
                    prixVenteProduit.setShop(shop);

                    prs.close();
                    res.close();
                    connection.close();

                    return prixVenteProduit;
                }
            }
            prs.close();
            res.close();
            connection.close();
        }
        return null;
    }

    public boolean enregistrerPrixVenteProduit(PrixVenteProduit prixVenteProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO prixventeproduit(");
            scriptSQL.append(" idProduit, idShop, valeurUSD, dateHeure, id)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

            prs.setString(1, prixVenteProduit.getProduit().getId());
            prs.setString(2, prixVenteProduit.getShop().getId());
            prs.setBigDecimal(3, prixVenteProduit.getValeurUSD());
            prs.setTimestamp(4, new Timestamp(prixVenteProduit.getDateHeure().getTime()));
            prs.setString(5, prixVenteProduit.getId());

            prs.execute();
            prs.close();
            connection.close();
        }
        return true;
    }

    public boolean enregistrerPrixVenteProduitShop(PrixVenteProduit prixVenteProduit, List<PrixVenteProduitShop> prixVenteProduitShops) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO prixventeproduit(");
            scriptSQL.append(" id, idProduit, , dateHeure, valeurUSD, idShop)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));

            prs.setString(1, prixVenteProduit.getId());
            prs.setString(2, prixVenteProduit.getProduit().getId());
            prs.setTimestamp(3, new Timestamp(prixVenteProduit.getDateHeure().getTime()));

            for (PrixVenteProduitShop prixVenteProduitShop : prixVenteProduitShops) {
                prs.setBigDecimal(4, prixVenteProduitShop.getValeurUSD());
                prs.setString(5, prixVenteProduitShop.getShop().getId());
                prs.execute();
            }
            prs.close();
            connection.close();
        }
        return true;
    }

    public boolean exclurePrixVenteProduit(String idPrixVenteProduit) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection connection = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM prixventeproduit WHERE id=?");

            prs = ((PreparedStatement) connection.prepareStatement(scriptSQL.toString()));
            prs.setString(1, idPrixVenteProduit);

            prs.execute();
            prs.close();
            connection.close();
        }

        return true;
    }

}
