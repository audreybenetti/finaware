package br.com.finaware.repository;

import br.com.finaware.api.DTO.LancamentoInputDTO;
import br.com.finaware.business.lancamento.LancamentoBO;
import br.com.finaware.repository.dao.LancamentoDAO;
import br.com.finaware.repository.dao.entity.Lancamento;
import br.com.finaware.repository.dao.entity.enums.TipoLancamentoEnum;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class LancamentoRepository {

    private final LancamentoDAO lancamentoDAO;
    private final ModelMapper modelMapper = new ModelMapper();

    public LancamentoBO save(LancamentoInputDTO input){
        Lancamento lancamento = buildLancamento(input);
        return modelMapper.map(lancamentoDAO.save(lancamento), LancamentoBO.class);
    }

    private Lancamento buildLancamento(LancamentoInputDTO inputDTO){
        return Lancamento.builder()
                .codigoAplicacao(inputDTO.getCodigoAplicacao())
                .tipoLancamento(modelMapper.map(inputDTO.getTipoLancamento(), TipoLancamentoEnum.class))
                .dataLancamento(inputDTO.getDataLancamento())
                .valorLancamento(inputDTO.getValorLancamento())
                .build();
    }

    public LancamentoBO findById(Integer id){
        if(existsById(id)){
            return modelMapper.map(lancamentoDAO.findById(id), LancamentoBO.class);
        } else throw new NoSuchElementException("Lancamento " + id + " não encontrado.");
    }

    public LancamentoBO updateLancamento(Integer id, LancamentoInputDTO input) {
        if(existsById(id)){
            return modelMapper.map(lancamentoDAO.save((toLancamentoAtualizado(id, input))), LancamentoBO.class);
        } else throw new NoSuchElementException("Lancamento " + id + " não encontrado.");
    }

    public void deleteLancamento(Integer id) {
        if(existsById(id)) {
            lancamentoDAO.deleteById(id);
        } else throw new NoSuchElementException("Lancamento " + id + " não encontrado.");
    }

    private boolean existsById(Integer id){
        return lancamentoDAO.existsById(id);
    }

    public List<LancamentoBO> listaLancamentos() {
        return lancamentoDAO.findAll().stream()
                .map(produto -> modelMapper.map(produto, LancamentoBO.class))
                .collect(Collectors.toList());
    }

    public Lancamento toLancamentoAtualizado(Integer id, LancamentoInputDTO input){
        return Lancamento.builder()
                .codigoLancamento(id)
                .codigoAplicacao(input.getCodigoAplicacao())
                .tipoLancamento(modelMapper.map(input.getTipoLancamento(), TipoLancamentoEnum.class))
                .valorLancamento(input.getValorLancamento())
                .dataLancamento(input.getDataLancamento())
                .build();
    }
}
