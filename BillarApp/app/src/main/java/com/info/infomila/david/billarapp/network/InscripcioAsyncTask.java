package com.info.infomila.david.billarapp.network;

import android.os.AsyncTask;

import com.info.infomila.david.billarapp.adapters.TorneigObertAdapter;
import com.info.infomila.david.billarapp.fragments.TornejosObertsFragment;

import java.util.List;

import info.infomila.billar.models.Torneig;


public class InscripcioAsyncTask extends AsyncTask<String, String, Boolean> {

    private TorneigObertAdapter torneigObertAdapter;

    public InscripcioAsyncTask(TorneigObertAdapter torneigObertAdapter){ this.torneigObertAdapter = torneigObertAdapter; }

    @Override
    protected Boolean doInBackground(String... params) {
        return TCPClient.ferInscripcio(params[0], Integer.valueOf(params[1]));
    }

    @Override
    protected void onPostExecute(Boolean inscrit) {
        super.onPostExecute(inscrit);
        torneigObertAdapter.OnInscripcioTorneigReceived(inscrit);
    }
}
