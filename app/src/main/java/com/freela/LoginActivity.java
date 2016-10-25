package com.freela;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Mateus - PC on 2016-10-25.
 */
public class LoginActivity extends Activity {
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
