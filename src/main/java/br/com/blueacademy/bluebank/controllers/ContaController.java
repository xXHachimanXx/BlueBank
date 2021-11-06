package br.com.blueacademy.bluebank.controllers;

import br.com.blueacademy.bluebank.dtos.ContaDTO;
import br.com.blueacademy.bluebank.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
