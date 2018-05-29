package com.info.infomila.david.billarapp.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.info.infomila.david.billarapp.R;
import com.info.infomila.david.billarapp.network.TornejosObertsAsyncTask;
import com.info.infomila.david.billarapp.network.UpdateProfileAsyncTask;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import info.infomila.billar.models.Soci;

public class ProfileActivity extends AppCompatActivity {

    public static final String SOCI = "Soci";
    public static final String SESSION_ID = "session_id";

    private Soci soci;
    private String sessionId;

    private EditText etNif;
    private EditText etNom;
    private EditText etCognom1;
    private EditText etCognom2;
    private EditText etPassword;
    private Button btnGuardar;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_left_arrow);
        toolbar.setTitle("Perfil");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        soci = (Soci) getIntent().getExtras().get(SOCI);
        sessionId = (String) getIntent().getExtras().get(SESSION_ID);

        etNif = findViewById(R.id.etNif);
        etNom = findViewById(R.id.etNom);
        etCognom1 = findViewById(R.id.etCognom1);
        etCognom2 = findViewById(R.id.etCognom2);
        etPassword = findViewById(R.id.etPassword);
        btnGuardar = findViewById(R.id.btnGuardar);

        etNif.setText(soci.getNif());
        etNom.setText(soci.getNom());
        etCognom1.setText(soci.getCognom1() != null ? soci.getCognom1() : "");
        etCognom2.setText(soci.getCognom2() != null ? soci.getCognom2() : "");

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nif = etNif.getText().toString();
                String nom = etNom.getText().toString();
                String cognom1 = etCognom1.getText().toString();
                String cognom2 = etCognom2.getText().toString();
                String password = etPassword.getText().toString();
                if (password.length() > 0) {
                    password = getPasswordHash(password);
                }

                if(nif != null && nom !=null && password != null) {
                    soci.setNif(nif);
                    soci.setNom(nom);
                    soci.setCognom1(cognom1);
                    soci.setCognom2(cognom2);
                    if (password.length() > 0) {
                        soci.setPasswordHash(password);
                    }
                    ProfileUpdateRequest();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.SOCI, soci);
        intent.putExtra(MainActivity.SESSION_ID, sessionId);
        setResult(1, intent);
        finish();
    }

    public void ProfileUpdateRequest() {
        UpdateProfileAsyncTask updateProfileAsyncTask = new UpdateProfileAsyncTask(this, soci, sessionId);
        updateProfileAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void OnProfileUpdatedReceived(Boolean updated) {
        if (updated) {
            Log.d("actualizado", updated+"");
        } else {

        }
    }

    public static String getPasswordHash(String pass) {
        String hash = "";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(pass.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte aByteData : byteData) {
                sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
            }

            hash = sb.toString();
        } catch (NoSuchAlgorithmException ignored) {
        }

        return hash;
    }
}
