package br.com.blueacademy.bluebank.controllers;

import br.com.blueacademy.bluebank.dtos.ClienteDTO;
import br.com.blueacademy.bluebank.exceptions.ClienteNotFoundException;
import br.com.blueacademy.bluebank.forms.ClienteForm;
import br.com.blueacademy.bluebank.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable UUID id) throws ClienteNotFoundException {
        ClienteDTO clienteDTO = clienteService.findById(id);

        return clienteDTO != null? ResponseEntity.ok(clienteDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> clienteDTOList = clienteService.findAll();
        return ResponseEntity.ok(clienteDTOList);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteDTO> create(@RequestBody @Valid ClienteForm clienteForm, UriComponentsBuilder uriBuilder) {
        ClienteDTO clienteDTO = clienteService.create(clienteForm);
        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(clienteDTO.id).toUri();

        return ResponseEntity.created(uri).body(clienteDTO);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteDTO> update(@PathVariable UUID id, @RequestBody @Valid ClienteForm clienteForm, UriComponentsBuilder uriBuilder) {
        ClienteDTO clienteDTO = clienteService.update(id, clienteForm);
        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(clienteDTO.id).toUri();

        return clienteDTO != null ? ResponseEntity.created(uri).body(clienteDTO) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable UUID id) throws ClienteNotFoundException {
        clienteService.remove(id);
        return ResponseEntity.ok(null);
    }

}

