package br.com.finaware.business.aplicacao;

import br.com.finaware.api.DTO.AplicacaoInputDTO;
import br.com.finaware.repository.AplicacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@AllArgsConstructor
@Service
public class AplicacaoService {

    private AplicacaoRepository aplicacaoRepository;

    public AplicacaoBO criarAplicacao(AplicacaoInputDTO input){
        return aplicacaoRepository.save(input);
    }

    public AplicacaoBO buscarAplicacao(Integer id){
        AplicacaoBO aplicacaoBO = aplicacaoRepository.findById(id);
        aplicacaoBO.setValorComRendimento(valorRendimentos(aplicacaoBO));
        return aplicacaoBO;
    }

    public AplicacaoBO atualizarAplicacao(Integer id, AplicacaoInputDTO input){
        return aplicacaoRepository.updateAplicacao(id, input);
    }

    public void deletarAplicacao(Integer id){
        aplicacaoRepository.deleteAplicacao(id);
    }

    public List<AplicacaoBO> listarAplicacoes(){
        return aplicacaoRepository.listaAplicacoes();
    }

    private BigDecimal valorRendimentos(AplicacaoBO aplicacao){
        long meses = ChronoUnit.MONTHS.between(aplicacao.getDataAplicacao(), aplicacao.getDataLiquidacao());
        return aplicacao.getValorAplicado().multiply((BigDecimal.valueOf(1L).add(BigDecimal.valueOf(aplicacao.getRentabilidade())).multiply(BigDecimal.valueOf(meses))));
    }
}
