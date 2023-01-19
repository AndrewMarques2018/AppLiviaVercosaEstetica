package com.andrewmarques.android.appliviavercosaestetica.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.andrewmarques.android.appliviavercosaestetica.bd.FirebaseHelper;
import com.andrewmarques.android.appliviavercosaestetica.databinding.ActivityCadastroBinding;
import com.andrewmarques.android.appliviavercosaestetica.model.Usuario;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.GoogleAuthProvider;

public class Cadastro extends AppCompatActivity {

    private ActivityCadastroBinding binding;
    private GoogleSignInClient googleSignInClient;
    private GoogleSignInOptions googleSignInOptions;
    private TextInputLayout txt_nome;
    private TextInputLayout txt_senha;
    private TextInputLayout txt_confirmaSenha;
    private TextInputLayout txt_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        txt_nome = binding.txtNomeCadastro;
        txt_senha = binding.txtSenhaCadastro;
        txt_confirmaSenha = binding.txtConfirmaSenhaCadastro;
        txt_email = binding.txtEmailCadastro;

        googleSignInOptions = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("224325117326-mlllafvbt7pqhksm63od2t2rmqc4hndg.apps.googleusercontent.com")
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
    }

    public void bt_cadastrar_cadastro(View view) {

        Usuario u = new Usuario();
        u.setEmail(txt_nome.getEditText().getText().toString());
        u.setSenha(txt_senha.getEditText().getText().toString());

        cadastrarNuvem(u);
    }

    private void verificarEmail (Usuario u) {

    }

    private void cadastrarNuvem (Usuario u) {
        Task<AuthResult> task = null;
        try{
            task = FirebaseHelper.cadastrarUsuario(u.getEmail(), u.getSenha());
        }catch (NullPointerException | IllegalArgumentException e){
            Toast.makeText(getApplicationContext(), "Email ou Senha Vazios", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        if (task != null) {
            task.addOnCompleteListener( cadatro -> {

                if (cadatro.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Sucesso ao cadastrar", Toast.LENGTH_SHORT).show();
                    finish();

                }else{
                    String msgException = "null";

                    try{
                        throw cadatro.getException();
                    }catch (FirebaseAuthWeakPasswordException e) {
                        msgException = "Digite uma senha mais forte";

                    }catch (FirebaseAuthInvalidCredentialsException e) {
                        msgException = "Por favor, digite um email válido";

                    }catch (FirebaseAuthUserCollisionException e) {
                        msgException = "Esta conta já foi cadastrada";

                    }catch (FirebaseNetworkException e) {
                        msgException = "Sem conexão com a internet";

                    }catch (Exception e) {
                        msgException = "Erro ao cadastrar usuario: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(getApplicationContext(), msgException, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void bt_cadastrar_cadastro_by_google (View view) {
        showIntentCadastroByGoogle();
    }

    private void showIntentCadastroByGoogle() {
        Intent intent = googleSignInClient.getSignInIntent();
        abreActivity.launch(intent);
    }

    ActivityResultLauncher<Intent> abreActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

                    try{
                        GoogleSignInAccount conta = task.getResult(ApiException.class);
                        loginByGoogle(conta.getIdToken());

                    } catch (ApiException e) {
                        Toast.makeText(getApplicationContext(), "Nenhum login com o google", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Erro ao efetuar login", Toast.LENGTH_SHORT).show();
                }
            }
    );

    private void loginByGoogle (String token){

        try{
            AuthCredential credential = GoogleAuthProvider.getCredential(token, null);

            FirebaseHelper.getInstance().signInWithCredential(credential).addOnCompleteListener(this, result -> {
                if (result.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Cadastro Google no firebase efetuado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Erro ao efetuar cadastro com o google no firebase", Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Log.i("Análise do dev", "credecial error: " + e.getMessage());
        }



    }

}
