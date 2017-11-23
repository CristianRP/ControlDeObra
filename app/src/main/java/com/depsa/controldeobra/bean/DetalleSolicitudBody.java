package com.depsa.controldeobra.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cristian on 9/24/17.
 */

public class DetalleSolicitudBody {
    @SerializedName("CodProyecto")
    @Expose
    private Integer codProyecto;
    @SerializedName("CodBodega")
    @Expose
    private Integer codBodega;
    @SerializedName("CodDetalle")
    @Expose
    private Integer codDetalle;
    @SerializedName("CodUnidad")
    @Expose
    private Integer codUnidad;
    @SerializedName("Solicitud")
    @Expose
    private Integer solicitud;
    @SerializedName("Bodega")
    @Expose
    private Double bodega;
    @SerializedName("Solicitado")
    @Expose
    private Double solicitado;
    @SerializedName("Saldo")
    @Expose
    private Double saldo;
    @SerializedName("Despacho")
    @Expose
    private Double despacho;
    @SerializedName("Usuario")
    @Expose
    private String usuario;
    @Expose
    @SerializedName("TipoTransaccion")
    private String tipoTransaccion;

    public DetalleSolicitudBody(Integer codProyecto, Integer codBodega, Integer codDetalle,
                                Integer codUnidad, Integer solicitud, Double bodega,
                                Double solicitado, Double saldo, Double despacho, String usuario,
                                String tipoTransaccion) {
        this.codProyecto = codProyecto;
        this.codBodega = codBodega;
        this.codDetalle = codDetalle;
        this.codUnidad = codUnidad;
        this.solicitud = solicitud;
        this.bodega = bodega;
        this.solicitado = solicitado;
        this.saldo = saldo;
        this.despacho = despacho;
        this.usuario = usuario;
        this.tipoTransaccion = tipoTransaccion;
    }

    public Integer getCodProyecto() {
        return codProyecto;
    }

    public void setCodProyecto(Integer codProyecto) {
        this.codProyecto = codProyecto;
    }

    public Integer getCodBodega() {
        return codBodega;
    }

    public void setCodBodega(Integer codBodega) {
        this.codBodega = codBodega;
    }

    public Integer getCodDetalle() {
        return codDetalle;
    }

    public void setCodDetalle(Integer codDetalle) {
        this.codDetalle = codDetalle;
    }

    public Integer getCodUnidad() {
        return codUnidad;
    }

    public void setCodUnidad(Integer codUnidad) {
        this.codUnidad = codUnidad;
    }

    public Integer getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Integer solicitud) {
        this.solicitud = solicitud;
    }

    public Double getBodega() {
        return bodega;
    }

    public void setBodega(Double bodega) {
        this.bodega = bodega;
    }

    public Double getSolicitado() {
        return solicitado;
    }

    public void setSolicitado(Double solicitado) {
        this.solicitado = solicitado;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getDespacho() {
        return despacho;
    }

    public void setDespacho(Double despacho) {
        this.despacho = despacho;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }
}
