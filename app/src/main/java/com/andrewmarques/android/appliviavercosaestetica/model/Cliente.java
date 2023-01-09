package com.andrewmarques.android.appliviavercosaestetica.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente {
    String nome, telefone, email, cpf_cnpj, endereco, contato_emergencia;
    String[] alergias, doencas;
    Date dataNasc, t1;
    String t2;

    public Cliente (String nome, String dataNasc, String telefone, String contato_emergencia, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.contato_emergencia = contato_emergencia;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            this.dataNasc = sdf.parse(dataNasc);
        }catch (Exception e){
            this.dataNasc = new Date();
        }

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContato_emergencia() {
        return contato_emergencia;
    }

    public void setContato_emergencia(String contato_emergencia) {
        this.contato_emergencia = contato_emergencia;
    }

    public String[] getAlergias() {
        return alergias;
    }

    public void setAlergias(String[] alergias) {
        this.alergias = alergias;
    }

    public String[] getDoencas() {
        return doencas;
    }

    public void setDoencas(String[] doencas) {
        this.doencas = doencas;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Date getT1() {
        return t1;
    }

    public void setT1(String t1) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            this.t1 = sdf.parse(t1);
        }catch (Exception e){
            this.t1 = new Date();
        }
    }

    public String getT2() {
        if(t2.isEmpty()){
            this.t2 = "Nada Marcado!";
        }
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }
}
