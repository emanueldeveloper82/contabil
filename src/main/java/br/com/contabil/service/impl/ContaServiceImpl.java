package br.com.contabil.service.impl;

import br.com.contabil.dto.ContaDTO;
import br.com.contabil.entity.Conta;
import br.com.contabil.repository.ContaRepository;
import br.com.contabil.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContaServiceImpl implements ContaService {

    private final ContaRepository repository;
    private final ModelMapper modelMapper;

    /**
     * Método que retorna uma lista de Contas a pagar;
     * @return
     */
    @Override
    public List<Conta> listarTodos() {
        return repository.findAll();
    }


    /**
     * Método que salva ou atualiza uma conta a pagar;
     * @param contaDTO
     * @return
     */
    @Override
    public ResponseEntity<?> salvar(ContaDTO contaDTO) {


        try {
            Conta conta = repository.save(modelMapper.map(contaDTO, Conta.class));
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(modelMapper.map(conta, ContaDTO.class));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao tentar salvar uma conta.");
        }
    }

    @Override
    public ResponseEntity<?> remover(ContaDTO contaDTO) {

        Optional<Conta> usuarioOptional = Optional.empty();

        if(contaDTO.getId() != null) {
            usuarioOptional = repository.findById(contaDTO.getId());
        }

        if (usuarioOptional.isEmpty()) {
            repository.delete(modelMapper.map(contaDTO, Conta.class));
            return ResponseEntity.ok().body("Conta removida com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Erro ao tentar excluir uma conta.");
        }
    }

}
