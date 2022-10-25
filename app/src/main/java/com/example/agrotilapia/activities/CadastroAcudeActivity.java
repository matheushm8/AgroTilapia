package com.example.agrotilapia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.agrotilapia.R;
import com.example.agrotilapia.controller.AcudeController;
import com.example.agrotilapia.controller.EspecieController;
import com.example.agrotilapia.dao.PropriedadeDao;
import com.example.agrotilapia.models.Acude;
import com.example.agrotilapia.singleton.LoginSingleton;

public class CadastroAcudeActivity extends AppCompatActivity {
    private Spinner spEspecie;
    private String[] vetorEspecies;
    private EspecieController econtroller;
    private AcudeController acontroller;
    private EditText edNomeAcude;
    private int especieSelecionada;
    private EditText edQtdPeixes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_acude);

        acontroller = new AcudeController(this);
        econtroller = new EspecieController(this);

        edNomeAcude= findViewById(R.id.edNomeAcude);
        spEspecie = findViewById(R.id.spEspecies);
        edQtdPeixes = findViewById(R.id.edQtdPeixes);
        vetorEspecies = new String[]{"Selecione...", "Tilápia", "Pacu", "Tambaqui", "Carpa", "Pintado"};

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vetorEspecies);
        spEspecie.setAdapter(adapter);

        spEspecie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0)
                    especieSelecionada = econtroller.retornaEspeciePeloCodigo(position).getCodigo();
                else
                    especieSelecionada = 0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void btSalvarOnClick(View view) {
        if (edNomeAcude.getText().toString().isEmpty() || especieSelecionada == 0 || edQtdPeixes.getText().toString().isEmpty()) {
            Toast.makeText(this, "Todos os Campos Devem ser Preenchidos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (acontroller.retornaAcude(edNomeAcude.getText().toString()) == null) {

            Acude newAcude = new Acude();
            newAcude.setCodigo(acontroller.retornaProximoId());
            newAcude.setNome(edNomeAcude.getText().toString());
            newAcude.setQtdPeixe(Integer.parseInt(edQtdPeixes.getText().toString()));
            newAcude.setIdEspecie(especieSelecionada);
            newAcude.setIdPropriedade(LoginSingleton.getCodigoPropriedade());

            acontroller.salvaAcude(newAcude);

            Toast.makeText(this, "Acude Cadastrado!", Toast.LENGTH_SHORT).show();

            retornaToHomePage();
        } else {
            Toast.makeText(this, "Já existe um acude com este nome!", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void retornaToHomePage() {
        Intent homePage = new Intent(this, HomeActivity.class);
        startActivity(homePage);
    }

    public void btVoltarOnClick(View view) {
        retornaToHomePage();
    }
}