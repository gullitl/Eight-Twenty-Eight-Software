package com.cecilsoftwares.reussoftmiddleend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class Version {

    private final int code;
    private final String description;
    private final int xmajeur;
    private final int ymineur;
    private final int zcorrection;
    private final int versionAnterieur;
    private final String observation;

    public Version(VersionBuilder versionBuilder) {
        code = versionBuilder.code;
        description = versionBuilder.description;
        xmajeur = versionBuilder.xmajeur;
        ymineur = versionBuilder.ymineur;
        zcorrection = versionBuilder.zcorrection;
        versionAnterieur = versionBuilder.versionAnterieur;
        observation = versionBuilder.observation;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public int getXmajeur() {
        return xmajeur;
    }

    public int getYmineur() {
        return ymineur;
    }

    public int getZcorrection() {
        return zcorrection;
    }

    public int getVersionAnterieur() {
        return versionAnterieur;
    }

    public String getObservation() {
        return observation;
    }

    public static class VersionBuilder {

        private int code;
        private String description;
        private int xmajeur;
        private int ymineur;
        private int zcorrection;
        private int versionAnterieur;
        private String observation;

        public VersionBuilder(int code) {
            this.code = code;
        }

        public VersionBuilder description(String description) {
            this.description = description;
            return this;
        }

        public VersionBuilder xmajeur(int xmajeur) {
            this.xmajeur = xmajeur;
            return this;
        }

        public VersionBuilder ymineur(int ymineur) {
            this.ymineur = ymineur;
            return this;
        }

        public VersionBuilder zcorrection(int zcorrection) {
            this.zcorrection = zcorrection;
            return this;
        }

        public VersionBuilder versionAnterieur(int versionAnterieur) {
            this.versionAnterieur = versionAnterieur;
            return this;
        }

        public VersionBuilder observation(String observation) {
            this.observation = observation;
            return this;
        }

        public Version build() {
            return new Version(this);
        }

    }

}
