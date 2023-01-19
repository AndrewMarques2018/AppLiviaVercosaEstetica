package com.andrewmarques.android.appliviavercosaestetica.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andrewmarques.android.appliviavercosaestetica.R;
import com.andrewmarques.android.appliviavercosaestetica.model.PerguntaResposta;

import java.util.List;

public class AdapterPerguntaRespostaHistoricoClinc extends RecyclerView.Adapter <AdapterPerguntaRespostaHistoricoClinc.MyViewHolder> {

    List<PerguntaResposta> perguntas;

    public AdapterPerguntaRespostaHistoricoClinc(List<PerguntaResposta> perguntas) {
        this.perguntas = perguntas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_perguntas_respostas_historico_clinico, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.pergunta.setText(perguntas.get(position).getPergunta());
        holder.resposta.setHint(perguntas.get(position).getHint());

    }

    @Override
    public int getItemCount() {
        return perguntas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView pergunta;
        EditText resposta;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            pergunta = itemView.findViewById(R.id.txt_pergunta);
            resposta = itemView.findViewById(R.id.input_resposta);
        }
    }
}
