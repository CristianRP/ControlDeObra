package com.depsa.controldeobra.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cramirez on 6/12/2017.
 */

public class ManoObra {

    @SerializedName("Solicitud")
    @Expose
    private int solicitud;
    @SerializedName("Cuadrilla")
    @Expose
    private int cuadrilla;
    @SerializedName("Estado")
    @Expose
    private String estado;

    public ManoObra() {

    }

    public ManoObra(int solicitud, int cuadrilla, String estado) {
        this.solicitud = solicitud;
        this.cuadrilla = cuadrilla;
        this.estado = estado;
    }

    public int getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(int solicitud) {
        this.solicitud = solicitud;
    }

    public int getCuadrilla() {
        return cuadrilla;
    }

    public void setCuadrilla(int cuadrilla) {
        this.cuadrilla = cuadrilla;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
