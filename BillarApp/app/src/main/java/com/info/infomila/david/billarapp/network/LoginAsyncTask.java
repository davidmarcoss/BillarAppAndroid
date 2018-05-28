package com.info.infomila.david.billarapp.network;

import android.os.AsyncTask;

import com.info.infomila.david.billarapp.activities.LoginActivity;

import info.infomila.billar.models.Soci;


public class LoginAsyncTask extends AsyncTask<String, String, Soci> {

    private LoginActivity loginActivity;

    public LoginAsyncTask(LoginActivity loginActivity){ this.loginActivity = loginActivity; }

    @Override
    protected Soci doInBackground(String... loginString) {
        return TCPClient.Login(loginString[0],loginString[1]);
    }

    @Override
    protected void onPostExecute(Soci s) {
        super.onPostExecute(s);
        loginActivity.loginRequestReceived(s);
    }
}
