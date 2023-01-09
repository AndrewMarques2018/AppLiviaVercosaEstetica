package com.andrewmarques.android.appliviavercosaestetica.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andrewmarques.android.appliviavercosaestetica.R;
import com.andrewmarques.android.appliviavercosaestetica.model.Cliente;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AdapterClientes extends RecyclerView.Adapter <AdapterClientes.MyViewHolder> {

    List<Cliente> clientes;

    public AdapterClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_clientes, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nome.setText(clientes.get(position).getNome());
        holder.contato.setText(clientes.get(position).getTelefone());
        String prox = clientes.get(position).getT2();

        if (!Objects.equals(prox, "")){
            holder.proxAgem.setText(prox);
        }else{
            holder.proxAgem.setText("Nada Marcado!");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = clientes.get(position).getT1();
        holder.data.setText(sdf.format(d));


    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome, contato, data, proxAgem;
        ImageView img_avatar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.adapter_nome_cliente);
            contato = itemView.findViewById(R.id.adapter_contato_cliente);
            data = itemView.findViewById(R.id.adapter_data_ultima_visita);
            proxAgem = itemView.findViewById(R.id.adapter_prox_agendamento_cliente);
            img_avatar = itemView.findViewById(R.id.adapter_img_cliente);
        }
    }

}
