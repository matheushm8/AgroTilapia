package com.example.agrotilapia.models;

import android.os.Parcel;
import android.os.Parcelable;
//entidades do banco
public class Acude implements Parcelable {
    private int codigo;
    private int qtdPeixe;
    private int idPropriedade;
    private String nome;
    private int idEspecie;

    public Acude( int codigo, int qtdPeixe, int idPropriedade, String nome, int idEspecie) {
        this.codigo = codigo;
        this.qtdPeixe = qtdPeixe;
        this.idPropriedade = idPropriedade;
        this.nome = nome;
        this.idEspecie = idEspecie;
    }

    public Acude() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQtdPeixe() {
        return qtdPeixe;
    }

    public void setQtdPeixe(int qtdPeixe) {
        this.qtdPeixe = qtdPeixe;
    }

    public int getIdPropriedade() {
        return idPropriedade;
    }

    public void setIdPropriedade(int idPropriedade) {
        this.idPropriedade = idPropriedade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(codigo);
        dest.writeInt(qtdPeixe);
        dest.writeInt(idPropriedade);
        dest.writeString(nome);
        dest.writeInt(idEspecie);
    }

    protected Acude(Parcel in) {
        codigo = in.readInt();
        qtdPeixe = in.readInt();
        idPropriedade = in.readInt();
        nome = in.readString();
        idEspecie = in.readInt();
    }

    public static final Creator<Acude> CREATOR = new Creator<Acude>() {
        @Override
        public Acude createFromParcel(Parcel in) {
            return new Acude(in);
        }

        @Override
        public Acude[] newArray(int size) {
            return new Acude[size];
        }
    };
}
