package br.com.finaware.repository;

import br.com.finaware.api.DTO.ProdutoInputDTO;
import br.com.finaware.api.DTO.TipoProdutoDTOEnum;
import br.com.finaware.business.produto.ProdutoBO;
import br.com.finaware.repository.dao.ProdutoDAO;
import br.com.finaware.repository.dao.entity.Produto;
import br.com.finaware.repository.dao.entity.enums.TipoProdutoEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoRepositoryTest {
    @InjectMocks
    private ProdutoRepository produtoRepository;
    @Mock
    private ProdutoDAO produtoDAO;
    private final ModelMapper mapper = new ModelMapper();

    @Test
    public void deveSalvarProduto(){
        ProdutoInputDTO input = buildProdutoDTO();
        Produto produto = mapper.map(input, Produto.class);
        when(produtoDAO.save(produto)).thenReturn(produto);
        ProdutoBO produtoBO = mapper.map(produto, ProdutoBO.class);

        ProdutoBO result = produtoRepository.save(input);
        Assert.assertEquals(result, produtoBO);
    }

    @Test
    public void deveBuscarProdutoPorId(){
        Produto produto = mapper.map(buildProdutoDTO(), Produto.class);
        produto.setCodigoProduto(1);
        when(produtoDAO.existsById(1)).thenReturn(true);
        when(produtoDAO.findById(1)).thenReturn(Optional.of(produto));
        ProdutoBO produtoBO = mapper.map(produto, ProdutoBO.class);

        ProdutoBO result = produtoRepository.findById(1);
        Assert.assertEquals(result, produtoBO);
    }

    @Test(expected = NoSuchElementException.class)
    public void deveBuscarProdutoPorIdErroNotFound(){
        when(produtoDAO.existsById(1)).thenReturn(false);
        produtoRepository.findById(1);
    }

    @Test
    public void deveAlterarProduto(){
        Produto produto = mapper.map(buildProdutoDTO(), Produto.class);

        when(produtoDAO.existsById(1)).thenReturn(true);
        when(produtoDAO.save(toProdutoAtualizado(1, buildProdutoDTO()))).thenReturn(produto);
        ProdutoBO produtoBO = mapper.map(produto, ProdutoBO.class);

        ProdutoBO result = produtoRepository.updateProduto(1, buildProdutoDTO());
        Assert.assertEquals(result, produtoBO);
    }

    @Test(expected = NoSuchElementException.class)
    public void deveAlterarAplicacaoErroNotFound(){
        when(produtoDAO.existsById(1)).thenReturn(false);
        produtoRepository.updateProduto(1, buildProdutoDTO());
    }

    @Test
    public void deveDeletarAplicacao(){
        when(produtoDAO.existsById(1)).thenReturn(true);
        produtoRepository.deleteProduto(1);
        verify(produtoDAO).deleteById(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void deveDeletarAplicacaoErroNotFound(){
        when(produtoDAO.existsById(1)).thenReturn(false);
        produtoRepository.deleteProduto(1);
    }

    @Test
    public void deveListarAplicacoes(){
        Produto produto =  mapper.map(buildProdutoDTO(), Produto.class);
        List<ProdutoBO> aplicacaoList = Collections.singletonList(mapper.map(produto, ProdutoBO.class));

        when(produtoDAO.findAll()).thenReturn(Collections.singletonList(produto));

        List<ProdutoBO> result = produtoRepository.listaProdutos();
        assertEquals(aplicacaoList, result);
    }

    public Produto toProdutoAtualizado(Integer id, ProdutoInputDTO input){
        return Produto.builder()
                .codigoProduto(id)
                .nomeProduto(input.getNomeProduto())
                .tipoProdutoEnum(mapper.map(input.getTipoProdutoDTOEnum(), TipoProdutoEnum.class))
                .taxaRemuneracao(input.getTaxaRemuneracao())
                .prazoVencimento(input.getPrazoVencimento())
                .prazoCarencia(input.getPrazoCarencia())
                .build();
    }

    private ProdutoInputDTO buildProdutoDTO() {
        return ProdutoInputDTO.builder()
                .nomeProduto("LCI Premium")
                .tipoProdutoDTOEnum(TipoProdutoDTOEnum.PRE_FIXADO)
                .taxaRemuneracao(10.0)
                .prazoVencimento(1080)
                .prazoCarencia(90)
                .build();
    }
}
