package com.andrewmarques.android.appliviavercosaestetica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.Intent;
import android.icu.text.Normalizer2;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class Cadastro extends AppCompatActivity {

    private TextInputLayout txt_nome;
    private TextInputLayout txt_senha;
    private TextInputLayout txt_confirmaSenha;
    private TextView txt_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txt_nome = findViewById(R.id.txt_nome_cadastro);
        txt_senha = findViewById(R.id.txt_senha_cadastro);
        txt_confirmaSenha = findViewById(R.id.txt_confirmaSenha_cadastro);
        txt_email = findViewById(R.id.txt_email_cadastro);

        txt_nome.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().equalsIgnoreCase("Andrew")){
                    txt_nome.setError("Usuário já existe!");
                }else {
                    txt_nome.setError(null);
                }

                if(s.length() >= 15){
                    txt_nome.setHelperText("Tamanho max excedido");
                }else {
                    txt_nome.setHelperText("");
                }

                txt_email.setText("Seu Login Será: " + s + "@estetica.com");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}