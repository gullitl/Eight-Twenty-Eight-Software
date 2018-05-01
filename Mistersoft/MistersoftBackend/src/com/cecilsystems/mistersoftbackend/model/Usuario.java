package com.cecilsystems.mistersoftbackend.model;

/**
 * @author Plamedi L. Lusembo
 */
public class Usuario {

    private int cdUsuario;
    private GrupoUsuario grupoUsuario;
    private String email;
    private String senha;
    private String nome;
    private String fotoPath;

    public Usuario() {
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public int getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(int cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public GrupoUsuario getGrupoUsuario() {
        return grupoUsuario;
    }

    public void setGrupoUsuario(GrupoUsuario grupoUsuario) {
        this.grupoUsuario = grupoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFotoPath() {
        return fotoPath;
    }

    public void setFotoPath(String fotoPath) {
        this.fotoPath = fotoPath;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.cdUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        return this.getCdUsuario() == other.getCdUsuario()
                && this.getEmail().equals(other.getEmail())
                && this.getSenha().equals(other.getSenha())
                && this.getNome().equals(other.getNome());
    }

    @Override
    public String toString() {
        return "[Id=" + getCdUsuario() + " Nome=" + getNome() + " E-mail=" + getEmail() + "]";
    }

}
