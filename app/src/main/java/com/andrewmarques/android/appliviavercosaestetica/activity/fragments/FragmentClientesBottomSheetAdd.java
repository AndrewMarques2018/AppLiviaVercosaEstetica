package com.andrewmarques.android.appliviavercosaestetica.activity.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andrewmarques.android.appliviavercosaestetica.R;
import com.andrewmarques.android.appliviavercosaestetica.model.Usuario;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.time.LocalDate;

public class FragmentClientesBottomSheetAdd extends BottomSheetDialogFragment implements View.OnClickListener{

    public FragmentClientesBottomSheetAdd() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_bottom_sheet_add_clientes, container, false);

        Button btAddClienteConfirma = fragmentView.findViewById(R.id.bt_add_cliente_confirma);
        btAddClienteConfirma.setOnClickListener(this);

        return fragmentView;
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bt_add_cliente_confirma) {
            adicionarCliente();
        }
    }

    private void adicionarCliente() {
        EditText nomeEditText = getView().findViewById(R.id.input_nome_cliente);
        EditText telefoneEditText = getView().findViewById(R.id.input_telefone_cliente);
        EditText dataNascEditText = getView().findViewById(R.id.input_data_nasc_cliente);
        EditText emailEditText = getView().findViewById(R.id.input_email_cliente);
        EditText enderecoEditText = getView().findViewById(R.id.input_endereco_cliente);

        String nome = nomeEditText.getText().toString();
        String telefone = telefoneEditText.getText().toString();
        LocalDate dataNascimento = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            try {
                dataNascimento = LocalDate.parse(dataNascEditText.getText().toString());
            } catch (Exception e) {
                Toast.makeText(this.getContext(), "Campo data com formato incorreto: ex:.12/03/2022", Toast.LENGTH_LONG);
            }

        }
        String email = emailEditText.getText().toString();
        String endereco = enderecoEditText.getText().toString();

        if(nome.isEmpty()){
            Toast.makeText(this.getContext(), "Campo nome é obrigatório", Toast.LENGTH_LONG);
        }

        Usuario novo_usuario = new Usuario();
        novo_usuario.setNome(nome);
        novo_usuario.setTelefone(telefone);
        novo_usuario.setDataNasc(dataNascimento);
        novo_usuario.setEmail(email);
        novo_usuario.setEndereco(endereco);

        Toast.makeText(getContext(), nome, Toast.LENGTH_LONG).show();
    }
}