package com.andrewmarques.android.appliviavercosaestetica.activity.ui.clientes;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.andrewmarques.android.appliviavercosaestetica.R;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 */

public class ClientesFragment extends Fragment {

    private View fragmentView;
    private Button bt_ordenar_clientes;

    public ClientesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clientes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        fragmentView = view;
        bt_ordenar_clientes = view.findViewById(R.id.bt_ordenar_clientes);

        bt_ordenar_clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_INDEFINITE);
                View custom = getLayoutInflater().inflate(R.layout.snackbar_order_client, null);
                snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
                Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
                snackbarLayout.setPadding(0,0,0,0);
                snackbarLayout.addView(custom, 0);
                snackbar.show();
                */
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}