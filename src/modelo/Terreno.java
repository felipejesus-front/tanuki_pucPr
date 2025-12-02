package modelo;

import java.io.Serializable;

public class Terreno extends Financiamento implements Serializable {

    private static final long serialVersionUID = 1L;

    private TipoZona tipoZona;

    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, TipoZona tipoZona) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tipoZona = tipoZona;
    }

    public double calcularPagamentoMensal(){
        double doisPorcento = 0.02;
        return super.calcularPagamentoMensal() + (super.calcularPagamentoMensal() * doisPorcento) ;
    }

    @Override
    public void mostrarDadosFinanciamento(String nomeFinanciamento, int count) {
        super.mostrarDadosFinanciamento(nomeFinanciamento, count);
        System.out.println("Tipo de Zona: " + tipoZona);
    }

    public TipoZona getTipoZona() {
        return tipoZona;
    }

    public void setTipoZona(TipoZona tipoZona) {
        if (tipoZona == null) {
            throw new IllegalArgumentException("O tipo de zona não pode ser nulo.");
        }
        this.tipoZona = tipoZona;
    }
}
