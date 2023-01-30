package br.com.finaware.business.lancamento;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TipoLancamentoBOEnum {
    APLICACAO("A", "Aplicação"),
    RESGATE("R", "Resgate"),
    IRF("IRF", "Imposto de Renda"),
    IOF("IOF", "Imposto sobre Operações Financeiras");

    @Getter
    private String codigo;
    @Getter
    private String descricao;
}
