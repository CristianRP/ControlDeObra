package com.depsa.controldeobra.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.depsa.controldeobra.bean.LoginResponse;

/**
 * Created by cristian on 9/28/17.
 */

public class PrefManager  {

    static SharedPreferences preferences;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "CONTROL_DE_OBRA";
    private static final String USER_NAME = "usuario";
    private static final String USER_PASSWORD = "password";
    private static final String USER_DISPOSITIVO = "dispositivo";
    private static final String USER_ESTADO = "estado";
    private static final String USER_PERFIL = "perfil";
    private static final String IS_LOGIN = "IsLoggedIn";

    public PrefManager(Context context) {
        this._context = context;
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }


    /**
     * Create login session
     */
    public void createLoginSession(LoginResponse lr) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(USER_NAME, lr.getUsuario());
        editor.putString(USER_PASSWORD, lr.getClave());
        editor.putString(USER_DISPOSITIVO, lr.getDispositivo());
        editor.putString(USER_ESTADO, lr.getEstado());
        editor.putString(USER_PERFIL, lr.getPerfil());

        // commit changes
        editor.commit();
    }

    public static String getUserName() {
        return preferences.getString(USER_NAME, null);
    }

    public static String getUserPassword() {
        return preferences.getString(USER_PASSWORD, null);
    }

    public static String getUserDispositivo() {
        return preferences.getString(USER_DISPOSITIVO, null);
    }

    public static String getUserEstado() {
        return preferences.getString(USER_ESTADO, null);
    }

    public static String getUserPerfil() {
        return preferences.getString(USER_PERFIL, null);
    }

    public boolean isLoggedIn() {
        return preferences.getBoolean(IS_LOGIN, false);
    }

    public void logout() {
        editor.clear();
        editor.commit();
    }

}
