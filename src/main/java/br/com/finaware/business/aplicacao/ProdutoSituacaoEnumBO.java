package br.com.finaware.business.aplicacao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ProdutoSituacaoEnumBO {
    ATIVO("A"),
    INATIVO("I");

    @Getter
    private String codigo;
}
