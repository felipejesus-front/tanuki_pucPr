package modelo;

public class Financiamento {


    private double valorImovel;
    private int prazoFinanciamento;
    private double taxaJurosAnual;


    public Financiamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual) {
        this.valorImovel = valorImovel;
        this.prazoFinanciamento = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;

    }


    public double calcularPagamentoMensal(){
        return (getValorImovel() / (getPrazoFinanciamento() * 12)) * (1 + (taxaJurosMensal(getTaxaJurosAnual())));
    }

    public double taxaJurosMensal(double taxaJurosAnual ){
        return (taxaJurosAnual/12);
    }

    public void mostrarDadosFinanciamento() {
        System.out.println("Valor do Imóvel: R$ " + String.format("%,.2f",valorImovel));
        System.out.println("Valor da Parcela: R$ " + String.format("%,.2f",calcularPagamentoMensal()));
        System.out.println("Valor Total do Financiamento: R$ " + String.format("%,.2f", calculoTotalPagamento()));

    }

    public double calculoTotalPagamento(){
        return this.calcularPagamentoMensal() * getPrazoFinanciamento() * 12;
    }


    public double getValorImovel() {
        return valorImovel;
    }

    public void setValorImovel(double valorImovel) {
        if (valorImovel <= 0) {
            throw new IllegalArgumentException("O valor do imóvel deve ser maior que zero.");
        }
        this.valorImovel = valorImovel;
    }

    public int getPrazoFinanciamento() {
        return prazoFinanciamento;
    }

    public void setPrazoFinanciamento(int prazoFinanciamento) {
        if (prazoFinanciamento <= 0) {
            throw new IllegalArgumentException("O prazo do financiamento deve ser maior que zero.");
        }
        if (prazoFinanciamento > 50) { // 50 anos é um limite absurdo, mas seguro
            throw new IllegalArgumentException("O prazo informado é irreal. Informe um prazo menor que 50 anos.");
        }
        this.prazoFinanciamento = prazoFinanciamento;
    }

    public double getTaxaJurosAnual() {
        return taxaJurosAnual;
    }

    public void setTaxaJurosAnual(double taxaJurosAnual) {
        if (taxaJurosAnual < 0) {
            throw new IllegalArgumentException("A taxa de juros anual não pode ser negativa.");
        }
        if (taxaJurosAnual > 30) {
            throw new IllegalArgumentException("Taxa de juros irreal para financiamento imobiliário. Informe um valor entre 3% e 30% ao ano.");
        }
        this.taxaJurosAnual = taxaJurosAnual;
    }

}