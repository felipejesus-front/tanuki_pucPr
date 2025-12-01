package main;

import modelo.Apartamento;
import modelo.Casa;
import modelo.Financiamento;
import modelo.Terreno;
import util.InterfaceUsuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class Main {
    public static void main(String[] args) {

        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();

        //crio as listas de financiamentos de qualquer um dos tipos de financiamentos
        List<? extends Financiamento> financiamentoCasa = criarFinanciamentos(interfaceUsuario, 2, "Casa", Casa.class);
        mostrarResumoFinanciamentos(financiamentoCasa, interfaceUsuario);


        List<? extends Financiamento> financiamentoApartamento = criarFinanciamentos(interfaceUsuario, 2,"Apartamento", Apartamento.class);
        mostrarResumoFinanciamentos(financiamentoApartamento, interfaceUsuario);


        List<? extends Financiamento> financiamentoTerreno = criarFinanciamentos(interfaceUsuario, 2, "Terreno", Terreno.class);
        mostrarResumoFinanciamentos(financiamentoTerreno, interfaceUsuario);

    }

    //Fiz uma mudança nesse metodo pra receber também classes que extendem financiamento. Não sabia q dava pra fazer isso, obrigado pelo incentivo!
    public static List<? extends Financiamento> criarFinanciamentos(InterfaceUsuario interfaceUsuario, int quantidade, String NomeFinanciamento, Class<? extends Financiamento> tipoFinanciamento) {
        List<Financiamento> financiamentoList = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            System.out.println("Financiamento  de "+ NomeFinanciamento + " " + (i + 1));
            double valorImovel = interfaceUsuario.pedirValorImovel();
            int prazoFinanciamentoEmAnos = interfaceUsuario.pedirPrazoFinanciamento();
            double taxaJuros = interfaceUsuario.pedirTaxaDeJuros();

            try {
                Financiamento financiamento = tipoFinanciamento
                        .getConstructor(double.class, int.class, double.class)
                        .newInstance(valorImovel, prazoFinanciamentoEmAnos, taxaJuros);
                financiamentoList.add(financiamento);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao criar financiamento de " + NomeFinanciamento, e);
            }
        }
        return financiamentoList;
    }

    //função para mostrar total que recebe o campo e o texto com o nome do campo.
    public static void mostrarTotal(List<? extends Financiamento> lista, String nomeCampo, java.util.function.ToDoubleFunction<Financiamento> extractor) {
        double total = lista.stream().mapToDouble(extractor).sum();
        System.out.println("Valor total de " + nomeCampo + ": R$ " + String.format("%,.2f", total));
    }

    //Se der certo, esse metodo vai mostrar qualquer tipo de financiamento q eu quizer, seja casa, Apartamento ou TErreno, desde que extenda Financiamento.
    public static void mostrarResumoFinanciamentos(
            List<? extends Financiamento> lista,
            InterfaceUsuario interfaceUsuario
    ) {

        interfaceUsuario.mostrarSeparador();
        for (Financiamento financiamento : lista) {
            financiamento.mostrarDadosFinanciamento();
        }
        interfaceUsuario.criarEspaço();
        mostrarTotal(lista, "imóveis", Financiamento::getValorImovel);
        mostrarTotal(lista, "financiamentos", Financiamento::calculoTotalPagamento);

        interfaceUsuario.mostrarSeparador();
        interfaceUsuario.criarEspaço();
    }

}
