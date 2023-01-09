package com.andrewmarques.android.appliviavercosaestetica.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.andrewmarques.android.appliviavercosaestetica.R;
import com.andrewmarques.android.appliviavercosaestetica.bd.FirebaseHelper;
import com.andrewmarques.android.appliviavercosaestetica.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;


public class Login extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private TextInputLayout txt_email;
    private TextInputLayout txt_senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        txt_email = binding.txtEmailLogin;
        txt_senha = binding.txtSenhaLogin;

    }

    public void bt_login_login (View view) {

        FirebaseHelper.singnOut();
        String email = txt_email.getEditText().getText().toString();
        String senha = txt_senha.getEditText().getText().toString();

        Task<AuthResult> task = null;

        try{
            task = FirebaseHelper.login_user(email, senha);
        }catch (NullPointerException | IllegalArgumentException e){
            Log.i("Análise do dev", "Login: login: Falha no login: login ou senha vazia");
        }catch (Exception e){
            Log.i("Análise do dev", "Login: login: Falha no login: " + e.getMessage());
            e.printStackTrace();
        }

        if (task != null)
        task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.i("Análise do dev", "Login: login: Sucesso no login: ");
                    finish();
                }else{
                    try {
                        throw task.getException();
                    }catch (Exception e){
                        Log.i("Análise do dev", "Login: login: Falha no login: " + e.getMessage());
                    }
                }
            }
        });

    }
}