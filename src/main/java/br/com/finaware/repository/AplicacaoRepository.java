package br.com.finaware.repository;

import br.com.finaware.api.DTO.AplicacaoInputDTO;
import br.com.finaware.business.aplicacao.AplicacaoBO;
import br.com.finaware.repository.dao.AplicacaoDAO;
import br.com.finaware.repository.dao.entity.Aplicacao;
import br.com.finaware.repository.dao.entity.enums.ProdutoSituacaoEnum;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Repository
public class AplicacaoRepository {

    private final AplicacaoDAO aplicacaoDAO;
    private final ModelMapper modelMapper = new ModelMapper();

    public AplicacaoBO save(AplicacaoInputDTO input){
        Aplicacao aplicacao = modelMapper.map(input, Aplicacao.class);
        return modelMapper.map(aplicacaoDAO.save(aplicacao), AplicacaoBO.class);
    }

    public AplicacaoBO findById(Integer id){
        if(existsById(id)){
            return modelMapper.map(aplicacaoDAO.findById(id), AplicacaoBO.class);
        } else throw new NoSuchElementException("Aplicação " + id + " não encontrada.");
    }

    public AplicacaoBO updateAplicacao(Integer id, AplicacaoInputDTO input) {
        if(existsById(id)){
            return modelMapper.map(aplicacaoDAO.save((toAplicacaoAtualizado(id, input))), AplicacaoBO.class);
        } else throw new NoSuchElementException("Aplicação " + id + " não encontrada.");
    }

    public void deleteAplicacao(Integer id) {
        if(existsById(id)) {
            aplicacaoDAO.deleteById(id);
        } else throw new NoSuchElementException("Aplicação " + id + " não encontrada.");
    }

    public List<AplicacaoBO> listaAplicacoes() {
        return aplicacaoDAO.findAll().stream()
                .map(aplicacao -> modelMapper.map(aplicacao, AplicacaoBO.class))
                .collect(Collectors.toList());
    }

    private boolean existsById(Integer id){
        return aplicacaoDAO.existsById(id);
    }

    public Aplicacao toAplicacaoAtualizado(Integer id, AplicacaoInputDTO input){
        return Aplicacao.builder()
                .codigoAplicacao(id)
                .nomeProduto(input.getNomeProduto())
                .situacaoEnum(modelMapper.map(input.getSituacaoEnum(), ProdutoSituacaoEnum.class))
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

}
