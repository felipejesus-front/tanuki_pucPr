package modelo;

/**
 * Exceção lançada quando o valor do acréscimo é maior que a metade do valor dos juros da mensalidade.
 */
public class AumentoMaiorDoQueJurosException extends Exception {

    private final double valorAumento;
    private final double valorJuros;
    private final double metadeJuros;

    public AumentoMaiorDoQueJurosException(double valorAumento, double valorJuros) {
        super(String.format("Erro: O acréscimo de R$ %.2f é maior que a metade dos juros (R$ %.2f). " +
                "Juros total da mensalidade: R$ %.2f",
                valorAumento, valorJuros / 2, valorJuros));
        this.valorAumento = valorAumento;
        this.valorJuros = valorJuros;
        this.metadeJuros = valorJuros / 2;
    }

    public double getValorAumento() {
        return valorAumento;
    }

    public double getValorJuros() {
        return valorJuros;
    }

    public double getMetadeJuros() {
        return metadeJuros;
    }
}

