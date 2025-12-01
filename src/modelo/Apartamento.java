package modelo;

public class Apartamento extends Financiamento {
    public Apartamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
    }

    public double calcularPagamentoMensal() {
        double taxaMensal = taxaJurosMensal(getTaxaJurosAnual());
        int meses = prazoFinanciamentoEmMeses();
        double base = 1 + taxaMensal;
        double potencia = Math.pow(base, meses);
        double numerador = getValorImovel() * taxaMensal * potencia;
        double denominador = potencia - 1;
        return numerador / denominador;
    }
}
