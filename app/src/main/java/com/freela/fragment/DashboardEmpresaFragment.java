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
import com.freela.adapter.MyAdapterFreelancer;
import com.freela.handler.FreelaDBHandler;
import com.freela.model.Freelancer;
import com.freela.model.Usuario;

import java.util.ArrayList;


public class DashboardEmpresaFragment extends Fragment implements SearchView.OnQueryTextListener {
    private Usuario usuario;
    private Button btSair;
    private Button btAddOportuniade;
    //private SessionManager sessao;
    TextView tvNome;
    TextView tvEmail;
    TextView tvCidade;
    TextView tvEstado;
    TextView tvPais;

    private RecyclerView myRecyclerView;
    private MyAdapterFreelancer adapter;
    private SearchView mSearchView;
    private SessionManager sessionManager;
    private FreelaDBHandler db;

    private LinearLayoutManager linearLayoutManager;
    ArrayList<Freelancer> freelancers = new ArrayList<>();



    public DashboardEmpresaFragment() {}

    private ArrayList<Freelancer> setFreelancers() {
//        Freelancer fr1 =  new Freelancer(0,
//                "adalovelace@email.com",
//                "Ada Lovelance",
//                "lovelance123",
//                "Desenvolvedora",
//                R.drawable.lovelance,
//                0,
//                0
//        );
//
//        freelancers.add(fr1);
//
//        Freelancer fr2 =  new Freelancer(0,
//                "adalovelace@email.com",
//                "Ada Lovelance teste",
//                "lovelance123",
//                "Desenvolvedora",
//                R.drawable.lovelance,
//                0,
//                0
//        );
//
//        freelancers.add(fr2);
//
//        Freelancer fr3 =  new Freelancer(0,
//                "adalovelace@email.com",
//                "Ada Lovelance",
//                "lovelance123",
//                "Desenvolvedora",
//                R.drawable.lovelance,
//                0,
//                0
//        );
//
//        freelancers.add(fr3);

        freelancers = db.findFreelancers();
        return freelancers;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        db = new FreelaDBHandler(getContext(), null, null, 1);
        freelancers.clear();
        setFreelancers();

        myRecyclerView = (RecyclerView) view.findViewById(R.id.cardViewRecentes);
        linearLayoutManager =  new LinearLayoutManager(getActivity());
        myRecyclerView.setLayoutManager(linearLayoutManager);
        myRecyclerView.setItemAnimator(new DefaultItemAnimator());
        myRecyclerView.setHasFixedSize(true);

        if(freelancers.size() > 0 && myRecyclerView != null) {
            myRecyclerView.setAdapter(new MyAdapterFreelancer(view.getContext(), freelancers, onClickFreelancer()));
        }

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
        ArrayList<Freelancer> freelancerList = new ArrayList<>();

        for(Freelancer freelancer : freelancers) {
            if (freelancer.getNome().toLowerCase().contains(newText.toLowerCase()) || freelancer.getProfissao().toLowerCase().contains(newText.toLowerCase())) {
                freelancerList.add(freelancer);
            }
        }
        myRecyclerView.setAdapter(new MyAdapterFreelancer(getContext(), freelancerList, onClickFreelancer()));
        return true;
    }

    protected MyAdapterFreelancer.FreelancerOnClickListener onClickFreelancer() {
        return new MyAdapterFreelancer.FreelancerOnClickListener() {
           @Override
            public void onClickFreelancer(View view, int idx) {
               Freelancer freelancer  = freelancers.get(idx);

               Fragment nextFragment = new FreelancerFragment();
               Bundle bundle = new Bundle();

               bundle.putSerializable("freelancer", freelancer);
               bundle.putInt("fragment", R.layout.fragment_dashboard);

               nextFragment.setArguments(bundle);

               FragmentManager fragmentManager =  getFragmentManager();

               final FragmentTransaction transaction = fragmentManager.beginTransaction();
               transaction.replace(R.id.bottom_container, nextFragment).commit();
            }

            @Override
            public void onClickLike(View view, int idx) {
                ImageView ivLike = (ImageView) view.findViewById(R.id.likeImageView);
                String tvNome = freelancers.get(idx).getNome();

                int id = (int) ivLike.getTag();

                if(id == R.drawable.ic_like) {
                    ivLike.setTag(R.drawable.ic_liked);
                    ivLike.setImageResource(R.drawable.ic_liked);

                    Toast.makeText(getActivity(), tvNome + " adicionado aos favoritos", Toast.LENGTH_SHORT).show();
                } else {
                    ivLike.setTag(R.drawable.ic_like);
                    ivLike.setImageResource(R.drawable.ic_like);

                    Toast.makeText(getActivity(), tvNome + " removido dos favoritos", Toast.LENGTH_SHORT).show();
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
    }

    /*
       Classe interna para operaçẽos assíncronas na base de dados.
    */
    private class Task extends AsyncTask<Void, Void, ArrayList<Freelancer>> { //<Params, Progress, Result>

        ArrayList<Freelancer> freelancers;

        @Override
        protected ArrayList<Freelancer> doInBackground(Void... voids) {
            return setFreelancers();
        }

        @Override
        protected void onPostExecute(ArrayList<Freelancer> freelancers) {
            super.onPostExecute(freelancers);
            //copia a lista de carros para uso no onQueryTextChange()
            DashboardEmpresaFragment.this.freelancers = freelancers;
            //atualiza a view na UIThread
            myRecyclerView.setAdapter(new MyAdapterFreelancer(getContext(), freelancers, onClickFreelancer())); //Context, fonte de dados, tratador do evento onClick
        }
    }//fim classe interna
}
