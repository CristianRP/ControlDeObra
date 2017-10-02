package com.depsa.controldeobra.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.depsa.controldeobra.R;
import com.depsa.controldeobra.adapter.SolicitudDetalleResponseAdapter;
import com.depsa.controldeobra.api.ControlObraWebAPI;
import com.depsa.controldeobra.api.ServiceGenerator;
import com.depsa.controldeobra.bean.DetalleSolicitudBody;
import com.depsa.controldeobra.bean.DetalleSolicitudResponse;
import com.depsa.controldeobra.preferences.PrefManager;
import com.depsa.controldeobra.util.Constants;

import java.io.IOException;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleSolicitudActivity extends AppCompatActivity {

    @BindString(R.string.escaner_load_text)
    String cargandoStr;
    @BindView(R.id.recyclerMateriales)
    RecyclerView mRecyclerMateriales;

    private LinearLayoutManager mLayoutManager;
    private SolicitudDetalleResponseAdapter mAdapter;
    private ControlObraWebAPI mControlObraWebAPI;
    private List<DetalleSolicitudResponse> mListaResponse;
    public static boolean checkDespacho;
    private PrefManager mPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_solicitud);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        mControlObraWebAPI = ServiceGenerator.createService(ControlObraWebAPI.class);
        mPrefManager = new PrefManager(this);

        mRecyclerMateriales.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerMateriales.setLayoutManager(mLayoutManager);
        mRecyclerMateriales.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
        );
        Intent entregaMateriales = getIntent();
        getDetalleSolicitud(entregaMateriales.getIntExtra("solicitud", 0),
                entregaMateriales.getIntExtra("tipoMaterial", 0));

        Constants.showDialog(this, "Cargando datos...");
    }

    private void setDataToRecycler(List<DetalleSolicitudResponse> items) {
        mAdapter = new SolicitudDetalleResponseAdapter(items, this);
        mAdapter.setHasStableIds(true);
        mRecyclerMateriales.setAdapter(mAdapter);
    }

    private void getDetalleSolicitud(Integer... parameters) {
        Call<List<DetalleSolicitudResponse>> getDetalleSolicitud = mControlObraWebAPI.getDetalleSolicitud(parameters[0], parameters[1]);
        getDetalleSolicitud.enqueue(new Callback<List<DetalleSolicitudResponse>>() {
            @Override
            public void onResponse(Call<List<DetalleSolicitudResponse>> call, Response<List<DetalleSolicitudResponse>> response) {
                Constants.dismissDialog();
                String error = "Ha ocurrido un error. Contacte con el administrador";
                if (!response.isSuccessful()) {
                    if (response.raw().code() != 200) {
                        Toast.makeText(DetalleSolicitudActivity.this, error, Toast.LENGTH_SHORT).show();
                        Log.e(LoginActivity.class.getSimpleName(), error);
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
                Log.e(EntregaMaterialesTableActivity.class.getSimpleName(), "Successfull!");
                mListaResponse = response.body();
                setDataToRecycler(response.body());
            }

            @Override
            public void onFailure(Call<List<DetalleSolicitudResponse>> call, Throwable t) {
                Constants.dismissDialog();
                t.printStackTrace();
                Log.e(EntregaMaterialesTableActivity.class.getSimpleName(), "Ha ocurrido un error. Contacte con el administrador");
            }
        });
    }

    private void sendReqToServer(DetalleSolicitudBody detalleSolicitud) {
        Constants.showDialog(DetalleSolicitudActivity.this, "Enviando datos...");
        Call<Void> updateSolicitud = mControlObraWebAPI.actualizarDetalle(detalleSolicitud);
        updateSolicitud.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Constants.dismissDialog();
                String error = "Ha ocurrido un error. Contacte con el administrador";
                if (!response.isSuccessful()) {
                    if (response.raw().code() != 200) {
                        Toast.makeText(DetalleSolicitudActivity.this, error, Toast.LENGTH_SHORT).show();
                        Log.e(LoginActivity.class.getSimpleName(), error);
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
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Constants.dismissDialog();
                t.printStackTrace();
                call.clone();
                Log.e(EntregaMaterialesTableActivity.class.getSimpleName(), "Ha ocurrido un error. Contacte con el administrador" + t.getLocalizedMessage() +
                t.getMessage() + t.getCause());
                Toast.makeText(DetalleSolicitudActivity.this, "Ha ocurrido un error. Contacte con el administrador" + t.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }

    @OnClick(R.id.btnEntregaMateriales)
    void OnClickEntregaMateriales() {
        new AlertDialog.Builder(this)
                .setTitle("Enviar materiales")
                .setMessage("Desea enviar los materiales?")
                .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e(EntregaMaterialesTableActivity.class.getSimpleName(), " " + mListaResponse.get(0).getDespacho());
                        Intent entregaMateriales = getIntent();
                        if (!mPrefManager.getUserPerfil().equals("RESIDENTE")){ ///BODEGUERO  SUPERVISOR
                            for (DetalleSolicitudResponse response : mListaResponse) {
                                if (!response.calcularSaldo()) {
                                    Toast.makeText(DetalleSolicitudActivity.this, "El despacho sobrepasa el valor de la existencia en bodega revisa los datos!", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    return;
                                }
                            }
                        }

                        for (DetalleSolicitudResponse response : mListaResponse) {
                            Log.e("DESPACHO", response.getDespacho() + " ");
                            DetalleSolicitudBody detailBody = new DetalleSolicitudBody(
                                    (int) entregaMateriales.getDoubleExtra("codProyecto", 0d),
                                    (int) entregaMateriales.getDoubleExtra("codBodega", 0d),
                                    Integer.parseInt(response.getCodigo()),
                                    response.getCodUnidad(),
                                    entregaMateriales.getIntExtra("solicitud", 0),
                                    response.getBodega(),
                                    response.getSolicitado(),
                                    response.getSaldo(),
                                    Double.parseDouble(String.valueOf(response.getDespacho())),
                                    "android"
                            );
                            sendReqToServer(detailBody);
                        }
                        if (getIntent().getIntExtra("tipoMaterial", 0) == 1) {
                            // MATERIALES
                            generarSalida();
                        } else {
                            // mano de obra
                            generarSalidaManoObra();
                        }
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void generarSalida() {
        Call<Void> generaSalida = mControlObraWebAPI.generarSalirda(getIntent().getIntExtra("solicitud", 0));
        generaSalida.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
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
                Toast.makeText(DetalleSolicitudActivity.this, "Salida generada con éxito", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DetalleSolicitudActivity.this, MenuActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Constants.dismissDialog();
                t.printStackTrace();
                Log.e(EntregaMaterialesTableActivity.class.getSimpleName(), "Ha ocurrido un error. Contacte con el administrador");
                //Toast.makeText(DetalleSolicitudActivity.this, "Ha ocurrido un error. Contacte con el administrador" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generarSalidaManoObra() {
        Call<Void> genearSalidaManoObra = mControlObraWebAPI.generarSalidaManoObra(getIntent().getIntExtra("solicitud", 0));
        genearSalidaManoObra.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
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

                Toast.makeText(DetalleSolicitudActivity.this, "Salida generada con éxito", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DetalleSolicitudActivity.this, MenuActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Constants.dismissDialog();
                t.printStackTrace();
                Log.e(EntregaMaterialesTableActivity.class.getSimpleName(), "Ha ocurrido un error. Contacte con el administrador");
                Toast.makeText(DetalleSolicitudActivity.this, "Ha ocurrido un error. Contacte con el administrador" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
