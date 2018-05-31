package com.info.infomila.david.billarapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.info.infomila.david.billarapp.R;
import com.info.infomila.david.billarapp.adapters.EstadisticaAdapter;
import com.info.infomila.david.billarapp.network.EstadistiquesAsyncTask;

import java.util.List;

import info.infomila.billar.models.EstadisticaModalitat;
import info.infomila.billar.models.Soci;

public class EstadistiquesFragment extends Fragment {
    private static final String SOCI = "soci";
    private static final String SESSION_ID = "session_id";

    private Soci soci;
    private String sessionId;
    private RecyclerView rcvEstadistiques;
    private EstadisticaAdapter estadisticaAdapter;
    private ProgressBar progressBar;

    public EstadistiquesFragment() {
        // Required empty public constructor
    }

    public static EstadistiquesFragment newInstance(String sessionId, Soci soci) {
        EstadistiquesFragment fragment = new EstadistiquesFragment();
        Bundle args = new Bundle();
        args.putSerializable(SESSION_ID, sessionId);
        args.putSerializable(SOCI, soci);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            sessionId = getArguments().getString(SESSION_ID);
            soci = (Soci) getArguments().getSerializable(SOCI);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contenidorFragment = inflater.inflate(R.layout.fragment_estadistiques, container, false);

        rcvEstadistiques = contenidorFragment.findViewById(R.id.rcvEstadistiques);
        progressBar = contenidorFragment.findViewById(R.id.progressBar);

        EstadistiquesRequest();
        progressBar.setVisibility(View.VISIBLE);

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

    public void EstadistiquesRequest() {
        EstadistiquesAsyncTask estadistiquesAsyncTask = new EstadistiquesAsyncTask(this, sessionId);
        estadistiquesAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void OnEstadistiquesReceived(Soci s) {
        if (s != null) {
            this.soci = s;
        }

        progressBar.setVisibility(View.INVISIBLE);

        if (rcvEstadistiques != null) {
            if (rcvEstadistiques.getAdapter() == null) {
                List<EstadisticaModalitat> llistaEstadistiques = soci.getEstadistiques();
                estadisticaAdapter = new EstadisticaAdapter(this.getContext(), llistaEstadistiques);
                rcvEstadistiques.setLayoutManager(new LinearLayoutManager(this.getContext()));
                rcvEstadistiques.setAdapter(estadisticaAdapter);
            }
        }
    }
}
