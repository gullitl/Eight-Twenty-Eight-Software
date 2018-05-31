package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.ClientDao;
import com.cecilsoftwares.reussoftmiddleend.model.Client;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class ClientService {

    private static ClientService uniqueInstance;

    public ClientService() {
    }

    public static synchronized ClientService getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ClientService();
        }
        return uniqueInstance;
    }

    public List<Client> listerTousLesClients() throws ClassNotFoundException, SQLException {
        return ClientDao.getInstance().listerTousLesClients();
    }

    public Client selectionnerClientParCode(int codeClient) throws ClassNotFoundException, SQLException {
        return ClientDao.getInstance().selectionnerClientParCode(codeClient);
    }

    public boolean enregistrerClient(Client client) throws ClassNotFoundException, SQLException {
        return ClientDao.getInstance().enregistrerClient(client);
    }

    public boolean exclureClient(int codeClient) throws ClassNotFoundException, SQLException {
        return ClientDao.getInstance().exclureClient(codeClient);
    }

}
