package main;

import modelo.Apartamento;
import modelo.Casa;
import modelo.Financiamento;
import modelo.Terreno;
import modelo.TipoZona;
import util.InterfaceUsuario;

import java.util.ArrayList;
import java.util.List;




public class Main {
    public static void main(String[] args) {

        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();

        //crio as listas de financiamentos de qualquer um dos tipos de financiamentos
        criarFinanciamentos(interfaceUsuario, 2, "Casa", Casa.class);
        criarFinanciamentos(interfaceUsuario, 2, "Apartamento", Apartamento.class);
        criarFinanciamentos(interfaceUsuario, 2, "Terreno", Terreno.class);

    }

    public static void criarFinanciamentos(
            InterfaceUsuario interfaceUsuario,
            int quantidade,
            String nomeFinanciamento,
            Class<? extends Financiamento> tipoFinanciamento) {

        List<Financiamento> financiamentoList = new ArrayList<>();

        System.out.println("\n=== Criando Financiamentos de " + nomeFinanciamento + " ===\n");

        for (int i = 0; i < quantidade; i++) {
            System.out.println("Financiamento de " + nomeFinanciamento + " " + (i + 1));

            try {
                // Coleta dados básicos (comuns a todos os financiamentos)
                double valorImovel = interfaceUsuario.pedirValorImovel();
                int prazoFinanciamentoEmAnos = interfaceUsuario.pedirPrazoFinanciamento();
                double taxaJuros = interfaceUsuario.pedirTaxaDeJuros();

                // Switch para coletar dados específicos e criar instância final
                Financiamento financiamento;
                switch (nomeFinanciamento) {
                    case "Casa":
                        double areaConstruida = interfaceUsuario.pedirAreaConstruida();
                        double tamanhoTerreno = interfaceUsuario.pedirTamanhoTerreno(areaConstruida);
                        financiamento = new Casa(valorImovel, prazoFinanciamentoEmAnos, taxaJuros, areaConstruida, tamanhoTerreno);
                        break;

                    case "Apartamento":
                        int numeroVagasGaragem = interfaceUsuario.pedirNumeroVagasGaragem();
                        int numeroAndar = interfaceUsuario.pedirNumeroAndar();
                        financiamento = new Apartamento(valorImovel, prazoFinanciamentoEmAnos, taxaJuros, numeroVagasGaragem, numeroAndar);
                        break;

                    case "Terreno":
                        String tipoZonaStr = interfaceUsuario.pedirTipoZona();
                        TipoZona tipoZona = TipoZona.fromString(tipoZonaStr);
                        financiamento = new Terreno(valorImovel, prazoFinanciamentoEmAnos, taxaJuros, tipoZona);
                        break;

                    default:
                        throw new IllegalArgumentException("Tipo de financiamento desconhecido: " + nomeFinanciamento);
                }

                financiamentoList.add(financiamento);

            } catch (Exception e) {
                throw new RuntimeException("Erro ao criar financiamento de " + nomeFinanciamento, e);
            }
        }

        mostrarResumoFinanciamentos(financiamentoList, interfaceUsuario, nomeFinanciamento);
    }

    //função para mostrar total que recebe o campo e o texto com o nome do campo.
    public static void mostrarTotal(List<? extends Financiamento> lista, String nomeCampo, java.util.function.ToDoubleFunction<Financiamento> extractor) {
        double total = lista.stream().mapToDouble(extractor).sum();
        System.out.println("Valor total de " + nomeCampo + ": R$ " + String.format("%,.2f", total));
    }

    public static void mostrarResumoFinanciamentos(
            List<? extends Financiamento> lista,
            InterfaceUsuario interfaceUsuario,
            String nomeFinanciamento
    ) {

        System.out.println("\n=== Resumo dos Financiamentos de " + nomeFinanciamento + " ===");
        interfaceUsuario.mostrarSeparador();

        int count = 1;
        for (Financiamento financiamento : lista) {
            financiamento.mostrarDadosFinanciamento(nomeFinanciamento, count);
            count ++;
        }

        interfaceUsuario.criarEspaco();
        mostrarTotal(lista, "imóveis de " + nomeFinanciamento, Financiamento::getValorImovel);
        mostrarTotal(lista, "financiamentos de " + nomeFinanciamento, Financiamento::calculoTotalPagamento);

        interfaceUsuario.mostrarSeparador();
        interfaceUsuario.criarEspaco();
    }

}
