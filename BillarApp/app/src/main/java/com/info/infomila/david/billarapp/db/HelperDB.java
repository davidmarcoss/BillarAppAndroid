package com.info.infomila.david.billarapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import info.infomila.billar.models.EntradaDetall;
import info.infomila.billar.models.Soci;

public class HelperDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "detallSoci1.db";
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public HelperDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(DetallDB.PARTIDA_DETALL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DetallDB.DETALL_PARTIDA_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public int insertDetall(EntradaDetall dt) {

        dt.setDataEntrada(new Date());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DetallDB.PartidaDetall.COLUMN_NAME_PARTIDA_ID, dt.getPartidaId());
        values.put(DetallDB.PartidaDetall.COLUMN_NAME_NUM_ENTRADA, dt.getEntrada());
        values.put(DetallDB.PartidaDetall.COLUMN_NAME_DATA_ENTRADA, dateFormat.format(dt.getDataEntrada()));
        values.put(DetallDB.PartidaDetall.COLUMN_NAME_ID_SOCI, dt.getSociId());
        values.put(DetallDB.PartidaDetall.COLUMN_NAME_NOM_SOCI, dt.getSociNom());
        values.put(DetallDB.PartidaDetall.COLUMN_NAME_TAG_SOCI, dt.getSociTag());
        values.put(DetallDB.PartidaDetall.COLUMN_NAME_NUM_CARAMBOLES, dt.getCaramboles());

        return (int) db.insert(DetallDB.PartidaDetall.TABLE_NAME, null, values);
    }

    public List<EntradaDetall> selectAllDetallEntrades(String pIDPartida) {
        SQLiteDatabase db = this.getReadableDatabase();

        List<EntradaDetall> llEntrades = new ArrayList<EntradaDetall>();

        if (pIDPartida == null || pIDPartida.length() == 0 ){
            return llEntrades;
        }

        String[] projection = {
                DetallDB.PartidaDetall._ID,
                DetallDB.PartidaDetall.COLUMN_NAME_PARTIDA_ID,
                DetallDB.PartidaDetall.COLUMN_NAME_NUM_ENTRADA,
                DetallDB.PartidaDetall.COLUMN_NAME_ID_SOCI,
                DetallDB.PartidaDetall.COLUMN_NAME_NOM_SOCI,
                DetallDB.PartidaDetall.COLUMN_NAME_TAG_SOCI,
                DetallDB.PartidaDetall.COLUMN_NAME_NUM_CARAMBOLES
        };

        String selection = DetallDB.PartidaDetall.COLUMN_NAME_PARTIDA_ID + " = ?";
        String[] selectionArgs = { pIDPartida };

        Cursor cursor = db.query(
                DetallDB.PartidaDetall.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                "num_entrada ASC"
        );

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DetallDB.PartidaDetall._ID));
            int partidaId = cursor.getInt(cursor.getColumnIndexOrThrow(DetallDB.PartidaDetall.COLUMN_NAME_PARTIDA_ID));
            int entrada = cursor.getInt(cursor.getColumnIndexOrThrow(DetallDB.PartidaDetall.COLUMN_NAME_NUM_ENTRADA));
            String strDataEntrada = cursor.getString(cursor.getColumnIndexOrThrow(DetallDB.PartidaDetall.COLUMN_NAME_DATA_ENTRADA));
            Date dataEntrada = null;
            try {
                dataEntrada = dateFormat.parse(strDataEntrada);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int sociId = cursor.getInt(cursor.getColumnIndexOrThrow(DetallDB.PartidaDetall.COLUMN_NAME_ID_SOCI));
            String sociNom = cursor.getString(cursor.getColumnIndexOrThrow(DetallDB.PartidaDetall.COLUMN_NAME_NOM_SOCI));
            String sociTag = cursor.getString(cursor.getColumnIndexOrThrow(DetallDB.PartidaDetall.COLUMN_NAME_TAG_SOCI));
            int caramboles = cursor.getInt(cursor.getColumnIndexOrThrow(DetallDB.PartidaDetall.COLUMN_NAME_NUM_CARAMBOLES));
            EntradaDetall detall = new EntradaDetall(id, partidaId, entrada, dataEntrada, sociId, sociTag, sociNom, caramboles);
            llEntrades.add(detall);
        }

        return llEntrades;
    }

    public void delEntradaDetall(String partidaId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String selection = DetallDB.PartidaDetall.COLUMN_NAME_PARTIDA_ID + " LIKE ?";
        String[] selectionArgs = { ""+partidaId};
        db.delete(DetallDB.PartidaDetall.TABLE_NAME, selection, selectionArgs);
    }
}
