package br.com.finaware.business.produto;

import br.com.finaware.business.produto.enums.TipoProdutoBOEnum;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProdutoBO {

    private Integer codigoProduto;
    private String nomeProduto;
    private TipoProdutoBOEnum tipoProdutoBOEnum;
    private Double taxaRemuneracao;
    private Integer prazoVencimento;
    private Integer prazoCarencia;

}
