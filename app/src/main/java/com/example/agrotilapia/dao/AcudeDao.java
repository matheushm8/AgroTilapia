package com.example.agrotilapia.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.agrotilapia.helper.SQLiteDataHelper;
import com.example.agrotilapia.models.Acude;
import com.example.agrotilapia.models.Propriedade;
import com.example.agrotilapia.singleton.LoginSingleton;

import java.util.ArrayList;
import java.util.Objects;

public class AcudeDao implements GenericDao<Acude>{

    //Abre a conexão com a BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase db;

    //nome das colunas da tabela
    private String[]colunas = {"CODIGO, QTD_PEIXE, ID_PROPRIEDADE, NOME, ID_ESPECIE"};

    //Nome da Tabela
    private String tableName = "ACUDE";

    //Contexto no qual o DAO foi chamado
    private Context context;

    private static AcudeDao instancia;

    public static AcudeDao getInstancia(Context context){
        if(instancia == null)
            return instancia = new AcudeDao(context);
        else
            return instancia;

        //return (instancia == null ? new AlunoDao(context) : instancia);
    }

    //Construtor
    private AcudeDao(Context context) {
        this.context = context;

        //Abrir a conexão com BD
        openHelper = new SQLiteDataHelper(this.context,
                "UNIPAR", null, 1);

        db = openHelper.getWritableDatabase();
    }

    @Override
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
    public long insert(Acude obj) {

        ContentValues valores = new ContentValues();
        valores.put("CODIGO", obj.getCodigo());
        valores.put("QTD_PEIXE", obj.getQtdPeixe());
        valores.put("ID_PROPRIEDADE", obj.getIdPropriedade());
        valores.put("NOME", obj.getNome());
        valores.put("ID_ESPECIE", obj.getIdEspecie());

        return db.insert(tableName, null, valores);
    }

    @Override
    public long update(Acude obj) {
        ContentValues valores = new ContentValues();
        valores.put("QTD_PEIXE", obj.getQtdPeixe());
        valores.put("ID_PROPRIEDADE", obj.getIdPropriedade());
        valores.put("NOME", obj.getNome());
        valores.put("ID_ESPECIE", obj.getIdEspecie());

        String[]identificador = {String.valueOf(obj.getCodigo())};

        return db.update(tableName, valores,
                "CODIGO = ?", identificador);
    }

    @Override
    public long delete(Acude obj) {
        String[]identificador = {String.valueOf(obj.getCodigo())};
        return db.delete(tableName,"CODIGO = ?",identificador);
    }

    @Override
    public ArrayList<Acude> getAll(int codigoPropriedade) {
        String[]identificador = {String.valueOf(codigoPropriedade)};
        ArrayList<Acude> listaAcude = new ArrayList();

        Cursor cursor = db.query(tableName, colunas,
                "ID_PROPRIEDADE = ?", identificador, null, null, "NOME asc");
        if(cursor.moveToFirst()){
            do{
                Acude acude = new Acude();
                acude.setCodigo(cursor.getInt(0));
                acude.setQtdPeixe(cursor.getInt(1));
                acude.setIdPropriedade(cursor.getInt(2));
                acude.setNome(cursor.getString(3));
                acude.setIdEspecie(cursor.getInt(4));

                listaAcude.add(acude);
            }while(cursor.moveToNext());
        }

        return listaAcude;
    }

    @Override
    public Acude getByCodigo(int codigo) {
        String[]identificador = {String.valueOf(codigo)};

        Cursor cursor = db.query(tableName, colunas,
                "CODIGO = ?", identificador,
                null, null, null);
        if(cursor.moveToFirst()){
            Acude acude = new Acude();
            acude.setCodigo(cursor.getInt(0));
            acude.setQtdPeixe(cursor.getInt(1));
            acude.setIdPropriedade(cursor.getInt(2));
            acude.setNome(cursor.getString(3));
            acude.setIdEspecie(cursor.getInt(4));

            return acude;
        }

        return null;
    }


    public Acude getByName(String nomeAcude) {
        String[]identificador = {nomeAcude, String.valueOf(LoginSingleton.getCodigoPropriedade())};

        Cursor cursor = db.query(tableName, colunas,
                "NOME = ? AND ID_PROPRIEDADE = ?", identificador,
                null, null, null);
        if(cursor.moveToFirst()){
            Acude acude = new Acude();
            acude.setCodigo(cursor.getInt(0));
            acude.setQtdPeixe(cursor.getInt(1));
            acude.setIdPropriedade(cursor.getInt(2));
            acude.setNome(cursor.getString(3));
            acude.setIdEspecie(cursor.getInt(4));

            return acude;
        }

        return null;
    }
}
