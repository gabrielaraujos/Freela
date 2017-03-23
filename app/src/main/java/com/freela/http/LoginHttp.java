package com.freela.http;

import com.freela.Papel;
import com.freela.model.Credenciais;
import com.freela.model.Empresa;
import com.freela.model.Freelancer;
import com.freela.model.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by Gabriel on 27/10/2016.
 */

public class LoginHttp extends Http {
    public static final String URL_API_LOGIN = "http://10.0.2.2:8081/fws/api/login";

    public static Usuario carregarUsuarioJson(Credenciais credenciais) {

        try {
            Usuario usuario = null;

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("login", credenciais.getLogin());
            jsonObject.put("senha", credenciais.getSenha());

            HttpURLConnection conexao = connectar(URL_API_LOGIN, jsonObject);

            int resposta = conexao.getResponseCode();

            if (resposta == HttpURLConnection.HTTP_OK) {

                InputStream is = conexao.getInputStream();

                JSONObject json = new JSONObject(bytesParaString(is));

                usuario = lerJsonUsuario(json);

            }

            return usuario;
        } catch (Exception e) {

            e.printStackTrace();

            return null;

        }

    }

    public static Usuario lerJsonUsuario(JSONObject jsonObject) throws  JSONException {

        //TODO: diferenciar Freelancer e Empresa

        Usuario usuario =  (Usuario) jsonObject.get("usuario");


        if(usuario.getPapel().equals(Papel.FREELANCER))   {

            usuario = (Freelancer) jsonObject.get("usuario");

        } else if(usuario.getPapel().equals(Papel.EMPRESA)) {

            usuario =  (Empresa) jsonObject.get("usuario");

        } else {

            usuario  = null;

        }

        return usuario;

    }

}
