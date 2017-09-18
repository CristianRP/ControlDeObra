package com.depsa.controldeobra.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Cristian Ramírez on 20/07/2017.
 * Grupo Rosul
 * cristianramirezgt@gmail.com
 */


public class Constants {
    public static final String HOST_NAME = "http://52.44.60.125:8090/";
    public static final String ENCRYPTOR_KEY = "&GRVP0.R0$u10617";

    private static ProgressDialog mProgressDialog;

    public static void showDialog(Context context, String message) {
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    public static void dismissDialog() {
        mProgressDialog.dismiss();
    }
}
