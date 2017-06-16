package com.freela.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.freela.R;
import com.freela.SessionManager.SessionManager;
import com.freela.activity.MainActivity;
import com.freela.model.Freelancer;
import com.freela.model.Papel;
import com.freela.model.Usuario;


public class PerfilFragment extends Fragment {
    private TextView nome;
    private TextView email;
    private TextView profissao;
    private Button sair;
    private SessionManager sessionManager;
    private Usuario usuario;

    public PerfilFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        nome = (TextView) v.findViewById(R.id.perfil_nome);
        email = (TextView) v.findViewById(R.id.perfil_email);
        profissao = (TextView) v.findViewById(R.id.perfil_profissao);
        profissao.setVisibility(View.GONE);

        sessionManager =  new SessionManager(getContext());

        usuario = (Usuario) sessionManager.getUsuario();
        nome.setText(usuario.getNome());
        email.setText(usuario.getEmail());

        if(usuario.getPapel().equals(Papel.FREELANCER)) {
            profissao.setText(((Freelancer) usuario).getProfissao());
            profissao.setVisibility(View.VISIBLE);
        }

      sair = (Button) v.findViewById(R.id.sair);


        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });

    }
}
