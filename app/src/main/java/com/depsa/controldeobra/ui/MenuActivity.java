package com.depsa.controldeobra.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

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

    }
}
