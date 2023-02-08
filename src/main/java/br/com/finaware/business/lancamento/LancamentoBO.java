package br.com.finaware.business.lancamento;

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
public class LancamentoBO {

    private Integer codigoLancamento;
    private Integer codigoAplicacao;
    private TipoLancamentoBOEnum tipoLancamento;
    private BigDecimal valorLancamento;
    private LocalDate dataLancamento;

}
