package br.com.finaware.api.aplicacao;

import br.com.finaware.api.DTO.AplicacaoInputDTO;
import br.com.finaware.api.DTO.AplicacaoOutputDTO;
import br.com.finaware.business.aplicacao.AplicacaoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping(path = "/aplicacoes")
@RestController
public class AplicacaoRest {

    private final ModelMapper modelMapper = new ModelMapper();
    private final AplicacaoService aplicacaoService;

    @ApiOperation(value = "Executa nova aplicação.")
    @PostMapping
    public ResponseEntity<AplicacaoOutputDTO> executarAplicacao(@RequestBody AplicacaoInputDTO aplicacaoInputDTO){
        return ResponseEntity.ok(modelMapper.map(aplicacaoService.criarAplicacao(aplicacaoInputDTO), AplicacaoOutputDTO.class));
    }

    @ApiOperation(value = "Lista todas as aplicações.")
    @GetMapping
    public ResponseEntity<List<AplicacaoOutputDTO>> listarAplicacoes(){
        return ResponseEntity.ok(aplicacaoService.listarAplicacoes().stream()
                .map(aplicacao -> modelMapper.map(aplicacao, AplicacaoOutputDTO.class))
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "Busca aplicação por identificador.")
    @GetMapping("/{aplicacao}")
    public ResponseEntity<AplicacaoOutputDTO> buscarAplicacao(@PathVariable("aplicacao") Integer id){
        return ResponseEntity.ok(modelMapper.map(aplicacaoService.buscarAplicacao(id), AplicacaoOutputDTO.class));
    }

    @ApiOperation(value = "Atualiza aplicação existente.")
    @PutMapping("/{aplicacao}")
    public ResponseEntity<AplicacaoOutputDTO> atualizarAplicacao(@PathVariable("aplicacao") Integer id,
                                                             @RequestBody AplicacaoInputDTO aplicacaoInputDTO){
        return ResponseEntity.ok(modelMapper.map(aplicacaoService.atualizarAplicacao(id, aplicacaoInputDTO), AplicacaoOutputDTO.class));
    }

    @ApiOperation(value = "Deleta aplicação por identificador.")
    @DeleteMapping("/{aplicacao}")
    public ResponseEntity<Void> deletarAplicacao(@PathVariable("aplicacao") Integer id){
        aplicacaoService.deletarAplicacao(id);
        return ResponseEntity.ok().build();
    }

}
