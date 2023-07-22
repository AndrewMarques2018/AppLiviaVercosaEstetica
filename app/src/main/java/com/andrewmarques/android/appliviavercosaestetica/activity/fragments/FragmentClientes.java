package com.andrewmarques.android.appliviavercosaestetica.activity.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.andrewmarques.android.appliviavercosaestetica.R;
import com.andrewmarques.android.appliviavercosaestetica.adapter.AdapterClientes;
import com.andrewmarques.android.appliviavercosaestetica.model.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FragmentClientes extends Fragment {

    private View fragmentView;
    private List<Usuario> clientes;
    AdapterClientes adapterClientes;

    public FragmentClientes() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_clientes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentView = view;
        FloatingActionButton fab = fragmentView.findViewById(R.id.fab_add_cliente);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(fragmentView).navigate(R.id.action_mostrar_bottomSheet_add_clientes);
            }
        });

        // configurar configurações de escolha de ordem das clientes
        String[] order_clientes_by = getResources().getStringArray(R.array.order_clientes_by);
        ArrayAdapter arrayAdapter = new ArrayAdapter(fragmentView.getContext(), R.layout.adapter_dropdown_item, order_clientes_by);
        AutoCompleteTextView autoCompleteTextView = fragmentView.findViewById(R.id.frag_bt_order_by_clientes);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.addTextChangedListener(order_clientes_change);

        // configurar recycle view clientes
        RecyclerView recyclerView = fragmentView.findViewById(R.id.frag_recycler_clientes);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(fragmentView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        gerarClientes();
        adapterClientes = new AdapterClientes(clientes, Navigation.findNavController(fragmentView));
        recyclerView.setAdapter(adapterClientes);

    }

    private void gerarClientes() {
        clientes = new ArrayList<>();
        Usuario c = new Usuario("Maria das Graças");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            c.setDataNasc(LocalDate.of(1994, 11, 3));
        }
        c.setEndereco("Fortaleza-CE Rua Jubileu Chirimbiola 452");
        c.setEmail("mariazinha@gmail.com");
        c.setTelefone("(89)91234-5943");
        c.setId("0001");
        clientes.add(c);

        c = new Usuario("Yaridsa Drummont Darsié");
        clientes.add(c);

        c = new Usuario("Astred Vanhalla");
        clientes.add(c);

        c = new Usuario("Emilly White Dantas");
        clientes.add(c);

        c = new Usuario("Arliene Pereira Filha");
        clientes.add(c);

        c = new Usuario("Gerlian das Dores");
        clientes.add(c);

        new Usuario("Milena Santos Costa");
        clientes.add(c);

        c = new Usuario("Lira da Silva");
        clientes.add(c);

        c = new Usuario("Antonia Obadias");
        clientes.add(c);

        c = new Usuario("Astoufa Criola");
        clientes.add(c);

        c = new Usuario("Jubileia Santos");
        clientes.add(c);

        c = new Usuario("Francisca Gerliane");
        clientes.add(c);
    }

    private TextWatcher order_clientes_change = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            // implemente o order aqui!
        }
    };

}