package com.info.infomila.david.billarapp.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import com.info.infomila.david.billarapp.R;
import com.info.infomila.david.billarapp.network.TornejosObertsAsyncTask;
import com.info.infomila.david.billarapp.network.UpdateProfileAsyncTask;

import info.infomila.billar.models.Soci;

public class ProfileActivity extends AppCompatActivity {

    public static final String SOCI = "Soci";
    public static final String SESSION_ID = "session_id";

    private Soci soci;
    private String sessionId;

    private Button btnGuardar;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("Perfil");

        soci = (Soci) getIntent().getExtras().get(SOCI);
        sessionId = (String) getIntent().getExtras().get(SESSION_ID);

        btnGuardar = findViewById(R.id.btnGuardar);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    public void ProfileUpdateRequest() {
        UpdateProfileAsyncTask updateProfileAsyncTask = new UpdateProfileAsyncTask(this, soci, sessionId);
        updateProfileAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void OnProfileUpdatedReceived(Boolean updated) {
        if (updated) {

        } else {

        }
    }
}
