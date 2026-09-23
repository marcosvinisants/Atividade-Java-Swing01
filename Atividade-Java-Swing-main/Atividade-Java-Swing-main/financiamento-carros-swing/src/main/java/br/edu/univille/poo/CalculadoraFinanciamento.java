package br.edu.univille.poo;

public class CalculadoraFinanciamento {

    private double valorVeiculo;
    private double entrada;
    private int numeroParcelas;
    private double taxaJuros;

    public CalculadoraFinanciamento(double valorVeiculo, double entrada, int numeroParcelas) {
        this.valorVeiculo = valorVeiculo;
        this.entrada = entrada;
        this.numeroParcelas = numeroParcelas;
        this.taxaJuros = 0.4;
    }

    public CalculadoraFinanciamento(double valorVeiculo, double entrada, int numeroParcelas, double taxaJuros) {
        this.valorVeiculo = valorVeiculo;
        this.entrada = entrada;
        this.numeroParcelas = numeroParcelas;
        this.taxaJuros = taxaJuros;
    }

    public double getValorFinanciado() {
        return valorVeiculo - entrada;
    }

    public double getValorTotal() {
        return getValorFinanciado() * Math.pow(1 + taxaJuros, numeroParcelas);
    }

    public double getValorParcela() {
        return getValorTotal() / numeroParcelas;
    }

    public double getTotalAPagar() {
        return getValorTotal();
    }
}