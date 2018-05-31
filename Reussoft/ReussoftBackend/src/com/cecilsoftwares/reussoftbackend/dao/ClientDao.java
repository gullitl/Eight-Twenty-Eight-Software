package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Client;
import com.cecilsoftwares.reussoftmiddleend.model.Client.ClientBuilder;
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
public class ClientDao {

    private StringBuilder scriptSQL;
    private static ClientDao uniqueInstance;

    public ClientDao() {
    }

    public static synchronized ClientDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ClientDao();
        }
        return uniqueInstance;
    }

    //valide = true
    public List<Client> listerTousLesClients() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Client> listeClients;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT client.code, client.responsable, client.entreprise, client.telephone, client.observation");
            scriptSQL.append(" client.idShop, shop.nom, shop.adresse, shop.observation, shop.active");
            scriptSQL.append(" FROM client LEFT JOIN shop ON client.shopId = shop.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeClients = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    Shop shop = new ShopBuilder(res.getInt(6))
                            .nom(res.getString(7))
                            .adresse(res.getString(8))
                            .observation(res.getString(9))
                            .active(res.getInt(10) == 0)
                            .build();

                    Client client = new ClientBuilder(res.getInt(1))
                            .responsable(res.getString(2))
                            .entreprise(res.getString(3))
                            .telephone(res.getString(4))
                            .observation(res.getString(5))
                            .shop(shop)
                            .build();

                    listeClients.add(client);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeClients;
    }

    //valide = true
    public Client selectionnerClientParCode(int codeClient) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT client.code, client.responsable, client.entreprise, client.telephone, client.observation");
            scriptSQL.append(" client.idShop, shop.nom, shop.adresse, shop.observation, shop.active");
            scriptSQL.append(" FROM client LEFT JOIN shop ON client.shopId = shop.code");
            scriptSQL.append(" WHERE client.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeClient);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    Shop shop = new ShopBuilder(res.getInt(6))
                            .nom(res.getString(7))
                            .adresse(res.getString(8))
                            .observation(res.getString(9))
                            .active(res.getInt(10) == 0)
                            .build();

                    Client client = new ClientBuilder(res.getInt(1))
                            .responsable(res.getString(2))
                            .entreprise(res.getString(3))
                            .telephone(res.getString(4))
                            .observation(res.getString(5))
                            .shop(shop)
                            .build();

                    prs.close();
                    res.close();
                    conexao.close();

                    return client;
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return null;
    }

    //Valide = true
    public boolean enregistrerClient(Client client) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("INSERT INTO client(");
            scriptSQL.append(" code, responsable, entreprise, telephone, observation, idShop)");
            scriptSQL.append(" VALUES (?, ?, ?, ?, ?, ?)");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setInt(1, client.getCode());
            prs.setString(2, client.getResponsable());
            prs.setString(3, client.getEntreprise());
            prs.setString(4, client.getTelephone());
            prs.setString(5, client.getObservation());
            prs.setInt(6, client.getShop().getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    //valide = true
    public boolean actualiserClient(Client client) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("UPDATE client");
            scriptSQL.append(" SET responsable=?, entreprise=?, telephone=?, observation=?, idShop=?");
            scriptSQL.append(" WHERE codeClient=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, client.getResponsable());
            prs.setString(2, client.getEntreprise());
            prs.setString(3, client.getTelephone());
            prs.setString(4, client.getObservation());
            prs.setInt(5, client.getShop().getCode());
            prs.setInt(6, client.getCode());

            prs.execute();
            prs.close();
            conexao.close();
        }
        return true;
    }

    public boolean exclureClient(int codeClient) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("DELETE FROM client WHERE code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeClient);

            prs.execute();
            prs.close();
            conexao.close();
        }

        return true;
    }

}
