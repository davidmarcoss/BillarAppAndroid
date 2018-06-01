package com.info.infomila.david.billarapp.network;

import android.os.AsyncTask;

import com.info.infomila.david.billarapp.activities.PartidaActivity;

import info.infomila.billar.models.Partida;

public class SendResultatPartidaAsyncTask extends AsyncTask<String, String, Boolean> {

    private PartidaActivity partidaActivity;
    private String sessionId;
    private Partida partida;
    private int sociAId;
    private int sociBId;

    public SendResultatPartidaAsyncTask(PartidaActivity partidaActivity, String sessionId,  Partida partida, int sociAId, int sociBId) {
        this.partidaActivity = partidaActivity;
        this.sessionId = sessionId;
        this.partida = partida;
        this.sociAId = sociAId;
        this.sociBId = sociBId;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        return TCPClient.SendResultatPartida(sessionId, partida, sociAId, sociBId);
    }

    @Override
    protected void onPostExecute(Boolean status) {
        super.onPostExecute(status);
        partidaActivity.SendResultPartidaResponse(status);
    }
}