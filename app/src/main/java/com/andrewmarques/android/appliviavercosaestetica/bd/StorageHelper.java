package com.andrewmarques.android.appliviavercosaestetica.bd;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class StorageHelper {

    Context fileContext;

    public StorageHelper(Context fileContext) {
        this.fileContext = fileContext;
    }

    public void gravar (String path, String dados) throws IOException {
        try{
            FileOutputStream output = fileContext.openFileOutput(path+".txt", Context.MODE_PRIVATE);
            OutputStreamWriter outputStream = new OutputStreamWriter(output);
            outputStream.write(dados);
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            throw e;
        }
    }

    public String ler ( String path ) throws IOException {
        StringBuffer dados = new StringBuffer();

        try {
            FileInputStream input = fileContext.openFileInput(path+".txt");
            InputStreamReader inputStream = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(inputStream);

            String readString = bufferedReader.readLine();
            while ( readString != null){
                dados.append(readString);
                readString = bufferedReader.readLine();
            }

            inputStream.close();

        }catch (IOException e) {
            throw e;
        }
        return dados.toString();
    }
}
