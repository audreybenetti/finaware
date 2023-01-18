package br.com.finaware.entity;

import br.com.finaware.entity.enums.TipoLancamentoEnum;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "lancamentos")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigoLancamento;
    @Column(name = "dt_vencimento")
    private Integer codigoAplicacao;
    @Column(name = "tipo_lancamento")
    private TipoLancamentoEnum tipoLancamento;
    @Column(name = "vl_lancamento")
    private BigDecimal valorLancamento;
    @Column(name = "dt_lancamento")
    private LocalDate dataLancamento;

}

