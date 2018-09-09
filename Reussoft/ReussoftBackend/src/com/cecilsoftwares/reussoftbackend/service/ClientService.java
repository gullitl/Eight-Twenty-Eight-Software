package com.cecilsoftwares.reussoftbackend.service;

import com.cecilsoftwares.reussoftbackend.dao.ClientDao;
import com.cecilsoftwares.reussoftmiddleend.model.Client;
import com.cecilsoftwares.reussoftmiddleend.util.IdGenerator;
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

    public Client selectionnerClientParId(String idClient) throws ClassNotFoundException, SQLException {
        return ClientDao.getInstance().selectionnerClientParId(idClient);
    }

    public boolean enregistrerClient(Client client) throws ClassNotFoundException, SQLException, Exception {
        if (client.getId().isEmpty()) {
            client.setId(IdGenerator.generateId());
            return ClientDao.getInstance().enregistrerClient(client);
        } else {
            return ClientDao.getInstance().actualiserClient(client);
        }
    }

    public boolean exclureClient(String idClient) throws ClassNotFoundException, SQLException {
        return ClientDao.getInstance().exclureClient(idClient);
    }

}
