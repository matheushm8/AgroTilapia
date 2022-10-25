package com.example.agrotilapia.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDataHelper extends SQLiteOpenHelper {

    public SQLiteDataHelper(@Nullable Context context,
                            @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory,
                            int version) {
        super(context, name, factory, version);
    }

    //Esse método é executado uma única vez
    //quando é instalado o aplicativo no celular
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE ESPECIE" +
                "(CODIGO INTEGER, DESCRICAO VARCHAR(100))");

        sqLiteDatabase.execSQL("CREATE TABLE ACUDE" +
                "(CODIGO INTEGER, QTD_PEIXE INTEGER, ID_PROPRIEDADE INTEGER, NOME VARCHAR(20), ID_ESPECIE INTEGER)");

        sqLiteDatabase.execSQL("CREATE TABLE PROPRIEDADE" +
                "(CODIGO INTEGER, NOME VARCHAR(20))");

        String[] especies = {"Tilápia", "Pacu", "Tambaqui", "Carpa", "Pintado"};
        sqLiteDatabase.execSQL("INSERT INTO ESPECIE (CODIGO, DESCRICAO) " +
                "VALUES (1, 'Tilápia'), (2, 'Pacu'), (3, 'Tambaqui1'), (4, 'Carpa'), (5, 'Pintado')");
    }

    //Esse método é executado quando é atualizado a versão
    // do aplicativo
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                          int oldVersion, int newVersion) {

    }
}