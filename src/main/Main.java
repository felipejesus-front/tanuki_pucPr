package main;

import modelo.Apartamento;
import modelo.AumentoMaiorDoQueJurosException;
import modelo.Casa;
import modelo.Financiamento;
import modelo.Terreno;
import modelo.TipoZona;
import util.InterfaceUsuario;
import util.GerenciadorArquivos;

import java.util.ArrayList;
import java.util.List;




public class Main {
    public static void main(String[] args) {

        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();

        // Lista principal que vai armazenar todos os financiamentos
        List<Financiamento> todosFinanciamentos = new ArrayList<>();

        // Cria as listas de financiamentos de qualquer um dos tipos de financiamentos
        todosFinanciamentos.addAll(criarFinanciamentos(interfaceUsuario, 2, "Casa", Casa.class));
        todosFinanciamentos.addAll(criarFinanciamentos(interfaceUsuario, 2, "Apartamento", Apartamento.class));
        todosFinanciamentos.addAll(criarFinanciamentos(interfaceUsuario, 2, "Terreno", Terreno.class));

        // === OPERAÇÕES DE ARQUIVO ===
        System.out.println("\n\n======================================");
        System.out.println("    OPERAÇÕES DE SALVAMENTO           ");
        System.out.println("======================================");

        // 1. Salvar em arquivo de texto
        GerenciadorArquivos.salvarEmTexto(todosFinanciamentos);

        // 2. Ler arquivo de texto para comprovar
        GerenciadorArquivos.lerDeTexto();

        // 3. Salvar em arquivo serializado
        GerenciadorArquivos.salvarSerializado(todosFinanciamentos);

        // 4. Ler arquivo serializado para comprovar
        List<Financiamento> financiamentosRecuperados = GerenciadorArquivos.lerSerializado();

        System.out.println("\n======================================");
        System.out.println("  VERIFICAÇÃO DE INTEGRIDADE DOS DADOS");
        System.out.println("======================================");
        System.out.println("Total de financiamentos originais: " + todosFinanciamentos.size());
        System.out.println("Total de financiamentos recuperados: " + financiamentosRecuperados.size());

        if (todosFinanciamentos.size() == financiamentosRecuperados.size()) {
            System.out.println("✓ Integridade verificada: Todos os dados foram salvos e recuperados corretamente!");
        } else {
            System.out.println("✗ Erro: Divergência no número de financiamentos!");
        }
    }

    public static List<Financiamento> criarFinanciamentos(
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

            } catch (AumentoMaiorDoQueJurosException e) {
                System.err.println("\n*** ERRO AO CRIAR FINANCIAMENTO DE CASA ***");
                System.err.println(e.getMessage());
                System.err.println("Por favor, tente novamente com valores diferentes (maior prazo ou maior taxa de juros).\n");
                i--; // Decrementa para repetir esta iteração
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
