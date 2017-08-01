package com.depsa.controldeobra.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Cristian Ramírez on 20/07/2017.
 * Grupo Rosul
 * cristianramirezgt@gmail.com
 */


public class BodyResponse {

    @SerializedName("CODIGO")
    @Expose
    private String codigo;
    @SerializedName("NOMBRE")
    @Expose
    private String nombre;
    @SerializedName("CANTIDAD")
    @Expose
    private Integer cantidad;
    @SerializedName("TIPO")
    @Expose
    private String tipo;
    @SerializedName("COD_UNIDAD")
    @Expose
    private Integer codUnidad;
    @SerializedName("ES_UNIDAD")
    @Expose
    private String esUnidad;
    @SerializedName("SOLICITADO")
    @Expose
    private int solicitado;
    @SerializedName("DESPACHO")
    @Expose
    private int despacho;
    @SerializedName("BODEGA")
    @Expose
    private int bodega;
    @SerializedName("INCLUIR")
    @Expose
    private String incluir;

    public BodyResponse(String codigo, String nombre, Integer cantidad, String tipo,
                        Integer codUnidad, String esUnidad, int solicitado, int despacho,
                        int bodega, String incluir) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.codUnidad = codUnidad;
        this.esUnidad = esUnidad;
        this.solicitado = solicitado;
        this.despacho = despacho;
        this.bodega = bodega;
        this.incluir = incluir;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public int getSolicitado() {
        return solicitado;
    }

    public void setSolicitado(int solicitado) {
        this.solicitado = solicitado;
    }

    public int getDespacho() {
        return despacho;
    }

    public void setDespacho(int despacho) {
        this.despacho = despacho;
    }

    public int getBodega() {
        return bodega;
    }

    public void setBodega(int bodega) {
        this.bodega = bodega;
    }

    public String getIncluir() {
        return incluir;
    }

    public void setIncluir(String incluir) {
        this.incluir = incluir;
    }
}
