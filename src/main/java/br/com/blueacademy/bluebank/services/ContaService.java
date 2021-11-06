package br.com.blueacademy.bluebank.services;

import br.com.blueacademy.bluebank.dtos.ContaDTO;
import br.com.blueacademy.bluebank.entities.Cliente;
import br.com.blueacademy.bluebank.entities.Conta;
import br.com.blueacademy.bluebank.factories.ContaFactory;
import br.com.blueacademy.bluebank.forms.ContaForm;
import br.com.blueacademy.bluebank.repositories.ClienteRepository;
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
    @Autowired
    private ClienteRepository clienteRepository;

    public ContaDTO findById(UUID id) {
        Optional<Conta> conta = contaRepository.findById(id);

        return conta.isPresent()? ContaFactory.Create(conta.get()) : null;
    }

    public List<ContaDTO> findAll() {
        return contaRepository.findAll().stream().map(ContaFactory::Create).collect(Collectors.toList());
    }

    public ContaDTO create(ContaForm form) {
        Conta conta = ContaFactory.Create(form);
        Optional<Cliente> clienteOptional = clienteRepository.findById(form.idClient);
        if(clienteOptional.isEmpty()) throw new RuntimeException("O cliente não existe na base.");

        var result = contaRepository.findByIdClientAndActive(form.idClient, true);
        if(result != null) throw new RuntimeException("O Cliente já tem conta ativa no banco.");

        var tempConta = contaRepository.findByAgencia(form.agencia);
        conta.setNumeroDaConta(tempConta.size()+1);

        contaRepository.save(conta);

        return ContaFactory.Create(conta);
    }

    public ContaDTO remove(UUID id) {

        Optional<Conta> conta = contaRepository.findById(id);
        if(conta.isEmpty()) throw new RuntimeException("A conta não existe na base.");

        conta.get().setActive(false);
        contaRepository.save(conta.get());

        return conta.isPresent()? ContaFactory.Create(conta.get()) : null;
    }

    public ContaDTO update(UUID id) {

        Optional<Conta> conta = contaRepository.findById(id);
        if(conta.isEmpty()) throw new RuntimeException("A conta não existe na base.");

        conta.get().setActive(true);
        contaRepository.save(conta.get());

        return conta.isPresent()? ContaFactory.Create(conta.get()) : null;
    }

}
