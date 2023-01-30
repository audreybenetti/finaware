package br.com.finaware.business.produto;

import br.com.finaware.api.DTO.ProdutoInputDTO;
import br.com.finaware.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoBO criarProduto(ProdutoInputDTO produtoInputDTO){
        return produtoRepository.save(produtoInputDTO);
    }

    public ProdutoBO buscarProduto(Integer id){
        return produtoRepository.findById(id);
    }

    public ProdutoBO atualizarProduto(Integer id, ProdutoInputDTO produtoInputDTO) {
        return produtoRepository.updateProduto(id, produtoInputDTO);
    }

    public void deletarProduto(Integer id){
        produtoRepository.deleteProduto(id);
    }

    public List<ProdutoBO> listarProdutos() {
        return produtoRepository.listaProdutos();
    }
}
