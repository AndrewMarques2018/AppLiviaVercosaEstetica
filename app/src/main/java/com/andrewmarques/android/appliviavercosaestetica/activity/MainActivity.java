package com.andrewmarques.android.appliviavercosaestetica.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.andrewmarques.android.appliviavercosaestetica.activity.fragments.FragmentSlider1Intro;
import com.andrewmarques.android.appliviavercosaestetica.activity.fragments.FragmentSliderRegistro;
import com.andrewmarques.android.appliviavercosaestetica.bd.FirebaseHelper;
import com.andrewmarques.android.appliviavercosaestetica.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class MainActivity extends FragmentActivity {

    ActivityMainBinding binding;
    private GoogleSignInClient googleSignInClient;
    private GoogleSignInOptions googleSignInOptions;
    private ViewPager2 viewPagerSliderIntro;
    private FragmentStateAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewPagerSliderIntro = binding.viewPager2;
        pagerAdapter = new ScreenSliderPageAdapter(this);
        viewPagerSliderIntro.setAdapter(pagerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (isCurrentUser()){
            redirecionar_menu_principal();
        }
    }

    private boolean isCurrentUser() {
        googleSignInOptions = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("224325117326-mlllafvbt7pqhksm63od2t2rmqc4hndg.apps.googleusercontent.com")
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if(account != null) {
            return true;
        }
        return FirebaseHelper.getInstance().getCurrentUser() != null;
    }

    public void redirecionar_cadastro_on_clicker (View view ) {
        startActivity(new Intent(this, Cadastro.class));
    }

    public void redirecionar_login_on_clicker ( View view ) {
        startActivity(new Intent(this, Login.class));
    }

    public void redirecionar_menu_principal () {
        //startActivity(new Intent(this, Design.class));
        startActivity(new Intent(this, Drawer.class));
    }

    private class ScreenSliderPageAdapter extends FragmentStateAdapter {

        public ScreenSliderPageAdapter(MainActivity mainActivity) {
            super(mainActivity);
        }

        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0:
                    return new FragmentSlider1Intro();
                case 1:
                    return new FragmentSliderRegistro();
                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}