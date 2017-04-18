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

public class CriarContaPt2Activity extends Activity implements View.OnClickListener {
    private EditText etNome;
    private EditText etEmail;
    private Button btProximo;
    private Button btVoltar;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_conta_pt2);

        etNome = (EditText) findViewById(R.id.conta2_et_nome);
        etEmail = (EditText) findViewById(R.id.conta2_et_email);

        btProximo = (Button) findViewById(R.id.conta2_bt_proximo);
        btProximo.setOnClickListener(this);

        btVoltar = (Button)  findViewById(R.id.conta2_bt_voltar);
        btVoltar.setOnClickListener(this);

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
            case R.id.conta2_bt_proximo:
                proximo();
                break;

            case R.id.conta2_bt_voltar:
                voltar();
                break;
        }
    }

    private void proximo() {
        usuario.setNome(etNome.getText().toString());
        usuario.setEmail(etEmail.getText().toString());

        Intent intent = new Intent(this, CriarSenhaActivity.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }

    private void voltar(){
        startActivity(new Intent(this, CriarContaActivity.class));
    }
}
