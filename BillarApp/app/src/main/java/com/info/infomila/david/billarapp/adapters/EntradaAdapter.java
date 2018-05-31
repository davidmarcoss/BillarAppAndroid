package com.info.infomila.david.billarapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.info.infomila.david.billarapp.R;

import java.text.SimpleDateFormat;
import java.util.List;

import info.infomila.billar.models.EntradaDetall;
import info.infomila.billar.models.EstadisticaModalitat;


public class EntradaAdapter extends RecyclerView.Adapter<EntradaAdapter.Holder> {

    private Context context;
    private List<EntradaDetall> entrades;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public EntradaAdapter(Context context, List<EntradaDetall> entrades) {
        this.context = context;
        this.entrades = entrades;
    }

    @Override
    public EntradaAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_entrada, parent, false);
        EntradaAdapter.Holder holder = new EntradaAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(EntradaAdapter.Holder holder, int position) {
        EntradaDetall entrada = entrades.get(position);

        holder.tvEntrada.setText(entrada.getEntrada()+"");
        holder.tvNomSoci.setText(entrada.getSociNom());
        holder.tvDataEntrada.setText(dateFormat.format(entrada.getDataEntrada()));
        holder.tvCaramboles.setText(entrada.getCaramboles()+"");
    }

    @Override
    public int getItemCount() {
        return entrades.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView tvEntrada;
        private TextView tvNomSoci;
        private TextView tvDataEntrada;
        private TextView tvCaramboles;

        public Holder(View itemView) {
            super(itemView);

            tvEntrada = itemView.findViewById(R.id.tvEntrada);
            tvNomSoci = itemView.findViewById(R.id.tvNomSoci);
            tvDataEntrada = itemView.findViewById(R.id.tvDataEntrada);
            tvCaramboles = itemView.findViewById(R.id.tvCaramboles);
        }
    }

    public void refreshEntrades(List<EntradaDetall> entrades) {
        this.entrades = entrades;
        this.notifyDataSetChanged();
    }
}
