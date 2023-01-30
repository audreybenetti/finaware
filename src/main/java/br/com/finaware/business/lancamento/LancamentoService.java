package br.com.finaware.business.lancamento;

import br.com.finaware.api.DTO.LancamentoInputDTO;
import br.com.finaware.repository.LancamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class LancamentoService {

    private LancamentoRepository lancamentoRepository;

    public LancamentoBO criarLancamento(LancamentoInputDTO produtoInputDTO){
        return lancamentoRepository.save(produtoInputDTO);
    }

    public LancamentoBO buscarLancamento(Integer id){
        return lancamentoRepository.findById(id);
    }

    public LancamentoBO atualizarLancamento(Integer id, LancamentoInputDTO produtoInputDTO) {
        return lancamentoRepository.updateLancamento(id, produtoInputDTO);
    }

    public void deletarLancamento(Integer id){
        lancamentoRepository.deleteLancamento(id);
    }

    public List<LancamentoBO> listarLancamentos() {
        return lancamentoRepository.listaLancamentos();
    }

}
