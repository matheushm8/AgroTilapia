package com.example.agrotilapia.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.agrotilapia.helper.SQLiteDataHelper;
import com.example.agrotilapia.models.Propriedade;

import java.util.ArrayList;

public class PropriedadeDao implements GenericDao<Propriedade>{
    //Abre a conexão com a BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase db;

    //nome das colunas da tabela
    private String[]colunas = {"CODIGO", "NOME"};

    //Nome da Tabela
    private String tableName = "PROPRIEDADE";

    //Contexto no qual o DAO foi chamado
    private Context context;

    private static PropriedadeDao instancia;

    public static PropriedadeDao getInstancia(Context context){
        if(instancia == null)
            return instancia = new PropriedadeDao(context);
        else
            return instancia;

        //return (instancia == null ? new AlunoDao(context) : instancia);
    }

    //Construtor
    private PropriedadeDao(Context context) {
        this.context = context;

        //Abrir a conexão com BD
        openHelper = new SQLiteDataHelper(this.context,
                "UNIPAR", null, 1);

        db = openHelper.getWritableDatabase();
    }

    public int retornaProximoCodigo() {
        int codigo;
        Cursor cursor = db.query(tableName, colunas, null, null, null, null, "CODIGO desc");
        try {
            cursor.moveToFirst();
            codigo = cursor.getInt(0) + 1;
        } catch (Exception E) {
            codigo = 1;
        }
        return codigo;
    }

    @Override
    public long insert(Propriedade obj) {

        ContentValues valores = new ContentValues();
        valores.put("CODIGO", obj.getCodigo());
        valores.put("NOME", obj.getNome());

        return db.insert(tableName, null, valores);
    }

    @Override
    public long update(Propriedade obj) {
        ContentValues valores = new ContentValues();
        valores.put("CODIGO", obj.getCodigo());
        valores.put("NOME", obj.getNome());

        String[]identificador = {String.valueOf(obj.getCodigo())};
        return db.update(tableName, valores,
                "CODIGO = ?", identificador);
    }

    @Override
    public long delete(Propriedade obj) {
        String[]identificador = {String.valueOf(obj.getCodigo())};
        return db.delete(tableName,"CODIGO = ?",identificador);
    }

    @Override
    public ArrayList<Propriedade> getAll(int codigoPropriedade) {
        ArrayList<Propriedade> listaPropriedade = new ArrayList();

        Cursor cursor = db.query(tableName, colunas,
                null, null, null, null,
                "CODIGO asc");
        if(cursor.moveToFirst()){
            do{
                Propriedade propriedade = new Propriedade();
                propriedade.setCodigo(cursor.getInt(0));
                propriedade.setNome(cursor.getString(1));

                listaPropriedade.add(propriedade);
            }while(cursor.moveToNext());
        }

        return listaPropriedade;
    }

    @Override
    public Propriedade getByCodigo(int codigo) {
        String[]identificador = {String.valueOf(codigo)};

        Cursor cursor = db.query(tableName, colunas,
                "CODIGO = ?", identificador,
                null, null, null);
        if(cursor.moveToFirst()){
            Propriedade propriedade = new Propriedade();
            propriedade.setCodigo(cursor.getInt(0));
            propriedade.setNome(cursor.getString(1));

            return propriedade;
        }

        return null;
    }

    public Propriedade getByName(String nomePropriedade) {
        String[]identificador = {nomePropriedade};

        Cursor cursor = db.query(tableName, colunas,
                "NOME = ?", identificador,
                null, null, null);
        if(cursor.moveToFirst()){
            Propriedade propriedade = new Propriedade();
            propriedade.setCodigo(cursor.getInt(0));
            propriedade.setNome(cursor.getString(1));

            return propriedade;
        }

        return null;
    }
}
