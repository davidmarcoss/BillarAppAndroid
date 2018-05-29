package com.info.infomila.david.billarapp.network;

import android.os.AsyncTask;

import com.info.infomila.david.billarapp.fragments.TornejosObertsFragment;
import com.info.infomila.david.billarapp.fragments.TornejosOnParticipoFragment;

import java.util.List;

import info.infomila.billar.models.Torneig;


public class TornejosOnParticipoAsyncTask extends AsyncTask<String, String, List<Torneig>> {

    private TornejosOnParticipoFragment tornejosOnParticipoFragment;

    public TornejosOnParticipoAsyncTask(TornejosOnParticipoFragment tornejosOnParticipoFragment){ this.tornejosOnParticipoFragment = tornejosOnParticipoFragment; }

    @Override
    protected List<Torneig> doInBackground(String... sessionid) {
        return TCPClient.getTornejosOnParticipo(sessionid[0]);
    }

    @Override
    protected void onPostExecute(List<Torneig> tornejos) {
        super.onPostExecute(tornejos);
        tornejosOnParticipoFragment.OnTornejosOnParticipoReceived(tornejos);
    }
}
