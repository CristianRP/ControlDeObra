package com.depsa.controldeobra.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.depsa.controldeobra.R;
import com.depsa.controldeobra.util.Constants;

import butterknife.BindString;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class EscanearActivity extends Activity
        implements ZBarScannerView.ResultHandler {

    @BindString(R.string.escaner_load_text)
    String loadingText;
    private ZBarScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZBarScannerView(this);
        setContentView(mScannerView);
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        Log.v(EscanearActivity.class.getSimpleName(), result.getContents()); // Prints scan results
        Log.v(EscanearActivity.class.getSimpleName(), result.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
        Constants.showDialog(this, loadingText);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
}
