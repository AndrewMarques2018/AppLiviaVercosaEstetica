package com.andrewmarques.android.appliviavercosaestetica.activity.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.andrewmarques.android.appliviavercosaestetica.R;

public class FragmentSliderRegistro extends Fragment {

    public FragmentSliderRegistro () {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_slider_registro, container, false);

        ImageView imgLogo = fragmentView.findViewById(R.id.img_logo_slider_registro);
        imgLogo.setImageResource(R.drawable.logo_estetica_branca);

        Button bt = fragmentView.findViewById(R.id.bt_cadastrese);
        bt.setBackground(getResources().getDrawable(R.drawable.roundedbutton));

        return (ViewGroup) fragmentView;
    }
}
