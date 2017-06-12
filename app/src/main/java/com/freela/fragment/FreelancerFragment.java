package com.freela.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.freela.R;
import com.freela.model.Freelancer;


public class FreelancerFragment extends Fragment  {
    private TextView tvNome;
    private TextView tvProfissao;
    private TextView tvEmail;
    private ImageView ivCover;
    private ImageView ivLike;
    private ImageView ivShare;
    private Freelancer freelancer;
    private ImageView voltar;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_oportunidade, container, false);
    }


        @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);


        tvNome = (TextView) v.findViewById(R.id.nomeTextView);
        tvProfissao = (TextView) v.findViewById(R.id.profissaoTextVew);
        tvEmail = (TextView) v.findViewById(R.id.emailTextVew);
        ivCover = (ImageView) v.findViewById(R.id.coverImageView);
//        ivLike = (ImageView) v.findViewById(R.id.likeImageView);
//        ivShare = (ImageView) v.findViewById(R.id.shareImageView);
            voltar = (ImageView) v.findViewById(R.id.voltar);


        final Bundle bundle = this.getArguments();
        Freelancer freelancer = (Freelancer) bundle.getSerializable("freelancer");

        tvNome.setText(freelancer.getNome());
        tvProfissao.setText(freelancer.getProfissao());
        tvEmail.setText(freelancer.getEmail());
        ivCover.setImageResource(freelancer.getImageResourceId());
        ivCover.setTag(freelancer.getImageResourceId());
//        ivLike.setImageResource(bundle.getInt("like"));
//        ivLike.setTag(bundle.getInt("like"));
//        ivShare.setImageResource(bundle.getInt("share"));
//        ivShare.setTag(bundle.getInt("share"));

            voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment nextFragment = null;

                    int id = bundle.getInt("fragment");

                    switch (id) {
                        case  R.layout.fragment_dashboard:
                            nextFragment = new DashboardFreelancerFragment();
                            break;
                        case R.layout.fragment_favoritos:
                            nextFragment = new FavoritosFragment();
                            break;
                    }

                    FragmentManager fragmentManager =  getFragmentManager();

                    final FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.bottom_container, nextFragment).commit();
                }
            });

    }
}
