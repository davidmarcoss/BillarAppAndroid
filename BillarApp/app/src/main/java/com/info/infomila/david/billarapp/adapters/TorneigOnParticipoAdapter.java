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

import info.infomila.billar.models.Torneig;

public class TorneigOnParticipoAdapter extends RecyclerView.Adapter<TorneigOnParticipoAdapter.Holder> {
    private Context activity;
    private List<Torneig> tornejos;

    public TorneigOnParticipoAdapter(Context activity, List<Torneig> tornejos) {
        this.activity = activity;
        this.tornejos = tornejos;
    }

    @Override
    public TorneigOnParticipoAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_torneig_on_participo, parent, false);
        TorneigOnParticipoAdapter.Holder holder = new TorneigOnParticipoAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TorneigOnParticipoAdapter.Holder holder, int position) {
        Torneig torneig = tornejos.get(position);
        SimpleDateFormat simpleDate =  new SimpleDateFormat("dd/MM/yyyy");

        holder.tvNomTorneig.setText(torneig.getNom());
        holder.tvNomModalitat.setText(torneig.getModalitat().getDescripcio());
        holder.tvDataInici.setText(simpleDate.format(torneig.getDataInici()));
        holder.tvDataFi.setText(simpleDate.format(torneig.getDataFi()));
    }

    @Override
    public int getItemCount() {
        return tornejos.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView tvNomTorneig;
        public TextView tvNomModalitat;
        public TextView tvDataInici;
        public TextView tvDataFi;

        public Holder(View itemView) {
            super(itemView);

            tvNomTorneig = itemView.findViewById(R.id.tvNomTorneig);
            tvNomModalitat = itemView.findViewById(R.id.tvNomModalitat);
            tvDataInici = itemView.findViewById(R.id.tvDataInici);
            tvDataFi = itemView.findViewById(R.id.tvDataFi);
        }
    }
}
