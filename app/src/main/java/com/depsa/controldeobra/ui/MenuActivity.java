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
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.depsa.controldeobra.R;
import com.depsa.controldeobra.adapter.MenuAdapter;
import com.depsa.controldeobra.bean.MenuItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity
        implements MenuAdapter.OnItemClickListener {

    public static String ENTREGA_MATERIALES_TXT = "ENTREGA";
    public static String DEVOLUCION_MATERIAL_TXT = "DEVOLUCION DE MATERIAL";
    public static String RECEPCION_TAREAS_TXT = "RECEPCION DE TAREAS";
    public static String AVANCE_DE_OBRA = "AVANCE DE OBRA";
    public static String SOBREGIROS = "SOBREGIROS";

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
        MenuItem menuItem = ITEMS.get(position);
        if (menuItem.getDescripcion().equals(AVANCE_DE_OBRA)) {
            Intent entrega = new Intent(MenuActivity.this, EntregaMaterialesActivity.class);
            entrega.putExtra("titulo", AVANCE_DE_OBRA);
            startActivity(entrega);
        } else if (menuItem.getDescripcion().equals(ENTREGA_MATERIALES_TXT)) {
            showDialogSolicitud();
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
    int conio;
    private void showDialogSolicitud() {

        conio = 0;
        new MaterialDialog.Builder(this)
                .title("Ingresa el número de solicitud:")
                .inputType(InputType.TYPE_CLASS_NUMBER)
                .input(null, null, false, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        conio = Integer.parseInt(input.toString());
                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Toast.makeText(MenuActivity.this, "cnoio " + conio, Toast.LENGTH_SHORT).show();
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
}
