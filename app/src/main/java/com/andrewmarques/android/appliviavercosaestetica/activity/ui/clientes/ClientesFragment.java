package com.andrewmarques.android.appliviavercosaestetica.activity.ui.clientes;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.andrewmarques.android.appliviavercosaestetica.R;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 */

public class ClientesFragment extends Fragment {

    private View fragmentView;

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
        super.onViewCreated(view, savedInstanceState);

        fragmentView = view;
        String[] order_clientes_by = getResources().getStringArray(R.array.order_clientes_by);
        ArrayAdapter arrayAdapter = new ArrayAdapter(fragmentView.getContext(), R.layout.dropdown_item, order_clientes_by);
        AutoCompleteTextView autoCompleteTextView = fragmentView.findViewById(R.id.autoCompleteTextView_orderClientes);
        autoCompleteTextView.setAdapter(arrayAdapter);
    }

}