package br.com.blueacademy.bluebank.services;

import br.com.blueacademy.bluebank.dtos.ClienteDTO;
import br.com.blueacademy.bluebank.entities.Cliente;
import br.com.blueacademy.bluebank.factories.ClienteFactory;
import br.com.blueacademy.bluebank.forms.ClienteForm;
import br.com.blueacademy.bluebank.repositories.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteDTO findById(UUID id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        return cliente.isPresent()? ClienteFactory.create(cliente.get()) : null;
    }

    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll().stream().map(ClienteFactory::create).collect(Collectors.toList());
    }

    public ClienteDTO findByCpf(String cpf) {
        Optional<Cliente> cliente = clienteRepository.findByCpf(cpf);

        return cliente.isPresent()? ClienteFactory.create(cliente.get()) : null;
    }

    public ClienteDTO create(ClienteForm clienteForm) {
        if(clienteRepository.findByCpf(clienteForm.cpf).isPresent()) throw new RuntimeException("O cliente já existe na base");

        Cliente cliente = ClienteFactory.create(clienteForm);

        Cliente clienteSalvo = clienteRepository.save(cliente);

        return ClienteFactory.create(clienteSalvo);
    }
}
