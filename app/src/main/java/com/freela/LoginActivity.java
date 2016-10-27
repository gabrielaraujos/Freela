package com.freela;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.freela.http.LoginHttp;
import com.freela.model.Credenciais;
import com.freela.model.Usuario;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.freela.R.layout.login;


/**
 * Created by Mateus - PC on 2016-10-25.
 */
public class LoginActivity extends Activity {
    private EditText email;
    private EditText senha;
    private LoginTask loginTask;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(login);

        email = (EditText) findViewById(R.id.email);
        senha = (EditText) findViewById(R.id.senha);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void entrarOnClick(View view) {

        Credenciais credenciais =  new Credenciais(email.getText().toString(), senha.getText().toString());

        if(loginTask == null) {

            if(LoginHttp.temConexao(this)) {

                loginTask = new LoginTask();
                loginTask.execute(credenciais);

            } else {

                //TODO: exibir mensagem de sem conexão

            }

        } else if(loginTask.getStatus() == AsyncTask.Status.RUNNING) {

            //TODO: mostrar progress

        }
        /*if ("freela".equals(emailInformado) &&
                "123".equals(senhaInformada)) {
            startActivity(new Intent(this, DashboardActivity.class));
        } else {
            String mensagemErro = getString(R.string.erro_autenticacao);
            Toast toast = Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT);
            toast.show();
        }*/


    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Login Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public void redirecionarDashboard(Usuario usuario){

        startActivity(new Intent(this, DashboardActivity.class));

    }

    public void exibirMensagemErroAutenticacao() {
        String mensagemErro = getString(R.string.erro_autenticacao);
        Toast toast = Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT);
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

                exibirMensagemErroAutenticacao();

            } else {

                redirecionarDashboard(usuario);

            }

        }
    }
}
