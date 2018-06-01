package com.info.infomila.david.billarapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.info.infomila.david.billarapp.R;
import com.info.infomila.david.billarapp.activities.PartidaActivity;
import com.info.infomila.david.billarapp.adapters.TorneigClassificacioAdapter;
import com.info.infomila.david.billarapp.adapters.TorneigPartidaAdapter;
import com.info.infomila.david.billarapp.listeners.PartidaItemClickListener;
import com.info.infomila.david.billarapp.network.TorneigClassificacioAsyncTask;
import com.info.infomila.david.billarapp.network.TorneigPartidesAsyncTask;

import java.util.List;

import info.infomila.billar.models.Classificacio;
import info.infomila.billar.models.Partida;
import info.infomila.billar.models.Soci;
import info.infomila.billar.models.Torneig;

public class TorneigPartidesFragment extends Fragment implements PartidaItemClickListener {
    private static final String SOCI = "soci";
    private static final String SESSION_ID = "session_id";
    private static final String TORNEIG = "torneig";
    public static final String LAST_PARTIDA_JUGADA = "last_partida_jugada";

    private Soci soci;
    private String sessionId;
    private Torneig torneig;
    private RecyclerView rcvPartides;
    private List<Partida> partides;
    private TorneigPartidaAdapter torneigPartidaAdapter;
    private int lastPartidaJugada = -1;

    public TorneigPartidesFragment() {
        // Required empty public constructor
    }

    public static TorneigPartidesFragment newInstance(Soci soci, Torneig torneig, String sessionId) {
        TorneigPartidesFragment fragment = new TorneigPartidesFragment();
        Bundle args = new Bundle();
        args.putSerializable(SOCI, soci);
        args.putSerializable(TORNEIG, torneig);
        args.putString(SESSION_ID, sessionId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            soci = (Soci) getArguments().getSerializable(SOCI);
            torneig = (Torneig) getArguments().getSerializable(TORNEIG);
            sessionId = getArguments().getString(SESSION_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contenidorFragment = inflater.inflate(R.layout.fragment_torneig_partides, container, false);

        rcvPartides = contenidorFragment.findViewById(R.id.rcvPartides);

        PartidesRequest();

        return contenidorFragment;
    }

    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void PartidesRequest() {
        TorneigPartidesAsyncTask torneigPartidesAsyncTask = new TorneigPartidesAsyncTask(this, sessionId, torneig);
        torneigPartidesAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void OnPartidesReceived(List<Partida> partides) {
        if (partides != null && partides.size() > 0) {
            this.partides = partides;
            if (lastPartidaJugada >= 0) {
                this.partides.remove(lastPartidaJugada);
            }
            if (rcvPartides != null) {
                if (rcvPartides.getAdapter() == null) {
                    torneigPartidaAdapter = new TorneigPartidaAdapter(this.getContext(), partides, soci, this);
                    rcvPartides.setLayoutManager(new LinearLayoutManager(this.getContext()));
                    rcvPartides.setAdapter(torneigPartidaAdapter);
                }
            }
        }
    }

    @Override
    public void OnItemClick(Partida selected) {
        if (selected != null) {
            Intent intent = new Intent(this.getActivity(), PartidaActivity.class);
            intent.putExtra(PartidaActivity.SESSION_ID, sessionId);
            intent.putExtra(PartidaActivity.SOCI, soci);
            intent.putExtra(PartidaActivity.PARTIDA, selected);
            this.startActivityForResult(intent, 1);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        lastPartidaJugada = -1;
        if (resultCode == 1) {
            lastPartidaJugada = (int) data.getExtras().get(LAST_PARTIDA_JUGADA);
        }
    }
}
