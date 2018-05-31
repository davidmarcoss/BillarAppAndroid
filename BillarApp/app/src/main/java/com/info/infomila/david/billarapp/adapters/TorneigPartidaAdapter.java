package com.info.infomila.david.billarapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.info.infomila.david.billarapp.R;
import com.info.infomila.david.billarapp.activities.TorneigActivity;
import com.info.infomila.david.billarapp.listeners.PartidaItemClickListener;

import java.util.List;

import info.infomila.billar.models.Classificacio;
import info.infomila.billar.models.Partida;
import info.infomila.billar.models.Soci;

public class TorneigPartidaAdapter extends RecyclerView.Adapter<TorneigPartidaAdapter.Holder> {
    private Context activity;
    private List<Partida> partides;
    private Soci soci;
    private PartidaItemClickListener partidaItemClickListener;

    public TorneigPartidaAdapter(Context activity, List<Partida> partides, Soci soci, PartidaItemClickListener partidaItemClickListener) {
        this.activity = activity;
        this.partides = partides;
        this.soci = soci;
        this.partidaItemClickListener = partidaItemClickListener;
    }

    @Override
    public TorneigPartidaAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_partida, parent, false);
        TorneigPartidaAdapter.Holder holder = new TorneigPartidaAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TorneigPartidaAdapter.Holder holder, int position) {
        Partida partida = partides.get(position);

        if (partida.getSociA().equals(soci)) {
            String nomComplet = partida.getSociB().getNom();
            if (partida.getSociB().getCognom1() != null) {
                nomComplet += " " + partida.getSociB().getCognom1();
            }
            if (partida.getSociB().getCognom2() != null) {
                nomComplet += " " + partida.getSociB().getCognom2();
            }
            holder.tvSociRival.setText(nomComplet);
        } else {
            String nomComplet = partida.getSociA().getNom();
            if (partida.getSociA().getCognom1() != null) {
                nomComplet += " " + partida.getSociA().getCognom1();
            }
            if (partida.getSociA().getCognom2() != null) {
                nomComplet += " " + partida.getSociA().getCognom2();
            }
            holder.tvSociRival.setText(nomComplet);
        }
    }

    @Override
    public int getItemCount() {
        return partides.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView tvSociRival;
        private Button btnJugar;


        public Holder(View itemView) {
            super(itemView);

            tvSociRival = itemView.findViewById(R.id.tvSociRival);
            btnJugar = itemView.findViewById(R.id.btnJugar);
            btnJugar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Partida partida = partides.get(getLayoutPosition());
                    if (TorneigPartidaAdapter.this.partidaItemClickListener != null) {
                        TorneigPartidaAdapter.this.partidaItemClickListener.OnItemClick(partida);
                    }
                }
            });
        }
    }
}
