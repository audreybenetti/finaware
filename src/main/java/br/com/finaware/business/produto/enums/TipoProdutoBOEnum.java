package br.com.finaware.business.produto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TipoProdutoBOEnum {
    PRE_FIXADO(1, "Pré-fixado"),
    POS_FIXADO(2, "Pós-fixado");

    @Getter
    private Integer codigo;
    @Getter
    private String descricao;
}
