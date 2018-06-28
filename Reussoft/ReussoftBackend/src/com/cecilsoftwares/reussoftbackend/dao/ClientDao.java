package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftmiddleend.model.Client;
import com.cecilsoftwares.reussoftmiddleend.model.Shop;
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

    public List<Client> listerTousLesClients() throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;
        List<Client> listeClients;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT client.code, client.nom, client.entreprise, client.telephone, ");
            scriptSQL.append(" client.idShop, shop.nom, shop.adresse, shop.active");
            scriptSQL.append(" FROM client LEFT JOIN shop ON client.idShop = shop.code");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            res = prs.executeQuery();

            listeClients = new ArrayList();
            if (res != null) {
                while (res.next()) {

                    Client client = new Client(res.getInt(1));
                    client.setNom(res.getString(2));
                    client.setEntreprise(res.getString(3));
                    client.setTelephone(res.getString(4));

                    Shop shop = new Shop(res.getInt(5));
                    shop.setNom(res.getString(6));
                    shop.setAdresse(res.getString(7));
                    shop.setActive(res.getInt(8) == 0);

                    client.setShop(shop);

                    listeClients.add(client);
                }
            }
            prs.close();
            res.close();
            conexao.close();
        }
        return listeClients;
    }

    public Client selectionnerClientParCode(int codeClient) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;
        ResultSet res;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {
            scriptSQL = new StringBuilder("SELECT client.code, client.nom, client.entreprise, client.telephone, ");
            scriptSQL.append(" client.idShop, shop.nom, shop.adresse, shop.active");
            scriptSQL.append(" FROM client LEFT JOIN shop ON client.idShop = shop.code");
            scriptSQL.append(" WHERE client.code=?");

            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));
            prs.setInt(1, codeClient);
            res = prs.executeQuery();

            if (res != null) {
                if (res.next()) {

                    Client client = new Client(res.getInt(1));
                    client.setNom(res.getString(2));
                    client.setEntreprise(res.getString(3));
                    client.setTelephone(res.getString(4));

                    Shop shop = new Shop(res.getInt(5));
                    shop.setNom(res.getString(6));
                    shop.setAdresse(res.getString(7));
                    shop.setActive(res.getInt(8) == 0);

                    client.setShop(shop);

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

    public boolean enregistrerClient(Client client) throws ClassNotFoundException, SQLException {
        PreparedStatement prs;

        try (Connection conexao = ConnectionFactory.getInstance().habiliterConnection()) {

            if (client.getCode() == 0) {
                scriptSQL = new StringBuilder("INSERT INTO client(");
                scriptSQL.append(" nom, entreprise, telephone, idShop, code )");
                scriptSQL.append(" VALUES (?, ?, ?, ?, ?)");
            } else {
                scriptSQL = new StringBuilder("UPDATE client");
                scriptSQL.append(" SET nom=?, entreprise=?, telephone=?, idShop=?");
                scriptSQL.append(" WHERE code=?");
            }
            prs = ((PreparedStatement) conexao.prepareStatement(scriptSQL.toString()));

            prs.setString(1, client.getNom());
            prs.setString(2, client.getEntreprise());
            prs.setString(3, client.getTelephone());
            prs.setInt(4, client.getShop().getCode());
            prs.setInt(5, client.getCode());

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
