package com.info.infomila.david.billarapp.network;

import android.os.AsyncTask;

import com.info.infomila.david.billarapp.activities.LoginActivity;
import com.info.infomila.david.billarapp.fragments.EstadistiquesFragment;

import info.infomila.billar.models.Soci;


public class EstadistiquesAsyncTask extends AsyncTask<String, String, Soci> {

    private EstadistiquesFragment estadistiquesFragment;
    private String sessionId;

    public EstadistiquesAsyncTask(EstadistiquesFragment estadistiquesFragment, String sessionId) {
        this.estadistiquesFragment = estadistiquesFragment;
        this.sessionId = sessionId;
    }

    @Override
    protected Soci doInBackground(String... sessionId) {
        return TCPClient.GetEstadistiques(this.sessionId);
    }

    @Override
    protected void onPostExecute(Soci s) {
        super.onPostExecute(s);
        estadistiquesFragment.OnEstadistiquesReceived(s);
    }
}
