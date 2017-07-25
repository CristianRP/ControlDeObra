package com.depsa.controldeobra.api;

import com.depsa.controldeobra.util.Constants;
import com.depsa.controldeobra.util.DateDeserializer;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cristian Ramírez on 24/07/2017.
 * Grupo Rosul
 * cristianramirezgt@gmail.com
 */


public class ServiceGenerator {

    private ServiceGenerator() {}

    private static final String API_BASE_URL = Constants.HOST_NAME;

    private static GsonBuilder gsonBuilder = new GsonBuilder()
            .registerTypeAdapter(Date.class, new DateDeserializer());

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));

    private static Retrofit retrofit = builder.build();

    private static HttpLoggingInterceptor loggin =
            new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder okHttpClient =
            new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS);

    public static <S> S createService(Class<S> serviceClass) {
        if (!okHttpClient.interceptors().contains(loggin)) {
            okHttpClient.addInterceptor(loggin);
            builder.client(okHttpClient.build());
            retrofit = builder.build();
        }
        return retrofit.create(serviceClass);
    }
}
