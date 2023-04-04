package br.com.finaware.business.lancamento;

import br.com.finaware.api.DTO.LancamentoInputDTO;
import br.com.finaware.api.DTO.TipoLancamentoDTOEnum;
import br.com.finaware.business.lancamento.enums.TipoLancamentoBOEnum;
import br.com.finaware.repository.LancamentoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LancamentoServiceTest {

    @Mock
    private LancamentoRepository lancamentoRepository;
    @InjectMocks
    private LancamentoService lancamentoService;

    @Test
    public void deveCriarLancamento(){
        LancamentoInputDTO input = buildLancamentoInput();
        Mockito.when(lancamentoRepository.save(input)).thenReturn(buildLancamentoBO());
        LancamentoBO result = lancamentoService.criarLancamento(input);
        Assert.assertNotNull(result.getCodigoLancamento());
    }

    @Test
    public void deveBuscarLancamento(){
        Mockito.when(lancamentoRepository.findById(1)).thenReturn(buildLancamentoBO());
        LancamentoBO lancamento = lancamentoRepository.findById(1);

        LancamentoBO result = lancamentoService.buscarLancamento(1);
        assertEquals(lancamento, result);
    }

    @Test
    public void deveAtualizarLancamento(){
        LancamentoInputDTO input = buildLancamentoInput();
        Mockito.when(lancamentoRepository.updateLancamento(1, input)).thenReturn(buildLancamentoBO());

        LancamentoBO result = lancamentoService.atualizarLancamento(1, input);
        Assert.assertNotNull(result);
    }

    @Test
    public void deveDeletarLancamento(){
        Mockito.doNothing().when(lancamentoRepository).deleteLancamento(1);
        lancamentoService.deletarLancamento(1);
        Mockito.verify(lancamentoRepository).deleteLancamento(1);
    }

    @Test
    public void deveListarLancamentos(){
        Mockito.when(lancamentoRepository.listaLancamentos()).thenReturn(Collections.singletonList(buildLancamentoBO()));
        List<LancamentoBO> lancamentoBOList = lancamentoService.listarLancamentos();
        Assert.assertNotNull(lancamentoBOList);
    }

    private LancamentoInputDTO buildLancamentoInput() {
        return LancamentoInputDTO.builder()
                .codigoAplicacao(1)
                .tipoLancamento(TipoLancamentoDTOEnum.IOF)
                .valorLancamento(BigDecimal.valueOf(1000))
                .dataLancamento(LocalDate.now())
                .build();
    }

    private static LancamentoBO buildLancamentoBO() {
        return LancamentoBO.builder()
                .codigoLancamento(1)
                .codigoAplicacao(1)
                .tipoLancamento(TipoLancamentoBOEnum.IOF)
                .valorLancamento(BigDecimal.valueOf(1000))
                .dataLancamento(LocalDate.now())
                .build();
    }
}
