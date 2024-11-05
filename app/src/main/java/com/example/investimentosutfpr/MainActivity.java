package com.example.investimentosutfpr;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final int TELA_EVOLUCAO = 789;
    EditText edValorAtingir, edDepositoInicial, edAporteMensal, edRendimentoMensal;
    Button btnCalcular, btnLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recebimento dos dados
        edValorAtingir = findViewById(R.id.edValorAtingir);
        edDepositoInicial = findViewById(R.id.edDepInicial);
        edAporteMensal = findViewById(R.id.edAporteMensal);
        edRendimentoMensal = findViewById(R.id.edRendMensal);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpar = findViewById(R.id.btnLimpar);


        //EVENTOS
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                calcular(view);
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                limpar();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    //METODOS DA ACTIVITY
    public void calcular(View view) {
        double valorAtingir = Double.parseDouble(edValorAtingir.getText().toString());
        double depositoInicial = Double.parseDouble(edDepositoInicial.getText().toString());
        double aporteMensal = Double.parseDouble(edAporteMensal.getText().toString());
        double rendimentoMensal = Double.parseDouble(edRendimentoMensal.getText().toString());
        double montanteTotal = depositoInicial;
        int mes = 0;

        while (montanteTotal < valorAtingir) {
            montanteTotal += (montanteTotal*rendimentoMensal/100) + aporteMensal;
            mes++;

            Log.d("TESTE", "Mes: " + mes + " Montante: " + montanteTotal);
        }

        if (montanteTotal >= valorAtingir) {
            Intent it = new Intent(this, EvolutionActivity.class);
            startActivity(it);
        }
    }

    public void limpar() {
        edValorAtingir.setText("");
        edDepositoInicial.setText("");
        edAporteMensal.setText("");
        edRendimentoMensal.setText("");
        edValorAtingir.requestFocus();
        showToast(getString(R.string.campos_limpos));
    }

    //MÉTODOS AUXILIARES
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        return;
    }
}