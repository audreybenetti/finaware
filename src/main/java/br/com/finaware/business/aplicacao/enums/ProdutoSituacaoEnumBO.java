package br.com.finaware.business.aplicacao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ProdutoSituacaoEnumBO {
    ATIVO("A"),
    INATIVO("I");

    @Getter
    private String codigo;
}
