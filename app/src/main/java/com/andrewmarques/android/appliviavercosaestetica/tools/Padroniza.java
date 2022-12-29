package com.andrewmarques.android.appliviavercosaestetica.tools;

public class Padroniza {

    public static enum TipoEntrada {
        NUMEROINTEIRO, NUMERODECIMAL, NOME, EMAIL, DATA, CPF;
    }

    private static String getRegex (TipoEntrada tpEntrada) {
        String regex = "";

        switch(tpEntrada){
            case NUMEROINTEIRO: regex = "[^0-9]";                 break;
            case NUMERODECIMAL: regex = "[^0-9,.]";               break;
            case NOME:          regex = "[^a-zA-Z ]";             break;
            case EMAIL:         regex = "[^a-zA-Z}@.\\-_][^0-9]"; break;
            case DATA:          regex = "[^0-9/]";                break;
            case CPF:           regex = "[^0-9.\\-]";             break;
        }

        return regex;
    }

    public static boolean isLimitado (String string, TipoEntrada tpEntrada, int qtdMinCaracteres, int qtdMaxCaracteres) {
        int length = string.length();

        if (length < qtdMinCaracteres || length > qtdMaxCaracteres){
            return false;
        }

        String regex = getRegex(tpEntrada);
        String novaString = string.replaceAll(regex, "");

        return length == novaString.length();
    }

    public static String limitar (String string, TipoEntrada tpEntrada, int qtdMaxCaracteres) {

        if (string == null || string.length() == 0){
            return "";
        }

        String regex = "";
        switch(tpEntrada){
            case NUMEROINTEIRO: regex = "[^0-9]"; break;
            case NUMERODECIMAL: regex = "[^0-9,.]"; break;
            case NOME:          regex = "[^\\p{IsLatin} ]"; break;
            case EMAIL:         regex = "[^\\p{IsLatin}@.\\-_][^0-9]"; break;
            case DATA:          regex = "[^0-9/]"; break;
        }

        String novaString = string.replaceAll(regex, "");

        if(string.length() > qtdMaxCaracteres){
            novaString = novaString.substring(0, qtdMaxCaracteres);
        }

        return novaString;
    }

    public static boolean isCPF (String string) {
        String regex = "[^%d%d%d.%d%d%d.%d%d%d-%d%d]";
        return true;
    }

}
