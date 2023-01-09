package com.andrewmarques.android.appliviavercosaestetica.activity.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andrewmarques.android.appliviavercosaestetica.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FragmentClientesBottomSheetAdd extends BottomSheetDialogFragment {

    public FragmentClientesBottomSheetAdd() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_bottom_sheet_add_clientes, container, false);
        return fragmentView;
    }
}