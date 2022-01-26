package br.com.contabil.service.impl;

import br.com.contabil.dto.ContaDTO;
import br.com.contabil.entity.Conta;
import br.com.contabil.repository.ContaRepository;
import br.com.contabil.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    public Optional<Conta> salvar(ContaDTO contaDTO) {

        Optional<Conta> usuarioOptional = Optional.empty();

        if(contaDTO.getId() != null) {
            usuarioOptional = repository.findById(contaDTO.getId());
        }

        if (usuarioOptional.isEmpty()) {
           return Optional.of(repository.save(modelMapper.map(contaDTO, Conta.class)));
        } else {
            if (usuarioOptional.get().getId().equals(contaDTO.getId())) {
                return Optional.of(repository.save(modelMapper.map(contaDTO, Conta.class)));
            }
            return usuarioOptional;
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
