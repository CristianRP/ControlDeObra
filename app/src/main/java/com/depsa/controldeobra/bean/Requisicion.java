package com.depsa.controldeobra.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Cristian Ramírez on 2/08/17.
 * Copyright (c) 2017
 */
public class Requisicion {
    @SerializedName("CodProyecto")
    @Expose
    private Integer codProyecto;
    @SerializedName("CodModelo")
    @Expose
    private Integer codModelo;
    @SerializedName("CodLote")
    @Expose
    private Integer codLote;
    @SerializedName("CodActividad")
    @Expose
    private Integer codActividad;
    @SerializedName("CodTarea")
    @Expose
    private Integer codTarea;
    @SerializedName("CodDetalle")
    @Expose
    private Integer codDetalle;
    @SerializedName("Cantidad")
    @Expose
    private Double cantidad;
    @SerializedName("CodUnidad")
    @Expose
    private Integer codUnidad;
    @SerializedName("EsUnidad")
    @Expose
    private String esUnidad;
    @SerializedName("Solicitado")
    @Expose
    private Double solicitado;
    @SerializedName("Despacho")
    @Expose
    private Double despacho;
    @SerializedName("Bodega")
    @Expose
    private Double bodega;
    @SerializedName("Incluir")
    @Expose
    private String incluir;
    @SerializedName("Numero")
    @Expose
    private Integer numero;
    @SerializedName("Usuario")
    @Expose
    private Integer usuario;
    @SerializedName("IdDispositivo")
    @Expose
    private String idDispositivo;
    @SerializedName("Tipo")
    @Expose
    private String tipo;
    @SerializedName("Estado")
    @Expose
    private String estado;

    public Requisicion() {
    }

    public Requisicion(Integer codProyecto, Integer codModelo, Integer codLote,
                       Integer codActividad, Integer codTarea, Integer codDetalle,
                       Double cantidad, Integer codUnidad, String esUnidad,
                       Double solicitado, Double despacho, Double bodega,
                       String incluir, Integer numero, Integer usuario,
                       String idDispositivo, String tipo, String estado) {
        this.codProyecto = codProyecto;
        this.codModelo = codModelo;
        this.codLote = codLote;
        this.codActividad = codActividad;
        this.codTarea = codTarea;
        this.codDetalle = codDetalle;
        this.cantidad = cantidad;
        this.codUnidad = codUnidad;
        this.esUnidad = esUnidad;
        this.solicitado = solicitado;
        this.despacho = despacho;
        this.bodega = bodega;
        this.incluir = incluir;
        this.numero = numero;
        this.usuario = usuario;
        this.idDispositivo = idDispositivo;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Integer getCodProyecto() {
        return codProyecto;
    }

    public void setCodProyecto(Integer codProyecto) {
        this.codProyecto = codProyecto;
    }

    public Integer getCodModelo() {
        return codModelo;
    }

    public void setCodModelo(Integer codModelo) {
        this.codModelo = codModelo;
    }

    public Integer getCodLote() {
        return codLote;
    }

    public void setCodLote(Integer codLote) {
        this.codLote = codLote;
    }

    public Integer getCodActividad() {
        return codActividad;
    }

    public void setCodActividad(Integer codActividad) {
        this.codActividad = codActividad;
    }

    public Integer getCodTarea() {
        return codTarea;
    }

    public void setCodTarea(Integer codTarea) {
        this.codTarea = codTarea;
    }

    public Integer getCodDetalle() {
        return codDetalle;
    }

    public void setCodDetalle(Integer codDetalle) {
        this.codDetalle = codDetalle;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCodUnidad() {
        return codUnidad;
    }

    public void setCodUnidad(Integer codUnidad) {
        this.codUnidad = codUnidad;
    }

    public String getEsUnidad() {
        return esUnidad;
    }

    public void setEsUnidad(String esUnidad) {
        this.esUnidad = esUnidad;
    }

    public Double getSolicitado() {
        return solicitado;
    }

    public void setSolicitado(Double solicitado) {
        this.solicitado = solicitado;
    }

    public Double getDespacho() {
        return despacho;
    }

    public void setDespacho(Double despacho) {
        this.despacho = despacho;
    }

    public Double getBodega() {
        return bodega;
    }

    public void setBodega(Double bodega) {
        this.bodega = bodega;
    }

    public String getIncluir() {
        return incluir;
    }

    public void setIncluir(String incluir) {
        this.incluir = incluir;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
