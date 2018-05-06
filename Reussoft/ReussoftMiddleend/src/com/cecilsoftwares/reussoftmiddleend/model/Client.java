package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class Client {

    private final int code;
    private final String entreprise;
    private final String responsable;
    private final String observation;

    public Client(ClientBuilder clientBuilder) {
        code = clientBuilder.code;
        entreprise = clientBuilder.entreprise;
        responsable = clientBuilder.responsable;
        observation = clientBuilder.observation;
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

    public String getObservation() {
        return observation;
    }

    public static class ClientBuilder {

        private int code;
        private String entreprise;
        private String responsable;
        private String observation;

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

        public ClientBuilder observation(String observation) {
            this.observation = observation;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}
