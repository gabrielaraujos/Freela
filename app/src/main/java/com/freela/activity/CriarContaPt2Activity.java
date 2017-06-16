package com.freela.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.freela.R;
import com.freela.model.Empresa;
import com.freela.model.Freelancer;

/**
 * Created by Gabriel on 01/11/2016.
 */

public class CriarContaPt2Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText etNome;
    private EditText etEmail;
    private TextView tvProfissao;
    private EditText etProfissao;
    private Button btProximo;
    private Button btVoltar;
    private Freelancer freelancer;
    private Empresa empresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_conta_pt2);

        etNome = (EditText) findViewById(R.id.conta2_et_nome);
        etEmail = (EditText) findViewById(R.id.conta2_et_email);
        etProfissao = (EditText) findViewById(R.id.conta2_et_profissao);

        tvProfissao = (TextView) findViewById(R.id.conta2_lb_profissao);

        btProximo = (Button) findViewById(R.id.conta2_bt_proximo);
        btProximo.setOnClickListener(this);

        btVoltar = (Button)  findViewById(R.id.conta2_bt_voltar);
        btVoltar.setOnClickListener(this);

        Intent intent = getIntent();

        if(intent.hasExtra("freelancer")) {
            freelancer = (Freelancer) intent.getSerializableExtra("freelancer");
        } else if(intent.hasExtra("empresa")) {
            empresa = (Empresa) intent.getSerializableExtra("empresa");
            etProfissao.setVisibility(View.GONE);
            tvProfissao.setVisibility(View.GONE);
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

        Intent intent = new Intent(this, CriarSenhaActivity.class);

        if(freelancer != null) {
            freelancer.setEmail(etEmail.getText().toString());
            freelancer.setNome(etNome.getText().toString());
            freelancer.setProfissao(etProfissao.getText().toString());

            intent.putExtra("freelancer", freelancer);
        } else if( empresa != null) {
            empresa.setEmail(etEmail.getText().toString());
            empresa.setNome(etNome.getText().toString());

            intent.putExtra("empresa", empresa);
        }

        startActivity(intent);
    }

    private void voltar(){
        startActivity(new Intent(this, CriarContaActivity.class));
    }
}
