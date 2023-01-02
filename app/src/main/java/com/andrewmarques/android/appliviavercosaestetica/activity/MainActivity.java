package com.andrewmarques.android.appliviavercosaestetica.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.andrewmarques.android.appliviavercosaestetica.R;
import com.andrewmarques.android.appliviavercosaestetica.bd.FirebaseHelper;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // // implementação do slider
        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide( new FragmentSlide.Builder()
                .background(R.color.md_theme_light_background)
                .fragment(R.layout.slider1_intro)
                .build()
        );

        addSlide( new FragmentSlide.Builder()
                .background(R.color.md_theme_light_background)
                .fragment(R.layout.slider_registro)
                .canGoForward(false) // impede o usuario de passar o slide
                .build()
        );

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isCurrentUser()){
            redirecionar_menu_principal();
        }
    }

    private boolean isCurrentUser() {
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
}