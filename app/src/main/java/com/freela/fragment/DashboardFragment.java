package com.freela.fragment;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.freela.R;
import com.freela.model.Area;
import com.freela.model.Oportunidade;
import com.freela.model.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DashboardFragment extends Fragment implements SearchView.OnQueryTextListener {
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
    private MyAdapter adapter;
    private SearchView mSearchView;

    private RecyclerView myRecyclerViewDestaque;
    private RecyclerView myRecyclerViewSugestoes;

    ArrayList<Oportunidade> oportunidadesRecentes = new ArrayList<>();
//    ArrayList<Oportunidade> oportunidadesDestaque = new ArrayList<>();
//    ArrayList<Oportunidade> oportunidadesSugestoes = new ArrayList<>();


    public DashboardFragment() {}

    private void setOportunidades() {
        Oportunidade op1 =  new Oportunidade(
                "Desenvolvedor de Softwares Sênior",
                "Desenvolver sistemas em um projeto Mobile de curta duração na cidade se São Paulo. É requerido conhecimentos na linguagem Swift.",
                new Date(),
                new Date(),
                R.drawable.apple_logo_2,
                0,
                0,
                new Area(0L, "TI")
                );

        oportunidadesRecentes.add(op1);

        Oportunidade op2 =  new Oportunidade(
                "Analista de Sistemas",
                "Analise de projetos de sistemas WEB voltados ao público em geral. É reqeurido dominio sobre diagrama UML.",
                new Date(),
                new Date(),
                R.drawable.google_logo,
                0,
                0,
                new Area(0L, "TI")
        );

        oportunidadesRecentes.add(op2);

        Oportunidade op3 =  new Oportunidade(
                "Designer de jogos",
                "Projetar layouts para projetos de Jogos para PC.",
                new Date(),
                new Date(),
                R.drawable.microsoft_logo,
                0,
                0,
                new Area(0L, "TI")
        );

        oportunidadesRecentes.add(op3);
//        oportunidadesDestaque.addAll(oportunidadesRecentes);
//        oportunidadesSugestoes.addAll(oportunidadesDestaque);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        oportunidadesRecentes.clear();
        setOportunidades();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        mSearchView = (SearchView) view.findViewById(R.id.search_view);

        myRecyclerViewRecentes = (RecyclerView) view.findViewById(R.id.cardViewRecentes);
        myRecyclerViewRecentes.setHasFixedSize(true);
        myRecyclerViewRecentes.setLayoutManager(new LinearLayoutManager(getContext()));


//        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//           @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.filter(newText);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//        });



//        myRecyclerViewDestaque = (RecyclerView) view.findViewById(R.id.cardViewDestaque);
//        myRecyclerViewDestaque.setHasFixedSize(true);
//
//        myRecyclerViewSugestoes = (RecyclerView) view.findViewById(R.id.cardViewSugestao);
//        myRecyclerViewSugestoes.setHasFixedSize(true);

        LinearLayoutManager mylLayoutManager = new LinearLayoutManager(getActivity());
        mylLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        adapter =  new MyAdapter(view.getContext(), oportunidadesRecentes);
        myRecyclerViewRecentes.setAdapter(adapter);
        setupSearchView();

//        if(oportunidadesRecentes.size() > 0 && myRecyclerViewRecentes != null) {
//            myRecyclerViewRecentes.setAdapter(new MyAdapter(view.getContext(),oportunidadesRecentes));
//        }

//        if(oportunidadesDestaque.size() > 0 && myRecyclerViewDestaque != null) {
//            myRecyclerViewDestaque.setAdapter(new MyAdapter(oportunidadesDestaque));
//        }
//
//        if(oportunidadesSugestoes.size() > 0 && myRecyclerViewSugestoes != null) {
//            myRecyclerViewSugestoes.setAdapter(new MyAdapter(oportunidadesSugestoes));
//        }

        myRecyclerViewRecentes.setLayoutManager(mylLayoutManager);
//        myRecyclerViewDestaque.setLayoutManager(mylLayoutManager);
//        myRecyclerViewSugestoes.setLayoutManager(mylLayoutManager);

        return view;
    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        //certo?
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Explorar oportunidades");
    }

    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ArrayList<Oportunidade> listOportunidades, filterListOportunidades;
        private Context mContext;

        public MyAdapter(Context context, ArrayList<Oportunidade> oportunidades) {
            this.listOportunidades = oportunidades;
            this.mContext = context;
            this.filterListOportunidades =  new ArrayList<Oportunidade>();
            this.filterListOportunidades.addAll(this.listOportunidades);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_items, parent, false);
            MyViewHolder holder = new MyViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tvTitle.setText(listOportunidades.get(position).getTitulo());
            holder.tvDescricacao.setText(listOportunidades.get(position).getDescricao());
//            holder.tvDtInicio.setText(formatarData(listOportunidades.get(position).getDtInicio()));
//            holder.tvDtFim.setText(formatarData(listOportunidades.get(position).getDtFim()));
            holder.ivCover.setImageResource(listOportunidades.get(position).getImageResourceId());
            holder.ivCover.setTag(listOportunidades.get(position).getImageResourceId());
            holder.ivLike.setImageResource(R.drawable.ic_like);
            holder.ivLike.setTag(R.drawable.ic_like);
            holder.ivShare.setImageResource(R.drawable.ic_share);
            holder.ivShare.setTag(R.drawable.ic_share);
//            holder.tvArea.setText(listOportunidades.get(position).getArea().getNome());
        }

        @Override
        public int getItemCount() {
            return ( listOportunidades != null ? listOportunidades.size() : 0);
        }

        private String formatarData(Date data) {
           SimpleDateFormat df = new SimpleDateFormat("EEEE, dd MMMM 'de' yyyy");
            return df.format(data);
        }

        public void filter(final String text) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //limpa a lista a ser filtrada
                    filterListOportunidades.clear();

                    //Se não há valor procurado, então adiciona todos os itens da lista original nas lista filtrada
                    if(TextUtils.isEmpty(text)) {
                        filterListOportunidades.addAll(listOportunidades);
                    } else {
                        //interage no lista original e adiciona ela à lista filtrada
                        for (Oportunidade oportunidade : listOportunidades) {
                            if(oportunidade.getTitulo().toLowerCase().contains(text.toLowerCase()) || oportunidade.getDescricao().toLowerCase().contains(text.toLowerCase())) {
                                filterListOportunidades.add(oportunidade);
                            }
                        }
                    }

                    // Seta na UI Thread
                    ((Activity) mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            notifyDataSetChanged();
                        }
                    });
                }
            }).start();
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvDescricacao;
        public TextView tvDtInicio;
        public TextView tvDtFim;
        public ImageView ivCover;
        public ImageView ivLike;
        public ImageView ivShare;
        public TextView tvArea;

        public MyViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.titleTextView);
            tvDescricacao = (TextView) v.findViewById(R.id.descricaoTextVew);
            tvDtInicio = (TextView) v.findViewById(R.id.dtInicioTextVew);
            tvDtFim = (TextView) v.findViewById(R.id.dtFimTextVew);
//            tvArea= (TextView) v.findViewById(R.id.areaTextVew);
            ivCover = (ImageView) v.findViewById(R.id.coverImageView);
            ivLike = (ImageView) v.findViewById(R.id.likeImageView);
            ivShare = (ImageView) v.findViewById(R.id.shareImageView);

            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment nextFragment = new OportunidadeFragment();
                    Bundle bundle = new Bundle();

                    bundle.putString("titulo", tvTitle.getText().toString());
                    bundle.putString("descricao", tvDescricacao.getText().toString());
//                    bundle.putString("dtInicio", tvDtInicio.getText().toString());
//                    bundle.putString("dtFinal", tvDtFim.getText().toString());
                    bundle.putInt("cover", (int) ivCover.getTag());
//                    bundle.putString("area", tvArea.getText().toString());
                    bundle.putInt("fragment", R.layout.fragment_dashboard);
//                    bundle.putInt("like", (int) ivCover.getTag());
                  //  bundle.putInt("share", (int) ivShare.getTag());

                    nextFragment.setArguments(bundle);

                    FragmentManager fragmentManager =  getFragmentManager();

                    final FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.bottom_container, nextFragment).commit();
                }
            });

            ivLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = (int) ivLike.getTag();

                    if(id == R.drawable.ic_like) {
                        ivLike.setTag(R.drawable.ic_liked);
                        ivLike.setImageResource(R.drawable.ic_liked);

                        Toast.makeText(getActivity(), tvTitle.getText() + " adicionado aos favoritos", Toast.LENGTH_SHORT).show();
                    } else {
                        ivLike.setTag(R.drawable.ic_like);
                        ivLike.setImageResource(R.drawable.ic_like);

                        Toast.makeText(getActivity(), tvTitle.getText() + " removido dos favoritos", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            ivShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                    "://" + getResources().getResourcePackageName(ivCover.getId())
                    + '/' + "drawable" + '/' + getResources().getResourceEntryName((int) ivCover.getTag()));

                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                    shareIntent.setType("image/jpeg");
                    startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.oportunidade_compartilhar)));
                }
            });
        }
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

}
