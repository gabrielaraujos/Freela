package com.freela;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.freela.model.Usuario;

/**
 * Created by Mateus - PC on 2016-10-25.
 */
public class DashboardActivity extends Activity implements View.OnClickListener {
    private Usuario usuario;
    private Button btnSair;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        TextView nome = (TextView) findViewById(R.id.nome);
        TextView email = (TextView) findViewById(R.id.email);
        TextView cidade = (TextView) findViewById(R.id.cidade);
        TextView estado = (TextView) findViewById(R.id.estado);
        TextView pais = (TextView) findViewById(R.id.pais);

        btnSair = (Button) findViewById(R.id.btnSair);

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnSair:
                sair();
                break;

        }
    }

    private void sair() {
        usuario = null;

        startActivity(new Intent(this, MainActivity.class));
    }
}
