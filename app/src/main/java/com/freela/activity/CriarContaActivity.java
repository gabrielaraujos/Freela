package com.freela.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.freela.R;
import com.freela.model.Empresa;
import com.freela.model.Freelancer;
import com.freela.model.Papel;

public class CriarContaActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btEmpresa;
    private Button btFreelancer;
    private Button btVoltar;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_conta);

        btEmpresa = (Button) findViewById(R.id.conta_bt_empresa);
        btEmpresa.setOnClickListener(this);

        btFreelancer = (Button) findViewById(R.id.conta_bt_freelancer);
        btFreelancer.setOnClickListener(this);

        btVoltar = (Button) findViewById(R.id.conta_bt_voltar);
        btVoltar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.conta_bt_empresa:
                Empresa empresa = new Empresa();
                empresa.setPapel(Papel.EMPRESA);
                proximo(empresa);
                break;

            case R.id.conta_bt_freelancer:
                Freelancer freelancer =  new Freelancer();
                freelancer.setPapel(Papel.FREELANCER);
                proximo(freelancer);
                break;

            case R.id.conta_bt_voltar:
                voltar();
                break;
        }
    }

    private void proximo(Freelancer freelancer) {
        Intent intent = new Intent(this, CriarContaPt2Activity.class);


        intent.putExtra("freelancer", freelancer);

        startActivity(intent);
    }

    private void proximo(Empresa empresa) {
        Intent intent = new Intent(this, CriarContaPt2Activity.class);


        intent.putExtra("empresa", empresa);

        startActivity(intent);
    }

    private void voltar() {
        startActivity(new Intent(this, MainActivity.class));
    }
}


