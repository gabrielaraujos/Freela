package com.freela.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Gabriel on 08/11/2016.
 */

public class Http {

    public static HttpURLConnection connectar(String urlArquivo, JSONObject jsonObject) throws IOException, Exception {
        try {

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

            prepararJsonDoRequest(conexao, jsonObject);

            conexao.connect();

            return conexao;

        } catch (Exception e) {

            throw new Exception("Erro ao conectar");

        }

    }



    public static boolean temConexao(Context ctx) {

        ConnectivityManager cm = (ConnectivityManager)
                ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = cm.getActiveNetworkInfo();

        return (info != null && info.isConnected());

    }



    public static String bytesParaString(InputStream is) throws IOException {

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

    public static void prepararJsonDoRequest(HttpURLConnection conexao, JSONObject jsonObject)  throws IOException {

        OutputStreamWriter wr = new OutputStreamWriter(conexao.getOutputStream());
        wr.write(jsonObject.toString());
        wr.flush();

    }


}
