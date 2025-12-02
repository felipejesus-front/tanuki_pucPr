package modelo;

import java.io.Serializable;

public enum TipoZona implements Serializable {
    RESIDENCIAL("Residencial"),
    COMERCIAL("Comercial");

    private final String descricao;

    TipoZona(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

    // Método auxiliar para converter String em TipoZona (case-insensitive)
    public static TipoZona fromString(String texto) {
        if (texto == null) {
            throw new IllegalArgumentException("O tipo de zona não pode ser nulo.");
        }

        String textoLimpo = texto.trim().toLowerCase();

        if (textoLimpo.equals("residencial")) {
            return RESIDENCIAL;
        } else if (textoLimpo.equals("comercial")) {
            return COMERCIAL;
        } else {
            throw new IllegalArgumentException(
                "Tipo de zona inválido: '" + texto + "'. Use 'residencial' ou 'comercial'."
            );
        }
    }
}

