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
import android.widget.LinearLayout;

import com.info.infomila.david.billarapp.R;
import com.info.infomila.david.billarapp.adapters.EstadisticaAdapter;
import com.info.infomila.david.billarapp.adapters.TorneigObertAdapter;
import com.info.infomila.david.billarapp.network.LoginAsyncTask;
import com.info.infomila.david.billarapp.network.TornejosObertsAsyncTask;

import java.util.List;

import info.infomila.billar.models.EstadisticaModalitat;
import info.infomila.billar.models.Soci;
import info.infomila.billar.models.Torneig;

public class TornejosObertsFragment extends Fragment {
    private static final String SESSION_ID = "session_id";
    private static final String SOCI = "soci";

    private List<Torneig> tornejos;
    private String sessionId;
    private Soci soci;
    private RecyclerView rcvTornejosOberts;
    private TorneigObertAdapter torneigObertAdapter;

    public TornejosObertsFragment() {
        // Required empty public constructor
    }

    public static TornejosObertsFragment newInstance(String sessionId, Soci soci) {
        TornejosObertsFragment fragment = new TornejosObertsFragment();
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
        View contenidorFragment = inflater.inflate(R.layout.fragment_tornejos_oberts, container, false);

        rcvTornejosOberts = contenidorFragment.findViewById(R.id.rcvTornejosOberts);

        TornejosObertsRequest();

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

    public void TornejosObertsRequest() {
        TornejosObertsAsyncTask tornejosObertsAsyncTask = new TornejosObertsAsyncTask(this);
        tornejosObertsAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, sessionId);
    }

    public void OnTornejosObertsReceived(List<Torneig> tornejos) {
        if (tornejos != null) this.tornejos = tornejos;
        if (rcvTornejosOberts != null) {
            if(rcvTornejosOberts.getAdapter()==null) {
                torneigObertAdapter = new TorneigObertAdapter(this.getContext(), tornejos, sessionId);
                rcvTornejosOberts.setLayoutManager(new LinearLayoutManager(this.getContext()));
                rcvTornejosOberts.setAdapter(torneigObertAdapter);
            }
        }
    }

}
