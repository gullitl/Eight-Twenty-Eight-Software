package com.cecilsystems.mistersoftbackend.model;

import java.util.List;

/**
 * @author Plamedi L. Lusembo
 */
public class GrupoUsuario {

    private int cdGrupoUsuario;
    private String dsGrupoUsuario;
    private String daGrupoUsuario;
    private List<Usuario> lstUsuarios;

    public GrupoUsuario() {
    }

    public GrupoUsuario(int cdGrupoUsuario) {
        this.cdGrupoUsuario = cdGrupoUsuario;
    }

    public GrupoUsuario(int cdGrupoUsuario, String dsGrupoUsuario, String daGrupoUsuario) {
        this.cdGrupoUsuario = cdGrupoUsuario;
        this.dsGrupoUsuario = dsGrupoUsuario;
        this.daGrupoUsuario = daGrupoUsuario;
    }

    public int getCdGrupoUsuario() {
        return cdGrupoUsuario;
    }

    public void setCdGrupoUsuario(int cdGrupoUsuario) {
        this.cdGrupoUsuario = cdGrupoUsuario;
    }

    public String getDsGrupoUsuario() {
        return dsGrupoUsuario;
    }

    public void setDsGrupoUsuario(String dsGrupoUsuario) {
        this.dsGrupoUsuario = dsGrupoUsuario;
    }

    public String getDaGrupoUsuario() {
        return daGrupoUsuario;
    }

    public void setDaGrupoUsuario(String daGrupoUsuario) {
        this.daGrupoUsuario = daGrupoUsuario;
    }

    public List<Usuario> getLstUsuarios() {
        return lstUsuarios;
    }

    public void setLstUsuarios(List<Usuario> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) getCdGrupoUsuario();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GrupoUsuario)) {
            return false;
        }
        GrupoUsuario other = (GrupoUsuario) object;
        return this.getCdGrupoUsuario() == other.getCdGrupoUsuario();
    }

    @Override
    public String toString() {
        return getDsGrupoUsuario();
    }

}
