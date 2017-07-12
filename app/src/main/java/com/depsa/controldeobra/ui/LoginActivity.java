package com.depsa.controldeobra.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.depsa.controldeobra.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.imgLogoLogin)
    ImageView mImgLogo;
    @BindView(R.id.txtUserName)
    EditText mUserName;
    @BindView(R.id.txtClave)
    EditText mClave;
    @BindView(R.id.btnEntrar)
    Button mBtnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Picasso.with(this)
                .load(R.drawable.logo_depsa)
                .into(mImgLogo);
    }

    @OnClick(R.id.btnEntrar)
    void OnEntrarClick() {
        startActivity(new Intent(LoginActivity.this, MenuActivity.class));
    }
}
