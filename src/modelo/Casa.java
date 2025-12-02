package modelo;

import java.io.Serializable;

public class Casa extends Financiamento implements Serializable {

    private static final long serialVersionUID = 1L;

    private double tamanhoareaConstrucao;
    private double tamanhoTerreno;

    public Casa(double valorImovel, int prazoFinanciamento, double taxaJurosAnual, double tamanhoareaConstrucao, double tamanhoTerreno) throws AumentoMaiorDoQueJurosException {
        super(valorImovel, prazoFinanciamento, taxaJurosAnual);
        this.tamanhoareaConstrucao = tamanhoareaConstrucao;
        this.tamanhoTerreno = tamanhoTerreno;

        // Valida se o acréscimo é válido
        validarAcrescimo();
    }

    /**
     * Valida se o acréscimo de R$ 80 não é maior que a metade dos juros da mensalidade
     */
    private void validarAcrescimo() throws AumentoMaiorDoQueJurosException {
        double pagamentoBase = super.calcularPagamentoMensal();
        double principal = getValorImovel() / (getPrazoFinanciamento() * 12);
        double juros = pagamentoBase - principal;
        double acrescimo = 80.0;

        if (acrescimo > (juros / 2)) {
            throw new AumentoMaiorDoQueJurosException(acrescimo, juros);
        }
    }

    public double calcularPagamentoMensal() {
        return super.calcularPagamentoMensal() + 80;
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

