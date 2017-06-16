package com.freela.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import com.freela.R;
import com.freela.SessionManager.SessionManager;
import com.freela.fragment.DashboardEmpresaFragment;
import com.freela.fragment.DashboardFreelancerFragment;
import com.freela.fragment.FavoritosFragment;
import com.freela.fragment.PerfilFragment;
import com.freela.model.Papel;
import com.freela.model.Usuario;

public class BottomNavActivity extends AppCompatActivity {
    private static final String TAG = BottomNavActivity.class.getSimpleName();
    private BottomNavigationView bottomNavigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private Intent intent;
    private SessionManager sessionManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav);

        this.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //ToolBar
        //mapeia a ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //insere a ToolBar como ActionBar (o estilo deve estar como .NoActionBar, veja o arquivo values/styles).
        setSupportActionBar(toolbar);

        sessionManager= new SessionManager(this);

     //  intent = getIntent();

        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigation.inflateMenu(R.menu.bottom_nav_items);
        fragmentManager = getSupportFragmentManager();

        selectItem(R.id.menu_home);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                selectItem(id);

                return true;
            }
        });


    }

    public void selectItem(int id) {
        switch (id) {
            case R.id.menu_home:
//                if (intent.hasExtra("usuario")) {
//                    Usuario usuario = (Usuario) intent.getSerializableExtra("usuario");
                    Usuario usuario =  (Usuario) sessionManager.getUsuario();

                    if(usuario.getPapel().equals(Papel.FREELANCER)) {
                        fragment = new DashboardFreelancerFragment();
                    } else {
                        fragment = new DashboardEmpresaFragment();
                    }

//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("usuario", usuario);
//                    fragment.setArguments(bundle);
//                }
                break;
            case R.id.menu_oportunidade:
                fragment = new FavoritosFragment();
                break;
//                    case R.id.menu_pesquisa:
//                        fragment = new PesquisaFragment();
//                        break;
            case R.id.menu_perfil:
                fragment = new PerfilFragment();
                break;
        }
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.bottom_container, fragment).commit();
    }
}
