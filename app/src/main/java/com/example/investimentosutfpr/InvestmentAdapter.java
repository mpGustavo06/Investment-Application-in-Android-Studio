package com.example.investimentosutfpr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class InvestmentAdapter extends ArrayAdapter<Investment> {

    private Context context;
    private ArrayList<Investment> items;

    public InvestmentAdapter(Context context, ArrayList<Investment> investments) {
        super(context, 0, investments);
        this.context = context;
        this.items = investments;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_evolution, parent, false);
        }

        Investment currentItem = items.get(position);

        TextView mes = convertView.findViewById(R.id.tv_mes);
        TextView juros = convertView.findViewById(R.id.tv_juros);
        TextView deposito = convertView.findViewById(R.id.tv_deposito);
        TextView reserva = convertView.findViewById(R.id.tv_reserva);

        mes.setText(String.valueOf(currentItem.getMeses()));
        juros.setText(String.valueOf(String.format("%.2f",currentItem.getJuros()))+"%");
        deposito.setText(String.valueOf(String.format("%.2f",currentItem.getAporte())));
        reserva.setText(String.valueOf(String.format("%.2f",currentItem.getReserva())));

        return convertView;
    }
}