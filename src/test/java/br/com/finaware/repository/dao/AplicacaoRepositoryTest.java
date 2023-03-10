package br.com.finaware.repository.dao;

import br.com.finaware.api.DTO.AplicacaoInputDTO;
import br.com.finaware.business.aplicacao.AplicacaoBO;
import br.com.finaware.repository.AplicacaoRepository;
import br.com.finaware.repository.dao.entity.Aplicacao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

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
        Mockito.when(aplicacaoDAO.save(aplicacao)).thenReturn(aplicacao);
        AplicacaoBO aplicacaoBO = mapper.map(aplicacao, AplicacaoBO.class);

        AplicacaoBO result = aplicacaoRepository.save(input);
        Assert.assertEquals(result, aplicacaoBO);
    }

    private AplicacaoInputDTO buildAplicacaoInput() {
        return AplicacaoInputDTO.builder()
                .valorAplicado(BigDecimal.valueOf(1000))
                .rentabilidade(10.0)
                .dataAplicacao(LocalDate.of(2022, 2, 1))
                .dataLiquidacao(LocalDate.of(2023, 2, 1))
                .build();
    }

}
