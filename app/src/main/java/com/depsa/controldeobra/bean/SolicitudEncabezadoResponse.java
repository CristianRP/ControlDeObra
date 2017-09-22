package com.depsa.controldeobra.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cramirez on 22/09/2017.
 */

public class SolicitudEncabezadoResponse {

    @SerializedName("SOLICITUD")
    @Expose
    private Double solicitud;
    @SerializedName("FECHA")
    @Expose
    private String fecha;
    @SerializedName("COD_PROYECTO")
    @Expose
    private Double codProyecto;
    @SerializedName("NOMBRE_PROYECTO")
    @Expose
    private String nombreProyecto;
    @SerializedName("COD_BODEGA")
    @Expose
    private Double codBodega;
    @SerializedName("NOMBRE_BODEGA")
    @Expose
    private String nombreBodega;
    @SerializedName("COD_MODELO")
    @Expose
    private Double codModelo;
    @SerializedName("NOMBRE_MODELO")
    @Expose
    private String nombreModelo;
    @SerializedName("COD_ACTIVIDAD")
    @Expose
    private Double codActividad;
    @SerializedName("NOMBRE_ACTIVIDAD")
    @Expose
    private String nombreActividad;
    @SerializedName("COD_TAREA")
    @Expose
    private Double codTarea;
    @SerializedName("NOMBRE_TAREA")
    @Expose
    private String nombreTarea;
    @SerializedName("COD_OBRA")
    @Expose
    private Double codObra;
    @SerializedName("NOMBRE_OBRA")
    @Expose
    private String nombreObra;
    @SerializedName("ESTADO_SINCRONIZACION")
    @Expose
    private String estadoSincronizacion;
    @SerializedName("FECHA_SINCRONIZACION")
    @Expose
    private String fechaSincronizacion;
    @SerializedName("USUARIO_ANDROID")
    @Expose
    private String usuarioAndroid;

    public SolicitudEncabezadoResponse() {
    }

    public SolicitudEncabezadoResponse(Double solicitud, String fecha, Double codProyecto,
                                       String nombreProyecto, Double codBodega, String nombreBodega,
                                       Double codModelo, String nombreModelo, Double codActividad,
                                       String nombreActividad, Double codTarea, String nombreTarea,
                                       Double codObra, String nombreObra, String estadoSincronizacion,
                                       String fechaSincronizacion, String usuarioAndroid) {
        this.solicitud = solicitud;
        this.fecha = fecha;
        this.codProyecto = codProyecto;
        this.nombreProyecto = nombreProyecto;
        this.codBodega = codBodega;
        this.nombreBodega = nombreBodega;
        this.codModelo = codModelo;
        this.nombreModelo = nombreModelo;
        this.codActividad = codActividad;
        this.nombreActividad = nombreActividad;
        this.codTarea = codTarea;
        this.nombreTarea = nombreTarea;
        this.codObra = codObra;
        this.nombreObra = nombreObra;
        this.estadoSincronizacion = estadoSincronizacion;
        this.fechaSincronizacion = fechaSincronizacion;
        this.usuarioAndroid = usuarioAndroid;
    }

    public Double getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Double solicitud) {
        this.solicitud = solicitud;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getCodProyecto() {
        return codProyecto;
    }

    public void setCodProyecto(Double codProyecto) {
        this.codProyecto = codProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public Double getCodBodega() {
        return codBodega;
    }

    public void setCodBodega(Double codBodega) {
        this.codBodega = codBodega;
    }

    public String getNombreBodega() {
        return nombreBodega;
    }

    public void setNombreBodega(String nombreBodega) {
        this.nombreBodega = nombreBodega;
    }

    public Double getCodModelo() {
        return codModelo;
    }

    public void setCodModelo(Double codModelo) {
        this.codModelo = codModelo;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public Double getCodActividad() {
        return codActividad;
    }

    public void setCodActividad(Double codActividad) {
        this.codActividad = codActividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public Double getCodTarea() {
        return codTarea;
    }

    public void setCodTarea(Double codTarea) {
        this.codTarea = codTarea;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public Double getCodObra() {
        return codObra;
    }

    public void setCodObra(Double codObra) {
        this.codObra = codObra;
    }

    public String getNombreObra() {
        return nombreObra;
    }

    public void setNombreObra(String nombreObra) {
        this.nombreObra = nombreObra;
    }

    public String getEstadoSincronizacion() {
        return estadoSincronizacion;
    }

    public void setEstadoSincronizacion(String estadoSincronizacion) {
        this.estadoSincronizacion = estadoSincronizacion;
    }

    public String getFechaSincronizacion() {
        return fechaSincronizacion;
    }

    public void setFechaSincronizacion(String fechaSincronizacion) {
        this.fechaSincronizacion = fechaSincronizacion;
    }

    public String getUsuarioAndroid() {
        return usuarioAndroid;
    }

    public void setUsuarioAndroid(String usuarioAndroid) {
        this.usuarioAndroid = usuarioAndroid;
    }
}
