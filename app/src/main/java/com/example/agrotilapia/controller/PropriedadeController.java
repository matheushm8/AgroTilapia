package com.example.agrotilapia.controller;

import android.content.Context;

import com.example.agrotilapia.dao.PropriedadeDao;
import com.example.agrotilapia.models.Propriedade;

import java.util.ArrayList;

public class PropriedadeController {
    private Context context;

    public PropriedadeController(Context context) {
        this.context = context;
    }

    public ArrayList<Propriedade> retornaPropriedades(int codigoPropriedade){
        return PropriedadeDao.getInstancia(context).getAll(codigoPropriedade);
    }

    public Propriedade buscarPropriedade(String nomePropriedade) {
        return PropriedadeDao.getInstancia(context).getByName(nomePropriedade.trim());
    }

    public long salvarPropriedade(Propriedade propriedade){
        return PropriedadeDao.getInstancia(context).insert(propriedade);
    }

    public long excluirPropriedade(Propriedade propriedade){
        return PropriedadeDao.getInstancia(context).delete(propriedade);
    }

}
