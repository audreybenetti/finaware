package br.com.finaware.business.aplicacao;

import br.com.finaware.repository.AplicacaoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

public class AplicacaoServiceTest {

    @Mock
    private AplicacaoRepository aplicacaoRepository;

    @InjectMocks
    private AplicacaoService aplicacaoService;

    @Test
    public void deveBuscarAplicacao(){
      AplicacaoBO aplicacao = aplicacaoRepository.findById(1);
      long meses = ChronoUnit.MONTHS.between(aplicacao.getDataAplicacao(), aplicacao.getDataLiquidacao());
      BigDecimal valorRendimento = aplicacao.getValorAplicado().multiply((BigDecimal.valueOf(1L).add(BigDecimal.valueOf(aplicacao.getRentabilidade())).multiply(BigDecimal.valueOf(meses))));

      AplicacaoBO result = aplicacaoService.buscarAplicacao(1);
      Assert.assertEquals(valorRendimento, result.getValorComRendimento());
    }

}
