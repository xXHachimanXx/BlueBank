package br.com.blueacademy.bluebank.controllers;

import br.com.blueacademy.bluebank.dtos.ContaDTO;
import br.com.blueacademy.bluebank.forms.ContaForm;
import br.com.blueacademy.bluebank.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/conta")
public class ContaController {
    @Autowired
    private ContaService contaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ContaDTO> findById(@PathVariable UUID id) {
        ContaDTO contaDTO = contaService.findById(id);

        return contaDTO != null? ResponseEntity.ok(contaDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ContaDTO>> findAll() {
        List<ContaDTO> contaDTOList = contaService.findAll();
        return ResponseEntity.ok(contaDTOList);
    }

    @PostMapping
    public ResponseEntity<ContaDTO> add(@RequestBody ContaForm form, UriComponentsBuilder uriBuilder) {
        var dto= contaService.add(form);
        URI uri = uriBuilder.path("/conta/{id}").buildAndExpand(dto.id).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/deposit/{id}")
    public ResponseEntity<ContaDTO> deposit(@PathVariable UUID id, @RequestBody ContaForm form) {
        ContaDTO dto = contaService.deposit(id,form);
        return ResponseEntity.ok().body(dto);

    }

    @PutMapping(value = "/withdraw/{id}")
    public ResponseEntity<ContaDTO> withdraw(@PathVariable UUID id, @RequestBody ContaForm form) {
        ContaDTO dto = contaService.withdraw(id,form);
        return ResponseEntity.ok().body(dto);

    }

}
