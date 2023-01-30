package br.com.finaware.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TipoLancamentoDTOEnum {
    APLICACAO("A", "Aplicação"),
    RESGATE("R", "Resgate"),
    IRF("IRF", "Imposto de Renda"),
    IOF("IOF", "Imposto sobre Operações Financeiras");

    @Getter
    private String codigo;
    @Getter
    private String descricao;
}
