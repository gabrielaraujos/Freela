package com.freela.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.freela.Papel;
import com.freela.R;
import com.freela.model.Usuario;

public class RegistroUsuarioActivity extends Activity implements View.OnClickListener {
    private Button btnEmpresa;
    private Button btnFreelancer;
    private Button btnVoltar;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_usuario);

        btnEmpresa = (Button) findViewById(R.id.btnEmpresa);
        btnEmpresa.setOnClickListener(this);

        btnFreelancer = (Button) findViewById(R.id.btnFreelancer);
        btnFreelancer.setOnClickListener(this);

        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Usuario usuario = new Usuario();

        switch (view.getId()) {

            case R.id.btnEmpresa:

                usuario.setPapel(Papel.EMPRESA);

                proximo(usuario);
                break;

            case R.id.btnFreelancer:
                usuario.setPapel(Papel.FREELANCER);

                proximo(usuario);
                break;

            case R.id.btnVoltar:
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


