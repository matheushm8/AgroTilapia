package com.example.agrotilapia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.agrotilapia.R;
import com.example.agrotilapia.adapter.AcudeListAdapter;
import com.example.agrotilapia.controller.AcudeController;
import com.example.agrotilapia.dao.PropriedadeDao;
import com.example.agrotilapia.models.Acude;
import com.example.agrotilapia.models.Propriedade;
import com.example.agrotilapia.singleton.LoginSingleton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private Button botaoVoltar, botaoCadastrar;
    private ListView lvListaAcude;
    private TextView tvListaVaziaMsg;
    private ArrayList<Acude> acudes;
    private AcudeController acontroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        botaoVoltar=findViewById(R.id.btVoltar);
        botaoCadastrar=findViewById(R.id.btCadastrar);
        tvListaVaziaMsg = findViewById(R.id.tvListaVaziaMsg);
        lvListaAcude = findViewById(R.id.lvListaAcude);

        acontroller = new AcudeController(this);

        carregaArrayListAcudes();
    }

    private void carregaArrayListAcudes() {
        atualizaListaAlunos();
        validaMensagemCentro();
    }

    private void validaMensagemCentro() {
        if (acudes.isEmpty()) {
            tvListaVaziaMsg.setVisibility(View.VISIBLE);
            lvListaAcude.setVisibility(View.GONE);
        }
        else {
            tvListaVaziaMsg.setVisibility(View.GONE);
            lvListaAcude.setVisibility(View.VISIBLE);
        }
    }

    public void btVoltarOnClick(View view) {
        Intent loginPage = new Intent(this, LoginActivity.class);
        startActivity(loginPage);
        LoginSingleton.limpaInstancia();
    }

    public void btCadastrarOnClick(View view) {
        Intent cadastroAcudePage = new Intent(this, CadastroAcudeActivity.class);
        startActivity(cadastroAcudePage);
    }

    private void atualizaListaAlunos() {
        acudes = new ArrayList<>();
        acudes = acontroller.retornaAcudes(LoginSingleton.getpropriedadeLogada().getCodigoPropriedade());
        AcudeListAdapter adapter = new AcudeListAdapter(
                acudes, this);

        lvListaAcude.setAdapter(adapter);
    }
}
