package com.freela.fragment;


import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.freela.R;
import com.freela.SessionManager.SessionManager;
import com.freela.adapter.MyAdapterOportunidade;
import com.freela.handler.FreelaDBHandler;
import com.freela.model.Freelancer;
import com.freela.model.Oportunidade;
import com.freela.model.Usuario;

import java.util.ArrayList;


public class DashboardFreelancerFragment extends Fragment implements SearchView.OnQueryTextListener {
    private Usuario usuario;
    private Button btSair;
    private Button btAddOportuniade;
    //private SessionManager sessao;
    TextView tvNome;
    TextView tvEmail;
    TextView tvCidade;
    TextView tvEstado;
    TextView tvPais;

    private RecyclerView myRecyclerViewRecentes;
    private MyAdapterOportunidade adapter;
    private SearchView mSearchView;

    private LinearLayoutManager linearLayoutManager;
    private  String tipo;

    private FreelaDBHandler db;
    private SessionManager sessionManager;
    private Freelancer freelancer;


//    private RecyclerView myRecyclerViewDestaque;
//    private RecyclerView myRecyclerViewSugestoes;

    ArrayList<Oportunidade> oportunidadesRecentes = new ArrayList<>();
//    ArrayList<Oportunidade> oportunidadesDestaque = new ArrayList<>();
//    ArrayList<Oportunidade> oportunidadesSugestoes = new ArrayList<>();


    public DashboardFreelancerFragment() {}

    private ArrayList<Oportunidade> setOportunidades() {
//               Oportunidade op1 =  new Oportunidade(
//                "Desenvolvedor de Softwares Sênior",
//                "Desenvolver sistemas em um projeto Mobile de curta duração na cidade se São Paulo. É requerido conhecimentos na linguagem Swift.",
//                new Date(),
//                new Date(),
//                R.drawable.apple_logo_2,
//                0,
//                0,
//                new Area(0L, "TI")
//                );
//
//        oportunidadesRecentes.add(op1);
//
//        Oportunidade op2 =  new Oportunidade(
//                "Analista de Sistemas",
//                "Analise de projetos de sistemas WEB voltados ao público em geral. É reqeurido dominio sobre diagrama UML.",
//                new Date(),
//                new Date(),
//                R.drawable.google_logo,
//                0,
//                0,
//                new Area(0L, "TI")
//        );
//
//        oportunidadesRecentes.add(op2);
//
//        Oportunidade op3 =  new Oportunidade(
//                "Designer de jogos",
//                "Projetar layouts para projetos de Jogos para PC.",
//                new Date(),
//                new Date(),
//                R.drawable.microsoft_logo,
//                0,
//                0,
//                new Area(0L, "TI")
//        );
//
//        oportunidadesRecentes.add(op3);

        oportunidadesRecentes =  db.findOportunidades();

        return oportunidadesRecentes;
//        oportunidadesDestaque.addAll(freelancers);
//        oportunidadesSugestoes.addAll(oportunidadesDestaque);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        if(getArguments() != null) {
//            this.tipo = getArguments().getString("tipo");
//        }

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        sessionManager = new SessionManager(getContext());
        freelancer = (Freelancer) sessionManager.getUsuario();

        db = new FreelaDBHandler(getContext() , null, null, 1);

        oportunidadesRecentes.clear();
        setOportunidades();

        myRecyclerViewRecentes = (RecyclerView) view.findViewById(R.id.cardViewRecentes);
        linearLayoutManager =  new LinearLayoutManager(getActivity());
        myRecyclerViewRecentes.setLayoutManager(linearLayoutManager);
        myRecyclerViewRecentes.setItemAnimator(new DefaultItemAnimator());
        myRecyclerViewRecentes.setHasFixedSize(true);

        if(oportunidadesRecentes.size() > 0 && myRecyclerViewRecentes != null) {
            myRecyclerViewRecentes.setAdapter(new MyAdapterOportunidade(view.getContext(),oportunidadesRecentes, onClickOportunidade()));
        }

//        mSearchView = (SearchView) view.findViewById(R.id.search_view);

//        myRecyclerViewDestaque = (RecyclerView) view.findViewById(R.id.cardViewDestaque);
//        myRecyclerViewDestaque.setHasFixedSize(true);
//
//        myRecyclerViewSugestoes = (RecyclerView) view.findViewById(R.id.cardViewSugestao);
//        myRecyclerViewSugestoes.setHasFixedSize(true);

//        LinearLayoutManager mylLayoutManager = new LinearLayoutManager(getActivity());
//        mylLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

//        adapter =  new MyAdapterOportunidade(view.getContext(), freelancers);
//        myRecyclerViewRecentes.setAdapter(adapter);
//        setupSearchView();

//        if(freelancers.size() > 0 && myRecyclerViewRecentes != null) {
//            myRecyclerViewRecentes.setAdapter(new MyAdapterOportunidade(view.getContext(),freelancers));
//        }

//        if(oportunidadesDestaque.size() > 0 && myRecyclerViewDestaque != null) {
//            myRecyclerViewDestaque.setAdapter(new MyAdapterOportunidade(oportunidadesDestaque));
//        }
//
//        if(oportunidadesSugestoes.size() > 0 && myRecyclerViewSugestoes != null) {
//            myRecyclerViewSugestoes.setAdapter(new MyAdapterOportunidade(oportunidadesSugestoes));
//        }

//        myRecyclerViewRecentes.setLayoutManager(mylLayoutManager);
//        myRecyclerViewDestaque.setLayoutManager(mylLayoutManager);
//        myRecyclerViewSugestoes.setLayoutManager(mylLayoutManager);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.menuitem_pesquisar).getActionView();
        searchView.setQueryHint("Explorar");
        searchView.setOnQueryTextListener(this);

    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        //certo?
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Explorar freelancers");
    }

    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    public boolean onQueryTextChange(String newText) {
        ArrayList<Oportunidade> oportunidadeList = new ArrayList<>();

        for(Oportunidade oportunidade : oportunidadesRecentes) {
            if (oportunidade.getTitulo().toLowerCase().contains(newText.toLowerCase()) || oportunidade.getDescricao().toLowerCase().contains(newText.toLowerCase())) {
                oportunidadeList.add(oportunidade);
            }
        }
        myRecyclerViewRecentes.setAdapter(new MyAdapterOportunidade(getContext(), oportunidadeList, onClickOportunidade()));
        return true;
    }

    protected MyAdapterOportunidade.OportunidadeOnClickListener onClickOportunidade() {
        return new MyAdapterOportunidade.OportunidadeOnClickListener() {
           @Override
            public void onClickOportunidade(View view, int idx) {
               Oportunidade oportunidade  = oportunidadesRecentes.get(idx);

               Fragment nextFragment = new OportunidadeFragment();
               Bundle bundle = new Bundle();

               bundle.putSerializable("oportunidade", oportunidade);
               bundle.putInt("fragment", R.layout.fragment_dashboard);

               nextFragment.setArguments(bundle);

               FragmentManager fragmentManager =  getFragmentManager();

               final FragmentTransaction transaction = fragmentManager.beginTransaction();
               transaction.replace(R.id.bottom_container, nextFragment).commit();
            }

            @Override
            public void onClickLike(View view, int idx) {
                ImageView ivLike = (ImageView) view.findViewById(R.id.likeImageView);
                String tvTitle = oportunidadesRecentes.get(idx).getTitulo();

                int id = (int) ivLike.getTag();

                if(id == R.drawable.ic_like) {
                    ivLike.setTag(R.drawable.ic_liked);
                    ivLike.setImageResource(R.drawable.ic_liked);

                    db.addFreelancerOportunidade(oportunidadesRecentes.get(idx).getId(), freelancer.getId());

                    Toast.makeText(getActivity(), tvTitle + " adicionado aos favoritos", Toast.LENGTH_SHORT).show();
                } else {
                    ivLike.setTag(R.drawable.ic_like);
                    ivLike.setImageResource(R.drawable.ic_like);

                    db.deleteFreelancerOportunidade(oportunidadesRecentes.get(idx).getId(), freelancer.getId());

                    Toast.makeText(getActivity(), tvTitle + " removido dos favoritos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onClickShare(View view, int idx) {
                ImageView ivCover =  (ImageView) view.findViewById(R.id.coverImageView);

                Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                        "://" + getResources().getResourcePackageName(ivCover.getId())
                        + '/' + "drawable" + '/' + getResources().getResourceEntryName((int) ivCover.getTag()));

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                shareIntent.setType("image/jpeg");
                startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.oportunidade_compartilhar)));
            }
        };

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }






        @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        tvNome = (TextView) view.findViewById(R.id.dashboard_tv_nome);
//        tvEmail = (TextView) view.findViewById(R.id.dashboard_tv_email);
//        tvCidade = (TextView) view.findViewById(R.id.dashboard_tv_cidade);
//        tvEstado = (TextView) view.findViewById(R.id.dashboard_tv_estado);
//        tvPais = (TextView) view.findViewById(R.id.dashboard_tv_pais);
//
//        btSair = (Button) view.findViewById(R.id.dashboard_bt_sair);
//        btSair.setOnClickListener(this);
//
//
//        Bundle bundle = this.getArguments();
//
//        usuario = (Usuario) bundle.getSerializable("usuario");
//
//
//        tvNome.setText(usuario.getNome());
//        tvEmail.setText(usuario.getEmail());
//
//        if (usuario.getLocalizacao() != null) {
//            tvCidade.setText(usuario.getLocalizacao().getCidade());
//            tvEstado.setText(usuario.getLocalizacao().getEstado());
//            tvPais.setText(usuario.getLocalizacao().getPais());
//        }
    }


//        @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//
//        tvNome = (TextView) view.findViewById(R.id.dashboard_tv_nome);
//        tvEmail = (TextView) view.findViewById(R.id.dashboard_tv_email);
//        tvCidade = (TextView) view.findViewById(R.id.dashboard_tv_cidade);
//        tvEstado = (TextView) view.findViewById(R.id.dashboard_tv_estado);
//        tvPais = (TextView) view.findViewById(R.id.dashboard_tv_pais);
//
//        btSair = (Button) view.findViewById(R.id.dashboard_bt_sair);
//        btSair.setOnClickListener(this);
//
//
//        Bundle bundle = this.getArguments();
//
//        usuario = (Usuario) bundle.getSerializable("usuario");
//
//
//        tvNome.setText(usuario.getNome());
//        tvEmail.setText(usuario.getEmail());
//
//        if (usuario.getLocalizacao() != null) {
//            tvCidade.setText(usuario.getLocalizacao().getCidade());
//            tvEstado.setText(usuario.getLocalizacao().getEstado());
//            tvPais.setText(usuario.getLocalizacao().getPais());
//        }
//    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.dashboard_bt_sair:
//                sair();
//                break;
//        }
//    }
//
//    private void sair() {
//        startActivity(new Intent(getContext(), MainActivity.class));
//        //sessao.logout();
//    }


    /*
       Classe interna para operaçẽos assíncronas na base de dados.
    */
    private class Task extends AsyncTask<Void, Void, ArrayList<Oportunidade>> { //<Params, Progress, Result>

        ArrayList<Oportunidade> oportunidades;

        @Override
        protected ArrayList<Oportunidade> doInBackground(Void... voids) {
            return setOportunidades();
        }

        @Override
        protected void onPostExecute(ArrayList<Oportunidade> oportunidades) {
            super.onPostExecute(oportunidades);
            //copia a lista de carros para uso no onQueryTextChange()
            DashboardFreelancerFragment.this.oportunidadesRecentes = oportunidades;
            //atualiza a view na UIThread
            myRecyclerViewRecentes.setAdapter(new MyAdapterOportunidade(getContext(), oportunidades, onClickOportunidade())); //Context, fonte de dados, tratador do evento onClick
        }
    }//fim classe interna
}
