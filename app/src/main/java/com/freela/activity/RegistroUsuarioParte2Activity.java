package com.freela.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.freela.R;
import com.freela.model.Usuario;

/**
 * Created by Gabriel on 01/11/2016.
 */

public class RegistroUsuarioParte2Activity extends Activity implements View.OnClickListener {
    private EditText nome;
    private EditText email;
    private Button btnProximo;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_usuario_parte2);

        nome = (EditText) findViewById(R.id.campo_nome);
        email = (EditText) findViewById(R.id.campo_email);

        btnProximo = (Button) findViewById(R.id.btnProximo);
        btnProximo.setOnClickListener(this);

        Intent intent = getIntent();

        if(intent.hasExtra("usuario")) {

            usuario =  (Usuario) intent.getSerializableExtra("usuario");

        } else {

            //TODO erro

        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnProximo:

                break;

            case R.id.btnVoltar:
                voltar();
                break;

        }

    }

    private void proximo() {

        usuario.setNome(nome.getText().toString());
        usuario.setEmail(email.getText().toString());

        Intent intent = new Intent(this, CriarSenhaActivity.class);
        intent.putExtra("usuario", usuario);

    }

    private void voltar() {

        startActivity(new Intent(this, CriarContaActivity.class));

    }
}
