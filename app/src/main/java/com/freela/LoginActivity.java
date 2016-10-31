package com.freela;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.freela.http.LoginHttp;
import com.freela.model.Credenciais;
import com.freela.model.Usuario;

import static com.freela.R.layout.login;


/**
 * Created by Mateus - PC on 2016-10-25.
 */
public class LoginActivity extends Activity implements  View.OnClickListener{
    private EditText email;
    private EditText senha;
    private Button btnEntrar;
    private LoginTask loginTask;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(login);

        email = (EditText) findViewById(R.id.email);
        senha = (EditText) findViewById(R.id.senha);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Credenciais credenciais =  new Credenciais(email.getText().toString(), senha.getText().toString());

        if(loginTask == null) {

            if(LoginHttp.temConexao(this)) {

                loginTask = new LoginTask();
                loginTask.execute(credenciais);

            } else {

                exibirMensagem(getString(R.string.erro_conexao));

            }

        } else if(loginTask.getStatus() == AsyncTask.Status.RUNNING) {

            //TODO: mostrar progress

        }

    }


    public void redirecionarDashboard(Usuario usuario){

        //TODO: passar parametros usuário

        startActivity(new Intent(this, DashboardActivity.class));

    }

    public void exibirMensagem(String texto) {

        Toast toast = Toast.makeText(this, texto, Toast.LENGTH_SHORT);

        toast.show();

    }

    class LoginTask extends AsyncTask<Credenciais, Void, Usuario> {
        @Override
        protected void onPreExecute() {

            //TODO: exibir o progress

        }

        @Override
        protected Usuario doInBackground(Credenciais... credenciais) {

            return LoginHttp.carregarUsuarioJson(credenciais[0]);

        }

        @Override
        protected void onPostExecute(Usuario usuario) {

            //TODO: esconder progress

            if(usuario == null) {

                exibirMensagem(getString(R.string.erro_autenticacao));

            } else {

                redirecionarDashboard(usuario);

            }

        }
    }
}
