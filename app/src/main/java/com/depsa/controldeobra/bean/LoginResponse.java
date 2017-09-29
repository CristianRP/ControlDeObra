package com.depsa.controldeobra.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cristian on 9/28/17.
 */

public class LoginResponse {

    @SerializedName("usuario")
    @Expose
    private String usuario;
    @SerializedName("clave")
    @Expose
    private String clave;
    @SerializedName("dispositivo")
    @Expose
    private String dispositivo;
    @SerializedName("estado")
    @Expose
    private String estado;
    @SerializedName("perfil")
    @Expose
    private String perfil;

    public LoginResponse() {

    }

    public LoginResponse(String usuario, String clave, String dispositivo, String estado, String perfil) {
        this.usuario = usuario;
        this.clave = clave;
        this.dispositivo = dispositivo;
        this.estado = estado;
        this.perfil = perfil;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
