package br.com.contabil.service.impl;

import br.com.contabil.dto.ContaDTO;
import br.com.contabil.entity.Conta;
import br.com.contabil.repository.ContaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ActiveProfiles("h2")
@ExtendWith(SpringExtension.class)
class ContaServiceImplTest {

    @InjectMocks
    ContaServiceImpl service;

    @Mock
    ContaRepository repository;

    @Mock
    ModelMapper modelMapper;

    @Test
    void listarTodos() {
        List<Conta> lista = new ArrayList<>();
        Conta conta = new Conta();
        conta.setId(1L);
        conta.setNome("Conta001");

        lista.add(conta);

        BDDMockito.given(repository.findAll()).willReturn(lista);
        Assertions.assertNotNull(service.listarTodos());
    }

    @Test
    void salvarAtrasoUmDia() {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setNome("Conta001");
        contaDTO.setDataVencimento(LocalDate.now());
        contaDTO.setDataPagamento(LocalDate.now().plusDays(1));
        contaDTO.setValorOriginal(BigDecimal.TEN);

        Conta conta = new Conta();
        conta.setNome("Conta001");
        conta.setDataVencimento(LocalDate.now());
        conta.setDataPagamento(LocalDate.now().plusDays(1));
        conta.setValorOriginal(BigDecimal.TEN);

        BDDMockito.given(modelMapper.map(contaDTO, Conta.class)).willReturn(conta);

        Assertions.assertEquals(HttpStatus.CREATED, service.salvar(contaDTO).getStatusCode());

    }
    @Test
    void salvarAtrasoQuatrosDias() {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setNome("Conta001");
        contaDTO.setDataVencimento(LocalDate.now());
        contaDTO.setDataPagamento(LocalDate.now().plusDays(4));
        contaDTO.setValorOriginal(BigDecimal.TEN);

        Conta conta = new Conta();
        conta.setNome("Conta001");
        conta.setDataVencimento(LocalDate.now());
        conta.setDataPagamento(LocalDate.now().plusDays(4));
        conta.setValorOriginal(BigDecimal.TEN);

        BDDMockito.given(modelMapper.map(contaDTO, Conta.class)).willReturn(conta);

        Assertions.assertEquals(HttpStatus.CREATED, service.salvar(contaDTO).getStatusCode());

    }
    @Test
    void salvarAtrasoDezDias() {
        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setNome("Conta001");
        contaDTO.setDataVencimento(LocalDate.now());
        contaDTO.setDataPagamento(LocalDate.now().plusDays(10));
        contaDTO.setValorOriginal(BigDecimal.TEN);

        Conta conta = new Conta();
        conta.setNome("Conta001");
        conta.setDataVencimento(LocalDate.now());
        conta.setDataPagamento(LocalDate.now().plusDays(10));
        conta.setValorOriginal(BigDecimal.TEN);

        BDDMockito.given(modelMapper.map(contaDTO, Conta.class)).willReturn(conta);

        Assertions.assertEquals(HttpStatus.CREATED, service.salvar(contaDTO).getStatusCode());

    }


    @Test
    void salvarError() {
        ContaDTO contaDTO = new ContaDTO();

        BDDMockito.given(modelMapper.map(contaDTO, Conta.class)).willReturn(null);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, service.salvar(contaDTO).getStatusCode());

    }



    @Test
    void remover() {

        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setId(1L);

        Conta conta = new Conta();
        conta.setNome("Conta001");
        conta.setDataVencimento(LocalDate.now());
        conta.setDataPagamento(LocalDate.now().plusDays(10));
        conta.setValorOriginal(BigDecimal.TEN);

        BDDMockito.given(repository.findById(contaDTO.getId())).willReturn(Optional.of(conta));

        Assertions.assertEquals(HttpStatus.OK, service.remover(contaDTO).getStatusCode());
    }

    @Test
    void removerContaNaoEncontrada() {

        ContaDTO contaDTO = new ContaDTO();
        contaDTO.setId(1L);

        BDDMockito.given(repository.findById(contaDTO.getId())).willReturn(Optional.empty());

        Assertions.assertEquals(HttpStatus.NO_CONTENT, service.remover(contaDTO).getStatusCode());
    }

    @Test
    void removerContaIdNull() {

        ContaDTO contaDTO = new ContaDTO();

        BDDMockito.given(repository.findById(contaDTO.getId())).willReturn(Optional.empty());

        Assertions.assertEquals(HttpStatus.NO_CONTENT, service.remover(contaDTO).getStatusCode());
    }





}