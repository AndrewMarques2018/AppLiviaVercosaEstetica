package com.andrewmarques.android.appliviavercosaestetica.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.Intent;
import android.icu.text.IDNA;
import android.icu.text.Normalizer2;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.andrewmarques.android.appliviavercosaestetica.R;
import com.andrewmarques.android.appliviavercosaestetica.bd.FirebaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

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

    public void bt_cadastrar_cadastro (View view) {

        FirebaseHelper.singnOut();
        String email = txt_nome.getEditText().getText().toString() + "@estetica.com";
        String senha = txt_senha.getEditText().getText().toString();

        Log.i("Análise do dev:", "Cadastro.java: cadatrar:" +
                "\n email: " +
                email +
                "\n senha: " +
                senha
                );

        Task<AuthResult> task = null;

        try{
            task = FirebaseHelper.cadastrar_new_user(email, senha);
        }catch (NullPointerException | IllegalArgumentException e){
            Log.i("Análise do dev", "Cadastro: cadastrar: Falha na requisição do cadastro: login ou senha vazia");
        }catch (Exception e){
            Log.i("Análise do dev", "Cadastro: cadastrar: Falha na requisição do cadastro: " + e.getMessage());
            e.printStackTrace();
        }

        if (task != null)
        task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.i("Análise do dev", "Cadastro: cadastrar: Sucesso ao cadastrar");
                }else{
                    String msgException = "null";
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e) {
                        msgException = "Digite uma senha mais forte";

                    }catch (FirebaseAuthInvalidCredentialsException e) {
                        msgException = "Por favor, digite um email válido";

                    }catch (FirebaseAuthUserCollisionException e) {
                        msgException = "Esta conta já foi cadastrada";

                    }catch (Exception e) {
                        msgException = "Erro ao cadastrar usuario: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Log.i("Análise do dev", "Cadastro: cadastrar: Falha ao cadastrar: " + msgException);
                }
            }
        });
    }
}