package com.info.infomila.david.billarapp.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.info.infomila.david.billarapp.R;
import com.info.infomila.david.billarapp.fragments.TornejosObertsFragment;
import com.info.infomila.david.billarapp.network.InscripcioAsyncTask;

import java.text.SimpleDateFormat;
import java.util.List;

import info.infomila.billar.models.EstadisticaModalitat;
import info.infomila.billar.models.Torneig;

public class TorneigObertAdapter extends RecyclerView.Adapter<TorneigObertAdapter.Holder> {

    private Context activity;
    private List<Torneig> tornejos;
    private String sessionId;
    private int lastTorneigId;
    private Torneig lastTorneig;

    public TorneigObertAdapter(Context activity, List<Torneig> tornejos, String sessionId) {
        this.activity = activity;
        this.tornejos = tornejos;
        this.sessionId = sessionId;
    }

    @Override
    public TorneigObertAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_torneig_obert, parent, false);
        TorneigObertAdapter.Holder holder = new TorneigObertAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TorneigObertAdapter.Holder holder, int position) {
        Torneig torneig = tornejos.get(position);
        SimpleDateFormat simpleDate =  new SimpleDateFormat("dd/MM/yyyy");

        holder.tvNomTorneig.setText(torneig.getNom());
        holder.tvNomModalitat.setText(torneig.getModalitat().getDescripcio());
        holder.tvDataInici.setText(simpleDate.format(torneig.getDataInici()));
        holder.tvDataFi.setText(simpleDate.format(torneig.getDataFi()));
        holder.btnInscripcio.setTag(torneig.getId());
    }

    @Override
    public int getItemCount() {
        return tornejos.size();
    }

    public void OnInscripcioTorneigReceived(Boolean inscrit) {
        if (inscrit) {
            if (lastTorneig != null) {
                tornejos.remove(lastTorneig);
            }

            this.notifyDataSetChanged();
        } else {
   
        }
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView tvNomTorneig;
        public TextView tvNomModalitat;
        public TextView tvDataInici;
        public TextView tvDataFi;
        public Button btnInscripcio;

        public Holder(View itemView) {
            super(itemView);

            tvNomTorneig = itemView.findViewById(R.id.tvNomTorneig);
            tvNomModalitat = itemView.findViewById(R.id.tvNomModalitat);
            tvDataInici = itemView.findViewById(R.id.tvDataInici);
            tvDataFi = itemView.findViewById(R.id.tvDataFi);
            btnInscripcio = itemView.findViewById(R.id.btnInscripcio);
            btnInscripcio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lastTorneig = tornejos.get(getLayoutPosition());
                    InscripcioAsyncTask inscripcioAsyncTask = new InscripcioAsyncTask(TorneigObertAdapter.this);
                    inscripcioAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, sessionId, String.valueOf(lastTorneig.getId()));
                }
            });
        }
    }
}
