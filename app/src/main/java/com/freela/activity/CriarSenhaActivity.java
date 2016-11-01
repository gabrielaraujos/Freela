package com.freela.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.freela.R;
import com.freela.model.Credenciais;
import com.freela.model.Usuario;

/**
 * Created by Gabriel on 01/11/2016.
 */

public class CriarSenhaActivity extends Activity implements View.OnClickListener {
    private EditText senha;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_senha);

        senha = (EditText) findViewById(R.id.campo_senha);

        Button btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(this);

        Button btnProximo = (Button) findViewById(R.id.btnProximo);
        btnProximo.setOnClickListener(this);

        Intent intent = getIntent();

        if(intent.hasExtra("usuario")) {

            usuario = (Usuario) intent.getSerializableExtra("usuario");

        } else {

            //TODO erro

        }


    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.btnProximo:
                proximo();
                break;

            case R.id.btnVoltar:
                voltar();
                break;
        }

    }

    private void proximo() {

        Credenciais credenciais = new Credenciais(usuario.getEmail(), senha.getText().toString());

        usuario.setCredenciais(credenciais);

        Intent intent = new Intent(this, DashboardActivity.class);
        intent.putExtra("usuario", usuario);

    }

    private void voltar(){

        startActivity(new Intent(this, RegistroUsuarioParte2Activity.class));

    }


}
