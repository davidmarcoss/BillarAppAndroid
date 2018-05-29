package com.info.infomila.david.billarapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.info.infomila.david.billarapp.R;

import java.util.List;

import info.infomila.billar.models.EstadisticaModalitat;


public class EstadisticaAdapter extends RecyclerView.Adapter<EstadisticaAdapter.Holder> {

    private Context activity;
    private List<EstadisticaModalitat> estadistiques;

    public EstadisticaAdapter(Context activity, List<EstadisticaModalitat> estadistiques) {
        this.activity = activity;
        this.estadistiques = estadistiques;
    }

    @Override
    public EstadisticaAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_estadistica, parent, false);
        EstadisticaAdapter.Holder holder = new EstadisticaAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(EstadisticaAdapter.Holder holder, int position) {
        EstadisticaModalitat estadistica = estadistiques.get(position);

        holder.tvNomModalitat.setText(estadistica.getModalitat().getDescripcio());
        holder.tvCoeficient.setText(estadistica.getCoeficientBase()+"");
        holder.tvCaramboles.setText(estadistica.getCarambolesTemporadaActual()+"");
        holder.tvEntrades.setText(estadistica.getEntradesTemporadaActual()+"");
    }

    @Override
    public int getItemCount() {
        return estadistiques.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView tvNomModalitat;
        public TextView tvCoeficient;
        public TextView tvCaramboles;
        public TextView tvEntrades;

        public Holder(View itemView) {
            super(itemView);

            tvNomModalitat = itemView.findViewById(R.id.tvNomModalitat);
            tvCoeficient = itemView.findViewById(R.id.tvCoeficient);
            tvCaramboles = itemView.findViewById(R.id.tvCaramboles);
            tvEntrades = itemView.findViewById(R.id.tvEntrades);
        }
    }
}
