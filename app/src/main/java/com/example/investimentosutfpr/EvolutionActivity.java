package com.example.investimentosutfpr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EvolutionActivity extends AppCompatActivity {
    Button btnFechar;
    private ListView listView;
    private InvestmentAdapter adapter;
    private ArrayList<Investment> investments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evolution);

        //Recebimento dos dados
        btnFechar = findViewById(R.id.btnFechar);
        listView = findViewById(R.id.list_evolution);
        Intent intent = getIntent();

        //CARREGAR ITENS NA LISTA
        investments = new ArrayList<>();
        investments = (ArrayList<Investment>) intent.getSerializableExtra("ITEMS");
        adapter = new InvestmentAdapter(this, investments);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //EVENTOS
        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                fechar(view);
            }
        });
    }

    //METODOS DA ACTIVITY
    public void fechar(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(R.string.close_message);

        alertDialog.setPositiveButton("Sim", (dialog, which) -> {
            Intent it = new Intent();
            it.putExtra("novo", 789);
            setResult(RESULT_OK, it);
            finish();
        });

        alertDialog.setNegativeButton("Não", null);

        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }

    //MÉTODOS AUXILIARES
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        return;
    }
}
