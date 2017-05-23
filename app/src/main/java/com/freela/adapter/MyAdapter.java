package com.freela.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.freela.R;
import com.freela.model.Oportunidade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Gabriel on 22/05/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Oportunidade> listOportunidades;
    private Context mContext;

    private OportunidadeOnClickListener oportunidadeOnClickListener;

    public MyAdapter(Context context, ArrayList<Oportunidade> oportunidades, OportunidadeOnClickListener oportunidadeOnClickListener) {
        this.listOportunidades = oportunidades;
        this.mContext = context;
        this.oportunidadeOnClickListener = oportunidadeOnClickListener;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_items, parent, false);
        MyAdapter.MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyAdapter.MyViewHolder holder, final int position) {
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

        if(oportunidadeOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    oportunidadeOnClickListener.onClickOportunidade(holder.itemView, position);
                }
            });
        }

        holder.ivLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              oportunidadeOnClickListener.onClickLike(holder.itemView, position);
            }
        });

        holder.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               oportunidadeOnClickListener.onClickShare(holder.itemView, position);
            }
        });



    }

    public interface  OportunidadeOnClickListener {
        public void onClickOportunidade(View view, int idx);
        public void onClickLike(View view, int idx);
        public void onClickShare(View view, int idx);
    }

    @Override
    public int getItemCount() {
        return ( listOportunidades != null ? listOportunidades.size() : 0);
    }

    private String formatarData(Date data) {
        SimpleDateFormat df = new SimpleDateFormat("EEEE, dd MMMM 'de' yyyy");
        return df.format(data);
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

//            tvTitle.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Fragment nextFragment = new OportunidadeFragment();
//                    Bundle bundle = new Bundle();
//
//                    bundle.putString("titulo", tvTitle.getText().toString());
//                    bundle.putString("descricao", tvDescricacao.getText().toString());
////                    bundle.putString("dtInicio", tvDtInicio.getText().toString());
////                    bundle.putString("dtFinal", tvDtFim.getText().toString());
//                    bundle.putInt("cover", (int) ivCover.getTag());
////                    bundle.putString("area", tvArea.getText().toString());
//                    bundle.putInt("fragment", R.layout.fragment_dashboard);
////                    bundle.putInt("like", (int) ivCover.getTag());
//                    //  bundle.putInt("share", (int) ivShare.getTag());
//
//                    nextFragment.setArguments(bundle);
//
//                    FragmentManager fragmentManager = getFragmentManager();
//
//                    final FragmentTransaction transaction = fragmentManager.beginTransaction();
//                    transaction.replace(R.id.bottom_container, nextFragment).commit();
//                }
//            });
        }
    }


//        public void filter(final String text) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    //limpa a lista a ser filtrada
//                    filterListOportunidades.clear();
//
//                    //Se não há valor procurado, então adiciona todos os itens da lista original nas lista filtrada
//                    if(TextUtils.isEmpty(text)) {
//                        filterListOportunidades.addAll(listOportunidades);
//                    } else {
//                        //interage no lista original e adiciona ela à lista filtrada
//                        for (Oportunidade oportunidade : listOportunidades) {
//                            if(oportunidade.getTitulo().toLowerCase().contains(text.toLowerCase()) || oportunidade.getDescricao().toLowerCase().contains(text.toLowerCase())) {
//                                filterListOportunidades.add(oportunidade);
//                            }
//                        }
//                    }
//
//                    // Seta na UI Thread
//                    ((Activity) mContext).runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            notifyDataSetChanged();
//                        }
//                    });
//                }
//            }).start();
//        }
}