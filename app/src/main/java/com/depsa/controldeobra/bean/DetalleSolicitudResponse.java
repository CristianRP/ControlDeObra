package com.depsa.controldeobra.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cramirez on 22/09/2017.
 */

public class DetalleSolicitudResponse {
    @SerializedName("CODIGO")
    @Expose
    private String codigo;
    @SerializedName("NOMBRE")
    @Expose
    private String nombre;
    @SerializedName("CANTIDAD")
    @Expose
    private double cantidad;
    @SerializedName("TIPO")
    @Expose
    private String tipo;
    @SerializedName("COD_UNIDAD")
    @Expose
    private Integer codUnidad;
    @SerializedName("UNIDAD_MEDIDA")
    @Expose
    private String unidadMedida;
    @SerializedName("SOLICITADO")
    @Expose
    private double solicitado;
    @SerializedName("BODEGA")
    @Expose
    private Double bodega;
    @SerializedName("DESPACHO")
    @Expose
    private double despacho;
    @SerializedName("SALDO")
    @Expose
    private double saldo;
    @SerializedName("INCLUIR")
    @Expose
    private String incluir;

    public DetalleSolicitudResponse() {
    }

    public DetalleSolicitudResponse(String codigo, String nombre, double cantidad, String tipo,
                                    Integer codUnidad, String unidadMedida, double solicitado,
                                    Double bodega, double despacho, Integer saldo, String incluir) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.codUnidad = codUnidad;
        this.unidadMedida = unidadMedida;
        this.solicitado = solicitado;
        this.bodega = bodega;
        this.despacho = despacho;
        this.saldo = saldo;
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

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
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

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public double getSolicitado() {
        return solicitado;
    }

    public void setSolicitado(Integer solicitado) {
        this.solicitado = solicitado;
    }

    public double getBodega() {
        return bodega;
    }

    public void setBodega(Double bodega) {
        this.bodega = bodega;
    }

    public double getDespacho() {
        return despacho;
    }

    public void setDespacho(double despacho) {
        this.despacho = despacho;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public String getIncluir() {
        return incluir;
    }

    public void setIncluir(String incluir) {
        this.incluir = incluir;
    }

    public boolean calcularSaldo() {
        return despacho <= bodega;
    }
}
