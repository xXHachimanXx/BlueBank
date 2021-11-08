package br.com.blueacademy.bluebank.controllers;

import br.com.blueacademy.bluebank.factories.ClienteFactory;
import com.google.gson.Gson;

import br.com.blueacademy.bluebank.builders.ClienteBuilder;
import br.com.blueacademy.bluebank.dtos.ClienteDTO;
import br.com.blueacademy.bluebank.exceptions.ClienteNotFoundException;
import br.com.blueacademy.bluebank.forms.ClienteForm;
import br.com.blueacademy.bluebank.services.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static br.com.blueacademy.bluebank.utils.JsonConvertionUtils.asJsonString;
import static br.com.blueacademy.bluebank.utils.JsonConvertionUtils.convertObjectToJsonBytes;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ClienteController.class)
class ClienteControllerTest {
    private static final String CLIENTE_API_URL_PATH = "/v1/clientes";

    private static final String JSON_TEST = """
                    {
                     "nome": "tinoco",
                     "telefone": "3333-3333",
                     "email": "lululu@gmail.com",
                     "cpf": "123.123.123-21",
                     "rg": "12873902",
                     "rua": "alencar",
                     "cidade": "são paulo",
                     "estado": "São Paulo",
                     "cep": "30332-000",
                     "pais": "Brasil"
                    }
            """;
    private final Gson gson = new Gson();

    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ClienteController clienteController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
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
                        .content(gson.toJson(clienteForm))
                )
                .andExpect(status().isCreated());
    }

    @Test
    void update() {
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