package util;

import modelo.Financiamento;

import java.util.Scanner;

public class InterfaceUsuario {

    Scanner scanner = new Scanner(System.in);
    Financiamento financiamento = new Financiamento(0, 0, 0);

    public double pedirValorImovel(){
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

    public int pedirPrazoFinanciamento(){

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

    public double pedirTaxaDeJuros(){
        while (true) {
            try {
                System.out.println("Digite o valor da taxa de juros: ");
                financiamento.setTaxaJurosAnual(scanner.nextDouble());
                return financiamento.getValorImovel();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine(); // limpa o buffer
            }

        }
    }

    public void criarEspaço() {
        System.out.println();
    }

        public void mostrarSeparador() {
        System.out.println("================================");
    }
}
