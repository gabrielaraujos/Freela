package com.freela.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.freela.model.Credenciais;
import com.freela.model.Localizacao;
import com.freela.model.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Gabriel on 27/10/2016.
 */

public class LoginHttp {
    public static final String URL_API_LOGIN = "http://10.0.2.2:8080/fws/api/login";

    private static HttpURLConnection connectar(String urlArquivo, Credenciais credenciais) throws IOException {

        final int SEGUNDOS = 1000;

        URL url = new URL(urlArquivo);

        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

        conexao.setReadTimeout(10 * SEGUNDOS);
        conexao.setConnectTimeout(15 * SEGUNDOS);
        conexao.setRequestMethod("POST");
        conexao.setDoInput(true);
        conexao.setDoOutput(true);
        conexao.setRequestProperty("Content-Type", "application/json");
        conexao.setRequestProperty("Accept", "application/json");

        prepararJsonDoRequest(conexao, credenciais);

        conexao.connect();

        return conexao;
    }

    public static void prepararJsonDoRequest(HttpURLConnection conexao, Credenciais credenciais)  throws IOException {

        JSONObject usuarioJson  = new JSONObject();

        try {

            usuarioJson.put("login", credenciais.getLogin());
            usuarioJson.put("senha", credenciais.getSenha());

        } catch (JSONException eJSON) {

            Log.i("LoginHttp", "Deu erro na montagem do JSON");

        }

        OutputStreamWriter wr = new OutputStreamWriter(conexao.getOutputStream());
        wr.write(usuarioJson.toString());
        wr.flush();

    }

    public static boolean temConexao(Context ctx) {

        ConnectivityManager cm = (ConnectivityManager)
                ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = cm.getActiveNetworkInfo();

        return (info != null && info.isConnected());
    }

    public static Usuario carregarUsuarioJson(Credenciais credenciais) {

        try {

            HttpURLConnection conexao = connectar(URL_API_LOGIN, credenciais);

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

        Usuario usuario = new Usuario(
                jsonObject.getString("email"),
                jsonObject.getString("nome"),
                (Localizacao) jsonObject.get("localizacao")
        );

        return usuario;

    }

    private static String bytesParaString(InputStream is) throws IOException {

        byte[] buffer = new byte[1024];

        // O bufferzao vai armazenar todos os bytes lidos
        ByteArrayOutputStream bufferzao = new ByteArrayOutputStream();

        // precisamos saber quantos bytes foram lidos
        int bytesLidos;

        // Vamos lendo de 1KB por vez...
        while ((bytesLidos = is.read(buffer)) != -1) {

            // copiando a quantidade de bytes lidos do buffer para o bufferzão
            bufferzao.write(buffer, 0, bytesLidos);

        }

        return new String(bufferzao.toByteArray(), "UTF-8");

    }

}
