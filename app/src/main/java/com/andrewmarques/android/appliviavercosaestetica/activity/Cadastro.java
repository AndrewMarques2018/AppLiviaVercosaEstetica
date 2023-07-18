package com.andrewmarques.android.appliviavercosaestetica.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.andrewmarques.android.appliviavercosaestetica.bd.FirebaseHelper;
import com.andrewmarques.android.appliviavercosaestetica.databinding.ActivityCadastroBinding;
import com.andrewmarques.android.appliviavercosaestetica.model.Usuario;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.util.Arrays;
import java.util.List;


public class Cadastro extends AppCompatActivity {

    private ActivityCadastroBinding binding;
    private TextInputLayout txt_nome;
    private TextInputLayout txt_senha;
    private TextInputLayout txt_confirmaSenha;
    private TextInputLayout txt_email;
    private static final int RC_SIGN_IN = 123;
    private FirebaseAuthUIActivityResultContract signInContract = new FirebaseAuthUIActivityResultContract();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        txt_nome = binding.txtNomeCadastro;
        txt_senha = binding.txtSenhaCadastro;
        txt_confirmaSenha = binding.txtConfirmaSenhaCadastro;
        txt_email = binding.txtEmailCadastro;
    }

    public void bt_cadastrar_cadastro(View view) {
        Usuario u = new Usuario();
        if (validaDados()) {
            u.setEmail(txt_email.getEditText().getText().toString());
            u.setSenha(txt_senha.getEditText().getText().toString());
            cadastrarNuvem(u);
        }
    }

    private boolean validaDados() {
        String nome = txt_nome.getEditText().getText().toString();
        String email = txt_email.getEditText().getText().toString();
        String senha = txt_senha.getEditText().getText().toString();
        String confSenha = txt_confirmaSenha.getEditText().getText().toString();

        if (nome.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Preencha o campo nome", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Preencha o campo e-mail", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (senha.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Preencha o campo senha", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (confSenha.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Confirme sua senha", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!senha.equals(confSenha)) {
            Toast.makeText(getApplicationContext(), "As senhas não coincidem", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void cadastrarNuvem(Usuario u) {
        Task<AuthResult> task = null;
        try {
            task = FirebaseHelper.cadastrarUsuario(u.getEmail(), u.getSenha());
        } catch (NullPointerException | IllegalArgumentException e) {
            Toast.makeText(getApplicationContext(), "Email ou Senha Vazios", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        if (task != null) {
            task.addOnCompleteListener(cadatro -> {

                if (cadatro.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Sucesso ao cadastrar", Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    String msgException = "null";

                    try {
                        throw cadatro.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        msgException = "Digite uma senha mais forte";

                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        msgException = "Por favor, digite um email válido";

                    } catch (FirebaseAuthUserCollisionException e) {
                        msgException = "Esta conta já foi cadastrada";

                    } catch (FirebaseNetworkException e) {
                        msgException = "Sem conexão com a internet";

                    } catch (Exception e) {
                        msgException = "Erro ao cadastrar usuario: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(getApplicationContext(), msgException, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void bt_cadastrar_cadastro_by_google(View view) {
        startSignInFlow();
    }

    private void startSignInFlow() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();

        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            FirebaseAuthUIAuthenticationResult result = signInContract.parseResult(resultCode, data);

            if (resultCode == RESULT_OK) {
                // Autenticação bem-sucedida
                Toast.makeText(this, "Autenticação bem-sucedida!", Toast.LENGTH_SHORT).show();
                // Você pode acessar o usuário atual usando FirebaseAuth.getInstance().getCurrentUser()
            } else {
                // Autenticação falhou
                IdpResponse response = result.getIdpResponse();
                if (response != null) {
                    Toast.makeText(this, "Autenticação falhou: " + response.getError().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Autenticação cancelada", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}