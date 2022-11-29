package com.example.agrotilapia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agrotilapia.R;
import com.example.agrotilapia.controller.AcudeController;
import com.example.agrotilapia.controller.CalculadoraController;
import com.example.agrotilapia.models.Acude;

public class CalcularActivity extends AppCompatActivity {

    private Acude acude;
    private AcudeController acontroller = new AcudeController(this);
    private CalculadoraController calculadora;
    private EditText edPesoMedio;
    private EditText edTempAgua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular);

        Bundle bundle = getIntent().getExtras();
        acude = acontroller.retornaAcude(bundle.getInt("codigo_acude"));

        edPesoMedio = findViewById(R.id.edPesoMedio);
        edTempAgua = findViewById(R.id.edTempAgua);

        calculadora = new CalculadoraController();
    }

    public void btCalcularOnClick(View view) {
    //Aqui recebe o açude escolhido que veio da açude list adapter
        Bundle bundle = getIntent().getExtras();
        acude = acontroller.retornaAcude(bundle.getInt("codigo_acude"));

        if (edPesoMedio.getText().toString().isEmpty() || edTempAgua.getText().toString().isEmpty()) {
            Toast.makeText(this, "Todos os Campos Devem ser Preenchidos", Toast.LENGTH_SHORT).show();
            return;
        }

        //com base no açude escolhido aqui faz o calculo.
        calculadora.calcular(
                acude.getQtdPeixe(), Double.parseDouble(edPesoMedio.getText().toString()), Double.parseDouble(edTempAgua.getText().toString()));
    // aqui abre a tela de resultado passando seus valores
        Intent resultadosPage = new Intent(this, ResultadosActivity.class);
        resultadosPage.putExtra("calculadora", calculadora);
        startActivity(resultadosPage);
    }

    public void btVoltarOnClick(View view) {
        Intent homePage = new Intent(this, HomeActivity.class);
        startActivity(homePage);
    }
}