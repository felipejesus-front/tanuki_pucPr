package util;

import modelo.Financiamento;

import java.util.Scanner;

public class InterfaceUsuario {

    Scanner scanner = new Scanner(System.in);

    public double pedirValorImovel(Financiamento financiamento){
        while (true){
            try{
                System.out.println("Digite o valor do imóvel: ");
                financiamento.setValorImovel(scanner.nextDouble());
                return financiamento.getValorImovel();
            }
            catch (IllegalArgumentException e ) {
                System.out.println(e.getMessage());
            }
            catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine(); // limpa o buffer
            }
        }
    }

    public int pedirPrazoFinanciamento(Financiamento financiamento){
        while (true){
            try{
                System.out.println("Digite o prazo do financiamento em anos: ");
                financiamento.setPrazoFinanciamento(scanner.nextInt());
                return financiamento.getPrazoFinanciamento();
            }
            catch (IllegalArgumentException e ) {
                System.out.println(e.getMessage());
            }
            catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine(); // limpa o buffer
            }
        }
    }

    public double pedirTaxaDeJuros(Financiamento financiamento){
        while (true) {
            try {
                System.out.println("Digite o valor da taxa de juros: ");
                financiamento.setTaxaJurosAnual(scanner.nextDouble());
                return financiamento.getTaxaJurosAnual();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine(); // limpa o buffer
            }
        }
    }

    public void criarEspaco() {
        System.out.println();
    }

        public void mostrarSeparador() {
        System.out.println("================================");
    }
}
