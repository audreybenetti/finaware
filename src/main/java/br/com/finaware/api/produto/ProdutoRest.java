package br.com.finaware.api.produto;

import br.com.finaware.api.DTO.ProdutoInputDTO;
import br.com.finaware.api.DTO.ProdutoOutputDTO;
import br.com.finaware.business.produto.ProdutoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping(path = "/produtos")
@RestController
public class ProdutoRest {

    private final ModelMapper modelMapper = new ModelMapper();
    private final ProdutoService produtoService;

    @ApiOperation(value = "Cadastra novo produtos financeiros.")
    @PostMapping
    public ResponseEntity<ProdutoOutputDTO> criarProduto(@RequestBody ProdutoInputDTO produtoInputDTO){
        return ResponseEntity.ok(modelMapper.map(produtoService.criarProduto(produtoInputDTO), ProdutoOutputDTO.class));
    }

    @ApiOperation(value = "Lista todos os produtos cadastrados.")
    @GetMapping
    public ResponseEntity<List<ProdutoOutputDTO>> listarProdutos(){
        return ResponseEntity.ok(produtoService.listarProdutos().stream()
                .map(produto -> modelMapper.map(produto, ProdutoOutputDTO.class))
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "Busca produto cadastrado por identificador.")
    @GetMapping("/{produto}")
    public ResponseEntity<ProdutoOutputDTO> buscarProduto(@PathVariable("produto") Integer id){
        return ResponseEntity.ok(modelMapper.map(produtoService.buscarProduto(id), ProdutoOutputDTO.class));
    }

    @ApiOperation(value = "Atualiza produto existente.")
    @PutMapping("/{produto}")
    public ResponseEntity<ProdutoOutputDTO> atualizarProduto(@PathVariable("produto") Integer id,
                                                             @RequestBody ProdutoInputDTO produtoInputDTO){
        return ResponseEntity.ok(modelMapper.map(produtoService.atualizarProduto(id, produtoInputDTO), ProdutoOutputDTO.class));
    }

    @ApiOperation(value = "Deleta produto por identificador.")
    @DeleteMapping("/{produto}")
    public ResponseEntity<Void> deletarProduto(@PathVariable("produto") Integer id){
        produtoService.deletarProduto(id);
        return ResponseEntity.ok().build();
    }
}
