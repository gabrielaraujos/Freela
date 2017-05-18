package com.freela.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.freela.R;
import com.freela.model.Oportunidade;


public class OportunidadeFragment extends Fragment  {
    private TextView tvTitle;
    private TextView tvDescricacao;
    private TextView tvDtInicio;
    private TextView tvDtFim;
    private ImageView ivCover;
    private ImageView ivLike;
    private ImageView ivShare;
    private TextView tvArea;
    private Oportunidade oportunidade;
    private ImageView voltar;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_oportunidade, container, false);
    }


        @Override
    public void onViewCreated(View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);


        tvTitle = (TextView) v.findViewById(R.id.titleTextView);
        tvDescricacao = (TextView) v.findViewById(R.id.descricaoTextVew);
        tvDtInicio = (TextView) v.findViewById(R.id.dtInicioTextVew);
        tvDtFim = (TextView) v.findViewById(R.id.dtFimTextVew);
        tvArea= (TextView) v.findViewById(R.id.areaTextVew);
        ivCover = (ImageView) v.findViewById(R.id.coverImageView);
//        ivLike = (ImageView) v.findViewById(R.id.likeImageView);
//        ivShare = (ImageView) v.findViewById(R.id.shareImageView);
            voltar = (ImageView) v.findViewById(R.id.voltar);


        Bundle bundle = this.getArguments();

        tvTitle.setText(bundle.getString("titulo"));
        tvDescricacao.setText(bundle.getString("descricao"));
        tvDtInicio.setText(bundle.getString("dtInicio"));
        tvDtFim.setText(bundle.getString("dtFim"));
        ivCover.setImageResource(bundle.getInt("cover"));
        ivCover.setTag(bundle.getInt("cover"));
//        ivLike.setImageResource(bundle.getInt("like"));
//        ivLike.setTag(bundle.getInt("like"));
//        ivShare.setImageResource(bundle.getInt("share"));
//        ivShare.setTag(bundle.getInt("share"));
        tvArea.setText(bundle.getString("area"));

            voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment nextFragment = new DashboardFragment();
//                    Bundle bundle = new Bundle();
//
//                    bundle.putString("titulo", tvTitle.getText().toString());
//                    bundle.putString("descricao", tvDescricacao.getText().toString());
//                    bundle.putString("dtInicio", tvDtInicio.getText().toString());
//                    bundle.putString("dtFinal", tvDtFim.getText().toString());
//                    bundle.putInt("cover", (int) ivCover.getTag());
//                    bundle.putString("area", tvArea.getText().toString());
//                 bundle.putInt("like", (int) ivCover.getTag());
//                     bundle.putInt("share", (int) ivShare.getTag());

//                    nextFragment.setArguments(bundle);

                    FragmentManager fragmentManager =  getFragmentManager();

                    final FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.bottom_container, nextFragment).commit();
                }
            });

    }
}
