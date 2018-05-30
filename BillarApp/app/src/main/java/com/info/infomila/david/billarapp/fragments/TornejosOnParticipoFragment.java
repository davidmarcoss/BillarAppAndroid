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
import com.info.infomila.david.billarapp.activities.MainActivity;
import com.info.infomila.david.billarapp.activities.ProfileActivity;
import com.info.infomila.david.billarapp.activities.TorneigActivity;
import com.info.infomila.david.billarapp.adapters.TorneigObertAdapter;
import com.info.infomila.david.billarapp.adapters.TorneigOnParticipoAdapter;
import com.info.infomila.david.billarapp.listeners.TorneigOnParticipoClickListener;
import com.info.infomila.david.billarapp.network.TornejosObertsAsyncTask;
import com.info.infomila.david.billarapp.network.TornejosOnParticipoAsyncTask;

import java.util.List;

import info.infomila.billar.models.Soci;
import info.infomila.billar.models.Torneig;

public class TornejosOnParticipoFragment extends Fragment implements TorneigOnParticipoClickListener {
    private static final String SESSION_ID = "session_id";
    private static final String SOCI = "soci";

    private String sessionId;
    private Soci soci;
    private List<Torneig> tornejos;
    private RecyclerView rcvTornejosOnParticipo;
    private TorneigOnParticipoAdapter torneigOnParticipoAdapter;

    public TornejosOnParticipoFragment() {
        // Required empty public constructor
    }

    public static TornejosOnParticipoFragment newInstance(String sessionId, Soci soci) {
        TornejosOnParticipoFragment fragment = new TornejosOnParticipoFragment();
        Bundle args = new Bundle();
        args.putString(SESSION_ID, sessionId);
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
        View contenidorFragment = inflater.inflate(R.layout.fragment_tornejos_on_participo, container, false);

        rcvTornejosOnParticipo = contenidorFragment.findViewById(R.id.rcvTornejosOnParticipo);

        TornejosOnParticipoRequest();

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

    public void TornejosOnParticipoRequest() {
        TornejosOnParticipoAsyncTask connect = new TornejosOnParticipoAsyncTask(this);
        connect.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, sessionId);
    }

    public void OnTornejosOnParticipoReceived(List<Torneig> tornejos) {
        if (tornejos != null) this.tornejos = tornejos;
        if (rcvTornejosOnParticipo != null) {
            if(rcvTornejosOnParticipo.getAdapter()==null) {
                torneigOnParticipoAdapter = new TorneigOnParticipoAdapter(this.getContext(), tornejos, this);
                rcvTornejosOnParticipo.setLayoutManager(new LinearLayoutManager(this.getContext()));
                rcvTornejosOnParticipo.setAdapter(torneigOnParticipoAdapter);
            }
        }
    }

    @Override
    public void OnItemClick(Torneig selected) {
        Intent intent = new Intent(this.getActivity(), TorneigActivity.class);
        intent.putExtra(TorneigActivity.SOCI, soci);
        intent.putExtra(TorneigActivity.SESSION_ID, sessionId);
        intent.putExtra(TorneigActivity.TORNEIG, selected);
        this.startActivityForResult(intent, 2);
    }
}
