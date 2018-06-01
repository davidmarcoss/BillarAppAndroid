package com.info.infomila.david.billarapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.info.infomila.david.billarapp.R;
import com.info.infomila.david.billarapp.network.LoginAsyncTask;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import info.infomila.billar.models.Soci;

public class LoginActivity extends AppCompatActivity {

    public static final String USER_DATA = "data_user";

    private EditText etNif;
    private EditText etPassword;
    private Button btnLogin;
    private ProgressBar progressBar;

    public static SharedPreferences pref = null;
    public static SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getApplicationContext().getSharedPreferences(USER_DATA, 0);
        editor = pref.edit();

        etNif = findViewById(R.id.etNif);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);

        /*editor.remove("nif");
        editor.remove("password");
        editor.remove("session_id");
        editor.commit();*/

        String nif = pref.getString("nif", null);
        String password = pref.getString("password", null);
        String sessionId = pref.getString("session_id", null);

        if (nif != null && password != null && sessionId != null) {
            progressBar.setVisibility(View.VISIBLE);
            LoginRequest(nif, password);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                btnLogin.setEnabled(false);
                String nifEntrada = etNif.getText().toString();
                String passEntrada = getPasswordHash(etPassword.getText().toString());

                if (nifEntrada.length() > 0 && passEntrada.length() > 0) {
                    LoginRequest(nifEntrada, passEntrada);
                } else {
                    Toast.makeText(getBaseContext(), "Revisa el formulari", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void LoginRequest(String nif, String pass) {
        LoginAsyncTask connect = new LoginAsyncTask(this);
        connect.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, nif, pass);
    }

    public void OnLoginReceived(Soci s) {
        progressBar.setVisibility(View.INVISIBLE);

        if (s != null) {
            editor.putString("nif", s.getNif());
            editor.putString("password", s.getPasswordHash());
            editor.commit();

            Toast.makeText(getBaseContext(), "Login success", Toast.LENGTH_LONG).show();

            Intent mainActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
            mainActivityIntent.putExtra(MainActivity.SOCI, s);
            mainActivityIntent.putExtra(MainActivity.SESSION_ID, pref.getString("session_id", null));
            startActivity(mainActivityIntent);
        } else {
            Toast.makeText(getBaseContext(), "Login fail", Toast.LENGTH_LONG).show();
            btnLogin.setEnabled(true);
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
