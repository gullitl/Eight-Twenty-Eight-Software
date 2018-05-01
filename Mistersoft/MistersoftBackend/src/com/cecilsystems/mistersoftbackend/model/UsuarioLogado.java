package com.cecilsystems.mistersoftbackend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class UsuarioLogado {

    private Usuario usuario;
    private boolean precisaFazerLogout;
    private static UsuarioLogado uniqueInstance;

    public UsuarioLogado() {
    }

    public static synchronized UsuarioLogado getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new UsuarioLogado();
        }
        return uniqueInstance;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isPrecisaFazerLogout() {
        return precisaFazerLogout;
    }

    public void setPrecisaFazerLogout(boolean precisaFazerLogout) {
        this.precisaFazerLogout = precisaFazerLogout;
    }

}
