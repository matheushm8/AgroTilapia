package com.example.agrotilapia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.agrotilapia.R;
import com.example.agrotilapia.controller.CalculadoraController;

import java.text.DecimalFormat;

public class ResultadosActivity extends AppCompatActivity {
    //FORMATADOR
    private DecimalFormat df = new DecimalFormat("#,##0.00");

    private TextView tvQtdRacaoPorDia;
    private TextView tvQtdRacaoPorRefeicao;
    private TextView tvTipoRacao;
    private TextView tvRefeicoesPorDia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        iniciaComponentes();

        retornarCalculo();
    }

    private void retornarCalculo() {
        Bundle bundleCalculadora = getIntent().getExtras();
        CalculadoraController calculadora = bundleCalculadora.getParcelable("calculadora");

        tvQtdRacaoPorDia.setText(df.format(calculadora.getQtdRacaoPorDia()));
        tvQtdRacaoPorRefeicao.setText(df.format(calculadora.getQtdRacaoPorRefeicao()));
        tvTipoRacao.setText(calculadora.getTipoRacao());
        tvRefeicoesPorDia.setText(String.valueOf(calculadora.getRefPorDia()));
    }

    private void iniciaComponentes() {
        tvQtdRacaoPorDia = findViewById(R.id.tvQtdRacaoPorDia);
        tvQtdRacaoPorRefeicao = findViewById(R.id.tvQtdRacaoPorRefeicao);
        tvTipoRacao = findViewById(R.id.tvTipoRacao);
        tvRefeicoesPorDia = findViewById(R.id.tvRefeicoesPorDia);
    }

    public void btNovoCalculoOnClick(View view) {
        Intent homePage = new Intent(this, HomeActivity.class);
        startActivity(homePage);

    }
}