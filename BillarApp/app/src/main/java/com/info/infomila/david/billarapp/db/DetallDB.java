package com.info.infomila.david.billarapp.db;

import android.provider.BaseColumns;

public class DetallDB {

    public DetallDB(){}

    public static class PartidaDetall implements BaseColumns {
        public static final String TABLE_NAME = "detall_partida";
        public static final String COLUMN_NAME_PARTIDA_ID = "partida_id";
        public static final String COLUMN_NAME_NUM_ENTRADA = "entrada";
        public static final String COLUMN_NAME_DATA_ENTRADA = "data_entrada";
        public static final String COLUMN_NAME_ID_SOCI = "soci_id";
        public static final String COLUMN_NAME_NOM_SOCI = "soci_nom";
        public static final String COLUMN_NAME_TAG_SOCI = "soci_tag";
        public static final String COLUMN_NAME_NUM_CARAMBOLES = "caramboles";
    }

    public static final String PARTIDA_DETALL_CREATE_ENTRIES =
            "CREATE TABLE " + PartidaDetall.TABLE_NAME + " (" +
                    PartidaDetall._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    PartidaDetall.COLUMN_NAME_PARTIDA_ID + " INTEGER," +
                    PartidaDetall.COLUMN_NAME_NUM_ENTRADA + " INTEGER," +
                    PartidaDetall.COLUMN_NAME_DATA_ENTRADA + " DATETIME," +
                    PartidaDetall.COLUMN_NAME_ID_SOCI + " TEXT," +
                    PartidaDetall.COLUMN_NAME_NOM_SOCI + " TEXT," +
                    PartidaDetall.COLUMN_NAME_TAG_SOCI + " TEXT," +
                    PartidaDetall.COLUMN_NAME_NUM_CARAMBOLES + " INTEGER );";

    public static final String DETALL_PARTIDA_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PartidaDetall.TABLE_NAME;

}
