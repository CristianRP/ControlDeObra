package com.depsa.controldeobra.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.depsa.controldeobra.R;
import com.depsa.controldeobra.adapter.MenuAdapter;
import com.depsa.controldeobra.bean.MenuItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity
        implements MenuAdapter.OnItemClickListener {

    public static ArrayList<MenuItem> ITEMS =
            new ArrayList<MenuItem>() {{
                add(new MenuItem("ENTREGA DE MATERIALES", R.drawable.entrega_materiales));
                add(new MenuItem("DEVOLUCION DE MATERIAL", R.drawable.devolucion_material));
                add(new MenuItem("RECEPCION DE TAREAS", R.drawable.recepcion_tareas));
                add(new MenuItem("AVANCE DE OBRA", R.drawable.avance_obra));
                add(new MenuItem("SOBREGIROS", R.drawable.sobre_giros));
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
        MenuItem menuItem = ITEMS.get(position);
        switch (menuItem.getDescripcion()) {
            case "ENTREGA DE MATERIALES":
                startActivity(new Intent(MenuActivity.this, EntregaMaterialesActivity.class));
                break;
            case "DEVOLUCION DE MATERIAL":
                break;
            case "RECEPCION DE TAREAS":
                break;
            case "AVANCE DE OBRA":
                break;
            case "SOBREGIROS":
                break;
        }
    }

    @Override
    public void btnManualOnClick(View v, int position) {
        Toast.makeText(this, "MANUAL :'v", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void btnScanOnClick(View v, int position) {
        Toast.makeText(this, "SCAN :'v", Toast.LENGTH_SHORT).show();
    }
}
