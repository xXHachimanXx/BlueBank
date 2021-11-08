package br.com.blueacademy.bluebank.services;

import br.com.blueacademy.bluebank.builders.ClienteBuilder;
import br.com.blueacademy.bluebank.dtos.ClienteDTO;
import br.com.blueacademy.bluebank.dtos.ContaDTO;
import br.com.blueacademy.bluebank.entities.Cliente;
import br.com.blueacademy.bluebank.entities.Conta;
import br.com.blueacademy.bluebank.factories.ClienteFactory;
import br.com.blueacademy.bluebank.factories.ContaFactory;
import br.com.blueacademy.bluebank.forms.ClienteForm;
import br.com.blueacademy.bluebank.forms.ContaForm;
import br.com.blueacademy.bluebank.repositories.ClienteRepository;
import br.com.blueacademy.bluebank.repositories.ContaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ContaService contaService;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() throws Exception {
        this.contaService = new ContaService(contaRepository,clienteRepository);
        this.clienteService = new ClienteService(clienteRepository);
    }

    @Test
    void deveriaRetornarContaQuandoMandamosSeuID() {
        // given
        ClienteDTO expectedFoundClienteDTO = new ClienteDTO(
                UUID.randomUUID(),
                "JUCA BALA",
                "3333-3333",
                "aaaa@gmail.com",
                "123.123.123-32",
                "12312312",
                "RUA TEST",
                "CIDADE TEST",
                "MINAS GERAIS",
                "35662-000",
                "BRASIL"
        );
        Cliente expectedFoundCliente = ClienteFactory.create(expectedFoundClienteDTO);

        ContaDTO expectedFoundContaDTO = new ContaDTO(
                UUID.randomUUID(),
                1,
                null,
                expectedFoundCliente.getId(),
                null,
                true
        );

        Conta expectedFoundConta = ContaFactory.Create(expectedFoundContaDTO);
        // when
        when(contaRepository.findById(expectedFoundConta.getId())).thenReturn(Optional.of(expectedFoundConta));

        // then
        ContaDTO foundContaDTO = contaService.findById(expectedFoundConta.getId());
        assertThat(ContaFactory.Create(foundContaDTO), is(equalTo(expectedFoundConta)));
    }

    @Test
    void deveriaRetornarTodasAsContasAtivas() {
        // given
        ClienteDTO expectedFoundClienteDTO = new ClienteDTO(
                UUID.randomUUID(),
                "JUCA BALA",
                "3333-3333",
                "aaaa@gmail.com",
                "123.123.123-32",
                "12312312",
                "RUA TEST",
                "CIDADE TEST",
                "MINAS GERAIS",
                "35662-000",
                "BRASIL"
        );
        Cliente expectedFoundCliente = ClienteFactory.create(expectedFoundClienteDTO);

        ContaDTO expectedFoundContaDTO = new ContaDTO(
                UUID.randomUUID(),
                1,
                null,
                expectedFoundCliente.getId(),
                null,
                true
        );

        Conta expectedFoundConta = ContaFactory.Create(expectedFoundContaDTO);
        // when
        when(contaRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundConta));

        // then
        List<ContaDTO> foundListContaDTO = contaService.findAll();

        assertThat(foundListContaDTO, is(not(empty())));
        assertEquals(foundListContaDTO.get(0).id,expectedFoundConta.getId());

    }

    @Test
    void deveriaRetornarUmErroCasoOClienteNaoExistaAoCriarUmaConta() {
        // given
        ContaForm expectedContaExample = new ContaForm();

        expectedContaExample.agencia = 1;
        expectedContaExample.idClient = UUID.randomUUID();

        try {
            // when
            ContaDTO foundContaDTO = contaService.create(expectedContaExample);
            Assertions.assertTrue(false);
        }catch (Exception exception){
            // then
            assertEquals("O cliente não existe na base.", exception.getMessage());
        }


    }

    @Test
    void deveriaRetornarAContaCriada() {
        // given Cliente
        ClienteForm clienteForm = ClienteBuilder.builder().build().toClienteForm();
        Cliente expectedSavedCliente = ClienteFactory.create(clienteForm);

        // when Cliente
        when(clienteRepository.findByCpf(clienteForm.cpf)).thenReturn(Optional.empty());
        when(clienteRepository.save(expectedSavedCliente)).thenReturn(expectedSavedCliente);

        // then Cliente
        ClienteDTO createdClienteDTO = clienteService.create(clienteForm);

        // given Conta
            ContaForm expectedContaExample = new ContaForm();
            expectedContaExample.agencia = 1;
            expectedContaExample.idClient = createdClienteDTO.id;

        Conta expectedSavedConta = ContaFactory.Create(expectedContaExample);
            expectedSavedConta.setNumeroDaConta(1);

        // when conta

        when(clienteRepository.findById(createdClienteDTO.id)).thenReturn(Optional.of(expectedSavedCliente));
        when(contaRepository.save(expectedSavedConta)).thenReturn(expectedSavedConta);

        // then Conta
        ContaDTO createdContaDTO = contaService.create(expectedContaExample);

        assertEquals(createdContaDTO.idClient,expectedSavedCliente.getId());
        assertEquals(createdContaDTO.agencia,expectedContaExample.agencia);
    }

    @Test
    void deveriaRetornarUmErroCasoAContaNaoExistaAoDesativarUmaConta() {
        // given
       UUID notExistsAcc = UUID.randomUUID();

        try {
            // when
            ContaDTO foundContaDTO = contaService.remove(notExistsAcc);
            Assertions.assertTrue(false);
        }catch (Exception exception){
            // then
            assertEquals("A conta não existe na base.", exception.getMessage());
        }


    }

    @Test
    void deveriaRetornarAContaDesativada() {
        // given Add Cliente
        ClienteForm clienteForm = ClienteBuilder.builder().build().toClienteForm();
        Cliente expectedSavedCliente = ClienteFactory.create(clienteForm);

        // when Add Cliente
        when(clienteRepository.findByCpf(clienteForm.cpf)).thenReturn(Optional.empty());
        when(clienteRepository.save(expectedSavedCliente)).thenReturn(expectedSavedCliente);

        // then Add Cliente
        ClienteDTO createdClienteDTO = clienteService.create(clienteForm);

        // given Add Conta
        ContaForm expectedContaExample = new ContaForm();
        expectedContaExample.agencia = 1;
        expectedContaExample.idClient = createdClienteDTO.id;

        Conta expectedSavedConta = ContaFactory.Create(expectedContaExample);
        expectedSavedConta.setNumeroDaConta(1);

        // when Add conta

        when(clienteRepository.findById(createdClienteDTO.id)).thenReturn(Optional.of(expectedSavedCliente));
        when(contaRepository.save(expectedSavedConta)).thenReturn(expectedSavedConta);

        // then Add Conta
        ContaDTO createdContaDTO = contaService.create(expectedContaExample);

        // when
        when(contaRepository.findById(createdContaDTO.id)).thenReturn(Optional.of(expectedSavedConta));
        // then
        ContaDTO removedContaDTO = contaService.remove(createdContaDTO.id);

        assertEquals(removedContaDTO.idClient,createdContaDTO.idClient);
        assertEquals(removedContaDTO.active,false);
    }


}