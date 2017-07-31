package com.depsa.controldeobra.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.depsa.controldeobra.R;
import com.depsa.controldeobra.adapter.MenuAdapter;
import com.depsa.controldeobra.bean.MenuItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity
        implements MenuAdapter.OnItemClickListener {

    public static String ENTREGA_MATERIALES_TXT = "ENTREGA DE MATERIALES";
    public static String DEVOLUCION_MATERIAL_TXT = "DEVOLUCION DE MATERIAL";
    public static String RECEPCION_TAREAS_TXT = "RECEPCION DE TAREAS";
    public static String AVANCE_DE_OBRA = "AVANCE DE OBRA";
    public static String SOBREGIROS = "SOBREGIROS";

    public static ArrayList<MenuItem> ITEMS =
            new ArrayList<MenuItem>() {{
                add(new MenuItem(ENTREGA_MATERIALES_TXT, R.drawable.entrega_materiales));
                add(new MenuItem(DEVOLUCION_MATERIAL_TXT, R.drawable.devolucion_material));
                add(new MenuItem(RECEPCION_TAREAS_TXT, R.drawable.recepcion_tareas));
                add(new MenuItem(AVANCE_DE_OBRA, R.drawable.avance_obra));
                add(new MenuItem(SOBREGIROS, R.drawable.sobre_giros));
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
        if (menuItem.getDescripcion().equals(ENTREGA_MATERIALES_TXT)) {
            Intent entrega = new Intent(MenuActivity.this, EntregaMaterialesActivity.class);
            entrega.putExtra("titulo", ENTREGA_MATERIALES_TXT);
            startActivity(entrega);
        } else if (menuItem.getDescripcion().equals(DEVOLUCION_MATERIAL_TXT)) {

        } else if (menuItem.getDescripcion().equals(RECEPCION_TAREAS_TXT)) {

        } else if (menuItem.getDescripcion().equals(AVANCE_DE_OBRA)) {

        } else if (menuItem.getDescripcion().equals(SOBREGIROS)) {

        }
    }

    @Override
    public void btnScanOnClick(View v, int position) {
        startActivity(new Intent(MenuActivity.this, EscanearActivity.class));
    }
}
