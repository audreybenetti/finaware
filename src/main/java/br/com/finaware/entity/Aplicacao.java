package br.com.finaware.entity;

import br.com.finaware.entity.enums.ProdutoSituacaoEnum;
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
@Table(name = "aplicacoes")
public class Aplicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigoAplicacao;
    @Column(name = "nome_produto")
    private String nomeProduto;
    @Column(name = "situacao")
    private ProdutoSituacaoEnum situacaoEnum;
    @Column(name = "pu")
    private BigDecimal pu;
    @Column(name = "quantidade")
    private Long quantidade;
    @Column(name = "rentabilidade")
    private Double rentabilidade;
    @Column(name = "vl_aplicado")
    private BigDecimal valorAplicado;
    @Column(name = "vl_resgatado")
    private BigDecimal valorResgatado;
    @Column(name = "dt_aplicacao")
    private LocalDate dataAplicacao;
    @Column(name = "dt_liquidacao")
    private LocalDate dataLiquidacao;
    @Column(name = "prazo_vencimento")
    private Integer prazoVencimento;
    @Column(name = "prazo_carencia")
    private Integer prazoCarencia;
    @Column(name = "iof")
    private BigDecimal iof;
    @Column(name = "irf")
    private BigDecimal ir;

}
