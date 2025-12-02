package modelo;

public class Apartamento extends Financiamento {

    private int numeroVagasGaragem;
    private int numeroAndar;

    public Apartamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
    }

    public Apartamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, int numeroVagasGaragem, int numeroAndar) {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.numeroVagasGaragem = numeroVagasGaragem;
        this.numeroAndar = numeroAndar;
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

    @Override
    public void mostrarDadosFinanciamento(String nomeFinanciamento, int count) {
        super.mostrarDadosFinanciamento(nomeFinanciamento, count);
        System.out.println("Número de Vagas na Garagem: " + numeroVagasGaragem);
        System.out.println("Número do Andar: " + numeroAndar);
    }

    public int getNumeroVagasGaragem() {
        return numeroVagasGaragem;
    }

    public void setNumeroVagasGaragem(int numeroVagasGaragem) {
        if (numeroVagasGaragem < 0) {
            throw new IllegalArgumentException("O número de vagas na garagem não pode ser negativo.");
        }
        this.numeroVagasGaragem = numeroVagasGaragem;
    }

    public int getNumeroAndar() {
        return numeroAndar;
    }

    public void setNumeroAndar(int numeroAndar) {
        if (numeroAndar < 0) {
            throw new IllegalArgumentException("O número do andar não pode ser negativo.");
        }
        this.numeroAndar = numeroAndar;
    }
}
