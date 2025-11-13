package main;

import modelo.Financiamento;
import util.InterfaceUsuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class Main {
    public static void main(String[] args) {

        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        List<Financiamento> financiamentoList = criarFinanciamentos(interfaceUsuario, 4);

        for (Financiamento financiamento : financiamentoList) {
            financiamento.mostrarDadosFinanciamento();
        }

        interfaceUsuario.mostrarSeparador();

        mostrarTotal(financiamentoList, "imóveis", Financiamento::getValorImovel);
        mostrarTotal(financiamentoList, "financiamentos", Financiamento::calculoTotalPagamento);

    }

    //Função para criar financiamentos diferentes com opção de quantidade de financiamentos.
    //por enquanto vai ser hardCoded e não por terminal. mas pedir a quantidade pro cliente pode ser util
    public static List<Financiamento> criarFinanciamentos(InterfaceUsuario interfaceUsuario, int quantidade) {
        List<Financiamento> financiamentoList = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            System.out.println("Financiamento #" + (i + 1));
            double valorImovel = interfaceUsuario.pedirValorImovel();
            int prazoFinanciamentoEmAnos = interfaceUsuario.pedirPrazoFinanciamento();
            double taxaJuros = interfaceUsuario.pedirTaxaDeJuros();

            financiamentoList.add(new Financiamento(valorImovel, prazoFinanciamentoEmAnos, taxaJuros));
        }
        return financiamentoList;
    }

    public static void mostrarTotal(List<Financiamento> lista, String nomeCampo, java.util.function.ToDoubleFunction<Financiamento> extractor) {
        double total = lista.stream().mapToDouble(extractor).sum();
        System.out.println("Valor total de " + nomeCampo + ": R$ " + String.format("%,.2f", total));
    }

}
