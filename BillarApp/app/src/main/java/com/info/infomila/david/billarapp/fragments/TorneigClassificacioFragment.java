package com.info.infomila.david.billarapp.fragments;

import android.content.Context;
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
import com.info.infomila.david.billarapp.adapters.ClassificacioAdapter;
import com.info.infomila.david.billarapp.adapters.TorneigOnParticipoAdapter;
import com.info.infomila.david.billarapp.network.ClassificacioAsyncTask;

import java.util.ArrayList;
import java.util.List;

import info.infomila.billar.models.Classificacio;
import info.infomila.billar.models.Soci;
import info.infomila.billar.models.Torneig;

public class TorneigClassificacioFragment extends Fragment {
    private static final String SOCI = "soci";
    private static final String SESSION_ID = "session_id";
    private static final String TORNEIG = "torneig";

    private Soci soci;
    private String sessionId;
    private Torneig torneig;
    private List<Classificacio> classificacions = new ArrayList<>();

    private RecyclerView rcvClassificacio;
    private ClassificacioAdapter classificacioAdapter;


    public TorneigClassificacioFragment() {
        // Required empty public constructor
    }

    public static TorneigClassificacioFragment newInstance(Soci soci, Torneig torneig, String sessionId) {
        TorneigClassificacioFragment fragment = new TorneigClassificacioFragment();
        Bundle args = new Bundle();
        args.putSerializable(SOCI, soci);
        args.putString(SESSION_ID, sessionId);
        args.putSerializable(TORNEIG, torneig);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            soci = (Soci) getArguments().getSerializable(SOCI);
            sessionId =  getArguments().getString(SESSION_ID);
            torneig = (Torneig) getArguments().getSerializable(TORNEIG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contenidorFragment = inflater.inflate(R.layout.fragment_torneig_classificacio, container, false);

        rcvClassificacio = contenidorFragment.findViewById(R.id.rcvClassificacio);

        ClassificacioRequest();

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

    public void ClassificacioRequest() {
        ClassificacioAsyncTask classificacioAsyncTask = new ClassificacioAsyncTask(this, sessionId, soci, torneig);
        classificacioAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void OnClassificacioReceived(List<Classificacio> classificacions) {
        if (classificacions != null && classificacions.size() > 0) {
            this.classificacions = classificacions;
            if (rcvClassificacio != null) {
                if(rcvClassificacio.getAdapter()==null) {
                    classificacioAdapter = new ClassificacioAdapter(this.getContext(), classificacions);
                    rcvClassificacio.setLayoutManager(new LinearLayoutManager(this.getContext()));
                    rcvClassificacio.setAdapter(classificacioAdapter);
                }
            }
        }
    }
}
