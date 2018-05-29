package com.info.infomila.david.billarapp.network;

import android.os.AsyncTask;

import com.info.infomila.david.billarapp.activities.ProfileActivity;
import com.info.infomila.david.billarapp.adapters.TorneigObertAdapter;

import info.infomila.billar.models.Soci;


public class UpdateProfileAsyncTask extends AsyncTask<String, String, Boolean> {

    private ProfileActivity profileActivity;
    private Soci soci;
    private String sessionId;

    public UpdateProfileAsyncTask(ProfileActivity profileActivity, Soci soci, String sessionId){
        this.profileActivity = profileActivity;
        this.soci = soci;
        this.sessionId = sessionId;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        return TCPClient.updateSoci(this.soci, this.sessionId);
    }

    @Override
    protected void onPostExecute(Boolean updated) {
        super.onPostExecute(updated);
        profileActivity.OnProfileUpdatedReceived(updated);
    }
}
