package com.depsa.controldeobra.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.depsa.controldeobra.R;
import com.depsa.controldeobra.adapter.BodyResponseAdapter;
import com.depsa.controldeobra.api.ControlObraWebAPI;
import com.depsa.controldeobra.api.ServiceGenerator;
import com.depsa.controldeobra.bean.BodyResponse;
import com.depsa.controldeobra.util.Constants;

import java.io.IOException;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BodyResponseActivity extends AppCompatActivity
        implements BodyResponseAdapter.OnItemClickListener {

    @BindView(R.id.rvBodyResponse)
    RecyclerView mRecyclerBodyResponse;
    @BindString(R.string.escaner_load_text)
    String cargandoStr;

    private LinearLayoutManager mLayoutManager;
    private BodyResponseAdapter mAdapter;
    private ControlObraWebAPI mControlObraWebAPI;
    private List<BodyResponse> mListaResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_response);
        ButterKnife.bind(this);

        mControlObraWebAPI = ServiceGenerator.createService(ControlObraWebAPI.class);

        mRecyclerBodyResponse.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerBodyResponse.setLayoutManager(mLayoutManager);
        mRecyclerBodyResponse.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
        );
        Intent entregaMateriales = getIntent();
        if (entregaMateriales.getIntExtra("tipoConsulta", 0) == 1) {
            Constants.showDialog(this, cargandoStr);
            getBodyResponse(entregaMateriales.getIntExtra("tipoConsulta", 0));
        } else if (entregaMateriales.getIntExtra("tipoConsulta", 0) == 2) {
            Constants.showDialog(this, cargandoStr);
            getBodyResponse(entregaMateriales.getIntExtra("tipoConsulta", 0),
                            entregaMateriales.getIntExtra("parametro", 0));
        } else if (entregaMateriales.getIntExtra("tipoConsulta", 0) == 3) {
            Constants.showDialog(this, cargandoStr);
            getBodyResponse(entregaMateriales.getIntExtra("tipoConsulta", 0),
                            entregaMateriales.getIntExtra("parametro", 0),
                            entregaMateriales.getIntExtra("parametro1", 0));
        } else if (entregaMateriales.getIntExtra("tipoConsulta", 0) == 4) {
            Constants.showDialog(this, cargandoStr);
            getBodyResponse(entregaMateriales.getIntExtra("tipoConsulta", 0),
                            entregaMateriales.getIntExtra("parametro", 0),
                            entregaMateriales.getIntExtra("parametro1", 0));
        } else if (entregaMateriales.getIntExtra("tipoConsulta", 0) == 5) {
            Constants.showDialog(this, cargandoStr);
            getBodyResponse(entregaMateriales.getIntExtra("tipoConsulta", 0),
                            entregaMateriales.getIntExtra("parametro", 0),
                            entregaMateriales.getIntExtra("parametro1", 0),
                            entregaMateriales.getIntExtra("parametro2", 0),
                            0);
        }

    }

    @Override
    public void onItemClick(BodyResponseAdapter.ViewHolder item, int position) {
        BodyResponse bodyResponse = mListaResponse.get(position);

        Log.d("VALUE",  " " + bodyResponse.getCodigo());
        Intent returnIntent = new Intent();
        returnIntent.putExtra("codigo", bodyResponse.getCodigo());
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    private void setDataToRecycler(List<BodyResponse> items) {
        mAdapter = new BodyResponseAdapter(items, this);
        mAdapter.setHasStableIds(true);
        mAdapter.setOnItemClickListener(this);
        mRecyclerBodyResponse.setAdapter(mAdapter);
    }

    private void getBodyResponse(Integer... parameters) {
        Call<List<BodyResponse>> getBodyResponse = mControlObraWebAPI.getProyectos(parameters[0]);
        if (parameters[0] == 1) {
            getBodyResponse = mControlObraWebAPI.getProyectos(parameters[0]);
        } else if (parameters[0] == 2) {
            getBodyResponse = mControlObraWebAPI.getModelo(parameters[0], parameters[1]);
        } else if (parameters[0] == 3) {
            getBodyResponse = mControlObraWebAPI.getObra(parameters[0], parameters[1], parameters[2]);
        } else if (parameters[0] == 4) {
            getBodyResponse = mControlObraWebAPI.getActividad(parameters[0], parameters[1], parameters[2]);
        } else if (parameters[0] == 5) {
            getBodyResponse = mControlObraWebAPI.getTarea(parameters[0], parameters[1], parameters[2], parameters[3],
                    0);
        }
        getBodyResponse.enqueue(new Callback<List<BodyResponse>>() {
            @Override
            public void onResponse(Call<List<BodyResponse>> call, Response<List<BodyResponse>> response) {
                Constants.dismissDialog();
                String error = "Ha ocurrido un error. Contacte con el administrador";
                if (!response.isSuccessful()) {
                    if (response.raw().code() != 200) {
                        Toast.makeText(BodyResponseActivity.this, error, Toast.LENGTH_SHORT).show();
                        Log.e(LoginActivity.class.getSimpleName(), error);
                        return;
                    }
                    if (response.errorBody()
                            .contentType()
                            .subtype()
                            .equals("json")) {
                        Log.e(BodyResponseActivity.class.getSimpleName(), "Error " + response.errorBody().toString());
                    } else {
                        try {
                            // Errores no relacionados con el API
                            Log.e(BodyResponseActivity.class.getSimpleName(), response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Log.e(BodyResponseActivity.class.getSimpleName(), "Successfull!");
                mListaResponse = response.body();
                setDataToRecycler(response.body());
            }

            @Override
            public void onFailure(Call<List<BodyResponse>> call, Throwable t) {
                Constants.dismissDialog();
                Log.e(BodyResponseActivity.class.getSimpleName(), "Ha ocurrido un error. Contacte con el administrador");
            }
        });
    }
}
