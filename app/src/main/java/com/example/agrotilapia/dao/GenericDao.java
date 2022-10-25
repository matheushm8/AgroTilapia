package com.example.agrotilapia.dao;

import java.util.ArrayList;

public interface GenericDao <Objeto>{

    long insert(Objeto obj);
    long update(Objeto obj);
    long delete(Objeto obj);
    int retornaProximoCodigo();
    ArrayList<Objeto> getAll(int codigoPropriedade);
    Objeto getByCodigo(int codigo);

}
