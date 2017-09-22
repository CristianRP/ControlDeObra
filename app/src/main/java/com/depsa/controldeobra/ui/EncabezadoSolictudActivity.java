package com.depsa.controldeobra.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.depsa.controldeobra.R;
import com.depsa.controldeobra.api.ControlObraWebAPI;
import com.depsa.controldeobra.api.ServiceGenerator;
import com.depsa.controldeobra.bean.SolicitudEncabezadoResponse;
import com.depsa.controldeobra.util.Constants;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EncabezadoSolictudActivity extends AppCompatActivity {

    @BindView(R.id.txtNumeroSolicitud)
    TextView mNumeroSolicitud;
    @BindView(R.id.txtProyecto)
    TextView mProyecto;
    @BindView(R.id.txtBodega)
    TextView mBodega;
    @BindView(R.id.txtModelo)
    TextView mModelo;
    @BindView(R.id.txtActividad)
    TextView mActividad;
    @BindView(R.id.txtTarea)
    TextView mTarea;
    @BindView(R.id.txtObra)
    TextView mObra;
    private ControlObraWebAPI mControlObraWebAPI;
    public static String TAG = EncabezadoSolictudActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encabezado_solictud);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle("Detalle Pre-Requisición");
        setSupportActionBar(toolbar);

        mControlObraWebAPI = ServiceGenerator.createService(ControlObraWebAPI.class);

        Intent solicitud = getIntent();
        obtenerEncabezadoSolicitud(solicitud.getIntExtra("solicitud", 0));
        mNumeroSolicitud.setText(""+solicitud.getIntExtra("solicitud", 0));

    }


    private void obtenerEncabezadoSolicitud(int solicitud) {
        Constants.showDialog(EncabezadoSolictudActivity.this, "Obteniendo datos...");
        Call<List<SolicitudEncabezadoResponse>> responseCall = mControlObraWebAPI.getSolicitud(solicitud);
        responseCall.enqueue(new Callback<List<SolicitudEncabezadoResponse>>() {
            @Override
            public void onResponse(Call<List<SolicitudEncabezadoResponse>> call, Response<List<SolicitudEncabezadoResponse>> response) {
                Constants.dismissDialog();
                String error = "Hubo un error!";
                if (!response.isSuccessful()) {
                    if (response.errorBody()
                            .contentType()
                            .subtype()
                            .equals("json")) {
                        Log.e(TAG, error);
                    } else {
                        try {
                            // Errores no relacionados con el API
                            Log.e(TAG, response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Toast.makeText(EncabezadoSolictudActivity.this, error, Toast.LENGTH_SHORT).show();
                    Log.e(TAG, error);
                    return;
                }
                Log.e(TAG + " Success", "Listado de rutas obtenido");

                Log.e(TAG + " Success", " " + response.body());
                setDataToViews(response.body().get(0));
            }
            @Override
            public void onFailure(Call<List<SolicitudEncabezadoResponse>> call, Throwable t) {
                Constants.dismissDialog();
                Log.e("failure", " " +
                        t.getMessage());
                Log.e("failure", " " + t.getLocalizedMessage());
                Log.e("failure", " " + call.toString());
            }
        });
    }

    private void setDataToViews(SolicitudEncabezadoResponse response) {
        String proyecto = String.format(Locale.getDefault(),"%.0f %2s", response.getCodProyecto(), response.getNombreProyecto());
        mProyecto.setText(proyecto);
        String bodega = String.format(Locale.getDefault(), "%.0f %2s", response.getCodBodega(), response.getNombreBodega());
        mBodega.setText(bodega);
        String modelo = String.format(Locale.getDefault(), "%.0f %2s", response.getCodModelo(), response.getNombreModelo());
        mModelo.setText(modelo);
        String actividad = String.format(Locale.getDefault(), "%.0f %2s", response.getCodActividad(), response.getNombreActividad());
        mActividad.setText(actividad);
        String tarea = String.format(Locale.getDefault(), "%.0f %2s", response.getCodTarea(), response.getNombreTarea());
        mTarea.setText(tarea);
        String obra = String.format(Locale.getDefault(), "%.0f %2s", response.getCodObra(), response.getNombreObra());
        mObra.setText(obra);
    }

}
