package com.freela.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.freela.R;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button btnEntrar;
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(this);

        btnRegistrar = (Button) findViewById(R.id.btnRegistrarUsr);
        btnRegistrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnEntrar:
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.btnRegistrarUsr:
                //TODO: redirecionar para Registro de usuario
                break;

        }

    }

}
