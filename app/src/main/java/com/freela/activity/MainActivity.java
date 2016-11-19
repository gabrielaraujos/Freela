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
    private Button btOpotundiade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(this);

        btnRegistrar = (Button) findViewById(R.id.btnRegistrarUsr);
        btnRegistrar.setOnClickListener(this);

        btOpotundiade = (Button) findViewById(R.id.btnOportunidade);
        btOpotundiade.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;

        switch (view.getId()) {

            case R.id.btnEntrar:
                intent = new Intent(view.getContext(), LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.btnRegistrarUsr:
                intent = new Intent(view.getContext(), RegistroUsuarioActivity.class);
                startActivity(intent);
                break;

            case R.id.btnOportunidade:
                intent = new Intent(view.getContext(), RegistrarOportunidadeActivity.class);
                startActivity(intent);
                break;

        }

    }

}
