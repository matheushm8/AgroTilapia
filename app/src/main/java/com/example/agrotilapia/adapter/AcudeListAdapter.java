package com.example.agrotilapia.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrotilapia.R;
import com.example.agrotilapia.activities.CalcularActivity;
import com.example.agrotilapia.activities.HomeActivity;
import com.example.agrotilapia.controller.AcudeController;
import com.example.agrotilapia.controller.EspecieController;
import com.example.agrotilapia.models.Acude;

import java.util.ArrayList;

public class AcudeListAdapter extends BaseAdapter {

    private ArrayList<Acude> listaAcude;
    private Context context;
    private EspecieController econtroller;
    private Acude acude;
    private AcudeController acontroller;
    private AlertDialog alertDialog;
    public AcudeListAdapter acudeListAdapter;

    public AcudeListAdapter(ArrayList<Acude> listaAcude,
                            Context context) {
        this.listaAcude = listaAcude;
        this.context = context;
        econtroller = new EspecieController(this.context);
        acontroller = new AcudeController(this.context);
        acudeListAdapter = this;
    }

    @Override
    public int getCount() {
        return listaAcude.size();
    }

    @Override
    public Object getItem(int position) {
        return listaAcude.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.items_list_acudes, viewGroup,
                            false);
        }
        TextView tvCultura = view.findViewById(R.id.tvCultura);
        TextView tvNomeAcude = view.findViewById(R.id.tvNomeAcude);

        acude = listaAcude.get(position);

        tvCultura.setText(
                String.valueOf(econtroller.retornaEspeciePeloCodigo(
                        acude.getIdEspecie())
                        .getDescricao()));

        tvNomeAcude.setText(acude.getNome());

        tvCultura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calculadoraPage = new Intent(context, CalcularActivity.class);
                acude = (Acude) getItem(position);
                calculadoraPage.putExtra("codigo_acude", acude.getCodigo());
                context.startActivity(calculadoraPage);

            }
        });

        tvNomeAcude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calculadoraPage = new Intent(context, CalcularActivity.class);
                acude = (Acude) getItem(position);
                calculadoraPage.putExtra("codigo_acude", acude.getCodigo());
                context.startActivity(calculadoraPage);
            }
        });

        tvNomeAcude.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(acude);
                return true;
            }
        });

        return view;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void showDialog(Acude acude) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //Não deixar ele fechar quando clica fora dele
        builder.setCancelable(false);

        builder.setTitle("Excluir");
        builder.setMessage("Deseja remover " + acude.getNome() + "?");
        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (acontroller.excluiAcude(acude) > 0) {
                    listaAcude.remove(acude);
                    acudeListAdapter.notifyDataSetChanged();
                    Toast.makeText(context, "Acude excluído!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Erro ao excluir acude!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }
}
