package com.andrewmarques.android.appliviavercosaestetica.tools;

import android.app.usage.ExternalStorageStats;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.security.CodeSource;
import java.util.Scanner;

public class GerenciadorTXT {

    Context fileContext;

    public GerenciadorTXT(Context fileContext) {
        this.fileContext = fileContext;
    }

    public void gravar (String dados) {
        try{
            FileOutputStream output = fileContext.openFileOutput("perguntaRespostaHistClinc.txt", Context.MODE_PRIVATE);
            OutputStreamWriter outputStream = new OutputStreamWriter(output);
            outputStream.write(dados);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String ler (){
        StringBuffer dados = new StringBuffer();

        try {
            FileInputStream input = fileContext.openFileInput("perguntaRespostaHistClinc.txt");
            InputStreamReader inputStream = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(inputStream);

            String readString = bufferedReader.readLine();
            while ( readString != null){
                dados.append(readString);
                readString = bufferedReader.readLine();
            }

            inputStream.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
        return dados.toString();
    }

}
