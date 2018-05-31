package com.info.infomila.david.billarapp.network;

import android.os.AsyncTask;

import com.info.infomila.david.billarapp.adapters.TorneigObertAdapter;
import com.info.infomila.david.billarapp.fragments.TorneigPartidesFragment;

import java.util.List;

import info.infomila.billar.models.Partida;
import info.infomila.billar.models.Torneig;


public class TorneigPartidesAsyncTask extends AsyncTask<String, String, List<Partida>> {

    private TorneigPartidesFragment torneigPartidesFragment;
    private String sessionId;
    private Torneig torneig;

    public TorneigPartidesAsyncTask(TorneigPartidesFragment torneigPartidesFragment, String sessionId, Torneig torneig){
        this.torneigPartidesFragment = torneigPartidesFragment;
        this.sessionId = sessionId;
        this.torneig = torneig;
    }

    @Override
    protected List<Partida> doInBackground(String... params) {
        return TCPClient.getPartides(sessionId, torneig.getId());
    }

    @Override
    protected void onPostExecute(List<Partida> partides) {
        super.onPostExecute(partides);
        torneigPartidesFragment.OnPartidesReceived(partides);
    }
}
