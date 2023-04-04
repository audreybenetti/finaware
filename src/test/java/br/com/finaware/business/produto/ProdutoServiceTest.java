package br.com.finaware.business.produto;

import br.com.finaware.api.DTO.ProdutoInputDTO;
import br.com.finaware.api.DTO.TipoProdutoDTOEnum;
import br.com.finaware.business.produto.enums.TipoProdutoBOEnum;
import br.com.finaware.repository.ProdutoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;
    @InjectMocks
    private ProdutoService produtoService;

    @Test
    public void deveCriarProduto(){
        ProdutoInputDTO input = buildProdutoInput();
        Mockito.when(produtoRepository.save(input)).thenReturn(buildProdutoBO());
        ProdutoBO result = produtoService.criarProduto(input);
        Assert.assertNotNull(result.getCodigoProduto());
    }

    @Test
    public void deveBuscarProduto(){
        Mockito.when(produtoRepository.findById(1)).thenReturn(buildProdutoBO());
        ProdutoBO produto = produtoRepository.findById(1);

        ProdutoBO result = produtoService.buscarProduto(1);
        assertEquals(produto, result);
    }

    @Test
    public void deveAtualizarProduto(){
        ProdutoInputDTO input = buildProdutoInput();
        Mockito.when(produtoRepository.updateProduto(1, input)).thenReturn(buildProdutoBO());

        ProdutoBO result = produtoService.atualizarProduto(1, input);
        Assert.assertNotNull(result);
    }

    @Test
    public void deveDeletarProduto(){
        Mockito.doNothing().when(produtoRepository).deleteProduto(1);
        produtoService.deletarProduto(1);
        Mockito.verify(produtoRepository).deleteProduto(1);
    }

    @Test
    public void deveListarProdutos(){
        Mockito.when(produtoRepository.listaProdutos()).thenReturn(Collections.singletonList(buildProdutoBO()));
        List<ProdutoBO> produtoBOList = produtoService.listarProdutos();
        Assert.assertNotNull(produtoBOList);
    }

    private ProdutoInputDTO buildProdutoInput() {
        return ProdutoInputDTO.builder()
                .nomeProduto("LCI Premium")
                .tipoProdutoDTOEnum((TipoProdutoDTOEnum.PRE_FIXADO))
                .taxaRemuneracao(10.0)
                .prazoVencimento(1080)
                .prazoCarencia(90)
                .build();
    }

    private static ProdutoBO buildProdutoBO() {
        return ProdutoBO.builder()
                .codigoProduto(1)
                .nomeProduto("LCI Premium")
                .tipoProdutoBOEnum((TipoProdutoBOEnum.PRE_FIXADO))
                .taxaRemuneracao(10.0)
                .prazoVencimento(1080)
                .prazoCarencia(90)
                .build();
    }
}
