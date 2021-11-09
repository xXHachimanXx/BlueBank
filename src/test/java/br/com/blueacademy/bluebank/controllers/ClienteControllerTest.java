package br.com.blueacademy.bluebank.controllers;

import br.com.blueacademy.bluebank.builders.ClienteBuilder;
import br.com.blueacademy.bluebank.dtos.ClienteDTO;
import br.com.blueacademy.bluebank.exceptions.ClienteNotFoundException;
import br.com.blueacademy.bluebank.forms.ClienteForm;
import br.com.blueacademy.bluebank.services.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ClienteController.class)
class ClienteControllerTest {
    private static final String CLIENTE_API_URL_PATH = "/v1/clientes";

    private final Gson gson = new Gson();
    private JacksonTester<ClienteForm> jsonClienteForm;

    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ClienteController clienteController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        this.mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    void whenGETIsCalledWithIdThenOkStatusIsReturned() throws Exception {
        // given
        ClienteDTO clienteDTO = ClienteBuilder.builder().build().toClienteDTO();

        // when
        when(clienteService.findById(clienteDTO.id)).thenReturn(clienteDTO);

        // then
        this.mockMvc.perform(get(CLIENTE_API_URL_PATH + "/" + clienteDTO.id)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    void whenGETIsCalledWithoutRegisteredClientThenNotFoundStatusIsReturned() throws Exception {
        // given
        ClienteDTO clienteDTO = ClienteBuilder.builder().build().toClienteDTO();

        //when
        when(clienteService.findById(clienteDTO.id)).thenThrow(ClienteNotFoundException.class);

        // then
        this.mockMvc.perform(get(CLIENTE_API_URL_PATH + "/" + clienteDTO.id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGETFindAllIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        ClienteDTO clienteDTO = ClienteBuilder.builder().build().toClienteDTO();

        // when
        when(clienteService.findAll()).thenReturn(List.of(clienteDTO));

        // then
        this.mockMvc.perform(
                get(CLIENTE_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.gson.toJson(clienteDTO))
                )
                .andExpect(status().isOk());
    }

    @Test
    void whenPOSTIsCalledThenAnClienteIsCreated() throws Exception {
        // given
        ClienteBuilder clienteBuilder = ClienteBuilder.builder().build();
        ClienteForm clienteForm = clienteBuilder.toClienteForm();
        ClienteDTO clienteDTO = clienteBuilder.toClienteDTO();

        // when
        when(clienteService.create(clienteForm)).thenReturn(clienteDTO);

        // then
        this.mockMvc.perform(post(CLIENTE_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonClienteForm.write(clienteForm).getJson())
                ).andExpect(status().isCreated());
    }

    @Test
    void whenPUTIsCalledThenAnClienteIsUpdated() throws Exception {
        // given
        ClienteBuilder clienteBuilder = ClienteBuilder.builder().build();
        ClienteForm clienteForm = clienteBuilder.toClienteForm();
        ClienteDTO clienteDTO = clienteBuilder.toClienteDTO();
        UUID clienteExpectedId = clienteBuilder.getId();

        // when
        when(clienteService.update(clienteExpectedId, clienteForm)).thenReturn(clienteDTO);

        // then
       this.mockMvc.perform(
                put(CLIENTE_API_URL_PATH + "/" + clienteExpectedId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonClienteForm.write(clienteForm).getJson())
        ).andExpect(status().isCreated());


        assertThat(clienteDTO.id, is(equalTo(clienteExpectedId)));
        assertThat(clienteDTO.nome, is(equalTo(clienteForm.nome)));
        assertThat(clienteDTO.telefone, is(equalTo(clienteForm.telefone)));
        assertThat(clienteDTO.email, is(equalTo(clienteForm.email)));
        assertThat(clienteDTO.cpf, is(equalTo(clienteForm.cpf)));
        assertThat(clienteDTO.rg, is(equalTo(clienteForm.rg)));
        assertThat(clienteDTO.rua, is(equalTo(clienteForm.rua)));
        assertThat(clienteDTO.cidade, is(equalTo(clienteForm.cidade)));
        assertThat(clienteDTO.estado, is(equalTo(clienteForm.estado)));
        assertThat(clienteDTO.cep, is(equalTo(clienteForm.cep)));
        assertThat(clienteDTO.pais, is(equalTo(clienteForm.pais)));

    }

    @Test
    void whenDELETEIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
        ClienteBuilder clienteBuilder = ClienteBuilder.builder().build();

        // when
        doThrow(ClienteNotFoundException.class).when(clienteService).remove(clienteBuilder.getId());

        // then
        mockMvc.perform(delete(CLIENTE_API_URL_PATH + "/" + clienteBuilder.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
}