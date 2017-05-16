package com.freela.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.freela.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btEntrar;
    private Button btRegistrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btEntrar = (Button) findViewById(R.id.main_bt_entrar);
        btEntrar.setOnClickListener(this);

        btRegistrar = (Button) findViewById(R.id.main_bt_criar_conta);
        btRegistrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_bt_entrar:
                startActivity(new Intent(this, LoginActivity.class));
                break;

            case R.id.main_bt_criar_conta:
                 startActivity(new Intent(this, CriarContaActivity.class));
                break;
        }

    }

}
