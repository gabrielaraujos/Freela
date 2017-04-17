package com.freela.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.freela.R;
import com.freela.model.Credenciais;
import com.freela.model.Localizacao;
import com.freela.model.Usuario;

/**
 * Created by Gabriel on 01/11/2016.
 */

public class CriarSenhaActivity extends Activity implements View.OnClickListener {
    private EditText etSenha;
    private Button btVoltar;
    private Button btProximo;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_senha);

        etSenha = (EditText) findViewById(R.id.senha_et_senha);

        btVoltar = (Button) findViewById(R.id.senha_bt_voltar);
        btVoltar.setOnClickListener(this);

        btProximo = (Button) findViewById(R.id.senha_bt_proximo);
        btProximo.setOnClickListener(this);

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
            case R.id.senha_bt_proximo:
                proximo();
                break;

            case R.id.senha_bt_voltar:
                voltar();
                break;
        }
    }

    private void proximo() {
        Credenciais credenciais = new Credenciais(usuario.getEmail(), etSenha.getText().toString());

        usuario.setCredenciais(credenciais);

        Intent intent = new Intent(this, DashboardActivity.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }

    private void voltar(){
        startActivity(new Intent(this, RegistroUsuarioParte2Activity.class));
    }

    public void exibirMensagem(String texto) {
        Toast toast = Toast.makeText(this, texto, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void redirecionarDashboard(Usuario usuario) {
        //TODO: passar parametros usuário
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }


    class AddUsuarioTask extends AsyncTask<Usuario, Void, Usuario> {
        @Override
        protected void onPreExecute() {
            //TODO: exibir o progress
        }

        @Override
        protected Usuario doInBackground(Usuario... usuarios) {
            //return AddHttp.addUsuario(usuarios[0]);
            return new Usuario("gabriel@tvEmail.com", "Gabriel", new Localizacao("Brasil", "Belo Horizonte", "Minas Gerais"));
        }

        @Override
        protected void onPostExecute(Usuario usuario) {
            //TODO: esconder progress
            if (usuario == null) {
                exibirMensagem(getString(R.string.erro_autenticacao));
            } else {
                redirecionarDashboard(usuario);
            }
        }
    }
}
