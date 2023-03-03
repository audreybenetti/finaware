package br.com.finaware.business.aplicacao;

import br.com.finaware.business.aplicacao.enums.ProdutoSituacaoEnumBO;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AplicacaoBO {

    private Integer codigoAplicacao;
    private String nomeProduto;
    private ProdutoSituacaoEnumBO situacaoEnum;
    private BigDecimal pu;
    private Long quantidade;
    private Double rentabilidade;
    private BigDecimal valorAplicado;
    private BigDecimal valorResgatado;
    private BigDecimal valorComRendimento;
    private LocalDate dataAplicacao;
    private LocalDate dataLiquidacao;
    private BigDecimal iof;
    private BigDecimal ir;
}
