package com.cecilsoftwares.reussoftbackend.dao;

import com.cecilsoftwares.reussoftbackend.model.ConnectionCredentials;

/**
 * @author Plamedi L. Lusembo
 */
public class ConnectionCredentialsKS {

    private ConnectionCredentials connectionCredentials;
    private static ConnectionCredentialsKS uniqueInstance;

    public ConnectionCredentialsKS() {
    }

    public static synchronized ConnectionCredentialsKS getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ConnectionCredentialsKS();
        }
        return uniqueInstance;
    }

    public ConnectionCredentials getConnectionCredentials() {
        return connectionCredentials;
    }

    public void setConnectionCredentials(ConnectionCredentials connectionCredentials) {
        this.connectionCredentials = connectionCredentials;
    }

    public static ConnectionCredentialsKS getUniqueInstance() {
        return uniqueInstance;
    }

    public static void setUniqueInstance(ConnectionCredentialsKS uniqueInstance) {
        ConnectionCredentialsKS.uniqueInstance = uniqueInstance;
    }

}
