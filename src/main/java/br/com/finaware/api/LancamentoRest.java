package br.com.finaware.api;

import br.com.finaware.api.DTO.LancamentoInputDTO;
import br.com.finaware.api.DTO.LancamentoOutputDTO;
import br.com.finaware.api.DTO.LancamentoInputDTO;
import br.com.finaware.api.DTO.LancamentoOutputDTO;
import br.com.finaware.business.lancamento.LancamentoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping(path = "/lancamentos")
@RestController
public class LancamentoRest {

    private final ModelMapper modelMapper = new ModelMapper();
    private final LancamentoService lancamentoService;

    @ApiOperation(value = "Cadastra novo produtos financeiros.")
    @PostMapping
    public ResponseEntity<LancamentoOutputDTO> criarLancamento(@RequestBody LancamentoInputDTO lancamentoInputDTO){
        return ResponseEntity.ok(modelMapper.map(lancamentoService.criarLancamento(lancamentoInputDTO), LancamentoOutputDTO.class));
    }

    @ApiOperation(value = "Lista todos os produtos cadastrados.")
    @GetMapping
    public ResponseEntity<List<LancamentoOutputDTO>> listarLancamentos(){
        return ResponseEntity.ok(lancamentoService.listarLancamentos().stream()
                .map(produto -> modelMapper.map(produto, LancamentoOutputDTO.class))
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "Busca produto cadastrado por identificador.")
    @GetMapping("/{produto}")
    public ResponseEntity<LancamentoOutputDTO> buscarLancamento(@PathVariable("produto") Integer id){
        return ResponseEntity.ok(modelMapper.map(lancamentoService.buscarLancamento(id), LancamentoOutputDTO.class));
    }

    @ApiOperation(value = "Atualiza produto existente.")
    @PutMapping("/{produto}")
    public ResponseEntity<LancamentoOutputDTO> atualizarLancamento(@PathVariable("produto") Integer id,
                                                             @RequestBody LancamentoInputDTO lancamentoInputDTO){
        return ResponseEntity.ok(modelMapper.map(lancamentoService.atualizarLancamento(id, lancamentoInputDTO), LancamentoOutputDTO.class));
    }

    @ApiOperation(value = "Deleta produto por identificador.")
    @DeleteMapping("/{produto}")
    public ResponseEntity<Void> deletarLancamento(@PathVariable("produto") Integer id){
        lancamentoService.deletarLancamento(id);
        return ResponseEntity.ok().build();
    }
}
