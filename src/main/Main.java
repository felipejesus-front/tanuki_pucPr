package main;

import modelo.Apartamento;
import modelo.Casa;
import modelo.Financiamento;
import modelo.Terreno;
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

    public static List<? extends Financiamento> criarFinanciamentos(
            InterfaceUsuario interfaceUsuario,
            int quantidade,
            String nomeFinanciamento,
            Class<? extends Financiamento> tipoFinanciamento) {

        List<Financiamento> financiamentoList = new ArrayList<>();

        System.out.println("\n=== Criando Financiamentos de " + nomeFinanciamento + " ===\n");

        for (int i = 0; i < quantidade; i++) {
            System.out.println("Financiamento de " + nomeFinanciamento + " " + (i + 1));

            try {
                // Cria instância temporária para coletar dados básicos
                Financiamento financiamentoTemp = tipoFinanciamento
                        .getConstructor(double.class, int.class, double.class)
                        .newInstance(0.0, 1, 0.0);

                // Coleta dados básicos (comuns a todos os financiamentos)
                double valorImovel = interfaceUsuario.pedirValorImovel(financiamentoTemp);
                int prazoFinanciamentoEmAnos = interfaceUsuario.pedirPrazoFinanciamento(financiamentoTemp);
                double taxaJuros = interfaceUsuario.pedirTaxaDeJuros(financiamentoTemp);

                // Switch para coletar dados específicos e criar instância final
                Financiamento financiamento;
                switch (nomeFinanciamento) {
                    case "Casa":
                        Casa casaTemp = (Casa) financiamentoTemp;
                        double tamanhoareaConstrucao = interfaceUsuario.pedirTamanhoareaConstrucao(casaTemp);
                        double tamanhoTerreno = interfaceUsuario.pedirTamanhoTerreno(casaTemp);
                        financiamento = new Casa(valorImovel, prazoFinanciamentoEmAnos, taxaJuros, tamanhoareaConstrucao, tamanhoTerreno);
                        break;

                    case "Apartamento":
                        Apartamento apartamentoTemp = (Apartamento) financiamentoTemp;
                        int numeroVagasGaragem = interfaceUsuario.pedirNumeroVagasGaragem(apartamentoTemp);
                        int numeroAndar = interfaceUsuario.pedirNumeroAndar(apartamentoTemp);
                        financiamento = new Apartamento(valorImovel, prazoFinanciamentoEmAnos, taxaJuros, numeroVagasGaragem, numeroAndar);
                        break;

                    case "Terreno":
                        Terreno terrenoTemp = (Terreno) financiamentoTemp;
                        String tipoZona = interfaceUsuario.pedirTipoZona(terrenoTemp);
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
        return financiamentoList;
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
