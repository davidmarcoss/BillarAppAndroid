package com.info.infomila.david.billarapp.network;

import android.os.AsyncTask;

import com.info.infomila.david.billarapp.activities.PartidaActivity;

import info.infomila.billar.models.Partida;

public class SendResultatPartidaAsyncTask extends AsyncTask<String, String, Boolean> {

    private PartidaActivity partidaActivity;
    private Partida mPartida;

    public SendResultatPartidaAsyncTask(PartidaActivity partidaActivity, Partida pPartida) {
        partidaActivity = partidaActivity;
        mPartida = pPartida;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        return TCPClient.SendResultatPartida(params[0],mPartida);
    }

    @Override
    protected void onPostExecute(Boolean status) {
        super.onPostExecute(status);
        partidaActivity.SendResultPartidaResponse(status);
    }
}