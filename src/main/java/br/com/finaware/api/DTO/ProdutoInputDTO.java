package br.com.finaware.api.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProdutoInputDTO {
    private String nomeProduto;
    private TipoProdutoDTOEnum tipoProdutoDTOEnum;
    private Double taxaRemuneracao;
    private Integer prazoVencimento;
    private Integer prazoCarencia;
}
