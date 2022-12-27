package com.andrewmarques.android.appliviavercosaestetica;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
                .background(android.R.color.white)
                .fragment(R.layout.slider1_intro)
                .build()
        );

        addSlide( new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.slider_registro)
                .canGoForward(false) // impede o usuario de passar o slide
                .build()
        );
    }

    public void redirecionar_cadastro_on_clicker (View view ) {
        startActivity(new Intent(this, Cadastro.class));
    }

    public void redirecionar_login_on_clicker ( View view ) {
        startActivity(new Intent(this, Login.class));
    }
}