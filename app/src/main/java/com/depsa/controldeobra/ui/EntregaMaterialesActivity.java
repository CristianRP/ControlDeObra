package com.depsa.controldeobra.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.depsa.controldeobra.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EntregaMaterialesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega_materiales);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSiguiente)
    void OnClickSiguiente() {
        startActivity(new Intent(EntregaMaterialesActivity.this, EntregaMaterialesTableActivity.class));
    }
}
