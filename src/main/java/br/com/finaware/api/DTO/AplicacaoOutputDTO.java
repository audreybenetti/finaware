package br.com.finaware.api.DTO;

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
public class AplicacaoOutputDTO {
    private Integer codigoAplicacao;
    private String nomeProduto;
    private ProdutoSituacaoEnumDTO situacaoEnum;
    private BigDecimal pu;
    private Long quantidade;
    private Double rentabilidade;
    private BigDecimal valorAplicado;
    private BigDecimal valorResgatado;
    private LocalDate dataAplicacao;
    private LocalDate dataLiquidacao;
    private BigDecimal iof;
    private BigDecimal ir;
}
