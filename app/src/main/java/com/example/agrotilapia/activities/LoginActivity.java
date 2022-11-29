package com.example.agrotilapia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agrotilapia.R;
import com.example.agrotilapia.activities.HomeActivity;
import com.example.agrotilapia.controller.PropriedadeController;
import com.example.agrotilapia.dao.PropriedadeDao;
import com.example.agrotilapia.models.Propriedade;
import com.example.agrotilapia.singleton.LoginSingleton;

public class LoginActivity extends AppCompatActivity {

    private Button botaoAbrirOutraTela;
    private EditText edPropriedade;
    private PropriedadeController pcontroller;
    private PropriedadeDao propriedadeDao;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pcontroller = new PropriedadeController(this);
        propriedadeDao = PropriedadeDao.getInstancia(this);

        botaoAbrirOutraTela=findViewById(R.id.btEntrar);
        edPropriedade = findViewById(R.id.username);
    }

    public void btCadastrarOnClick(View view) {
        if (edPropriedade.getText().toString().isEmpty()) {
            Toast.makeText(this, "Informe uma propriedade!", Toast.LENGTH_LONG).show();
            edPropriedade.requestFocus();
        } else {
            if (pcontroller.buscarPropriedade(edPropriedade.getText().toString()) != null) {
                Toast.makeText(this, "Propriedade já cadastrada!", Toast.LENGTH_LONG).show();
                edPropriedade.requestFocus();
                return;
            }

            Propriedade propriedade = new Propriedade();
            propriedade.setCodigo(propriedadeDao.retornaProximoCodigo());
            propriedade.setNome(edPropriedade.getText().toString());

            pcontroller.salvarPropriedade(propriedade);
            Toast.makeText(this, "Propriedade cadastrada com sucesso!", Toast.LENGTH_SHORT).show();

            LoginSingleton.limpaInstancia();
            LoginSingleton.iniciaPropriedadeLogada(propriedade.getCodigo(), propriedade.getNome());

            Intent homePage = new Intent(this, HomeActivity.class);
            startActivity(homePage);
        }
    }

    public void btEntraOnClick(View view) {
        if (edPropriedade.getText().toString().isEmpty()) {
            Toast.makeText(this, "Informe uma propriedade!", Toast.LENGTH_SHORT).show();
            edPropriedade.requestFocus();
        } else {
            if (pcontroller.buscarPropriedade(edPropriedade.getText().toString()) == null) {
                Toast.makeText(this, "Não foi possível encontrar a propriedade informada!", Toast.LENGTH_SHORT).show();
                edPropriedade.requestFocus();
            } else {

                Toast.makeText(this, "Login Efetuado!", Toast.LENGTH_SHORT).show();

                Propriedade propriedade = pcontroller.buscarPropriedade(edPropriedade.getText().toString());

                LoginSingleton.limpaInstancia();
                LoginSingleton.iniciaPropriedadeLogada(propriedade.getCodigo(), propriedade.getNome());

                Intent telaHome = new Intent(this, HomeActivity.class);
                startActivity(telaHome);
            }
        }
    }
}

