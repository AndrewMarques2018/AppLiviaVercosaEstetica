package com.andrewmarques.android.appliviavercosaestetica.activity.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.andrewmarques.android.appliviavercosaestetica.R;
import com.andrewmarques.android.appliviavercosaestetica.adapter.AdapterClientes;
import com.andrewmarques.android.appliviavercosaestetica.model.Cliente;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class FragmentClientes extends Fragment {

    private View fragmentView;
    private List<Cliente> clientes;
    AdapterClientes adapterClientes;

    RecyclerView recyclerView;

    public FragmentClientes() {
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

        // configurar configurações de escolha de ordem das clientes
        String[] order_clientes_by = getResources().getStringArray(R.array.order_clientes_by);
        ArrayAdapter arrayAdapter = new ArrayAdapter(fragmentView.getContext(), R.layout.dropdown_item, order_clientes_by);
        AutoCompleteTextView autoCompleteTextView = fragmentView.findViewById(R.id.frag_bt_order_by_clientes);
        autoCompleteTextView.setAdapter(arrayAdapter);
        Log.i("Análize do dev", "retorno bt_order_by_clientes: " + autoCompleteTextView.getText());
        autoCompleteTextView.addTextChangedListener(order_clientes_change);

        // configurar recycle view clientes
        RecyclerView recyclerView = view.findViewById(R.id.frag_recycler_clientes);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        gerarClientes();
        adapterClientes = new AdapterClientes(clientes);
        recyclerView.setAdapter(adapterClientes);

    }

    private void gerarClientes() {
        clientes = new ArrayList<>();
        Cliente c = new Cliente("Maria das Graças", "09/12/1987", "(83) 94532-7493", null, null);
        c.setT1("01/01/2023");
        c.setT2("12/01/2023 14:40 Hidrolipo");
        clientes.add(c);

        c = new Cliente("Yaridsa Drummont Darsié", "27/01/1988", "(12) 12331-3217", null, null);
        c.setT1("05/01/2023");
        c.setT2("20/01/2023 14:40 D-Tox");
        clientes.add(c);

        c = new Cliente("Astred Vanhalla", "06/02/1993", "(01) 99934-9432", null, null);
        c.setT1("20/12/2022");
        c.setT2("");
        clientes.add(c);

        c = new Cliente("Emilly White Dantas", "21/11/1991", "(90) 86543-2300", null, null);
        c.setT1("11/12/2022");
        c.setT2("");
        clientes.add(c);

        c = new Cliente("Arliene Pereira Filha", "30/07/1992", "(65) 10364-5463", null, null);
        c.setT1("06/01/2023");
        c.setT2("31/01/2023 09:40 Massagem Modeladora");
        clientes.add(c);

        c = new Cliente("Gerlian das Dores", "14/10/2000", "(55) 42748-8483", null, null);
        c.setT1("02/01/2023");
        c.setT2("30/01/2023 14:40 Hidrolipo");
        clientes.add(c);

        new Cliente("Milena Santos Costa", "19/09/1998", "(78) 94683-6848", null, null);
        c.setT1("03/01/2023");
        c.setT2("");
        clientes.add(c);

        c = new Cliente("Lira da Silva", "02/01/1996", "(82) 42748-8492", null, null);
        c.setT1("25/11/2022");
        c.setT2("");
        clientes.add(c);

        c = new Cliente("Antonia Obadias", "07/07/1990", "(85) 02843-3313", null, null);
        c.setT1("17/11/2022");
        c.setT2("28/01/2023 14:40 Ultrassom");
        clientes.add(c);

        c = new Cliente("Astoufa Criola", "04/05/1989", "(85) 72947-3490", null, null);
        c.setT1("04/01/2023");
        c.setT2("19/01/2023 09:40 Limpeza Pele");
        clientes.add(c);

        c = new Cliente("Jubileia Santos", "11/11/1997", "(18) 04782-2324", null, null);
        c.setT1("04/01/2023");
        c.setT2("");
        clientes.add(c);

        c = new Cliente("Francisca Gerliane", "12/03/1995", "(85) 64823-5392", null, null);
        c.setT1("26/12/2022");
        c.setT2("13/01/2023 13:30 Hidrolipo");
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
            switch (s.toString()) {
                case "Nome":
                    Collections.sort(clientes, new Comparator<Cliente>() {
                        @Override
                        public int compare(Cliente o1, Cliente o2) {
                            return o1.getNome().compareTo(o2.getNome());
                        }
                    });
                    adapterClientes.notifyDataSetChanged();
                    break;
                case "Idade":
                    Collections.sort(clientes, new Comparator<Cliente>() {
                        @Override
                        public int compare(Cliente o1, Cliente o2) {
                            return o1.getDataNasc().compareTo(o2.getDataNasc());
                        }
                    });
                    adapterClientes.notifyDataSetChanged();
                    break;
                case "Data":
                    Collections.sort(clientes, new Comparator<Cliente>() {
                        @Override
                        public int compare(Cliente o1, Cliente o2) {
                            if (o1.getT1().before(o2.getT1())) {
                                return 1;
                            }else{
                                return -1;
                            }
                        }
                    });
                    adapterClientes.notifyDataSetChanged();
                    break;
                case "Agendamento":
                    Collections.sort(clientes, new Comparator<Cliente>() {
                        @Override
                        public int compare(Cliente o1, Cliente o2) {
                            if (o1.getT2().isEmpty()){
                                return -1;
                            }

                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh/mm");
                            try {
                                if(sdf.parse(o1.getT2().substring(0,15)).before(sdf.parse(o2.getT2().substring(0,15)))){
                                    return 1;
                                }
                            }catch (Exception e){

                            }
                            return o1.getT2().compareTo(o2.getT2());
                        }
                    });
                    adapterClientes.notifyDataSetChanged();
                    break;
            }
        }
    };

}