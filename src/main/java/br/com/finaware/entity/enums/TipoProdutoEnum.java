package br.com.finaware.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TipoProdutoEnum {
    PRE_FIXADO(1, "Pré-fixado"),
    POS_FIXADO(2, "Pós-fixado");

    @Getter
    private Integer codigo;
    @Getter
    private String descricao;
}
