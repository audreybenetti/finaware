package br.com.finaware.repository.dao.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TipoLancamentoEnum {
    APLICACAO(1, "Aplicação"),
    RESGATE(2, "Resgate"),
    IRF(3, "Imposto de Renda"),
    IOF(4, "Imposto sobre Operações Financeiras");

    @Getter
    private Integer codigo;
    @Getter
    private String descricao;
}
