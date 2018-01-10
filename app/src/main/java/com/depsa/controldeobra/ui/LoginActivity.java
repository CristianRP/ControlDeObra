package com.depsa.controldeobra.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.depsa.controldeobra.R;
import com.depsa.controldeobra.api.ControlObraWebAPI;
import com.depsa.controldeobra.api.ServiceGenerator;
import com.depsa.controldeobra.bean.LoginResponse;
import com.depsa.controldeobra.preferences.PrefManager;
import com.depsa.controldeobra.util.Constants;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.imgLogoLogin)
    ImageView mImgLogo;
    @BindView(R.id.txtUserName)
    EditText mUserName;
    @BindView(R.id.txtClave)
    EditText mClave;
    @BindView(R.id.btnEntrar)
    Button mBtnEntrar;
    @BindView(R.id.tvVersion)
    TextView mVersion;
    private ControlObraWebAPI mControlAPI;
    private PrefManager mPrefManager;

    private String[] permissions = new String[] {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    int PERMISSION_ALL = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Picasso.with(this)
                .load(R.drawable.logo_depsa)
                .into(mImgLogo);

        mPrefManager = new PrefManager(this);

        if (!hasPermissions(this, permissions)) {
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_ALL);
        }

        /*if (mPrefManager.isLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, MenuActivity.class));
            finish();
        }*/

        mControlAPI = ServiceGenerator.createService(ControlObraWebAPI.class);
        PackageManager manager = this.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(
                    this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = info != null ? info.versionName : null;
        mVersion.setText(version);
    }

    @OnClick(R.id.btnEntrar)
    void OnEntrarClick() {
        if (!mUserName.getText().toString().isEmpty() &&
                !mClave.getText().toString().isEmpty()) {
            Constants.showDialog(LoginActivity.this, "Autenticando...");
            autenticacionUser();
        } else {
            Toast.makeText(this, "Ingresa tus credenciales!", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private void autenticacionUser() {
        Call<List<LoginResponse>> autenticarUser = mControlAPI.autenticarUsuario(mUserName.getText().toString(),
                mClave.getText().toString());
        autenticarUser.enqueue(new Callback<List<LoginResponse>>() {
            @Override
            public void onResponse(Call<List<LoginResponse>> call, Response<List<LoginResponse>> response) {
                Constants.dismissDialog();
                String error = "Ha ocurrido un error. Contacte con el administrador";
                if (!response.isSuccessful()) {
                    if (response.raw().code() != 200) {
                        //Toast.makeText(DetalleSolicitudActivity.this, error, Toast.LENGTH_SHORT).show();
                        Log.e("ERROR ENVIANDO ", error + " " + response.errorBody().toString());
                        return;
                    }
                    if (response.errorBody()
                            .contentType()
                            .subtype()
                            .equals("json")) {
                        Log.e(EntregaMaterialesTableActivity.class.getSimpleName(), "Error " + response.errorBody().toString());
                    } else {
                        try {
                            // Errores no relacionados con el API
                            Log.e(EntregaMaterialesTableActivity.class.getSimpleName(), response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                //Toast.makeText(LoginActivity.this, "Salida generada con éxito", Toast.LENGTH_SHORT).show();
                try {
                    if (!response.body().get(0).getUsuario().isEmpty()) {
                        mPrefManager.createLoginSession(response.body().get(0));
                        startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Error de crendeciales.", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    Toast.makeText(LoginActivity.this, "Error de crendeciales.", Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<List<LoginResponse>> call, Throwable t) {
                Constants.dismissDialog();
                t.printStackTrace();
                Log.e(LoginActivity.class.getSimpleName(), "Ha ocurrido un error. Contacte con el administrador");
                Toast.makeText(LoginActivity.this, "Ha ocurrido un error. Contacte con el administrador" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
