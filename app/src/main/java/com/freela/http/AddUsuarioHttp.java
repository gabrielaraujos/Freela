package com.freela.http;

import com.freela.model.Papel;
import com.freela.model.Empresa;
import com.freela.model.Freelancer;
import com.freela.model.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * Created by Gabriel on 08/11/2016.
 */

public class AddUsuarioHttp extends Http {

    public static String URL_API_ADD = "http://10.0.2.2:8080/fws/api/add";


    public static Usuario addUsuario(Usuario usuario) {

        try {

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("email", usuario.getEmail());
            jsonObject.put("nome", usuario.getNome());
            jsonObject.put("papel", usuario.getPapel());
            jsonObject.put("credenciais", usuario.getCredenciais());

            HttpURLConnection conexao = connectar(URL_API_ADD, jsonObject);

            int resposta = conexao.getResponseCode();

            if (resposta == HttpURLConnection.HTTP_OK) {

                InputStream is = conexao.getInputStream();

                JSONObject json = new JSONObject(bytesParaString(is));

                return lerJsonUsuario(json);

            }
        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

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
