package com.andrewmarques.android.appliviavercosaestetica.activity.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andrewmarques.android.appliviavercosaestetica.R;
import com.andrewmarques.android.appliviavercosaestetica.adapter.AdapterPerguntaRespostaHistoricoClinc;
import com.andrewmarques.android.appliviavercosaestetica.model.PerguntaResposta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentFichaCliente extends Fragment {

    public FragmentFichaCliente() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ficha_cliente, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_perguntas_respostas_historico_clinc);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        AdapterPerguntaRespostaHistoricoClinc adapter = new AdapterPerguntaRespostaHistoricoClinc(buscarPerguntas());
        recyclerView.setAdapter(adapter);
    }

    private List<PerguntaResposta> buscarPerguntas (){
        List<PerguntaResposta> perguntas = new ArrayList<>();
        String[] p = getResources().getStringArray(R.array.perguntas_historico_clinico);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Arrays.stream(p).forEach(s -> perguntas.add(new PerguntaResposta(s)));
        }
        return perguntas;
    }
}