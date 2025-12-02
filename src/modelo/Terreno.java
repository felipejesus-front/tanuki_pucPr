package modelo;

public class Terreno  extends Financiamento{

    private String tipoZona;

    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
    }

    public Terreno(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, String tipoZona) {
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

    public String getTipoZona() {
        return tipoZona;
    }

    public void setTipoZona(String tipoZona) {
        if (tipoZona == null || tipoZona.trim().isEmpty()) {
            throw new IllegalArgumentException("O tipo de zona não pode ser vazio.");
        }
        this.tipoZona = tipoZona;
    }
}
