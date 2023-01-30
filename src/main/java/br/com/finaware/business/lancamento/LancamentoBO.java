package br.com.finaware.business.lancamento;

import br.com.finaware.entity.enums.TipoLancamentoEnum;
import br.com.finaware.entity.enums.TipoProdutoEnum;
import lombok.*;

import javax.persistence.Column;
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
