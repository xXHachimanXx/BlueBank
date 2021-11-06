package br.com.blueacademy.bluebank.services;

import br.com.blueacademy.bluebank.dtos.ContaDTO;
import br.com.blueacademy.bluebank.entities.Conta;
import br.com.blueacademy.bluebank.factories.ContaFactory;
import br.com.blueacademy.bluebank.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    public ContaDTO findById(UUID id) {
        Optional<Conta> conta = contaRepository.findById(id);

        return conta.isPresent()? ContaFactory.create(conta.get()) : null;
    }

    public List<ContaDTO> findAll() {
        return contaRepository.findAll().stream().map(ContaFactory::create).collect(Collectors.toList());
    }

}
