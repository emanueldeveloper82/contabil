package br.com.contabil.service;

import br.com.contabil.dto.ContaDTO;
import br.com.contabil.entity.Conta;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ContaService {

    List<Conta> listarTodos();
    ResponseEntity<?> salvar(ContaDTO contaDTO);
    ResponseEntity<?> remover(ContaDTO contaDTO);

}
