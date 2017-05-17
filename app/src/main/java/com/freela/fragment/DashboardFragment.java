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
import com.freela.activity.MainActivity;
import com.freela.model.Usuario;


public class DashboardFragment extends Fragment implements View.OnClickListener {
    private Usuario usuario;
    private Button btSair;
    private Button btAddOportuniade;
    //private SessionManager sessao;
    TextView tvNome;
    TextView tvEmail;
    TextView tvCidade;
    TextView tvEstado;
    TextView tvPais;

    public DashboardFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        tvNome = (TextView) view.findViewById(R.id.dashboard_tv_nome);
        tvEmail = (TextView) view.findViewById(R.id.dashboard_tv_email);
        tvCidade = (TextView) view.findViewById(R.id.dashboard_tv_cidade);
        tvEstado = (TextView) view.findViewById(R.id.dashboard_tv_estado);
        tvPais = (TextView) view.findViewById(R.id.dashboard_tv_pais);

        btSair = (Button) view.findViewById(R.id.dashboard_bt_sair);
        btSair.setOnClickListener(this);


        Bundle bundle = this.getArguments();

        usuario = (Usuario) bundle.getSerializable("usuario");


        tvNome.setText(usuario.getNome());
        tvEmail.setText(usuario.getEmail());

        if (usuario.getLocalizacao() != null) {
            tvCidade.setText(usuario.getLocalizacao().getCidade());
            tvEstado.setText(usuario.getLocalizacao().getEstado());
            tvPais.setText(usuario.getLocalizacao().getPais());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dashboard_bt_sair:
                sair();
                break;
        }
    }

    private void sair() {
        startActivity(new Intent(getContext(), MainActivity.class));
        //sessao.logout();
    }
}
