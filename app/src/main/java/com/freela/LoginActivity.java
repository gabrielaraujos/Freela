package com.freela;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.freela.model.Localizacao;
import com.freela.model.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mateus - PC on 2016-10-25.
 */
public class  LoginActivity extends Activity {
    private EditText email;
    private EditText senha;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email = (EditText) findViewById(R.id.email);
        senha = (EditText) findViewById(R.id.senha);
    }

    public void entrarOnClick(View view){
        String emailInformado = email.getText().toString();
        String senhaInformada = senha.getText().toString();

        if("freela".equals(emailInformado) &&
                "123".equals(senhaInformada)){
            startActivity(new Intent(this, DashboardActivity.class));
        }else{
            String mensagemErro = getString(R.string.erro_autenticacao);
            Toast toast = Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}

private class LoginTask extends AsyncTask<Usuario, Void, Usuario> {
    private final static String URL = "http://10.0.2.2/fws/api/login";
    ProgressDialog dialog;

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(LoginActivity.this);
        dialog.show();
    }

    @Override
    protected Usuario doInBackground(Usuario usuario) throws JSONException {
       try {
           Map<String, String> credenciais = new HashMap<>();

           credenciais.put("email", usuario.getEmail());
           credenciais.put("senha", usuario.getSenha());

           String json = HttpRequest.post(URL).form(credenciais).body();

           JSONObject retorno = new JSONObject(json);


           usuario = new Usuario(
            (String)  retorno.get("email"),
            (String)  retorno.get("senha"),
            (String) retorno.get("nome"),
            (Localizacao) retorno.get("localizacao")
           );

           return usuario;
       } catch (Exception e) {
           return null;
       }
    }

    @Override
    protected void onPostExecute(Usuario usuario) {
        dialog.dismiss();
    }
}
