package br.com.contabil.service;

import br.com.contabil.dto.ContaDTO;
import br.com.contabil.entity.Conta;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ContaService {

    List<Conta> listarTodos();
    Optional<Conta> salvar(ContaDTO contaDTO);
    ResponseEntity<?> remover(ContaDTO contaDTO);

}
