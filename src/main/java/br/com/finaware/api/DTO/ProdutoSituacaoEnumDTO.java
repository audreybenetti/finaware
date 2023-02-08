package br.com.finaware.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ProdutoSituacaoEnumDTO {
    ATIVO("A"),
    INATIVO("I");

    @Getter
    private String codigo;
}
