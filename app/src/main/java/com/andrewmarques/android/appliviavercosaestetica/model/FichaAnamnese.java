package com.andrewmarques.android.appliviavercosaestetica.model;

import java.util.Date;

public class FichaAnamnese {

    private PerguntaResposta[] questionario;
    private Date dateMod;

    public Date getDateMod() {
        return dateMod;
    }

    public void setDateMod(Date dateMod) {
        this.dateMod = dateMod;
    }

    public PerguntaResposta[] getQuestionario() {
        return questionario;
    }

    public void setQuestionario(PerguntaResposta[] questionario) {
        this.questionario = questionario;
    }
}
