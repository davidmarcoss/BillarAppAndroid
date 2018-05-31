package com.info.infomila.david.billarapp.activities;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.info.infomila.david.billarapp.R;
import com.info.infomila.david.billarapp.adapters.EntradaAdapter;
import com.info.infomila.david.billarapp.db.HelperDB;
import com.info.infomila.david.billarapp.listeners.PartidaItemClickListener;
import com.info.infomila.david.billarapp.network.SendResultatPartidaAsyncTask;

import java.util.ArrayList;
import java.util.List;

import info.infomila.billar.models.EntradaDetall;
import info.infomila.billar.models.Partida;
import info.infomila.billar.models.Soci;
import info.infomila.billar.models.Torneig;

public class PartidaActivity extends AppCompatActivity {

    public static final String SESSION_ID = "session_id";
    public static final String SOCI = "soci";
    public static final String PARTIDA = "partida";

    private String sessionId;
    private Soci soci;
    private Partida partida;
    private Soci contrincant;

    private HelperDB helperDB;

    private EntradaAdapter entradaAdapter;
    private List<EntradaDetall> entrades = new ArrayList<>();
    private EntradaDetall entradaDetallA = null;
    private EntradaDetall entradaDetallB = null;
    private EntradaDetall dadesPartidaA = null;
    private EntradaDetall dadesPartidaB = null;
    private int torn = 0;

    private TextView tvSociNom;
    private TextView tvSociTag;
    private TextView tvCarambolesEnCurs;
    private TextView tvEntradaActualSociA;
    private TextView tvNomSociA;
    private TextView tvCarambolesSociA;
    private TextView tvEntradaActualSociB;
    private TextView tvNomSociB;
    private TextView tvCarambolesSociB;
    private Button btnRestarCaramboles;
    private Button btnSumarCaramboles;
    private Button btnCanviarTorn;
    private Button btnCancelarTorn;
    private Button btnAbandonar;
    private RecyclerView rcvDetallPartida;

    private String strGuanyador;
    private String strModeVictoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        helperDB = new HelperDB(this);

        if (getIntent().getExtras() != null) {
            sessionId = (String) getIntent().getExtras().get(SESSION_ID);
            soci = (Soci) getIntent().getExtras().get(SOCI);
            partida = (Partida) getIntent().getExtras().get(PARTIDA);

            boolean initContrincant = false;
            if (partida.getSociA().equals(soci)) {
                entradaDetallA = crearEntradaDetall(soci, 1);
                dadesPartidaA = crearEntradaDetall(soci, 1);
                entradaDetallB = crearEntradaDetall(contrincant, 0);
                entradaDetallB = crearEntradaDetall(contrincant, 0);
                contrincant = partida.getSociB();
            } else {
                contrincant = partida.getSociA();
                entradaDetallA = crearEntradaDetall(contrincant, 1);
                dadesPartidaA = crearEntradaDetall(contrincant, 1);
                entradaDetallB = crearEntradaDetall(soci, 0);
                dadesPartidaB = crearEntradaDetall(soci, 0);
            }
        }

        tvSociNom = findViewById(R.id.tvSociNom);
        tvSociTag = findViewById(R.id.tvSociTag);
        tvCarambolesEnCurs = findViewById(R.id.tvCarambolesEnCurs);
        tvEntradaActualSociA = findViewById(R.id.tvEntradaActualSociA);
        tvNomSociA = findViewById(R.id.tvNomSociA);
        tvCarambolesSociA = findViewById(R.id.tvCarambolesSociA);
        tvEntradaActualSociB = findViewById(R.id.tvEntradaActualSociB);
        tvNomSociB = findViewById(R.id.tvNomSociB);
        tvCarambolesSociB = findViewById(R.id.tvCarambolesSociB);
        btnRestarCaramboles = findViewById(R.id.btnRestarCaramboles);
        btnSumarCaramboles = findViewById(R.id.btnSumarCaramboles);
        btnCanviarTorn = findViewById(R.id.btnCanviarTorn);
        btnCancelarTorn = findViewById(R.id.btnCancelarTorn);
        btnCancelarTorn.setEnabled(false);
        btnAbandonar = findViewById(R.id.btnAbandonar);

        rcvDetallPartida = findViewById(R.id.rcvDetallPartida);
        if (rcvDetallPartida != null) {
            entradaAdapter = new EntradaAdapter(this, entrades);
            rcvDetallPartida.setLayoutManager(new LinearLayoutManager(this));
            rcvDetallPartida.setAdapter(entradaAdapter);
        }

        btnSumarCaramboles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifyCarambolesEnCurs(1);
            }
        });

        btnRestarCaramboles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifyCarambolesEnCurs(-1);
            }
        });

        btnCanviarTorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                canviarTorn();
            }
        });

        btnAbandonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        populateTorn();
    }

    private void showConfirmDialog() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Sortir")
                .setMessage("Estas segur que vols cancelar de la partida en curs?")
                .setPositiveButton("Abortar partida", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: BORRAR CANVIS DE LA BASE DE DADES
                        finish();
                    }
                })
                .setNegativeButton("Cancel·lar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private String getSociTag(Soci soci) {
        return partida.getSociA().equals(soci) ? "A" : "B";
    }

    private EntradaDetall crearEntradaDetall(Soci soci, int entrada) {
        return new EntradaDetall(partida.getId(), entrada, soci.getId(), getSociTag(soci), soci.getNomComplet(), 0);
    }

    private void populateTorn() {
        if (torn % 2 == 0) {
            tvSociNom.setText(entradaDetallA.getSociNom());
            tvSociTag.setText(entradaDetallA.getSociTag());

        } else {
            tvSociNom.setText(entradaDetallB.getSociNom());
            tvSociTag.setText(entradaDetallB.getSociTag());
        }

        tvCarambolesEnCurs.setText("0");

        entradaAdapter.refreshEntrades(entrades);

        tvEntradaActualSociA.setText(dadesPartidaA.getEntrada() + "");
        tvNomSociA.setText(dadesPartidaA.getSociNom());
        tvCarambolesSociA.setText(dadesPartidaA.getCaramboles() + "");
        tvEntradaActualSociB.setText(dadesPartidaB.getEntrada() + "");
        tvNomSociB.setText(dadesPartidaB.getSociNom());
        tvCarambolesSociB.setText(dadesPartidaB.getCaramboles() + "");
    }

    private void modifyCarambolesEnCurs(int i) {
        int caramboles = Integer.parseInt(tvCarambolesEnCurs.getText().toString());
        caramboles += i;
        if (caramboles < 0) caramboles = 0;
        if (caramboles > partida.getGrup().getCarambolesVictoria())
            caramboles = partida.getGrup().getCarambolesVictoria();

        if (torn % 2 == 0) {
            entradaDetallA.setCaramboles(caramboles);
        } else {
            entradaDetallB.setCaramboles(caramboles);
        }

        tvCarambolesEnCurs.setText(caramboles + "");
    }

    private void canviarTorn() {
        int lastId = helperDB.insertDetall(torn % 2 == 0 ? entradaDetallA : entradaDetallB);

        Partida.Guanyador guanyador = null;
        Partida.ModeVictoria modeVictoria = null;
        if (lastId > 0) {
            actualitzarTotals();
            if (torn % 2 == 0) {
                entrades.add(new EntradaDetall(entradaDetallA));
                entradaDetallA.setId(lastId);
                entradaDetallA.setCaramboles(0);
                entradaDetallB.setEntrada(entradaDetallB.getEntrada() + 1);
                if (dadesPartidaA.getCaramboles() >= partida.getGrup().getCarambolesVictoria()) {
                    guanyador = Partida.Guanyador.A;
                    modeVictoria = Partida.ModeVictoria.PER_CARAMBOLES;
                }
            } else {
                entrades.add(new EntradaDetall(entradaDetallB));
                entradaDetallB.setId(lastId);
                entradaDetallB.setCaramboles(0);
                entradaDetallA.setEntrada(entradaDetallA.getEntrada() + 1);
                if (dadesPartidaB.getCaramboles() >= partida.getGrup().getCarambolesVictoria()) {
                    guanyador = Partida.Guanyador.B;
                    modeVictoria = Partida.ModeVictoria.PER_CARAMBOLES;
                }
            }
            torn++;
            btnCanviarTorn.setEnabled(true);
            if (torn / 2 >= partida.getGrup().getLimitEntrades()) {
                if (entradaDetallA.getCaramboles() != entradaDetallB.getCaramboles()) {
                    guanyador = dadesPartidaA.getCaramboles() > dadesPartidaB.getCaramboles() ? Partida.Guanyador.A : Partida.Guanyador.B;
                    modeVictoria = Partida.ModeVictoria.ENTRADES_ASSOLIDES;
                    btnCanviarTorn.setEnabled(false);
                }
            }
            populateTorn();
            entradaAdapter.refreshEntrades(entrades);

            if (guanyador != null) {
                finalitzarPartida(guanyador, modeVictoria);
            }
        }
    }

    private void finalitzarPartida(Partida.Guanyador guanyador, Partida.ModeVictoria modeVictoria) {
        strGuanyador = guanyador == Partida.Guanyador.A ? partida.getSociA().getNomComplet() : partida.getSociB().getNomComplet();

        switch (modeVictoria) {
            case PER_CARAMBOLES: {
                strModeVictoria = "Per caramboles";
                break;
            }
            case ENTRADES_ASSOLIDES: {
                strModeVictoria = "Entrades assolides";
                break;
            }
            case ABANDONAMENT: {
                strModeVictoria = "Abandonament";
                break;
            }
        }

        Partida pAux = new Partida(partida);
        pAux.setNumEntradesA(dadesPartidaA.getEntrada());
        pAux.setNumEntradesB(dadesPartidaB.getEntrada());
        pAux.setCarambolesA(dadesPartidaA.getCaramboles());
        pAux.setCarambolesB(dadesPartidaB.getCaramboles());
        pAux.setGuanyador(guanyador);
        pAux.setModeVictoria(modeVictoria);
        pAux.setEstatPartida(Partida.EstatPartida.JUGAT);

        SendResultatPartidaAsyncTask connect = new SendResultatPartidaAsyncTask(this, pAux);
        connect.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, sessionId);
    }

    private void actualitzarTotals() {
        dadesPartidaA.setEntrada(entradaDetallA.getEntrada());
        dadesPartidaB.setEntrada(entradaDetallB.getEntrada());
        dadesPartidaA.setCaramboles(dadesPartidaA.getCaramboles() + entradaDetallA.getCaramboles());
        dadesPartidaB.setCaramboles(dadesPartidaB.getCaramboles() + entradaDetallB.getCaramboles());
    }

    private void SendResultPartidaRequest() {
        SendResultatPartidaAsyncTask sendResultatPartidaAsyncTask = new SendResultatPartidaAsyncTask(this, partida);
        sendResultatPartidaAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public void SendResultPartidaResponse(Boolean status) {
        if (status) {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setTitle("Sortir")
                    .setMessage("Ha guanyat el soci " + strGuanyador + " per " + strModeVictoria)
                    .setPositiveButton("Acceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
        } else {
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setTitle("Error")
                    .setMessage("Hi ha hagut un error al guardar la partida")
                    .setPositiveButton("Acceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
        }
    }
}
