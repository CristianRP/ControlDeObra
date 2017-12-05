package com.depsa.controldeobra.api;

import com.depsa.controldeobra.bean.BodyResponse;
import com.depsa.controldeobra.bean.DetalleSolicitudBody;
import com.depsa.controldeobra.bean.DetalleSolicitudResponse;
import com.depsa.controldeobra.bean.LoginResponse;
import com.depsa.controldeobra.bean.Requisicion;
import com.depsa.controldeobra.bean.SolicitudEncabezadoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Cristian Ramírez on 20/07/2017.
 * Grupo Rosul
 * cristianramirezgt@gmail.com
 */


public interface ControlObraWebAPI {

    @GET("api/Proyecto")
    Call<List<BodyResponse>> getProyectos(@Query("tipoConsulta") int tipoConsulta);

    @GET("api/Modelo")
    Call<List<BodyResponse>> getModelo(@Query("tipoConsulta") int tipoConsulta,
                                       @Query("parametro") int parametro);

    @GET("api/Obra")
    Call<List<BodyResponse>> getObra(@Query("tipoConsulta") int tipoConsulta,
                                     @Query("parametro") int parametro,
                                     @Query("parametro1") int parametro1);

    @GET("api/Actividad")
    Call<List<BodyResponse>> getActividad(@Query("tipoConsulta") int tipoConsulta,
                                          @Query("parametro") int parametro,
                                          @Query("parametro1") int parametro1);

    @GET("api/Tarea")
    Call<List<BodyResponse>> getTarea(@Query("tipoConsulta") int tipoConsulta,
                                      @Query("parametro") int parametro,
                                      @Query("parametro1") int parametro1,
                                      @Query("parametro2") int parametro2,
                                      @Query("parametro3") int parametro3);

    @GET("api/Detalle")
    Call<List<BodyResponse>> getDetalle(@Query("tipoConsulta") int tipoConsulta,
                                        @Query("parametro") int parametro,
                                        @Query("parametro1") int parametro1,
                                        @Query("parametro2") int parametro2,
                                        @Query("parametro3") int parametro3);

    @POST("api/Requisicion")
    Call<Requisicion> postRequisicion(@Body Requisicion requisicion);

    @POST("api/SolicitudEncabezado")
    Call<List<SolicitudEncabezadoResponse>> getSolicitud(@Query("solicitud") int solicitud);

    @POST("api/DetalleSolicitud")
    Call<List<DetalleSolicitudResponse>> getDetalleSolicitud(@Query("solicitud") int solicitud,
                                                             @Query("tipoMaterial") int tipoMaterial,
                                                             @Query("tipoTransaccion") String tipoTransaccion);

    @POST("api/ActualizarDetalleReq")
    Call<Void> actualizarDetalle(@Body DetalleSolicitudBody detalleSolicitudBody);

    @POST("api/GeneraSalida")
    Call<Void> generarSalirda(@Query("prSolicitud") int solicitud);

    @POST("api/GeneraSalidaManoObra")
    Call<Void> generarSalidaManoObra(@Query("prSolicitud") int solicitud);

    @POST("api/GeneraSalidaMO")
    Call<Void> generarSalidaMO(@Query("pSolicitud") int solicitud, @Query("pCuadrilla") int cuadrilla,
                               @Query("pEstado") String estado);

    @POST("api/login")
    Call<List<LoginResponse>> autenticarUsuario(@Query("username") String username, @Query("password") String password);

    @POST("api/GeneraDevolucion")
    Call<Void> generaDevolucion(@Query("prDevolucion") int devolucion);
}
