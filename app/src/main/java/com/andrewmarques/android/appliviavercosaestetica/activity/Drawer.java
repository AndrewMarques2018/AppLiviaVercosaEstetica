package com.andrewmarques.android.appliviavercosaestetica.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.andrewmarques.android.appliviavercosaestetica.R;
import com.andrewmarques.android.appliviavercosaestetica.bd.FirebaseHelper;
import com.google.android.material.navigation.NavigationView;

public class Drawer extends AppCompatActivity {

    //private ActivityMenuPrincipalBinding binding;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding = ActivityMenuPrincipalBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
        setContentView(R.layout.activity_drawer);

        //setSupportActionBar(binding.appBarMenuPrincipal.toolbar);
        setSupportActionBar(findViewById(R.id.toolbar));

        //DrawerLayout drawer = binding.drawerLayout;
        //NavigationView navigationView = binding.navView;
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_clientes)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_principal);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu_principal);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_geral, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_sair:
                FirebaseHelper.singnOut();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}