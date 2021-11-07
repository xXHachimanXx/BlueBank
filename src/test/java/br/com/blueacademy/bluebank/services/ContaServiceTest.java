package br.com.blueacademy.bluebank.services;

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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    private ClienteService clientService;

    @BeforeEach
    void setUp() throws Exception {
        this.contaService = new ContaService(contaRepository,clienteRepository);
        this.clientService = new ClienteService(clienteRepository);
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
        ContaForm expectedExample = new ContaForm(
                1,
                null,
                UUID.randomUUID(),
                null
        );

        try {
            // when
            ContaDTO foundContaDTO = contaService.create(expectedExample);
            Assertions.assertTrue(false);
        }catch (Exception exception){
            // then
            assertEquals("O cliente não existe na base.", exception.getMessage());
        }


    }

    @Test
    void deveriaRetornarAContaCriada() {
        // given
        ClienteForm expectedCreatedClientForm = new ClienteForm(
                "JUCA BALA",
                "3333-3333",
                "aaaa@gmail.com",
                "123.123.223-32",
                "12312312",
                "RUA TEST",
                "CIDADE TEST",
                "MINAS GERAIS",
                "35662-000",
                "BRASIL"
        );

        ClienteDTO expectedClienteDTO = clientService.create(expectedCreatedClientForm);

        System.out.println(expectedClienteDTO.id);

        ContaForm expectedExample = new ContaForm(
                1,
                null,
                expectedClienteDTO.id,
                null
        );

        // when
        ContaDTO createdContaDTO = contaService.create(expectedExample);

        // then
        assertEquals(createdContaDTO.idClient,expectedClienteDTO.id);
        assertEquals(createdContaDTO.agencia,expectedExample.agencia);
    }

    @Test
    void deveriaRetornarAContaDesativada() {
        // given
        // when
        // then
    }


}