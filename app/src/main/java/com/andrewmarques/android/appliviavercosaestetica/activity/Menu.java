package com.andrewmarques.android.appliviavercosaestetica.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.andrewmarques.android.appliviavercosaestetica.R;
import com.andrewmarques.android.appliviavercosaestetica.bd.FirebaseHelper;
import com.andrewmarques.android.appliviavercosaestetica.tools.Padroniza;
import com.google.android.material.textfield.TextInputLayout;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        txt_input = findViewById(R.id.txt_teste_input);
        txt_output = findViewById(R.id.txt_teste_output);
    }

    public void sair (View view){
        FirebaseHelper.singnOut();
        finish();
    }

    private TextInputLayout txt_input;
    private TextView txt_output;

    public void testes (View view) {
        String str = txt_input.getEditText().getText().toString();

        if ( Padroniza.isLimitado(str, Padroniza.TipoEntrada.CPF, 0,20) ) {
            txt_output.setText("True");
        }else{
            txt_output.setText("False");
        }

    }
}