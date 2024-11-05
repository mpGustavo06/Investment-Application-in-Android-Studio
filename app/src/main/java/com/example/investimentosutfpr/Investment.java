package com.example.investimentosutfpr;

import java.io.Serializable;

public class Investment implements Serializable {
    private double valorFinal,depInicial,aporte,juros,reserva;
    private int meses;

    public Investment(double valorFinal, double depInicial, double aporte, double juros, double reserva, int meses) {
        this.valorFinal = valorFinal;
        this.depInicial = depInicial;
        this.aporte = aporte;
        this.juros = juros;
        this.reserva = reserva;
        this.meses = meses;
    }

    public Investment(int meses, double juros, double aporte, double reserva) {
        this.meses = meses;
        this.juros = juros;
        this.aporte = aporte;
        this.reserva = reserva;
    }

    public Investment() {
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public double getDepInicial() {
        return depInicial;
    }

    public void setDepInicial(double depInicial) {
        this.depInicial = depInicial;
    }

    public double getAporte() {
        return aporte;
    }

    public void setAporte(double aporte) {
        this.aporte = aporte;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public double getReserva() {
        return reserva;
    }

    public void setReserva(double reserva) {
        this.reserva = reserva;
    }

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }
}