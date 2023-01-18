package br.com.finaware.entity;

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
    @Column(name = "taxa_remuneracao")
    private Double taxaRemuneracao;

}
