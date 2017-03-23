package com.freela.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.freela.R;
import com.freela.model.Empresa;
import com.freela.model.Freelancer;
import com.freela.model.Usuario;

//import com.freela.manager.SessionManager;

/**
 * Created by Mateus - PC on 2016-10-25.
 */
public class DashboardActivity extends Activity implements View.OnClickListener {
    private Usuario usuario;
    private Button btnSair;
    //private SessionManager sessao;
    TextView nome;
    TextView email;
    TextView cidade;
    TextView estado;
    TextView pais;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

         nome = (TextView) findViewById(R.id.nome);
         email = (TextView) findViewById(R.id.email);
         cidade = (TextView) findViewById(R.id.cidade);
         estado = (TextView) findViewById(R.id.estado);
         pais = (TextView) findViewById(R.id.pais);

        btnSair = (Button) findViewById(R.id.btnSair);
        btnSair.setOnClickListener(this);

       /* sessao =  new SessionManager(getApplicationContext());

        sessao.checkLogin();

        if(((Usuario) sessao.getUsuarioLogado()).getPapel().equals(Papel.FREELANCER)) {

            Freelancer freelancer = (Freelancer) sessao.getUsuarioLogado();

            init(freelancer);

        } else {

            Empresa empresa = (Empresa) sessao.getUsuarioLogado();

            init(empresa);

        }*/



        Intent intent = getIntent();

        if (intent.hasExtra("usuario")) {

            usuario = (Usuario) intent.getSerializableExtra("usuario");

            nome.setText(usuario.getNome());
            email.setText(usuario.getEmail());
            cidade.setText(usuario.getLocalizacao().getCidade());
            estado.setText(usuario.getLocalizacao().getEstado());
            pais.setText(usuario.getLocalizacao().getPais());

       } else {

            Toast toast = Toast.makeText(this, "Erro!!!", Toast.LENGTH_SHORT);

            toast.show();

        }

    }

    private void init(Freelancer freelancer) {

    }

    private void init(Empresa empresa){

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnSair:
                sair();
                break;

        }
    }

    private void sair() {
        startActivity(new Intent(this, MainActivity.class));
        //sessao.logout();
    }
}
