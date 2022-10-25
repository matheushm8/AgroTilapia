package com.example.agrotilapia.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.agrotilapia.helper.SQLiteDataHelper;
import com.example.agrotilapia.models.Especie;

import java.util.ArrayList;

public class EspecieDao implements GenericDao<Especie> {
    //Abre a conexão com a BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase db;

    //nome das colunas da tabela
    private String[]colunas = {"CODIGO, DESCRICAO"};

    //Nome da Tabela
    private String tableName = "ESPECIE";

    //Contexto no qual o DAO foi chamado
    private Context context;

    private static EspecieDao instancia;

    public static EspecieDao getInstancia(Context context){
        if(instancia == null)
            return instancia = new EspecieDao(context);
        else
            return instancia;

        //return (instancia == null ? new AlunoDao(context) : instancia);
    }

    @Override
    public int retornaProximoCodigo() {
        int codigo;
        Cursor cursor = db.query(tableName, colunas, null, null, null, null, "CODIGO desc");
        try {
            codigo = cursor.getInt(0);
        } catch (Exception E) {
            codigo = 1;
        }
        return codigo + 1;
    }

    //Construtor
    private EspecieDao(Context context) {
        this.context = context;

        //Abrir a conexão com BD
        openHelper = new SQLiteDataHelper(this.context,
                "UNIPAR", null, 1);

        db = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Especie obj) {

        ContentValues valores = new ContentValues();
        valores.put("DESCRICAO", obj.getDescricao());


        return db.insert(tableName, null, valores);
    }

    @Override
    public long update(Especie obj) {
        ContentValues valores = new ContentValues();
        valores.put("DESCRICAO", obj.getDescricao());

        String[]identificador = {String.valueOf(obj)};
        return db.update(tableName, valores,
                "ID = ?", identificador);
    }

    @Override
    public long delete(Especie obj) {
        String[]identificador = {String.valueOf(obj)};
        return db.delete(tableName,"ID= ?",identificador);
    }

    @Override
    public ArrayList<Especie> getAll(int codigoPropriedade) {
        ArrayList<Especie> listaEspecie = new ArrayList();

        Cursor cursor = db.query(tableName, colunas,
                null, null, null, null,
                "ID asc");
        if(cursor.moveToFirst()){
            do{
                Especie especie = new Especie();
                especie.setDescricao(cursor.getString(0));

                listaEspecie.add(especie);
            }while(cursor.moveToNext());
        }

        return listaEspecie;
    }

    @Override
    public Especie getByCodigo(int codigo) {
        String[]identificador = {String.valueOf(codigo)};

        Cursor cursor = db.query(tableName, colunas,
                "CODIGO = ?", identificador,
                null, null, null);
        if(cursor.moveToFirst()){
            Especie especie = new Especie();
            especie.setCodigo(cursor.getInt(0));
            especie.setDescricao(cursor.getString(1));

            return especie;
        }

        return null;
    }

    public Especie getByNome(String nomeEspecie) {
        String[]identificador = {nomeEspecie};

        Cursor cursor = db.query(tableName, colunas,
                "NOME = ?", identificador,
                null, null, null);
        if(cursor.moveToFirst()){
            Especie especie = new Especie();
            especie.setDescricao(cursor.getString(0));

            return especie;
        }

        return null;
    }
}
