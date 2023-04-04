package br.com.finaware.repository;

import br.com.finaware.api.DTO.AplicacaoInputDTO;
import br.com.finaware.api.DTO.ProdutoSituacaoEnumDTO;
import br.com.finaware.business.aplicacao.AplicacaoBO;
import br.com.finaware.repository.dao.AplicacaoDAO;
import br.com.finaware.repository.dao.entity.Aplicacao;
import br.com.finaware.repository.dao.entity.enums.ProdutoSituacaoEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AplicacaoRepositoryTest {

    @InjectMocks
    private AplicacaoRepository aplicacaoRepository;
    @Mock
    private AplicacaoDAO aplicacaoDAO;
    private final ModelMapper mapper = new ModelMapper();

    @Test
    public void deveSalvarAplicacao(){
        AplicacaoInputDTO input = buildAplicacaoInput();
        Aplicacao aplicacao = mapper.map(input, Aplicacao.class);
        when(aplicacaoDAO.save(aplicacao)).thenReturn(aplicacao);
        AplicacaoBO aplicacaoBO = mapper.map(aplicacao, AplicacaoBO.class);

        AplicacaoBO result = aplicacaoRepository.save(input);
        Assert.assertEquals(result, aplicacaoBO);
    }

    @Test
    public void deveBuscarAplicacaoPorId(){
        Aplicacao aplicacao = mapper.map(buildAplicacaoInput(), Aplicacao.class);
        aplicacao.setCodigoAplicacao(1);
        when(aplicacaoDAO.existsById(1)).thenReturn(true);
        when(aplicacaoDAO.findById(1)).thenReturn(Optional.of(aplicacao));
        AplicacaoBO aplicacaoBO = mapper.map(aplicacao, AplicacaoBO.class);

        AplicacaoBO result = aplicacaoRepository.findById(1);
        Assert.assertEquals(result, aplicacaoBO);
    }

    @Test(expected = EntityNotFoundException.class)
    public void deveBuscarAplicacaoPorIdErroNotFound(){
        when(aplicacaoDAO.existsById(1)).thenReturn(false);
        aplicacaoRepository.findById(1);
    }

    @Test
    public void deveAlterarAplicacao(){
        Aplicacao aplicacao = mapper.map(buildAplicacaoInput(), Aplicacao.class);

        when(aplicacaoDAO.existsById(1)).thenReturn(true);
        when(aplicacaoDAO.save(toAplicacaoAtualizado(1, buildAplicacaoInput()))).thenReturn(aplicacao);
        AplicacaoBO aplicacaoBO = mapper.map(aplicacao, AplicacaoBO.class);

        AplicacaoBO result = aplicacaoRepository.updateAplicacao(1, buildAplicacaoInput());
        Assert.assertEquals(result, aplicacaoBO);
    }

    @Test(expected = EntityNotFoundException.class)
    public void deveAlterarAplicacaoErroNotFound(){
        when(aplicacaoDAO.existsById(1)).thenReturn(false);
        aplicacaoRepository.updateAplicacao(1, buildAplicacaoInput());
    }

    @Test
    public void deveDeletarAplicacao(){
        when(aplicacaoDAO.existsById(1)).thenReturn(true);
        aplicacaoRepository.deleteAplicacao(1);
        verify(aplicacaoDAO).deleteById(1);
    }

    @Test(expected = EntityNotFoundException.class)
    public void deveDeletarAplicacaoErroNotFound(){
        when(aplicacaoDAO.existsById(1)).thenReturn(false);
        aplicacaoRepository.deleteAplicacao(1);
    }

    @Test
    public void deveListarAplicacoes(){
        Aplicacao aplicacao =  mapper.map(buildAplicacaoInput(), Aplicacao.class);
        List<AplicacaoBO> aplicacaoList = Collections.singletonList(mapper.map(aplicacao, AplicacaoBO.class));

        when(aplicacaoDAO.findAll()).thenReturn(Collections.singletonList(aplicacao));

        List<AplicacaoBO> result = aplicacaoRepository.listaAplicacoes();
        assertEquals(aplicacaoList, result);
    }

    public Aplicacao toAplicacaoAtualizado(Integer id, AplicacaoInputDTO input){
        return Aplicacao.builder()
                .codigoAplicacao(id)
                .nomeProduto(input.getNomeProduto())
                .situacaoEnum(mapper.map(input.getSituacaoEnum(), ProdutoSituacaoEnum.class))
                .pu(input.getPu())
                .quantidade(input.getQuantidade())
                .rentabilidade(input.getRentabilidade())
                .valorAplicado(input.getValorAplicado())
                .valorResgatado(input.getValorResgatado())
                .dataAplicacao(input.getDataAplicacao())
                .dataLiquidacao(input.getDataLiquidacao())
                .iof(input.getIof())
                .ir(input.getIr())
                .build();
    }

    private AplicacaoInputDTO buildAplicacaoInput() {
        return AplicacaoInputDTO.builder()
                .nomeProduto("LCI Premium")
                .situacaoEnum(ProdutoSituacaoEnumDTO.ATIVO)
                .pu(BigDecimal.TEN)
                .quantidade(10L)
                .rentabilidade(10.0)
                .valorAplicado(BigDecimal.valueOf(1000))
                .rentabilidade(10.0)
                .dataAplicacao(LocalDate.of(2022, 2, 1))
                .dataLiquidacao(LocalDate.of(2023, 2, 1))
                .iof(BigDecimal.ONE)
                .ir(BigDecimal.ONE)
                .build();
    }

}
