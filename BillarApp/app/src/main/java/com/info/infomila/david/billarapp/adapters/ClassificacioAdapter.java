package com.info.infomila.david.billarapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.info.infomila.david.billarapp.R;
import com.info.infomila.david.billarapp.listeners.TorneigOnParticipoClickListener;

import java.text.SimpleDateFormat;
import java.util.List;

import info.infomila.billar.models.Classificacio;
import info.infomila.billar.models.Torneig;

public class ClassificacioAdapter extends RecyclerView.Adapter<ClassificacioAdapter.Holder> {
    private Context activity;
    private List<Classificacio> classificacions;

    public ClassificacioAdapter(Context activity, List<Classificacio> classificacions) {
        this.activity = activity;
        this.classificacions = classificacions;
    }

    @Override
    public ClassificacioAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_classificacio, parent, false);
        ClassificacioAdapter.Holder holder = new ClassificacioAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ClassificacioAdapter.Holder holder, int position) {
        Classificacio classificacio = classificacions.get(position);

        holder.tvPos.setText(position + "");
        holder.tvSoci.setText(classificacio.getSoci());
        holder.tvPartidesGuanyades.setText(classificacio.getPatidesGuanyades()+"");
        holder.tvPartidesPerdudes.setText(classificacio.getPartidesPerdudes()+"");
    }

    @Override
    public int getItemCount() {
        return classificacions.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView tvPos;
        public TextView tvSoci;
        public TextView tvPartidesGuanyades;
        public TextView tvPartidesPerdudes;

        public Holder(View itemView) {
            super(itemView);

            tvPos = itemView.findViewById(R.id.tvPos);
            tvSoci = itemView.findViewById(R.id.tvSoci);
            tvPartidesGuanyades = itemView.findViewById(R.id.tvPartidesGuanyades);
            tvPartidesPerdudes = itemView.findViewById(R.id.tvPartidesPerdudes);
        }
    }
}
