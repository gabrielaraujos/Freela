package com.freela.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.freela.R;
import com.freela.SessionManager.SessionManager;
import com.freela.handler.FreelaDBHandler;
import com.freela.model.Empresa;
import com.freela.model.Freelancer;
import com.freela.model.Usuario;

/**
 * Created by Gabriel on 01/11/2016.
 */

public class CriarSenhaActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etSenha;
    private Button btVoltar;
    private Button btProximo;
    private Freelancer freelancer;
    private Empresa empresa;
    private SessionManager sessionManager;

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

        if(intent.hasExtra("freelancer")) {
            freelancer = (Freelancer) intent.getSerializableExtra("freelancer");
        } else  if(intent.hasExtra("empresa")) {
            empresa = (Empresa) intent.getSerializableExtra("empresa");
        }

        sessionManager = new SessionManager(this);
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
       // Credenciais credenciais = new Credenciais(usuario.getEmail(), etSenha.getText().toString());

       // usuario.setCredenciais(credenciais);

        FreelaDBHandler db = new FreelaDBHandler(getApplicationContext(), null, null, 1);

        if(freelancer  != null) {
            freelancer.setSenha(etSenha.getText().toString());
            freelancer.setId(db.addUsuario(freelancer));
            freelancer.setImageResourceId(R.drawable.freelancer_icon);

            db.addFreelancer(freelancer);
            sessionManager.setUsuario(freelancer);
        } else if(empresa != null) {
            empresa.setSenha(etSenha.getText().toString());
            empresa.setId(db.addUsuario(empresa));

            db.addEmpresa(empresa);
            sessionManager.setUsuario(empresa);
        }

        Intent intent = new Intent(this, BottomNavActivity.class);

        //intent.putExtra("usuario", usuario);

        startActivity(intent);

    }

    private void voltar(){
        startActivity(new Intent(this, CriarContaPt2Activity.class));
    }

    public void exibirMensagem(String texto) {
        Toast toast = Toast.makeText(this, texto, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void redirecionarDashboard
            (Usuario usuario) {
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

            Usuario usrAdd =  usuarios[0];

            FreelaDBHandler db = new FreelaDBHandler(getApplicationContext(), null, null, 1);
            usrAdd.setId(db.addUsuario(usrAdd));

            return  usrAdd;
//            return new Usuario("gabriel@tvEmail.com", "Gabriel",  "123456");
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
