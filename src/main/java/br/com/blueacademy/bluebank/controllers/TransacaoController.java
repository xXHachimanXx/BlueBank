package br.com.blueacademy.bluebank.controllers;

import br.com.blueacademy.bluebank.dtos.TransacaoDTO;
import br.com.blueacademy.bluebank.forms.TransacaoForm;
import br.com.blueacademy.bluebank.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/transacao")
public class TransacaoController {
    @Autowired
    private TransacaoService transacaoService;

    @GetMapping
    public ResponseEntity<List<TransacaoDTO>> findAll() {
        List<TransacaoDTO> transacaoList = transacaoService.findAll();
        return ResponseEntity.ok().body(transacaoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<TransacaoDTO>> findAll(@PathVariable UUID id) {
        List<TransacaoDTO> transacaoList = transacaoService.findAll(id);
        return ResponseEntity.ok().body(transacaoList);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<TransacaoDTO>make(@PathVariable UUID id, @RequestBody TransacaoForm form, UriComponentsBuilder uriBuilder) {
        TransacaoDTO dto = transacaoService.transaction(id,form);
        URI uri = uriBuilder.path("/transacao/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }




}
