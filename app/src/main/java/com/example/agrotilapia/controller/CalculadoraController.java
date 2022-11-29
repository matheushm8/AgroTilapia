package com.example.agrotilapia.controller;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CalculadoraController implements Parcelable {

    //VALORES DA TABELA
    private int refPorDia = 0;
    private double percPV = 0.0;
    private double percTemp = 0.0;
    private String tipoRacao = "";

    private double qtdRacaoPorRefeicao = 0.0;
    private double qtdRacaoPorDia = 0.0;

    public CalculadoraController (){};


    public void calcular(int qtdPeixes, double pesoMedio, double temperatura) {
        limpaValores();

        if (percRacaoPorTemp(temperatura) > 0) {

            valorestabela(pesoMedio); // Atualiza valores da tabela

        valorestabela(pesoMedio); // Atualiza valores da tabela
        qtdRacaoPorTemp(temperatura);

        limpaValores();

        if (percRacaoPorTemp(temperatura) > 0) { //Aqui ele faz a verificação para ver se da pra alimentar baseado na temperatura

            valorestabela(pesoMedio); // Atualiza valores pela tabela

            double biomassa = (qtdPeixes * pesoMedio) / 1000;


        double biomassa = (qtdPeixes * pesoMedio) / 1000;


        qtdRacaoPorDia = (biomassa * (percPV/100)) * (percTemp/100);


            double biomassa = (qtdPeixes * pesoMedio) / 1000;

            qtdRacaoPorDia = (biomassa * (percPV / 100)) * (percTemp / 100);

            qtdRacaoPorRefeicao = qtdRacaoPorDia / refPorDia;
        } else {
            tipoRacao = "Não alimentar";
        }
    }


    private void qtdRacaoPorTemp(double temperatura) {

            qtdRacaoPorRefeicao = qtdRacaoPorDia / refPorDia;
        } else {
            tipoRacao = "Não alimentar";
        }
    }
    //apenas para limpar a calculadora
    private void limpaValores() {
        refPorDia = 0;
        percPV = 0.0;
        percTemp = 0.0;
        tipoRacao = "";

        qtdRacaoPorRefeicao = 0.0;
        qtdRacaoPorDia = 0.0;
    }

    private double percRacaoPorTemp(double temperatura) {

        if (temperatura < 16) {
            percTemp = 0;
            return percTemp;
        }

        if (temperatura >= 16 && temperatura < 20) { // 16 a 19
            percTemp = 60;
            return percTemp;
        }
        if (temperatura < 25) { // 20 a 24
            percTemp = 80;
            return percTemp;
        }
        if (temperatura < 30) { // 25 a 29
            percTemp = 100;
            return percTemp;
        }
        if (temperatura < 33) { // 30 a 32
            percTemp = 80;
            return percTemp;
        }

        if (temperatura > 32) { // > 32 ou < 16

        if (temperatura > 32) { // > 32

        if (temperatura < 16  || temperatura > 32) { //se a temperatura for menor que 16 ou maior que 32 entao nao alimenta


            percTemp = 0.0;
            return percTemp;
        }
        return 0;
    }

    private void valorestabela(double pesoMedio) {
        if (pesoMedio >= 1 && pesoMedio < 5) { //gramas
            tipoRacao = "Ração em pó - 42% PB";
            refPorDia = 5;
            percPV = 14.0;//percentual peso vivo
            return;
        }
        if (pesoMedio < 10) {
            tipoRacao = "2-3 mm - 42% PB";
            refPorDia = 4;
            percPV = 8.0;
            return;
        }
        if (pesoMedio < 20) {
            tipoRacao = "2-3 mm - 42% PB";
            refPorDia = 3;
            percPV = 5.0;
            return;
        }
        if (pesoMedio < 50) {
            tipoRacao = "2-3 mm - 42% PB";
            refPorDia = 3;
            percPV = 4.5;
            return;
        }
        if (pesoMedio < 150) {
            tipoRacao = "3-4 mm - 36% PB";
            refPorDia = 3;
            percPV = 3.4;
            return;
        }
        if (pesoMedio < 250) {
            tipoRacao = "4-6 mm - 32% PB";
            refPorDia = 3;
            percPV = 3.0;
            return;
        }
        if (pesoMedio < 400) {
            tipoRacao = "4-6 mm - 28-32% PB";
            refPorDia = 2;
            percPV = 2.2;
            return;
        }
        if (pesoMedio < 600) {
            tipoRacao = "4-6 mm - 28-32% PB";
            refPorDia = 2;
            percPV = 1.4;
            return;
        }
        if (pesoMedio < 800) {
            tipoRacao = "4-6 mm - 28-32% PB";
            refPorDia = 2;
            percPV = 1.0;
            return;
        }
        if (pesoMedio < 1300) {
            tipoRacao = "6-8 mm - 28-32% PB";
            refPorDia = 2;
            percPV = 0.8;
            return;
        }
        if (pesoMedio >= 1300 && pesoMedio < 1800) {
            tipoRacao = "6-8 mm - 28-32% PB";
            refPorDia = 2;
            percPV = 0.6;
            return;
        }
    }
//Aqui são os getters para pegalos de fora da classe
    public int getRefPorDia() {
        return refPorDia;
    }

    public String getTipoRacao() {
        return tipoRacao;
    }

    public double getQtdRacaoPorRefeicao() {
        return qtdRacaoPorRefeicao;
    }

    public double getQtdRacaoPorDia() {
        return qtdRacaoPorDia;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(refPorDia);
        dest.writeDouble(percPV);
        dest.writeDouble(percTemp);
        dest.writeString(tipoRacao);
        dest.writeDouble(qtdRacaoPorRefeicao);
        dest.writeDouble(qtdRacaoPorDia);
    }

    protected CalculadoraController(Parcel in) {
        refPorDia = in.readInt();
        percPV = in.readDouble();
        percTemp = in.readDouble();
        tipoRacao = in.readString();
        qtdRacaoPorRefeicao = in.readDouble();
        qtdRacaoPorDia = in.readDouble();
    }

    public static final Creator<CalculadoraController> CREATOR = new Creator<CalculadoraController>() {
        @Override
        public CalculadoraController createFromParcel(Parcel in) {
            return new CalculadoraController(in);
        }

        @Override
        public CalculadoraController[] newArray(int size) {
            return new CalculadoraController[size];
        }
    };

}
