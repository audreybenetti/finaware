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
        existsById(1);
        return modelMapper.map(aplicacaoDAO.findById(id), AplicacaoBO.class);
    }

    public AplicacaoBO updateAplicacao(Integer id, AplicacaoInputDTO input) {
        existsById(id);
        return modelMapper.map(aplicacaoDAO.save((toAplicacaoAtualizado(id, input))), AplicacaoBO.class);
    }

    public void deleteAplicacao(Integer id) {
        existsById(id);
        aplicacaoDAO.deleteById(id);
    }

    public List<AplicacaoBO> listaAplicacoes() {
        return aplicacaoDAO.findAll().stream()
                .map(aplicacao -> modelMapper.map(aplicacao, AplicacaoBO.class))
                .collect(Collectors.toList());
    }

    private void existsById(Integer id){
        if(!aplicacaoDAO.existsById(id)) {
            throw new NoSuchElementException("Aplicação " + id + " não encontrada.");
        }
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
