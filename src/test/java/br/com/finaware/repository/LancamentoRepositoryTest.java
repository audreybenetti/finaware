package br.com.finaware.repository;

import br.com.finaware.api.DTO.LancamentoInputDTO;
import br.com.finaware.api.DTO.TipoLancamentoDTOEnum;
import br.com.finaware.business.lancamento.LancamentoBO;
import br.com.finaware.repository.dao.LancamentoDAO;
import br.com.finaware.repository.dao.entity.Lancamento;
import br.com.finaware.repository.dao.entity.enums.TipoLancamentoEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LancamentoRepositoryTest {
    @InjectMocks
    private LancamentoRepository lancamentoRepository;
    @Mock
    private LancamentoDAO lancamentoDAO;
    private final ModelMapper mapper = new ModelMapper();

    @Test
    public void deveSalvarLancamento(){
        LancamentoInputDTO input = buildLancamentoDTO();
        Lancamento lancamento = mapper.map(input, Lancamento.class);
        when(lancamentoDAO.save(lancamento)).thenReturn(lancamento);
        LancamentoBO lancamentoBO = mapper.map(lancamento, LancamentoBO.class);

        LancamentoBO result = lancamentoRepository.save(input);
        Assert.assertEquals(result, lancamentoBO);
    }

    @Test
    public void deveBuscarLancamentoPorId(){
        Lancamento lancamento = mapper.map(buildLancamentoDTO(), Lancamento.class);
        lancamento.setCodigoLancamento(1);
        when(lancamentoDAO.existsById(1)).thenReturn(true);
        when(lancamentoDAO.findById(1)).thenReturn(Optional.of(lancamento));
        LancamentoBO lancamentoBO = mapper.map(lancamento, LancamentoBO.class);

        LancamentoBO result = lancamentoRepository.findById(1);
        Assert.assertEquals(result, lancamentoBO);
    }

    @Test(expected = NoSuchElementException.class)
    public void deveBuscarLancamentoPorIdErroNotFound(){
        when(lancamentoDAO.existsById(1)).thenReturn(false);
        lancamentoRepository.findById(1);
    }

    @Test
    public void deveAlterarLancamento(){
        Lancamento lancamento = mapper.map(buildLancamentoDTO(), Lancamento.class);

        when(lancamentoDAO.existsById(1)).thenReturn(true);
        when(lancamentoDAO.save(toLancamentoAtualizado(1, buildLancamentoDTO()))).thenReturn(lancamento);
        LancamentoBO lancamentoBO = mapper.map(lancamento, LancamentoBO.class);

        LancamentoBO result = lancamentoRepository.updateLancamento(1, buildLancamentoDTO());
        Assert.assertEquals(result, lancamentoBO);
    }

    @Test(expected = NoSuchElementException.class)
    public void deveAlterarAplicacaoErroNotFound(){
        when(lancamentoDAO.existsById(1)).thenReturn(false);
        lancamentoRepository.updateLancamento(1, buildLancamentoDTO());
    }

    @Test
    public void deveDeletarAplicacao(){
        when(lancamentoDAO.existsById(1)).thenReturn(true);
        lancamentoRepository.deleteLancamento(1);
        verify(lancamentoDAO).deleteById(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void deveDeletarAplicacaoErroNotFound(){
        when(lancamentoDAO.existsById(1)).thenReturn(false);
        lancamentoRepository.deleteLancamento(1);
    }

    @Test
    public void deveListarAplicacoes(){
        Lancamento lancamento =  mapper.map(buildLancamentoDTO(), Lancamento.class);
        List<LancamentoBO> aplicacaoList = Collections.singletonList(mapper.map(lancamento, LancamentoBO.class));

        when(lancamentoDAO.findAll()).thenReturn(Collections.singletonList(lancamento));

        List<LancamentoBO> result = lancamentoRepository.listaLancamentos();
        assertEquals(aplicacaoList, result);
    }

    public Lancamento toLancamentoAtualizado(Integer id, LancamentoInputDTO input){
        return Lancamento.builder()
                .codigoLancamento(id)
                .codigoAplicacao(input.getCodigoAplicacao())
                .tipoLancamento(mapper.map(input.getTipoLancamento(), TipoLancamentoEnum.class))
                .valorLancamento(input.getValorLancamento())
                .dataLancamento(input.getDataLancamento())
                .build();
    }

    private LancamentoInputDTO buildLancamentoDTO() {
        return LancamentoInputDTO.builder()
                .codigoAplicacao(1)
                .tipoLancamento(TipoLancamentoDTOEnum.IOF)
                .valorLancamento(BigDecimal.valueOf(1000))
                .dataLancamento(LocalDate.now())
                .build();
    }
}
