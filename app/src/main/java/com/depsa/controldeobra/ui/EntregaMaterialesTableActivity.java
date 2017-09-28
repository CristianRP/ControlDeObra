package com.depsa.controldeobra.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.depsa.controldeobra.R;
import com.depsa.controldeobra.adapter.BodyResponseDetalleAdapter;
import com.depsa.controldeobra.api.ControlObraWebAPI;
import com.depsa.controldeobra.api.ServiceGenerator;
import com.depsa.controldeobra.bean.BodyResponse;
import com.depsa.controldeobra.bean.Requisicion;
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

public class EntregaMaterialesTableActivity extends AppCompatActivity {

    @BindString(R.string.escaner_load_text)
    String cargandoStr;
    @BindView(R.id.recyclerMateriales)
    RecyclerView mRecyclerMateriales;

    private LinearLayoutManager mLayoutManager;
    private BodyResponseDetalleAdapter mAdapter;
    private ControlObraWebAPI mControlObraWebAPI;
    private List<BodyResponse> mListaResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega_materiales_table);
        ButterKnife.bind(this);
        mControlObraWebAPI = ServiceGenerator.createService(ControlObraWebAPI.class);

        mRecyclerMateriales.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerMateriales.setLayoutManager(mLayoutManager);
        mRecyclerMateriales.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
        );
        Intent entregaMateriales = getIntent();
        getMateriales(entregaMateriales.getIntExtra("tipoConsulta", 0),
                entregaMateriales.getIntExtra("parametro", 0),
                entregaMateriales.getIntExtra("parametro1", 0),
                entregaMateriales.getIntExtra("parametro2", 0),
                entregaMateriales.getIntExtra("parametro3", 0));

        Constants.showDialog(this, "Cargando datos...");
    }

    private void setDataToRecycler(List<BodyResponse> items) {
        mAdapter = new BodyResponseDetalleAdapter(items, this);
        mAdapter.setHasStableIds(true);
        mRecyclerMateriales.setAdapter(mAdapter);
    }

    private void getMateriales(Integer... parameters) {
        Call<List<BodyResponse>> getMaterialesWS = mControlObraWebAPI.getDetalle(parameters[0], parameters[1],
                parameters[2], parameters[3], parameters[4]);
        getMaterialesWS.enqueue(new Callback<List<BodyResponse>>() {
            @Override
            public void onResponse(Call<List<BodyResponse>> call, Response<List<BodyResponse>> response) {
                Constants.dismissDialog();
                String error = "Ha ocurrido un error. Contacte con el administrador";
                if (!response.isSuccessful()) {
                    if (response.raw().code() != 200) {
                        Toast.makeText(EntregaMaterialesTableActivity.this, error, Toast.LENGTH_SHORT).show();
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
            public void onFailure(Call<List<BodyResponse>> call, Throwable t) {
                Constants.dismissDialog();
                t.printStackTrace();
                Log.e(EntregaMaterialesTableActivity.class.getSimpleName(), "Ha ocurrido un error. Contacte con el administrador");
            }
        });
    }

    private void sendReqToServer(Requisicion requisicion) {
        Call<Requisicion>  postReqToServer = mControlObraWebAPI.postRequisicion(requisicion);
        postReqToServer.enqueue(new Callback<Requisicion>() {
            @Override
            public void onResponse(Call<Requisicion> call, Response<Requisicion> response) {
                Constants.dismissDialog();
                String error = "Ha ocurrido un error. Contacte con el administrador";
                if (!response.isSuccessful()) {
                    if (response.raw().code() != 200) {
                        Toast.makeText(EntregaMaterialesTableActivity.this, error, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(EntregaMaterialesTableActivity.this, "Datos enviados correctamente!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EntregaMaterialesTableActivity.this, MenuActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<Requisicion> call, Throwable t) {
                Constants.dismissDialog();
                t.printStackTrace();
                Log.e(EntregaMaterialesTableActivity.class.getSimpleName(), "Ha ocurrido un error. Contacte con el administrador");
                Toast.makeText(EntregaMaterialesTableActivity.this, "Ha ocurrido un error. Contacte con el administrador" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btnEntregaMateriales)
    void OnClickEntregaMateriales() {
        new AlertDialog.Builder(this)
                .setTitle("Envio de avance de obra")
                .setMessage("Desea enviar el avance?")
                .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e(EntregaMaterialesTableActivity.class.getSimpleName(), " " + mListaResponse.get(0).getDespacho());
                        Intent entregaMateriales = getIntent();
                        for (BodyResponse response : mListaResponse) {
                            Requisicion req = new Requisicion(
                                    // parametro = codproyecto parametro1 =codmodelo, parametro2 = codobra, parametro3 = codactividad
                                    entregaMateriales.getIntExtra("parametro", 0),
                                    entregaMateriales.getIntExtra("parametro1", 0),
                                    entregaMateriales.getIntExtra("parametro2", 0),
                                    entregaMateriales.getIntExtra("parametro3", 0),
                                    entregaMateriales.getIntExtra("parametro4", 0),
                                    Integer.parseInt(response.getCodigo()),
                                    Double.parseDouble(String.valueOf(response.getDespacho())),
                                    response.getCodUnidad(),
                                    response.getEsUnidad(),
                                    Double.parseDouble(String.valueOf(response.getSolicitado())),
                                    Double.parseDouble(String.valueOf(response.getDespacho())),
                                    Double.parseDouble(String.valueOf(response.getBodega())),
                                    response.getIncluir(),
                                    0,
                                    1,
                                    "Dispositivo",
                                    response.getTipo(),
                                    "ACTIVO"
                            );
                            sendReqToServer(req);
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
}
