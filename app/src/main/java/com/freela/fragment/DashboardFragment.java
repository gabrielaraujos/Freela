package com.freela.fragment;


import android.content.ContentResolver;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.freela.R;
import com.freela.activity.MainActivity;
import com.freela.model.Area;
import com.freela.model.Oportunidade;
import com.freela.model.Usuario;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;


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

    ArrayList<Oportunidade> listitems = new ArrayList<>();
    RecyclerView myRecyclerView;
    ArrayList<Oportunidade> Oportunidades = new ArrayList<>();

    public DashboardFragment() {}

    private void setOportunidades() {
        Oportunidade op1 =  new Oportunidade(
                "Desenvolvedor de Softwares Senior",
                "Desenvolver sistemas Mobile bra bra bra bra bra bra bra bra...",
                new Date(),
                new Date(),
                R.drawable.apple_logo,
                0,
                0,
                new Area(0L, "TI")
                );

        Oportunidades.add(op1);

        Oportunidade op2 =  new Oportunidade(
                "Analista de Software",
                "Analise de sistemas WEB bra bra bra bra bra bra bra bra...",
                new Date(),
                new Date(),
                R.drawable.google_logo,
                0,
                0,
                new Area(0L, "TI")
        );

        Oportunidades.add(op2);

        Oportunidade op3 =  new Oportunidade(
                "Designer de jogos",
                "Projetar layouts para Jogos  bra bra bra bra bra bra bra bra...",
                new Date(),
                new Date(),
                R.drawable.microsoft_logo,
                0,
                0,
                new Area(0L, "TI")
        );

        Oportunidades.add(op3);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listitems.clear();
        setOportunidades();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        myRecyclerView = (RecyclerView) view.findViewById(R.id.cardView);
        myRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mylLayoutManager = new LinearLayoutManager(getActivity());
        mylLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        if(listitems.size() > 0 && myRecyclerView != null) {
            myRecyclerView.setAdapter(new MyAdapter(listitems));
        }

        myRecyclerView.setLayoutManager(mylLayoutManager);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ArrayList<Oportunidade> listOportunidades;

        public MyAdapter(ArrayList<Oportunidade> oportunidades) {
            listOportunidades = oportunidades;
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
            holder.ivCover.setImageResource(listOportunidades.get(position).getImageResourceId());
            holder.ivCover.setTag(listOportunidades.get(position).getImageResourceId());
            holder.ivLike.setTag(R.drawable.ic_like);
        }

        @Override
        public int getItemCount() {
            return listOportunidades.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public ImageView ivCover;
        public ImageView ivLike;
        public ImageView ivShare;

        public MyViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.titleTextView);
            ivCover = (ImageView) v.findViewById(R.id.coverImageView);
            ivLike = (ImageView) v.findViewById(R.id.likeImageView);
            ivShare = (ImageView) v.findViewById(R.id.shareImageView);

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
