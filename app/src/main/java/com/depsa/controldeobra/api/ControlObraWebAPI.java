package com.depsa.controldeobra.api;

import com.depsa.controldeobra.bean.BodyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Cristian Ramírez on 20/07/2017.
 * Grupo Rosul
 * cristianramirezgt@gmail.com
 */


public interface ControlObraWebAPI {

    @GET("api/Proyecto")
    Call<List<BodyResponse>> getProyectos();

    @GET("api/Modelo")
    Call<List<BodyResponse>> getModelo();

    @GET("api/Obra")
    Call<List<BodyResponse>> getObra();

    @GET("api/Actividad")
    Call<List<BodyResponse>> getActividad();

    @GET("api/Tarea")
    Call<List<BodyResponse>> getTarea();

    @GET("api/Detalle")
    Call<List<BodyResponse>> getDetalle();

}
