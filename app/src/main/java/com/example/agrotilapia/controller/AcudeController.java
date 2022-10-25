package com.example.agrotilapia.controller;

import android.content.Context;

import com.example.agrotilapia.dao.AcudeDao;
import com.example.agrotilapia.models.Acude;

import java.util.ArrayList;

public class AcudeController {
    private Context context;

    public AcudeController(Context context) {
        this.context = context;
    }
    
    public ArrayList<Acude> retornaAcudes(int codigoPropriedade){
        return AcudeDao.getInstancia(this.context).getAll(codigoPropriedade);
    }

    public Acude retornaAcude(int codigoAcude) {return AcudeDao.getInstancia(context).getByCodigo(codigoAcude); }

    public Acude retornaAcude(String nomeAcude) {return AcudeDao.getInstancia(context).getByName(nomeAcude); }

    public long salvaAcude(Acude acude){
        return AcudeDao.getInstancia(context).insert(acude);
    }

    public int retornaProximoId() {return AcudeDao.getInstancia(context).retornaProximoCodigo();}

    public long excluiAcude(Acude acude){
        return AcudeDao.getInstancia(context).delete(acude);
    }
}
