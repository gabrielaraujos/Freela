package com.freela.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.freela.R;
import com.freela.fragment.DashboardFragment;
import com.freela.fragment.OportunidadeFragment;
import com.freela.fragment.PerfilFragment;
import com.freela.fragment.PesquisaFragment;

public class BottomNavActivity extends AppCompatActivity {
    private static final String TAG = BottomNavActivity.class.getSimpleName();
    private BottomNavigationView bottomNavigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav);

        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigation.inflateMenu(R.menu.bottom_nav_items);
        fragmentManager = getSupportFragmentManager();
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                switch (id) {
                    case R.id.menu_home:
                        fragment = new DashboardFragment();
                        break;
                    case R.id.menu_oportunidade:
                        fragment = new OportunidadeFragment();
                        break;
                    case R.id.menu_pesquisa:
                        fragment = new PesquisaFragment();
                        break;
                    case R.id.menu_perfil:
                        fragment = new PerfilFragment();
                        break;
                    default:
                        fragment = new DashboardFragment();
                        break;
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.bottom_container, fragment).commit();
                return true;
            }
        });
    }
}
