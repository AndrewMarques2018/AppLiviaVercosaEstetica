package com.andrewmarques.android.appliviavercosaestetica.activity.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.andrewmarques.android.appliviavercosaestetica.R;

public class FragmentSlider1Intro extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_slider_1_intro, container, false);

        ImageView imgMarcaDagua = fragmentView.findViewById(R.id.img_marca_dagua);
        imgMarcaDagua.setImageResource(R.drawable.marca_dagua1_transp_30);

        ImageView imgLogo = fragmentView.findViewById(R.id.img_logo_slider_1);
        imgLogo.setImageResource(R.drawable.logo_estetica_branca);

        return (ViewGroup) fragmentView;
    }
}
