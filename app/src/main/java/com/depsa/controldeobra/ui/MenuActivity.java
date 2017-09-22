package com.depsa.controldeobra.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.ImageView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.depsa.controldeobra.R;
import com.depsa.controldeobra.adapter.MenuAdapter;
import com.depsa.controldeobra.bean.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity
        implements MenuAdapter.OnItemClickListener {

    public static String ENTREGA_MATERIALES_TXT = "ENTREGA";
    public static String DEVOLUCION_MATERIAL_TXT = "DEVOLUCION DE MATERIAL";
    public static String RECEPCION_TAREAS_TXT = "RECEPCION DE TAREAS";
    public static String AVANCE_DE_OBRA = "AVANCE DE OBRA";
    public static String SOBREGIROS = "SOBREGIROS";
    public static int TIPO_MATERIAL;
    public static int NUMERO_SOLICITUD;

    public static ArrayList<MenuItem> ITEMS =
            new ArrayList<MenuItem>() {{
                add(new MenuItem(ENTREGA_MATERIALES_TXT, R.drawable.entrega_materiales));
                add(new MenuItem(AVANCE_DE_OBRA, R.drawable.avance_obra));
                //add(new MenuItem(RECEPCION_TAREAS_TXT, R.drawable.recepcion_tareas));
                //add(new MenuItem(AVANCE_DE_OBRA, R.drawable.avance_obra));
                //add(new MenuItem(SOBREGIROS, R.drawable.sobre_giros));
            }};
    @BindView(R.id.recyclerViewMenu)
    RecyclerView mRecyclerViewMenu;
    @BindView(R.id.imgLogo)
    ImageView mImgLogo;

    private LinearLayoutManager mLayoutManager;
    private MenuAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        mRecyclerViewMenu.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewMenu.setLayoutManager(mLayoutManager);

        mAdapter = new MenuAdapter(ITEMS, this);
        mAdapter.setHasStableIds(true);
        mAdapter.setOnItemClickListener(this);
        mRecyclerViewMenu.setAdapter(mAdapter);

    }

    @Override
    public void onItemClick(MenuAdapter.ViewHolder item, int position) {

    }

    @Override
    public void btnManualOnClick(View v, int position) {
        List<String> op = new ArrayList<>();
        op.add("Ingresar número de solicitud");
        op.add("Escanear");
        MenuItem menuItem = ITEMS.get(position);
        if (menuItem.getDescripcion().equals(AVANCE_DE_OBRA)) {
            Intent entrega = new Intent(MenuActivity.this, EntregaMaterialesActivity.class);
            entrega.putExtra("titulo", AVANCE_DE_OBRA);
            startActivity(entrega);
        } else if (menuItem.getDescripcion().equals(ENTREGA_MATERIALES_TXT)) {
            new MaterialDialog.Builder(this)
                    .title("Entrega materiales")
                    .items(op)
                    .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                        @Override
                        public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                            if (which == 0) {
                                showDialogSolicitud();
                            } else if (which == 1) {
                                startActivity(new Intent(MenuActivity.this, EscanearActivity.class));
                            }
                            return false;
                        }
                    })
                    .positiveText("Aceptar")
                    .negativeText("Cancelar")
                    .show();
        } else if (menuItem.getDescripcion().equals(DEVOLUCION_MATERIAL_TXT)) {

        } else if (menuItem.getDescripcion().equals(RECEPCION_TAREAS_TXT)) {

        } else if (menuItem.getDescripcion().equals(SOBREGIROS)) {

        }
    }

    @Override
    public void btnScanOnClick(View v, int position) {
        MenuItem menuItem = ITEMS.get(position);
        if (menuItem.getDescripcion().equals(AVANCE_DE_OBRA)) {
            /*Intent entrega = new Intent(MenuActivity.this, EntregaMaterialesActivity.class);
            entrega.putExtra("titulo", AVANCE_DE_OBRA);
            startActivity(new Intent(MenuActivity.this, EscanearActivity.class));
            startActivity(entrega);*/
        } else if (menuItem.getDescripcion().equals(ENTREGA_MATERIALES_TXT)) {
            startActivity(new Intent(MenuActivity.this, EscanearActivity.class));
        } else if (menuItem.getDescripcion().equals(DEVOLUCION_MATERIAL_TXT)) {

        } else if (menuItem.getDescripcion().equals(RECEPCION_TAREAS_TXT)) {

        } else if (menuItem.getDescripcion().equals(SOBREGIROS)) {

        }
    }

    private void showDialogSolicitud() {
        new MaterialDialog.Builder(this)
                .title("Ingresa el número de solicitud:")
                .inputType(InputType.TYPE_CLASS_NUMBER)
                .input(null, null, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        NUMERO_SOLICITUD = Integer.parseInt(input.toString());
                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        showDialogTipoMaterial();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void showDialogTipoMaterial() {
        List<String> tipos = new ArrayList<>();
        tipos.add("Material");
        tipos.add("Mano de obra");
        new MaterialDialog.Builder(this)
                .title("Tipo de material")
                .items(tipos)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        TIPO_MATERIAL = which + 1;
                        Intent encabezado = new Intent(MenuActivity.this, EncabezadoSolictudActivity.class);
                        encabezado.putExtra("solicitud", NUMERO_SOLICITUD);
                        encabezado.putExtra("tipoMaterial", TIPO_MATERIAL);
                        startActivity(encabezado);
                        return false;
                    }
                })
                .positiveText("Aceptar")
                .negativeText("Cancelar")
                .show();
    }
}
