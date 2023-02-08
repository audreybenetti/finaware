package br.com.finaware.business.aplicacao;

import br.com.finaware.api.DTO.AplicacaoInputDTO;
import br.com.finaware.repository.AplicacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AplicacaoService {

    private AplicacaoRepository aplicacaoRepository;

    public AplicacaoBO criarAplicacao(AplicacaoInputDTO input){
        return aplicacaoRepository.save(input);
    }

    public AplicacaoBO buscarAplicacao(Integer id){
        return aplicacaoRepository.findById(id);
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
}
