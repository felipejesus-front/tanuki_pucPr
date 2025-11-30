package main;

import modelo.Casa;
import modelo.Financiamento;
import util.InterfaceUsuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class Main {
    public static void main(String[] args) {

        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        List<? extends Financiamento> financiamentoList = criarFinanciamentos(interfaceUsuario, 2, Financiamento.class);
        List<? extends Financiamento> financiamentoCasa = criarFinanciamentos(interfaceUsuario, 2, Casa.class);
        for (Financiamento financiamento : financiamentoList) {
            financiamento.mostrarDadosFinanciamento();
        }

        interfaceUsuario.mostrarSeparador();

        mostrarTotal(financiamentoList, "imóveis", Financiamento::getValorImovel);
        mostrarTotal(financiamentoList, "financiamentos", Financiamento::calculoTotalPagamento);

    }

    //Função para criar financiamentos diferentes com opção de quantidade de financiamentos.
    //por enquanto vai ser hardCoded e não por terminal. mas pedir a quantidade pro cliente pode ser util
    public static List<? extends Financiamento> criarFinanciamentos(InterfaceUsuario interfaceUsuario, int quantidade, Class<? extends Financiamento> tipoFinanciamento) {
        List<Financiamento> financiamentoList = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            System.out.println("Financiamento #" + (i + 1));
            double valorImovel = interfaceUsuario.pedirValorImovel();
            int prazoFinanciamentoEmAnos = interfaceUsuario.pedirPrazoFinanciamento();
            double taxaJuros = interfaceUsuario.pedirTaxaDeJuros();

            try {
                Financiamento financiamento = tipoFinanciamento
                        .getConstructor(double.class, int.class, double.class)
                        .newInstance(valorImovel, prazoFinanciamentoEmAnos, taxaJuros);
                financiamentoList.add(financiamento);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao criar financiamento", e);
            }
        }
        return financiamentoList;
    }

    //função para mostrar total que recebe o campo e o texto com o nome do campo.
    public static void mostrarTotal(List<? extends Financiamento> lista, String nomeCampo, java.util.function.ToDoubleFunction<Financiamento> extractor) {
        double total = lista.stream().mapToDouble(extractor).sum();
        System.out.println("Valor total de " + nomeCampo + ": R$ " + String.format("%,.2f", total));
    }

}
