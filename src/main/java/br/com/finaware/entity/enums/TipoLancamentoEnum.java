package br.com.finaware.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TipoLancamentoEnum {
    APLICACAO("A", "Aplicação"),
    RESGATE("R", "Resgate");

    @Getter
    private String codigo;
    @Getter
    private String descricao;
}
