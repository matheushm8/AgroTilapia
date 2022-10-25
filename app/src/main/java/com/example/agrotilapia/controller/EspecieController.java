package com.example.agrotilapia.controller;

import android.content.Context;

import com.example.agrotilapia.dao.EspecieDao;
import com.example.agrotilapia.models.Especie;

import java.util.ArrayList;

public class EspecieController {
    private Context context;

    public EspecieController(Context context) {
        this.context = context;
    }

    public ArrayList<Especie> retornaEspecies(int codigoPropriedade){
        return EspecieDao.getInstancia(context).getAll(codigoPropriedade);
    }

    public Especie retornaEspeciePeloNome(String nomeEspecie) {
        return EspecieDao.getInstancia(context).getByNome(nomeEspecie);
    }

    public Especie retornaEspeciePeloCodigo(int codigo) {
        return EspecieDao.getInstancia(context).getByCodigo(codigo);
    }

    public long salvarEspecie(Especie especie){
        return EspecieDao.getInstancia(context).insert(especie);
    }

    public long excluirEspecie(Especie especie){
        return EspecieDao.getInstancia(context).delete(especie);
    }
}
