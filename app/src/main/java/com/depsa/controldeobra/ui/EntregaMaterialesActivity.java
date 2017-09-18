package com.depsa.controldeobra.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.depsa.controldeobra.R;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.depsa.controldeobra.ui.MenuActivity.AVANCE_DE_OBRA;
import static com.depsa.controldeobra.ui.MenuActivity.DEVOLUCION_MATERIAL_TXT;
import static com.depsa.controldeobra.ui.MenuActivity.ENTREGA_MATERIALES_TXT;
import static com.depsa.controldeobra.ui.MenuActivity.RECEPCION_TAREAS_TXT;
import static com.depsa.controldeobra.ui.MenuActivity.SOBREGIROS;

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
    @BindView(R.id.imgLogoItem)
    ImageView imgLogoItem;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindDrawable(R.drawable.entrega_materiales)
    Drawable imgEntregaMateriales;
    @BindDrawable(R.drawable.devolucion_material)
    Drawable imgDevolucionMaterial;
    @BindDrawable(R.drawable.recepcion_tareas)
    Drawable imgRepecionTarea;
    @BindDrawable(R.drawable.avance_obra)
    Drawable imgAvanceObra;
    @BindDrawable(R.drawable.sobre_giros)
    Drawable sobregiros;

    private static final int GET_PROYECTOS = 1;
    private static final int GET_MODELOS = 2;
    private static final int GET_OBRA = 3;
    private static final int GET_ACTIVIDAD = 4;
    private static final int GET_TAREA = 5;
    private static final int GET_DETALLE = 6;
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

        Intent menu = getIntent();
        txtTitle.setText(menu.getStringExtra("titulo"));
        if (menu.getStringExtra("titulo").equals(ENTREGA_MATERIALES_TXT)) {
            imgLogoItem.setBackground(imgEntregaMateriales);
        } else if (menu.getStringExtra("titulo").equals(DEVOLUCION_MATERIAL_TXT)) {
            imgLogoItem.setBackground(imgDevolucionMaterial);
        } else if (menu.getStringExtra("titulo").equals(RECEPCION_TAREAS_TXT)) {
            imgLogoItem.setBackground(imgRepecionTarea);
        } else if (menu.getStringExtra("titulo").equals(AVANCE_DE_OBRA)) {
            imgLogoItem.setBackground(imgAvanceObra);
        } else if (menu.getStringExtra("titulo").equals(SOBREGIROS)) {
            imgLogoItem.setBackground(sobregiros);
        }

    }

    @OnClick(R.id.btnProyecto)
    void OnClickProyecto() {
        Intent proyecto = new Intent(EntregaMaterialesActivity.this, BodyResponseActivity.class);
        proyecto.putExtra("tipoConsulta", GET_PROYECTOS);
        startActivityForResult(proyecto, GET_PROYECTOS);
    }

    @OnClick(R.id.btnModelo)
    void OnClickModelo() {
        if (codProyecto != 0) {
            Intent proyecto = new Intent(EntregaMaterialesActivity.this, BodyResponseActivity.class);
            proyecto.putExtra("tipoConsulta", GET_MODELOS);
            proyecto.putExtra("parametro", codProyecto);
            startActivityForResult(proyecto, GET_MODELOS);
        } else {
            Toast.makeText(this, "Seleccione el proyecto", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btnObra)
    void OnClickObra() {
        if (codModelo != 0) {
            Intent proyecto = new Intent(EntregaMaterialesActivity.this, BodyResponseActivity.class);
            proyecto.putExtra("tipoConsulta", GET_OBRA);
            proyecto.putExtra("parametro", codProyecto);
            proyecto.putExtra("parametro1", codModelo);
            startActivityForResult(proyecto, GET_OBRA);
        } else {
            Toast.makeText(this, "Seleccione el modelo", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btnActividad)
    void OnClickActividad() {
        if (codObra != 0) {
            Intent proyecto = new Intent(EntregaMaterialesActivity.this, BodyResponseActivity.class);
            proyecto.putExtra("tipoConsulta", GET_ACTIVIDAD);
            proyecto.putExtra("parametro", codProyecto);
            proyecto.putExtra("parametro1", codModelo);
            startActivityForResult(proyecto, GET_ACTIVIDAD);
        } else {
            Toast.makeText(this, "Selecciona la obra", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btnTarea)
    void OnClickTarea() {
        if (codActividad != 0) {
            Intent proyecto = new Intent(EntregaMaterialesActivity.this, BodyResponseActivity.class);
            proyecto.putExtra("tipoConsulta", GET_TAREA);
            proyecto.putExtra("parametro", codProyecto);
            proyecto.putExtra("parametro1", codModelo);
            proyecto.putExtra("parametro2", codActividad);
            startActivityForResult(proyecto, GET_TAREA);
        } else {
            Toast.makeText(this, "Selecciona la actividad", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btnSiguiente)
    void OnClickSiguiente() {
        Intent entrega = new Intent(EntregaMaterialesActivity.this, EntregaMaterialesTableActivity.class);
        //startActivity(new Intent(EntregaMaterialesActivity.this, EntregaMaterialesTableActivity.class));
        entrega.putExtra("tipoConsulta", GET_DETALLE);
        entrega.putExtra("parametro", codProyecto);
        entrega.putExtra("parametro1", codModelo);
        entrega.putExtra("parametro2", codObra);
        entrega.putExtra("parametro3", codActividad);
        entrega.putExtra("parametro4", codTarea);
        startActivity(entrega);
        Log.e("VALUES:", " " + codProyecto + " " + codModelo + " " + codObra + " " + codActividad +
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
            } else {
                Toast.makeText(this, "Selecciona el proyecto", Toast.LENGTH_SHORT).show();
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
        } else if (requestCode == GET_DETALLE) {
            if (resultCode == RESULT_OK) {
                //codTarea = Integer.parseInt(data.getStringExtra("codigo"));
                mButtonSiguiente.setEnabled(true);
                mBtnTarea.setBackgroundColor(colorGreen);
            }
        }
    }
}
