package br.com.blueacademy.bluebank.services;

import br.com.blueacademy.bluebank.builders.ClienteBuilder;
import br.com.blueacademy.bluebank.dtos.ClienteDTO;
import br.com.blueacademy.bluebank.entities.Cliente;
import br.com.blueacademy.bluebank.factories.ClienteFactory;
import br.com.blueacademy.bluebank.repositories.ClienteRepository;
import lombok.Builder;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() throws Exception {
        this.clienteService = new ClienteService(clienteRepository);
    }

    @Test
    void shouldReturnAClientById() {
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

        // when
        when(clienteRepository.findById(expectedFoundCliente.getId())).thenReturn(Optional.of(expectedFoundCliente));

        // then
        ClienteDTO foundClienteDTO = clienteService.findById(expectedFoundCliente.getId());
        assertThat(ClienteFactory.create(foundClienteDTO), is(equalTo(expectedFoundCliente)));
    }

    @Test
    void shouldReturnAListWithAllCLients() {
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

        // when
        when(clienteRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundCliente));

        // then
        List<ClienteDTO> foundListClienteDTO = clienteService.findAll();

        assertThat(foundListClienteDTO, is(not(empty())));
        assertEquals(foundListClienteDTO.get(0).id, expectedFoundClienteDTO.id);
    }

    @Test
    void shouldReturnAClientByCpf() {
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

        // when
        when(clienteRepository.findByCpf(expectedFoundCliente.getCpf())).thenReturn(Optional.of(expectedFoundCliente));

        // then
        ClienteDTO foundClienteDTO = clienteService.findByCpf(expectedFoundCliente.getCpf());
        assertThat(ClienteFactory.create(foundClienteDTO), is(equalTo(expectedFoundCliente)));
    }

    @Test
    void shouldCreateAClient() {

    }

    @Test
    void update() {
    }

    @Test
    void remove() {
    }
}