package com.cecilsystems.mistersoftfrontend.enumerable;

/**
 * @author Plamedi L. Lusembo
 */
public enum PathEnum {
    REPORT_PATH("/com/cecilsystems/mistersoftfrontend/report/"),
    VIEW_PATH("/com/cecilsystems/mistersoftfrontend/view/"),
    IMAGES_PATH("/com/cecilsystems/mistersoftfrontend/resources/images/"),
    STYLES_PATH("/com/cecilsystems/mistersoftfrontend/resources/styles/");

    private final String titulo;

    PathEnum(String title) {
        this.titulo = title;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return titulo;
    }

}
