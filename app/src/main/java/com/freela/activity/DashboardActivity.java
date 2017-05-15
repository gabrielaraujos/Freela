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

public class DashboardActivity extends Activity implements View.OnClickListener {
    private Usuario usuario;
    private Button btSair;
    private Button btAddOportuniade;
    //private SessionManager sessao;
    TextView tvNome;
    TextView tvEmail;
    TextView tvCidade;
    TextView tvEstado;
    TextView tvPais;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        tvNome = (TextView) findViewById(R.id.dashboard_tv_nome);
        tvEmail = (TextView) findViewById(R.id.dashboard_tv_email);
        tvCidade = (TextView) findViewById(R.id.dashboard_tv_cidade);
        tvEstado = (TextView) findViewById(R.id.dashboard_tv_estado);
        tvPais = (TextView) findViewById(R.id.dashboard_tv_pais);

        btSair = (Button) findViewById(R.id.dashboard_bt_sair);
        btSair.setOnClickListener(this);

        btAddOportuniade = (Button) findViewById(R.id.dashboard_bt_add_oportunidade);
        btAddOportuniade.setOnClickListener(this);

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

            tvNome.setText(usuario.getNome());
            tvEmail.setText(usuario.getEmail());

            if (usuario.getLocalizacao() != null) {
                tvCidade.setText(usuario.getLocalizacao().getCidade());
                tvEstado.setText(usuario.getLocalizacao().getEstado());
                tvPais.setText(usuario.getLocalizacao().getPais());
            }
        } else {
            Toast toast = Toast.makeText(this, "Erro!!!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void init(Freelancer freelancer) {
    }

    private void init(Empresa empresa) {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dashboard_bt_sair:
                sair();
                break;

            case R.id.dashboard_bt_add_oportunidade:
                addOportundidade();
                break;
        }
    }

    private void sair() {
        startActivity(new Intent(this, MainActivity.class));
        //sessao.logout();
    }

    private void addOportundidade() {
        startActivity(new Intent(this, CriarOportunidadeActivity.class));
    }
}