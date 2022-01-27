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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

        Conta conta = modelMapper.map(contaDTO, Conta.class);

        try {
            conta.setDataCadastro(LocalDate.now());
            conta.setQtdDiasAtraso(ChronoUnit.DAYS.between(conta.getDataVencimento(), conta.getDataPagamento()));
            conta.setValorCorrigido(calcularValorCorrigido(conta));

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(modelMapper.map(repository.save(conta), ContaDTO.class));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao tentar salvar uma conta.");
        }
    }

    @Override
    public ResponseEntity<?> remover(ContaDTO contaDTO) {

        Optional<Conta> optionalConta = Optional.empty();

        if(contaDTO.getId() != null) {
            optionalConta = repository.findById(contaDTO.getId());
        }

        if (!optionalConta.isEmpty()) {
            repository.delete(modelMapper.map(contaDTO, Conta.class));
            return ResponseEntity.ok().body("Conta removida com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Conta não encontrada.");
        }
    }


    private BigDecimal calcularValorCorrigido(Conta conta) {
        return conta.getValorOriginal()
                .add(conta.getValorOriginal().multiply(regraMulta(conta.getQtdDiasAtraso()))
                        .add(conta.getValorOriginal().multiply(regraJuros(conta.getQtdDiasAtraso()))));
    }

    private BigDecimal regraMulta(Long atraso) {
        if (atraso <= 3) {
            return new BigDecimal("0.02");
        }else if (atraso > 3 && atraso <= 5) {
            return new BigDecimal("0.03");
        }else {
            return new BigDecimal("0.05");
        }
    }

    private BigDecimal regraJuros(Long atraso) {
        if (atraso <= 3) {
            return new BigDecimal("0.001");
        }else if (atraso > 3 && atraso <= 5) {
            return new BigDecimal("0.002");
        }else {
            return new BigDecimal("0.003");
        }
    }


}
