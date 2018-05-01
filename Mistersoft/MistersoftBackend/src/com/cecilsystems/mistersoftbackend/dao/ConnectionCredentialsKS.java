package com.cecilsystems.mistersoftbackend.dao;

import com.cecilsystems.mistersoftbackend.model.ConnectionCredentials;

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
