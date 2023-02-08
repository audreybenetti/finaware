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
public class LancamentoInputDTO {
    private Integer codigoAplicacao;
    private TipoLancamentoDTOEnum tipoLancamento;
    private BigDecimal valorLancamento;
    private LocalDate dataLancamento;
}
