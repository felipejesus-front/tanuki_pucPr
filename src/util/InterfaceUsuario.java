package util;

import java.util.Scanner;

public class InterfaceUsuario {

    Scanner scanner = new Scanner(System.in);

    // Métodos genéricos que retornam valores validados
    public double pedirValorImovel() {
        while (true) {
            try {
                System.out.println("Digite o valor do imóvel: ");
                double valor = scanner.nextDouble();
                if (valor <= 0) {
                    throw new IllegalArgumentException("O valor do imóvel deve ser maior que zero.");
                }
                return valor;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine(); // limpa o buffer
            } finally {
                // O finally é executado independente de ocorrer exceção ou não
                System.out.flush(); // garante que a saída seja exibida
            }
        }
    }

    public int pedirPrazoFinanciamento() {
        while (true) {
            try {
                System.out.println("Digite o prazo do financiamento em anos: ");
                int prazo = scanner.nextInt();
                if (prazo <= 0) {
                    throw new IllegalArgumentException("O prazo do financiamento deve ser maior que zero.");
                }
                if (prazo > 50) {
                    throw new IllegalArgumentException("O prazo informado é irreal. Informe um prazo menor que 50 anos.");
                }
                return prazo;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine(); // limpa o buffer
            } finally {
                System.out.flush();
            }
        }
    }

    public double pedirTaxaDeJuros() {
        while (true) {
            try {
                System.out.println("Digite o valor da taxa de juros: ");
                double taxa = scanner.nextDouble();
                if (taxa < 0) {
                    throw new IllegalArgumentException("A taxa de juros anual não pode ser negativa.");
                }
                if (taxa > 30) {
                    throw new IllegalArgumentException("Taxa de juros irreal para financiamento imobiliário. Informe um valor entre 3% e 30% ao ano.");
                }
                return taxa;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine(); // limpa o buffer
            } finally {
                System.out.flush();
            }
        }
    }

    // Métodos específicos para Casa
    public double pedirAreaConstruida() {
        while (true) {
            try {
                System.out.println("Digite a área construída (m²): ");
                double area = scanner.nextDouble();
                if (area <= 0) {
                    throw new IllegalArgumentException("A área construída deve ser maior que zero.");
                }
                return area;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine();
            } finally {
                System.out.flush();
            }
        }
    }

    public double pedirTamanhoTerreno(double areaConstruida) {
        while (true) {
            try {
                System.out.println("Digite o tamanho do terreno (m²): ");
                double tamanho = scanner.nextDouble();
                if (tamanho <= 0) {
                    throw new IllegalArgumentException("O tamanho do terreno deve ser maior que zero.");
                }
                if (tamanho < areaConstruida) {
                    throw new IllegalArgumentException("O tamanho do terreno não pode ser menor que a área construída.");
                }
                return tamanho;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine();
            } finally {
                System.out.flush();
            }
        }
    }

    // Métodos específicos para Apartamento
    public int pedirNumeroVagasGaragem() {
        while (true) {
            try {
                System.out.println("Digite o número de vagas na garagem: ");
                int vagas = scanner.nextInt();
                if (vagas < 0) {
                    throw new IllegalArgumentException("O número de vagas na garagem não pode ser negativo.");
                }
                return vagas;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine();
            } finally {
                System.out.flush();
            }
        }
    }

    public int pedirNumeroAndar() {
        while (true) {
            try {
                System.out.println("Digite o número do andar: ");
                int andar = scanner.nextInt();
                if (andar < 0) {
                    throw new IllegalArgumentException("O número do andar não pode ser negativo.");
                }
                return andar;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine();
            } finally {
                System.out.flush();
            }
        }
    }

    // Métodos específicos para Terreno
    public String pedirTipoZona() {
        while (true) {
            try {
                System.out.println("Digite o tipo de zona (residencial/comercial): ");
                scanner.nextLine(); // limpa o buffer
                String tipo = scanner.nextLine();
                if (tipo == null || tipo.trim().isEmpty()) {
                    throw new IllegalArgumentException("O tipo de zona não pode ser vazio.");
                }
                String textoLimpo = tipo.trim().toLowerCase();
                if (!textoLimpo.equals("residencial") && !textoLimpo.equals("comercial")) {
                    throw new IllegalArgumentException("Tipo de zona inválido. Use 'residencial' ou 'comercial'.");
                }
                return tipo;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Entrada inválida. Tente novamente.");
            } finally {
                System.out.flush();
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

