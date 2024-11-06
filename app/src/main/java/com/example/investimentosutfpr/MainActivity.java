package com.example.investimentosutfpr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int TELA_EVOLUCAO = 789;
    private EditText edValorAtingir, edDepositoInicial, edAporteMensal, edRendimentoMensal;
    private Button btnCalcular, btnLimpar;

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
    public void calcular(View view)
    {
        double valorAtingir = 0;
        double depositoInicial = 0;
        double aporteMensal = 0;
        double juros = 0;

        try
        {
            valorAtingir = Double.parseDouble(edValorAtingir.getText().toString());
            depositoInicial = Double.parseDouble(edDepositoInicial.getText().toString());
            aporteMensal = Double.parseDouble(edAporteMensal.getText().toString());
            juros = Double.parseDouble(edRendimentoMensal.getText().toString());
        }
        catch (NumberFormatException e)
        {
            Log.d("TESTE", "NumberFormatException: Erro ao converter os dados");
            return;
        }

        //Testa se os campos foram preenchidos corretamente
        if (valorAtingir < 10000)
        {
            showToast(getString(R.string.valor_atingir_invalido));
            return;
        }
        if (depositoInicial < 0)
        {
            showToast(getString(R.string.deposito_invalido));
            return;
        }
        if (aporteMensal <= 0)
        {
            showToast(getString(R.string.aporte_invalido));
            return;
        }
        if (juros <= 0)
        {
            showToast(getString(R.string.juros_invalido));
            return;
        }

        //Criando os itens da ListView
        ArrayList<Investment> investments = new ArrayList<>();
        double montanteTotal = depositoInicial;
        int mes = 0;

        while (montanteTotal < valorAtingir) {
            if (mes == 0) {
                montanteTotal += montanteTotal*(juros/100);
                mes++;
            }
            else {
                montanteTotal += (montanteTotal*(juros/100)) + aporteMensal;
                mes++;
            }

            Investment newItem = new Investment(mes, juros, aporteMensal, montanteTotal);

            if (newItem.getMeses() > 0 &&
                newItem.getReserva() > 0 &&
                newItem.getAporte() >= 0 &&
                newItem.getJuros() > 0)
            {
                investments.add(newItem);
            }

            Log.d("TESTE", "Mes: " + mes + " Montante: " + montanteTotal);
        }

        //Enviando os dados para a tela de Evolução do Investimento
        if (montanteTotal >= valorAtingir)
        {
            Intent intent = new Intent(MainActivity.this, EvolutionActivity.class);
            intent.putExtra("ITEMS", investments);
            startActivity(intent);
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