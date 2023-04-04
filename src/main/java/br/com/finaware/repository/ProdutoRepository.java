package br.com.finaware.repository;

import br.com.finaware.api.DTO.ProdutoInputDTO;
import br.com.finaware.business.produto.ProdutoBO;
import br.com.finaware.repository.dao.ProdutoDAO;
import br.com.finaware.repository.dao.entity.Produto;
import br.com.finaware.repository.dao.entity.enums.TipoProdutoEnum;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class ProdutoRepository {

    private final ProdutoDAO produtoDAO;
    private final ModelMapper modelMapper = new ModelMapper();

    public ProdutoBO save(ProdutoInputDTO produtoInputDTO){
        Produto produto = modelMapper.map(produtoInputDTO, Produto.class);
        return modelMapper.map(produtoDAO.save(produto), ProdutoBO.class);
    }

    public ProdutoBO findById(Integer id){
        existsById(id);
        return modelMapper.map(produtoDAO.findById(id), ProdutoBO.class);
    }

    public ProdutoBO updateProduto(Integer id, ProdutoInputDTO produtoInputDTO) {
        existsById(id);
        return modelMapper.map(produtoDAO.save((toProdutoAtualizado(id, produtoInputDTO))), ProdutoBO.class);
    }

    public void deleteProduto(Integer id) {
        existsById(id);
        produtoDAO.deleteById(id);
    }

    private void existsById(Integer id){
        if(!produtoDAO.existsById(id)){
            throw new EntityNotFoundException("Produto " + id + " não encontrado.");
        }
    }

    public List<ProdutoBO> listaProdutos() {
        return produtoDAO.findAll().stream()
                .map(produto -> modelMapper.map(produto, ProdutoBO.class))
                .collect(Collectors.toList());
    }
    
    public Produto toProdutoAtualizado(Integer id, ProdutoInputDTO input){
        return Produto.builder()
                .codigoProduto(id)
                .nomeProduto(input.getNomeProduto())
                .tipoProdutoEnum(modelMapper.map(input.getTipoProdutoDTOEnum(), TipoProdutoEnum.class))
                .taxaRemuneracao(input.getTaxaRemuneracao())
                .prazoVencimento(input.getPrazoVencimento())
                .prazoCarencia(input.getPrazoCarencia())
                .build();
    }
}
