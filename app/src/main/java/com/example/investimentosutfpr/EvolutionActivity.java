package com.example.investimentosutfpr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EvolutionActivity extends AppCompatActivity {
    Button btnFechar;
    ArrayList<Investment> investments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evolution);

        //Recebimento dos dados
        btnFechar = findViewById(R.id.btnFechar);

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

    //CLASSE ADAPTER
    class ContatoAdapter extends ArrayAdapter<Investment> {
        public ContatoAdapter(Context ctx) {
            super(ctx, 0, investments);
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public View getView(int pos, View recycled, ViewGroup grupo) {
            View v = recycled;
            if (v == null) {
                v = getLayoutInflater().inflate(R.layout.item_evolution, null);
            }
            Investment invest = investments.get(pos);
            ((TextView) v.findViewById(R.id.tv_mes)).setText(invest.getMeses());
            ((TextView) v.findViewById(R.id.tv_juros)).setText(String.valueOf(invest.getJuros()));
            ((TextView) v.findViewById(R.id.tv_deposito)).setText(String.valueOf(invest.getAporte()));
            ((TextView) v.findViewById(R.id.tv_reserva)).setText(String.valueOf(invest.getReserva()));

            return v;
        }
    }
}
