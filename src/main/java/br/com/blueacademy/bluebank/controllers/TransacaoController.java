package br.com.blueacademy.bluebank.controllers;

import br.com.blueacademy.bluebank.dtos.TransacaoDTO;
import br.com.blueacademy.bluebank.forms.TransacaoForm;
import br.com.blueacademy.bluebank.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(value = "/{id}")
    public ResponseEntity<TransacaoDTO>make(@PathVariable UUID id, @RequestBody TransacaoForm form) {
        TransacaoDTO dto = transacaoService.transaction(id,form);
        return ResponseEntity.ok().body(dto);
    }




}
