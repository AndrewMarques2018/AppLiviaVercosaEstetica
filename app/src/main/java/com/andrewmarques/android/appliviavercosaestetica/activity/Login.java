package com.andrewmarques.android.appliviavercosaestetica.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.andrewmarques.android.appliviavercosaestetica.R;
import com.andrewmarques.android.appliviavercosaestetica.bd.FirebaseHelper;
import com.andrewmarques.android.appliviavercosaestetica.databinding.ActivityLoginBinding;
import com.andrewmarques.android.appliviavercosaestetica.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;


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
        String email = txt_email.getEditText().getText().toString();
        String senha = txt_senha.getEditText().getText().toString();

        loginNuvem(email, senha);
    }

    private void loginNuvem (String email, String senha) {
        Task<AuthResult> task = null;

        try{
            task = FirebaseHelper.loginUsuario(email, senha);
        }catch (NullPointerException | IllegalArgumentException e){
            Toast.makeText(getApplicationContext(), "Email ou Senha Vazios", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        if (task != null) {
            task.addOnCompleteListener( login -> {

                if (login.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Bem vindo", Toast.LENGTH_SHORT).show();
                    finish();

                }else{
                    String msgException = "null";

                    try{
                        throw login.getException();

                    }catch (FirebaseAuthInvalidCredentialsException e) {
                        msgException = "Email ou Senha inválidos";

                    }catch (FirebaseNetworkException e) {
                        msgException = "Sem conexão com a internet";

                    }catch (Exception e) {
                        msgException = "error: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(getApplicationContext(), msgException, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void bt_login_login_by_google (View view) {

    }
}