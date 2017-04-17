package com.freela.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.freela.Papel;
import com.freela.R;
import com.freela.model.Usuario;

public class CriarContaActivity extends Activity implements View.OnClickListener {
    private Button btEmpresa;
    private Button btFreelancer;
    private Button btVoltar;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_usuario);

        btEmpresa = (Button) findViewById(R.id.conta_bt_empresa);
        btEmpresa.setOnClickListener(this);

        btFreelancer = (Button) findViewById(R.id.conta_bt_freelancer);
        btFreelancer.setOnClickListener(this);

        btVoltar = (Button) findViewById(R.id.conta_bt_voltar);
        btVoltar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Usuario usuario = new Usuario();

        switch (view.getId()) {
            case R.id.conta_bt_empresa:
                usuario.setPapel(Papel.EMPRESA);
                proximo(usuario);
                break;

            case R.id.conta_bt_freelancer:
                usuario.setPapel(Papel.FREELANCER);
                proximo(usuario);
                break;

            case R.id.conta_bt_voltar:
                voltar();
                break;
        }
    }

    private void proximo(Usuario usuario) {
        Intent intent = new Intent(this, RegistroUsuarioParte2Activity.class);
        intent.putExtra("usuario", usuario);

        startActivity(intent);
    }

    private void voltar() {
        startActivity(new Intent(this, MainActivity.class));
    }
}


