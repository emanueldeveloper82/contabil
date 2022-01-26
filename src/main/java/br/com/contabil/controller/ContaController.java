package br.com.contabil.controller;

import br.com.contabil.dto.ContaDTO;
import br.com.contabil.entity.Conta;
import br.com.contabil.service.ContaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/conta/")
@Api(value = "API de Cadastro de Usuario")
@CrossOrigin(origins = "*")
public class ContaController {

    private static final Logger log = LoggerFactory.getLogger(ContaController.class);
    private final ContaService service;
    private final ModelMapper modelMapper;

    @GetMapping("/listar")
    @ApiOperation(value = "Retorna uma lista de contas a pagar")
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok().body(service.listarTodos());
    }


    @PostMapping("/salvar")
    @ApiOperation(value = "Salvar uma conta")
    public ResponseEntity<?> salvar(@Valid @RequestBody ContaDTO contaDTO) {
        log.info("Salvar conta.");

        Optional<Conta> optionalConta = service.salvar(contaDTO);

        if (!optionalConta.isEmpty()){
            log.info("Salvo com sucesso.");
            return ResponseEntity.ok(modelMapper.map(optionalConta.get(), ContaDTO.class));
        }
        return ResponseEntity.badRequest().body("Erro ao tentar salvar uma conta.");
    }

    @DeleteMapping("/excluir")
    @ApiOperation(value = "Excluir uma Conta")
    public ResponseEntity<?> remover(@RequestBody ContaDTO contaDTO) {
        return ResponseEntity.ok(service.remover(contaDTO));
    }

}
