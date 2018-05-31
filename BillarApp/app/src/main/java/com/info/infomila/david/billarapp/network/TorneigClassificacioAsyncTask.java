package com.info.infomila.david.billarapp.network;

import android.os.AsyncTask;

import com.info.infomila.david.billarapp.adapters.TorneigObertAdapter;
import com.info.infomila.david.billarapp.fragments.TorneigClassificacioFragment;

import java.util.List;

import info.infomila.billar.models.Classificacio;
import info.infomila.billar.models.Soci;
import info.infomila.billar.models.Torneig;

public class TorneigClassificacioAsyncTask extends AsyncTask<String, String, List<Classificacio>> {
    private TorneigClassificacioFragment torneigClassificacioFragment;
    private Soci soci;
    private String sessionId;
    private Torneig torneig;

    public TorneigClassificacioAsyncTask(TorneigClassificacioFragment torneigClassificacioFragment, String sessionId, Soci soci, Torneig torneig){
        this.torneigClassificacioFragment = torneigClassificacioFragment;
        this.soci = soci;
        this.sessionId = sessionId;
        this.torneig = torneig;
    }

    @Override
    protected List<Classificacio> doInBackground(String... params) {
        return TCPClient.getClassificacio(sessionId, soci.getId(), torneig.getId(), torneig.getModalitat().getId());
    }

    @Override
    protected void onPostExecute(List<Classificacio> classificacions) {
        super.onPostExecute(classificacions);
        torneigClassificacioFragment.OnClassificacioReceived(classificacions);
    }
}
