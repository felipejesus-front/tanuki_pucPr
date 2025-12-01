package modelo;

public class Terreno  extends Financiamento{


    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
    }

    public double calcularPagamentoMensal(){
        double doisPorcento = 0.02;
        return super.calcularPagamentoMensal() + (super.calcularPagamentoMensal() * doisPorcento) ;
    }
}
