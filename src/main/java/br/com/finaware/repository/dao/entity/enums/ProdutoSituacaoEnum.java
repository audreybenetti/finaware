package br.com.finaware.repository.dao.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ProdutoSituacaoEnum {
    ATIVO("A"),
    INATIVO("I");

    @Getter
    private String codigo;
}
