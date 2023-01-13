package com.andrewmarques.android.appliviavercosaestetica.model;

public class PerguntaRespostaHistClinc {

    String pergunta, resposta, hint;

    public PerguntaRespostaHistClinc(String pergunta) {
        this.pergunta = pergunta;
    }

    public PerguntaRespostaHistClinc(String pergunta, String hint) {
        this.pergunta = pergunta;
        this.hint = hint;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
