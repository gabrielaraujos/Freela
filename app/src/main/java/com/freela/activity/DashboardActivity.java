package com.freela.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.freela.R;
import com.freela.model.Empresa;
import com.freela.model.Freelancer;
import com.freela.model.Usuario;

public class DashboardActivity extends AppCompatActivity {  private static final String SELECTED_ITEM = "arg_selected_item";

    private BottomNavigationView mBottomNav;
    private int mSelectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        mBottomNav = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });

        MenuItem selectedItem;
        if (savedInstanceState != null) {
            mSelectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0);
            selectedItem = mBottomNav.getMenu().findItem(mSelectedItem);
        } else {
            selectedItem = mBottomNav.getMenu().getItem(0);
        }
        selectFragment(selectedItem);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM, mSelectedItem);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        MenuItem homeItem = mBottomNav.getMenu().getItem(0);
        if (mSelectedItem != homeItem.getItemId()) {
            // select home item
            selectFragment(homeItem);
        } else {
            super.onBackPressed();
        }
    }

    private void selectFragment(MenuItem item) {
        Fragment frag = null;
        // init corresponding fragment
        switch (item.getItemId()) {
            case R.id.menu_home:
                frag = MenuFragment.newInstance(getString(R.string.text_home),
                        getColorFromRes(R.color.color_home));
                break;
            case R.id.menu_notifications:
                frag = MenuFragment.newInstance(getString(R.string.text_notifications),
                        getColorFromRes(R.color.color_notifications));
                break;
            case R.id.menu_search:
                frag = MenuFragment.newInstance(getString(R.string.text_search),
                        getColorFromRes(R.color.color_search));
                break;
        }

        // update selected item
        mSelectedItem = item.getItemId();

        // uncheck the other items.
        for (int i = 0; i< mBottomNav.getMenu().size(); i++) {
            MenuItem menuItem = mBottomNav.getMenu().getItem(i);
            menuItem.setChecked(menuItem.getItemId() == item.getItemId());
        }

        updateToolbarText(item.getTitle());

        if (frag != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.container, frag, frag.getTag());
            ft.commit();
        }
    }

    private void updateToolbarText(CharSequence text) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(text);
        }
    }

    private int getColorFromRes(@ColorRes int resId) {
        return ContextCompat.getColor(this, resId);
    }

//    private Usuario usuario;
//    private Button btSair;
//    private Button btAddOportuniade;
//    //private SessionManager sessao;
//    TextView tvNome;
//    TextView tvEmail;
//    TextView tvCidade;
//    TextView tvEstado;
//    TextView tvPais;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.dashboard);
//
//        tvNome = (TextView) findViewById(R.id.dashboard_tv_nome);
//        tvEmail = (TextView) findViewById(R.id.dashboard_tv_email);
//        tvCidade = (TextView) findViewById(R.id.dashboard_tv_cidade);
//        tvEstado = (TextView) findViewById(R.id.dashboard_tv_estado);
//        tvPais = (TextView) findViewById(R.id.dashboard_tv_pais);
//
//        btSair = (Button) findViewById(R.id.dashboard_bt_sair);
//        btSair.setOnClickListener(this);
//
//        btAddOportuniade = (Button) findViewById(R.id.dashboard_bt_add_oportunidade);
//        btAddOportuniade.setOnClickListener(this);
//
//       /* sessao =  new SessionManager(getApplicationContext());
//
//        sessao.checkLogin();
//
//        if(((Usuario) sessao.getUsuarioLogado()).getPapel().equals(Papel.FREELANCER)) {
//
//            Freelancer freelancer = (Freelancer) sessao.getUsuarioLogado();
//
//            init(freelancer);
//
//        } else {
//
//            Empresa empresa = (Empresa) sessao.getUsuarioLogado();
//
//            init(empresa);
//
//        }*/
//
//        Intent intent = getIntent();
//
//        if (intent.hasExtra("usuario")) {
//            usuario = (Usuario) intent.getSerializableExtra("usuario");
//
//            tvNome.setText(usuario.getNome());
//            tvEmail.setText(usuario.getEmail());
//
//            if (usuario.getLocalizacao() != null) {
//                tvCidade.setText(usuario.getLocalizacao().getCidade());
//                tvEstado.setText(usuario.getLocalizacao().getEstado());
//                tvPais.setText(usuario.getLocalizacao().getPais());
//            }
//        } else {
//            Toast toast = Toast.makeText(this, "Erro!!!", Toast.LENGTH_SHORT);
//            toast.show();
//        }
//    }
//
//    private void init(Freelancer freelancer) {
//    }
//
//    private void init(Empresa empresa) {
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.dashboard_bt_sair:
//                sair();
//                break;
//
//            case R.id.dashboard_bt_add_oportunidade:
//                addOportundidade();
//                break;
//        }
//    }
//
//    private void sair() {
//        startActivity(new Intent(this, MainActivity.class));
//        //sessao.logout();
//    }
//
//    private void addOportundidade() {
//        startActivity(new Intent(this, CriarOportunidadeActivity.class));
//    }
}