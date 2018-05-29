package com.info.infomila.david.billarapp.network;

import android.os.AsyncTask;

import com.info.infomila.david.billarapp.fragments.TornejosObertsFragment;

import java.util.List;

import info.infomila.billar.models.Torneig;


public class TornejosObertsAsyncTask extends AsyncTask<String, String, List<Torneig>> {

    private TornejosObertsFragment tornejosObertsFragment;

    public TornejosObertsAsyncTask(TornejosObertsFragment tornejosObertsFragment){ this.tornejosObertsFragment = tornejosObertsFragment; }

    @Override
    protected List<Torneig> doInBackground(String... sessionid) {
        return TCPClient.getTornejosOberts(sessionid[0]);
    }

    @Override
    protected void onPostExecute(List<Torneig> tornejos) {
        super.onPostExecute(tornejos);
        tornejosObertsFragment.OnTornejosObertsReceived(tornejos);
    }
}
