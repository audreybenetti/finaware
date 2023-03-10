package br.com.finaware.business.aplicacao;

import br.com.finaware.api.DTO.AplicacaoInputDTO;
import br.com.finaware.repository.AplicacaoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AplicacaoServiceTest {

    @Mock
    private AplicacaoRepository aplicacaoRepository;
    @InjectMocks
    private AplicacaoService aplicacaoService;

    @Test
    public void deveCriarAplicacao(){
        AplicacaoInputDTO input = buildAplicacaoInput();
        Mockito.when(aplicacaoRepository.save(input)).thenReturn(buildAplicacaoBO());
        AplicacaoBO result = aplicacaoService.criarAplicacao(input);
        Assert.assertNotNull(result.getCodigoAplicacao());
    }

    @Test
    public void deveBuscarAplicacao(){
      Mockito.when(aplicacaoRepository.findById(1)).thenReturn(buildAplicacaoBO());
      AplicacaoBO aplicacao = aplicacaoRepository.findById(1);
      long meses = ChronoUnit.MONTHS.between(aplicacao.getDataAplicacao(), aplicacao.getDataLiquidacao());
      BigDecimal valorRendimento = aplicacao.getValorAplicado().multiply((BigDecimal.valueOf(1L).add(BigDecimal.valueOf(aplicacao.getRentabilidade())).multiply(BigDecimal.valueOf(meses))));

      AplicacaoBO result = aplicacaoService.buscarAplicacao(1);
      Assert.assertEquals(valorRendimento, result.getValorComRendimento());
    }

    @Test
    public void deveAtualizarAplicacao(){
        AplicacaoInputDTO input = buildAplicacaoInput();
        Mockito.when(aplicacaoRepository.updateAplicacao(1, input)).thenReturn(buildAplicacaoBO());

        AplicacaoBO result = aplicacaoService.atualizarAplicacao(1, input);
        Assert.assertNotNull(result);
    }

    @Test
    public void deveDeletarAplicacao(){
        Mockito.doNothing().when(aplicacaoRepository).deleteAplicacao(1);
        aplicacaoService.deletarAplicacao(1);
        Mockito.verify(aplicacaoRepository).deleteAplicacao(1);
    }

    @Test
    public void deveListarAplicacoes(){
        Mockito.when(aplicacaoRepository.listaAplicacoes()).thenReturn(Collections.singletonList(buildAplicacaoBO()));
        List<AplicacaoBO> aplicacaoBOList = aplicacaoService.listarAplicacoes();
        Assert.assertNotNull(aplicacaoBOList);
    }

    private AplicacaoInputDTO buildAplicacaoInput() {
        return AplicacaoInputDTO.builder()
                .valorAplicado(BigDecimal.valueOf(1000))
                .rentabilidade(10.0)
                .dataAplicacao(LocalDate.of(2022, 2, 1))
                .dataLiquidacao(LocalDate.of(2023, 2, 1))
                .build();
    }

    private static AplicacaoBO buildAplicacaoBO() {
        return AplicacaoBO.builder()
                .codigoAplicacao(1)
                .valorAplicado(BigDecimal.valueOf(1000))
                .rentabilidade(10.0)
                .dataAplicacao(LocalDate.of(2022, 2, 1))
                .dataLiquidacao(LocalDate.of(2023, 2, 1))
                .build();
    }

}
