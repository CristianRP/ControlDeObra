package com.depsa.controldeobra.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.depsa.controldeobra.R;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EntregaMaterialesActivity extends AppCompatActivity {

    @BindView(R.id.btnSiguiente)
    Button mButtonSiguiente;
    @BindView(R.id.btnProyecto)
    Button mBtnProyecto;
    @BindView(R.id.btnModelo)
    Button mBtnModelo;
    @BindView(R.id.btnObra)
    Button mBtnObra;
    @BindView(R.id.btnActividad)
    Button mBtnActividad;
    @BindView(R.id.btnTarea)
    Button mBtnTarea;
    @BindColor(R.color.colorGreen)
    int colorGreen;

    private static final int GET_PROYECTOS = 1;
    private static final int GET_MODELOS = 2;
    private static final int GET_OBRA = 3;
    private static final int GET_ACTIVIDAD = 4;
    private static final int GET_TAREA = 5;
    private int codProyecto;
    private int codModelo;
    private int codObra;
    private int codActividad;
    private int codTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega_materiales);
        ButterKnife.bind(this);
        mButtonSiguiente.setEnabled(false);

    }

    @OnClick(R.id.btnProyecto)
    void OnClickProyecto() {
        Intent proyecto = new Intent(EntregaMaterialesActivity.this, BodyResponseActivity.class);
        proyecto.putExtra("tipoConsulta", GET_PROYECTOS);
        startActivityForResult(proyecto, GET_PROYECTOS);
    }

    @OnClick(R.id.btnModelo)
    void OnClickModelo() {
        Intent proyecto = new Intent(EntregaMaterialesActivity.this, BodyResponseActivity.class);
        proyecto.putExtra("tipoConsulta", GET_MODELOS);
        proyecto.putExtra("parametro", codProyecto);
        startActivityForResult(proyecto, GET_MODELOS);
    }

    @OnClick(R.id.btnObra)
    void OnClickObra() {
        Intent proyecto = new Intent(EntregaMaterialesActivity.this, BodyResponseActivity.class);
        proyecto.putExtra("tipoConsulta", GET_OBRA);
        proyecto.putExtra("parametro", codProyecto);
        proyecto.putExtra("parametro1", codModelo);
        startActivityForResult(proyecto, GET_OBRA);
    }

    @OnClick(R.id.btnActividad)
    void OnClickActividad() {
        Intent proyecto = new Intent(EntregaMaterialesActivity.this, BodyResponseActivity.class);
        proyecto.putExtra("tipoConsulta", GET_ACTIVIDAD);
        proyecto.putExtra("parametro", codProyecto);
        proyecto.putExtra("parametro1", codModelo);
        startActivityForResult(proyecto, GET_ACTIVIDAD);
    }

    @OnClick(R.id.btnTarea)
    void OnClickTarea() {
        Intent proyecto = new Intent(EntregaMaterialesActivity.this, BodyResponseActivity.class);
        proyecto.putExtra("tipoConsulta", GET_TAREA);
        proyecto.putExtra("parametro", codProyecto);
        proyecto.putExtra("parametro1", codModelo);
        proyecto.putExtra("parametro2", codActividad);
        startActivityForResult(proyecto, GET_TAREA);
    }

    @OnClick(R.id.btnSiguiente)
    void OnClickSiguiente() {
        Intent entrega = new Intent(EntregaMaterialesActivity.this, EntregaMaterialesTableActivity.class);
        //startActivity(new Intent(EntregaMaterialesActivity.this, EntregaMaterialesTableActivity.class));
        entrega.putExtra("tipoConsulta", GET_TAREA);
        entrega.putExtra("parametro", codProyecto);
        entrega.putExtra("parametro1", codModelo);
        entrega.putExtra("parametro2", codActividad);
        entrega.putExtra("parametro3", codTarea);
        startActivity(entrega);
        Log.e("VALUES:", " " + codProyecto + " " + codModelo + " " + codObra + " " + codActividad  +
        " " + codTarea);
    }

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_PROYECTOS) {
            if (resultCode == RESULT_OK) {
                codProyecto = Integer.parseInt(data.getStringExtra("codigo"));
                Log.d("VALUE PROYECTO", " " + codProyecto);
                mBtnProyecto.setBackgroundColor(colorGreen);
            }
        } else if (requestCode == GET_MODELOS) {
            if (resultCode == RESULT_OK) {
                codModelo = Integer.parseInt(data.getStringExtra("codigo"));
                Log.d("VALUE MODELO", " " + codModelo);
                mBtnModelo.setBackgroundColor(colorGreen);
            }
        } else if (requestCode == GET_OBRA) {
            if (resultCode == RESULT_OK) {
                codObra = Integer.parseInt(data.getStringExtra("codigo"));
                Log.d("VALUE OBRA", " " + codObra);
                mBtnObra.setBackgroundColor(colorGreen);
            }
        } else if (requestCode == GET_ACTIVIDAD) {
            if (resultCode == RESULT_OK) {
                codActividad = Integer.parseInt(data.getStringExtra("codigo"));
                Log.d("VALUE ACTIVIDAD", " " + codActividad);
                mBtnActividad.setBackgroundColor(colorGreen);
            }
        } else if (requestCode == GET_TAREA) {
            if (resultCode == RESULT_OK) {
                codTarea = Integer.parseInt(data.getStringExtra("codigo"));
                mButtonSiguiente.setEnabled(true);
                mBtnTarea.setBackgroundColor(colorGreen);
            }
        }
    }
}
