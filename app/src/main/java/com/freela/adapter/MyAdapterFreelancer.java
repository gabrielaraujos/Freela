package com.freela.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.freela.R;
import com.freela.model.Freelancer;

import java.util.ArrayList;

/**
 * Created by Gabriel on 22/05/2017.
 */

public class MyAdapterFreelancer extends RecyclerView.Adapter<MyAdapterFreelancer.MyViewHolder> {
    private ArrayList<Freelancer> listFreelancers;
    private Context mContext;

    private FreelancerOnClickListener freelancerOnClickListener;

    public MyAdapterFreelancer(Context context, ArrayList<Freelancer> freelancers, FreelancerOnClickListener freelancerOnClickListener) {
        this.listFreelancers = freelancers;
        this.mContext = context;
        this.freelancerOnClickListener = freelancerOnClickListener;
    }

    @Override
    public MyAdapterFreelancer.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_freelancers, parent, false);
        MyAdapterFreelancer.MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyAdapterFreelancer.MyViewHolder holder, final int position) {
        holder.tvNome.setText(listFreelancers.get(position).getNome());
        holder.tvProfissao.setText(listFreelancers.get(position).getProfissao());
        holder.tvEmail.setText(listFreelancers.get(position).getEmail());
        holder.ivCover.setImageResource(listFreelancers.get(position).getImageResourceId());
        holder.ivCover.setTag(listFreelancers.get(position).getImageResourceId());
        holder.ivLike.setImageResource(R.drawable.ic_like);
        holder.ivLike.setTag(R.drawable.ic_like);
        holder.ivShare.setImageResource(R.drawable.ic_share);
        holder.ivShare.setTag(R.drawable.ic_share);

        if(freelancerOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    freelancerOnClickListener.onClickFreelancer(holder.itemView, position);
                }
            });
        }

        holder.ivLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              freelancerOnClickListener.onClickLike(holder.itemView, position);
            }
        });

        holder.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               freelancerOnClickListener.onClickShare(holder.itemView, position);
            }
        });



    }

    public interface FreelancerOnClickListener {
        public void onClickFreelancer(View view, int idx);
        public void onClickLike(View view, int idx);
        public void onClickShare(View view, int idx);
    }

    @Override
    public int getItemCount() {
        return ( listFreelancers != null ? listFreelancers.size() : 0);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNome;
        public TextView tvProfissao;
        public TextView tvEmail;
        public ImageView ivCover;
        public ImageView ivLike;
        public ImageView ivShare;

        public MyViewHolder(View v) {
            super(v);
            tvNome = (TextView) v.findViewById(R.id.nomeTextView);
            tvProfissao = (TextView) v.findViewById(R.id.profissaoTextVew);
            tvEmail = (TextView) v.findViewById(R.id.emailTextVew);
            ivCover = (ImageView) v.findViewById(R.id.coverImageView);
            ivLike = (ImageView) v.findViewById(R.id.likeImageView);
            ivShare = (ImageView) v.findViewById(R.id.shareImageView);
        }
    }
}