package com.freela.SessionManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.freela.model.Freelancer;
import com.freela.model.Papel;
import com.freela.model.Usuario;

/**
 * Created by Gabriel on 16/06/2017.
 */

public class SessionManager {

    private SharedPreferences prefs;

    public SessionManager(Context context) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setUsuario(Usuario usuario) {
        prefs.edit().putLong("id", usuario.getId()).commit();
        prefs.edit().putString("email", usuario.getEmail()).commit();
        prefs.edit().putString("nome", usuario.getNome()).commit();
        prefs.edit().putString("papel", usuario.getPapel().name()).commit();
        prefs.edit().putString("senha", usuario.getSenha()).commit();

        if(usuario instanceof Freelancer) {
            prefs.edit().putString("profissao", ((Freelancer) usuario).getProfissao()).commit();
            prefs.edit().putInt("imageResourceId", ((Freelancer) usuario).getImageResourceId()).commit();
            prefs.edit().putInt("isFav", ((Freelancer) usuario).getIsFav()).commit();
            prefs.edit().putInt("isTurned", ((Freelancer) usuario).getIsTurned()).commit();
        }

        prefs.edit().commit();
    }


    public Object getUsuario() {
        long id = prefs.getLong("id", -1);
        String email = prefs.getString("email", "");
        String nome = prefs.getString("nome", "");
        String papel = prefs.getString("papel", "");
        String senha = prefs.getString("senha", "");

        String profissao = null;
        int imageResourceId = -1;
        int isFav = -1;
        int isTurned = -1;

        Object usuario = null;

        if(prefs.getString("profissao", "") != "") {
            profissao = prefs.getString("profissao", "");
            imageResourceId = prefs.getInt("imageResourceId", -1);
            isFav = prefs.getInt("isFav", -1);
            isTurned = prefs.getInt("isTurned", -1);

            Freelancer freelancer = new Freelancer();

            freelancer.setId(id);
            freelancer.setEmail(email);
            freelancer.setNome(nome);
            freelancer.setPapel(Papel.valueOf(papel));
            freelancer.setSenha(senha);
            freelancer.setProfissao(profissao);
            freelancer.setImageResourceId(imageResourceId);
            freelancer.setIsFav(isFav);
            freelancer.setIsTurned(isTurned);

            usuario = freelancer;
        } else {
            usuario = new Usuario(id, email, nome, Papel.valueOf(papel), senha);
        }

        return usuario;
    }
}
