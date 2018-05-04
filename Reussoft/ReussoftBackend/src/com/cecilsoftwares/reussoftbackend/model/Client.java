package com.cecilsoftwares.reussoftbackend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class Client {

    private final int code;
    private final String entreprise;
    private final String responsable;

    public Client(ClientBuilder clientBuilder) {
        code = clientBuilder.code;
        entreprise = clientBuilder.entreprise;
        responsable = clientBuilder.responsable;
    }

    public int getCode() {
        return code;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public String getResponsable() {
        return responsable;
    }

    public static class ClientBuilder {

        private int code;
        private String entreprise;
        private String responsable;

        public ClientBuilder(int code) {
            this.code = code;
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
