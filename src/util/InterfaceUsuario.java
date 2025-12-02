package util;

import modelo.Apartamento;
import modelo.Casa;
import modelo.Financiamento;
import modelo.Terreno;

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

    // Métodos específicos para Casa
    public double pedirTamanhoareaConstrucao(Casa casa) {
        while (true) {
            try {
                System.out.println("Digite a área construída (m²): ");
                casa.setTamanhoareaConstrucao(scanner.nextDouble());
                return casa.getTamanhoareaConstrucao();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine();
            }
        }
    }

    public double pedirTamanhoTerreno(Casa casa) {
        while (true) {
            try {
                System.out.println("Digite o tamanho do terreno (m²): ");
                casa.setTamanhoTerreno(scanner.nextDouble());
                return casa.getTamanhoTerreno();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine();
            }
        }
    }

    // Métodos específicos para Apartamento
    public int pedirNumeroVagasGaragem(Apartamento apartamento) {
        while (true) {
            try {
                System.out.println("Digite o número de vagas na garagem: ");
                apartamento.setNumeroVagasGaragem(scanner.nextInt());
                return apartamento.getNumeroVagasGaragem();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine();
            }
        }
    }

    public int pedirNumeroAndar(Apartamento apartamento) {
        while (true) {
            try {
                System.out.println("Digite o número do andar: ");
                apartamento.setNumeroAndar(scanner.nextInt());
                return apartamento.getNumeroAndar();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine();
            }
        }
    }

    // Métodos específicos para Terreno
    public String pedirTipoZona(Terreno terreno) {
        while (true) {
            try {
                System.out.println("Digite o tipo de zona (residencial/comercial): ");
                scanner.nextLine(); // limpa o buffer
                String tipo = scanner.nextLine();
                terreno.setTipoZona(tipo);
                return terreno.getTipoZona();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
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
