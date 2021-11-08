package br.com.blueacademy.bluebank.services;

import br.com.blueacademy.bluebank.builders.ClienteBuilder;
import br.com.blueacademy.bluebank.dtos.ClienteDTO;
import br.com.blueacademy.bluebank.entities.Cliente;
import br.com.blueacademy.bluebank.exceptions.ClienteNotFoundException;
import br.com.blueacademy.bluebank.factories.ClienteFactory;
import br.com.blueacademy.bluebank.forms.ClienteForm;
import br.com.blueacademy.bluebank.repositories.ClienteRepository;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

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
    void shouldReturnAClientById() throws ClienteNotFoundException {
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
    void shouldReturnAClientByCpf() throws ClienteNotFoundException{
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
        //given
        ClienteForm clienteForm = ClienteBuilder.builder().build().toClienteForm();
        Cliente expectedSavedCliente = ClienteFactory.create(clienteForm);

        //when
        // Ensina o Mockito a retornar um Optional vazio quando o findByName e' chamado
        when(clienteRepository.findByCpf(clienteForm.cpf)).thenReturn(Optional.empty());
        when(clienteRepository.save(expectedSavedCliente)).thenReturn(expectedSavedCliente);

        //then
        ClienteDTO createdClienteDTO = clienteService.create(clienteForm);

        assertThat(createdClienteDTO.nome, is(equalTo(clienteForm.nome)));
        assertThat(createdClienteDTO.telefone, is(equalTo(clienteForm.telefone)));
        assertThat(createdClienteDTO.email, is(equalTo(clienteForm.email)));
        assertThat(createdClienteDTO.cpf, is(equalTo(clienteForm.cpf)));
        assertThat(createdClienteDTO.rg, is(equalTo(clienteForm.rg)));
        assertThat(createdClienteDTO.rua, is(equalTo(clienteForm.rua)));
        assertThat(createdClienteDTO.cidade, is(equalTo(clienteForm.cidade)));
        assertThat(createdClienteDTO.estado, is(equalTo(clienteForm.estado)));
        assertThat(createdClienteDTO.cep, is(equalTo(clienteForm.cep)));
        assertThat(createdClienteDTO.pais, is(equalTo(clienteForm.pais)));
    }

    @Test
    void shouldUpdateAClientById() {
        //given
        ClienteBuilder expectedClienteBuilder = ClienteBuilder.builder().build();

        UUID clienteUUID = expectedClienteBuilder.getId();
        ClienteForm clienteForm = expectedClienteBuilder.toClienteForm();

        Cliente expectedSavedCliente = expectedClienteBuilder.toCliente();

        //when
        // Ensina o Mockito a retornar um Optional vazio quando o findByName e' chamado
        when(clienteRepository
                .findById(clienteUUID))
                .thenReturn(Optional.of(expectedSavedCliente));
        when(clienteRepository.save(expectedSavedCliente)).thenReturn(expectedSavedCliente);

        //then
        ClienteDTO createdClienteDTO = clienteService.update(clienteUUID, clienteForm);

        assertThat(createdClienteDTO.id, is(equalTo(clienteUUID)));
        assertThat(createdClienteDTO.nome, is(equalTo(clienteForm.nome)));
        assertThat(createdClienteDTO.telefone, is(equalTo(clienteForm.telefone)));
        assertThat(createdClienteDTO.email, is(equalTo(clienteForm.email)));
        assertThat(createdClienteDTO.cpf, is(equalTo(clienteForm.cpf)));
        assertThat(createdClienteDTO.rg, is(equalTo(clienteForm.rg)));
        assertThat(createdClienteDTO.rua, is(equalTo(clienteForm.rua)));
        assertThat(createdClienteDTO.cidade, is(equalTo(clienteForm.cidade)));
        assertThat(createdClienteDTO.estado, is(equalTo(clienteForm.estado)));
        assertThat(createdClienteDTO.cep, is(equalTo(clienteForm.cep)));
        assertThat(createdClienteDTO.pais, is(equalTo(clienteForm.pais)));
    }

    @Test
    void remove() throws ClienteNotFoundException {
        //given
        ClienteBuilder expectedClienteBuilder = ClienteBuilder.builder().build();

        UUID clienteUUID = expectedClienteBuilder.getId();

        Cliente expectedSavedCliente = expectedClienteBuilder.toCliente();

        //when
        // Ensina o Mockito a retornar um Optional vazio quando o findByName e' chamado
        when(clienteRepository
                .findById(clienteUUID))
                .thenReturn(Optional.of(expectedSavedCliente));
        when(clienteRepository.save(expectedSavedCliente)).thenReturn(expectedSavedCliente);

        //then
        Cliente clientSaved = clienteService.remove(clienteUUID);

        assertThat(clientSaved.getId(), is(equalTo(expectedSavedCliente.getId())));
        assertThat(clientSaved.getNome(), is(equalTo(expectedSavedCliente.getNome())));
        assertThat(clientSaved.getTelefone(), is(equalTo(expectedSavedCliente.getTelefone())));
        assertThat(clientSaved.getEmail(), is(equalTo(expectedSavedCliente.getEmail())));
        assertThat(clientSaved.getCpf(), is(equalTo(expectedSavedCliente.getCpf())));
        assertThat(clientSaved.getRg(), is(equalTo(expectedSavedCliente.getRg())));
        assertThat(clientSaved.getRua(), is(equalTo(expectedSavedCliente.getRua())));
        assertThat(clientSaved.getCidade(), is(equalTo(expectedSavedCliente.getCidade())));
        assertThat(clientSaved.getEstado(), is(equalTo(expectedSavedCliente.getEstado())));
        assertThat(clientSaved.getCep(), is(equalTo(expectedSavedCliente.getCep())));
        assertThat(clientSaved.getPais(), is(equalTo(expectedSavedCliente.getPais())));
        assertFalse(clientSaved.isActive());
    }
}