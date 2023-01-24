package br.com.finaware.entity;

import br.com.finaware.entity.enums.TipoProdutoEnum;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigoProduto;
    @Column(name = "nome_produto")
    private String nomeProduto;
    @Column(name = "tipo_produto")
    private TipoProdutoEnum tipoProdutoEnum;
    @Column(name = "taxa_remuneracao")
    private Double taxaRemuneracao;
    @Column(name = "prazo_vencimento")
    private Integer prazoVencimento;
    @Column(name = "prazo_carencia")
    private Integer prazoCarencia;

}
