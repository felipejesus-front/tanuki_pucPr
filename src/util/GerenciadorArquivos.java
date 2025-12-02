package util;

import modelo.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar a leitura e escrita de financiamentos em arquivos
 */
public class GerenciadorArquivos {

    private static final String ARQUIVO_TEXTO = "financiamentos.txt";
    private static final String ARQUIVO_SERIALIZADO = "financiamentos.dat";

    /**
     * Salva os financiamentos em arquivo de texto formatado
     */
    public static void salvarEmTexto(List<Financiamento> financiamentos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_TEXTO))) {

            writer.write("=== DADOS DOS FINANCIAMENTOS ===\n");
            writer.write("Formato: TipoFinanciamento;ValorImovel;ValorFinanciamento;TaxaJuros;PrazoAnos;AtributosEspecificos\n\n");

            for (Financiamento f : financiamentos) {
                String linha = formatarFinanciamento(f);
                writer.write(linha);
                writer.newLine();
            }

            System.out.println("\n✓ Dados salvos com sucesso em arquivo de texto: " + ARQUIVO_TEXTO);

        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo de texto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Formata um financiamento para uma linha de texto
     */
    private static String formatarFinanciamento(Financiamento f) {
        StringBuilder sb = new StringBuilder();

        // Dados comuns
        String tipoFinanciamento = f.getClass().getSimpleName();
        sb.append(tipoFinanciamento).append(";");
        sb.append(String.format("%.2f", f.getValorImovel())).append(";");
        sb.append(String.format("%.2f", f.calculoTotalPagamento())).append(";");
        sb.append(String.format("%.2f", f.getTaxaJurosAnual())).append(";");
        sb.append(f.getPrazoFinanciamento()).append(";");

        // Atributos específicos por tipo
        if (f instanceof Casa) {
            Casa casa = (Casa) f;
            sb.append("AreaConstruida=").append(String.format("%.2f", casa.getTamanhoareaConstrucao()));
            sb.append(";TamanhoTerreno=").append(String.format("%.2f", casa.getTamanhoTerreno()));

        } else if (f instanceof Apartamento) {
            Apartamento apt = (Apartamento) f;
            sb.append("NumeroVagas=").append(apt.getNumeroVagasGaragem());
            sb.append(";NumeroAndar=").append(apt.getNumeroAndar());

        } else if (f instanceof Terreno) {
            Terreno terreno = (Terreno) f;
            sb.append("TipoZona=").append(terreno.getTipoZona());
        }

        return sb.toString();
    }

    /**
     * Lê os financiamentos do arquivo de texto e exibe no console
     */
    public static void lerDeTexto() {
        System.out.println("\n=== LENDO DADOS DO ARQUIVO DE TEXTO ===");

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_TEXTO))) {
            String linha;
            int count = 0;

            while ((linha = reader.readLine()) != null) {
                if (!linha.isEmpty() && !linha.startsWith("===") && !linha.startsWith("Formato:")) {
                    count++;
                    System.out.println("Linha " + count + ": " + linha);
                    interpretarLinha(linha);
                }
            }

            System.out.println("\n✓ Total de " + count + " financiamentos lidos do arquivo de texto");

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + ARQUIVO_TEXTO);
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo de texto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Interpreta e exibe uma linha do arquivo de texto
     */
    private static void interpretarLinha(String linha) {
        try {
            String[] partes = linha.split(";");

            if (partes.length < 5) {
                System.out.println("  [Linha inválida - formato incorreto]");
                return;
            }

            String tipo = partes[0];
            String valorImovel = partes[1];
            String valorFinanciamento = partes[2];
            String taxaJuros = partes[3];
            String prazoAnos = partes[4];

            System.out.println("  → Tipo: " + tipo);
            System.out.println("    Valor Imóvel: R$ " + valorImovel);
            System.out.println("    Valor Total Financiamento: R$ " + valorFinanciamento);
            System.out.println("    Taxa de Juros: " + taxaJuros + "%");
            System.out.println("    Prazo: " + prazoAnos + " anos");

            // Atributos específicos
            for (int i = 5; i < partes.length; i++) {
                String[] atributo = partes[i].split("=");
                if (atributo.length == 2) {
                    System.out.println("    " + atributo[0] + ": " + atributo[1]);
                }
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println("  [Erro ao interpretar linha: " + e.getMessage() + "]");
        }
    }

    /**
     * Salva os financiamentos em arquivo serializado
     */
    public static void salvarSerializado(List<Financiamento> financiamentos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_SERIALIZADO))) {

            oos.writeObject(financiamentos);

            System.out.println("\n✓ Dados serializados salvos com sucesso: " + ARQUIVO_SERIALIZADO);
            System.out.println("  Total de objetos serializados: " + financiamentos.size());

        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo serializado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Lê os financiamentos do arquivo serializado
     */
    @SuppressWarnings("unchecked")
    public static List<Financiamento> lerSerializado() {
        System.out.println("\n=== LENDO DADOS DO ARQUIVO SERIALIZADO ===");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_SERIALIZADO))) {

            List<Financiamento> financiamentos = (List<Financiamento>) ois.readObject();

            System.out.println("✓ Dados desserializados com sucesso!");
            System.out.println("  Total de objetos recuperados: " + financiamentos.size());

            // Exibe os dados recuperados
            System.out.println("\n--- Dados Recuperados do Arquivo Serializado ---");
            int count = 1;
            for (Financiamento f : financiamentos) {
                String tipo = f.getClass().getSimpleName();
                f.mostrarDadosFinanciamento(tipo, count);
                count++;
            }

            return financiamentos;

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo serializado não encontrado: " + ARQUIVO_SERIALIZADO);
            return new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo serializado: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        } catch (ClassNotFoundException e) {
            System.err.println("Erro: Classe não encontrada ao desserializar: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Retorna o nome do arquivo de texto
     */
    public static String getArquivoTexto() {
        return ARQUIVO_TEXTO;
    }

    /**
     * Retorna o nome do arquivo serializado
     */
    public static String getArquivoSerializado() {
        return ARQUIVO_SERIALIZADO;
    }
}

