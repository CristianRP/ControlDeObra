package com.depsa.controldeobra.api;

import com.depsa.controldeobra.bean.BodyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
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
                                        @Query("parametro3") int parametro3,
                                        @Query("parametro3") int parametro4,
                                        @Query("parametro3") int parametro5,
                                        @Query("parametro3") int parametro6,
                                        @Query("parametro3") int parametro7);

}
