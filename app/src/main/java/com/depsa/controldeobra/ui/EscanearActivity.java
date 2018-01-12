package com.depsa.controldeobra.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.depsa.controldeobra.R;
import com.depsa.controldeobra.preferences.PrefManager;
import com.depsa.controldeobra.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class EscanearActivity extends Activity
        implements ZBarScannerView.ResultHandler {

    @BindString(R.string.escaner_load_text)
    String loadingText;
    private ZBarScannerView mScannerView;
    public static int TIPO_MATERIAL;
    public static int NUMERO_SOLICITUD;
    private PrefManager mPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZBarScannerView(this);
        setContentView(mScannerView);
        mScannerView.startCamera();
        mPrefManager = new PrefManager(this);
    }

    @Override
    public void handleResult(Result result) {
        Log.v(EscanearActivity.class.getSimpleName(), result.getContents()); // Prints scan results
        Log.v(EscanearActivity.class.getSimpleName(), result.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
        Constants.showDialog(this, loadingText);
        NUMERO_SOLICITUD = Integer.parseInt(result.getContents().replaceFirst("^0*", ""));
        showDialogTipoMaterial(NUMERO_SOLICITUD);
        Constants.dismissDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    private void showDialogTipoMaterial(final int noSolicitud) {
        List<String> tipos = new ArrayList<>();
        if (mPrefManager.getUserPerfil().equals("BODEGUERO")) {
            tipos.add("Material");
        }
        if (mPrefManager.getUserPerfil().equals("RESIDENTE") && MenuActivity.TIPO_MENU.equals(MenuActivity.ENTREGA_MATERIALES_TXT)) {
            tipos.add("Mano de obra");
        }
        if (mPrefManager.getUserPerfil().equals("RESIDENTE") &&  MenuActivity.TIPO_MENU.equals(MenuActivity.SOBREGIROS)) {
            tipos.add("Material");
            tipos.add("Mano de obra");
        }

        if (mPrefManager.getUserPerfil().equals("SUPERVISOR")){
            tipos.add("Material");
            tipos.add("Mano de obra");
        }
        new MaterialDialog.Builder(this)
                .title("Tipo de material")
                .items(tipos)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        TIPO_MATERIAL = which + 1;
                        NUMERO_SOLICITUD = noSolicitud;
                        Intent encabezado = new Intent(EscanearActivity.this, EncabezadoSolictudActivity.class);
                        encabezado.putExtra("solicitud", NUMERO_SOLICITUD);
                        encabezado.putExtra("tipoMaterial", TIPO_MATERIAL);
                        MenuActivity.NUMERO_SOLICITUD = NUMERO_SOLICITUD;
                        startActivity(encabezado);
                        return false;
                    }
                })
                .positiveText("Aceptar")
                .negativeText("Cancelar")
                .show();
    }
}
