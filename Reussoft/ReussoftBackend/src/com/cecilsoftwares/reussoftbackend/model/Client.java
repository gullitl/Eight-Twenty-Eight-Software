package com.cecilsoftwares.reussoftbackend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class Client {

    private final int codeClient;
    private final String entreprise;
    private final String responsable;

    public Client(ClientBuilder clientBuilder) {
        codeClient = clientBuilder.codeClient;
        entreprise = clientBuilder.entreprise;
        responsable = clientBuilder.responsable;
    }

    public int getCodeClient() {
        return codeClient;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public String getResponsable() {
        return responsable;
    }

    public static class ClientBuilder {

        private int codeClient;
        private String entreprise;
        private String responsable;

        public ClientBuilder(int codeClient) {
            this.codeClient = codeClient;
        }

        public ClientBuilder entreprise(String entreprise) {
            this.entreprise = entreprise;
            return this;
        }

        public ClientBuilder responsable(String responsable) {
            this.responsable = responsable;
            return this;
        }

        public Client build() {
            return new Client(this);
        }

    }

}
