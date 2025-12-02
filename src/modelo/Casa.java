package modelo;

public class Casa  extends Financiamento{

    private double tamanhoareaConstrucao;
    private double tamanhoTerreno;

    public Casa(double valorImovel, int prazoFinanciamento, double taxaJurosAnual) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
    }

    public Casa(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, double tamanhoareaConstrucao, double tamanhoTerreno) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tamanhoareaConstrucao = tamanhoareaConstrucao;
        this.tamanhoTerreno = tamanhoTerreno;
    }

    public double calcularPagamentoMensal(){
        return super.calcularPagamentoMensal() + 80 ;
    }

    @Override
    public void mostrarDadosFinanciamento(String nomeFinanciamento, int count) {
        super.mostrarDadosFinanciamento(nomeFinanciamento, count);
        System.out.println("Área Construída: " + String.format("%.2f", tamanhoareaConstrucao) + " m²");
        System.out.println("Tamanho do Terreno: " + String.format("%.2f", tamanhoTerreno) + " m²");
    }

    public double getTamanhoareaConstrucao() {
        return tamanhoareaConstrucao;
    }

    public void setTamanhoareaConstrucao(double tamanhoareaConstrucao) {
        if (tamanhoareaConstrucao <= 0) {
            throw new IllegalArgumentException("A área construída deve ser maior que zero.");
        }
        this.tamanhoareaConstrucao = tamanhoareaConstrucao;
    }

    public double getTamanhoTerreno() {
        return tamanhoTerreno;
    }

    public void setTamanhoTerreno(double tamanhoTerreno) {
        if (tamanhoTerreno <= 0) {
            throw new IllegalArgumentException("O tamanho do terreno deve ser maior que zero.");
        }
        if (tamanhoTerreno < tamanhoareaConstrucao) {
            throw new IllegalArgumentException("O tamanho do terreno não pode ser menor que a área construída.");
        }
        this.tamanhoTerreno = tamanhoTerreno;
    }
}
