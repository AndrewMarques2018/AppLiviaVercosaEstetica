package com.andrewmarques.android.appliviavercosaestetica.tools;

/*
    Criado por: Andrew Marques Silva
    Github: https://github.com/AndrewMarques2018
    Linkedin: https://www.linkedin.com/in/andrewmarques2018
    Instagram: https://www.instagram.com/andrewmarquessilva
 */

public class CodificadorBase64 {

    public static String codificarBase64 (String txt){
        return android.util.Base64.encodeToString(txt.getBytes(), android.util.Base64.DEFAULT)
                .replaceAll("\\n|\\r",""); // remover espacos
    }

    public static String decodificarBase64 (String txtCodificado){
        return new String(android.util.Base64.decode(txtCodificado, android.util.Base64.DEFAULT));
    }
}
