package br.com.blueacademy.bluebank.services;

import br.com.blueacademy.bluebank.dtos.ClienteDTO;
import br.com.blueacademy.bluebank.entities.Cliente;
import br.com.blueacademy.bluebank.exceptions.ClienteNotFoundException;
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

    public ClienteDTO findById(UUID id) throws ClienteNotFoundException {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        return cliente.isPresent() && cliente.get().isActive()? ClienteFactory.create(cliente.get()) : null;
    }

    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll().stream()
                .filter(c -> c.isActive())
                .map(ClienteFactory::create)
                .collect(Collectors.toList());
    }

    public ClienteDTO findByCpf(String cpf) throws ClienteNotFoundException {
        Optional<Cliente> cliente = clienteRepository.findByCpf(cpf);

        return cliente.isPresent() && cliente.get().isActive()? ClienteFactory.create(cliente.get()) : null;
    }

    public ClienteDTO create(ClienteForm clienteForm) {
        Optional<Cliente> clienteOptional = clienteRepository.findByCpf(clienteForm.cpf);

        if(clienteOptional.isPresent() && clienteOptional.get().isActive())
            throw new RuntimeException("O cliente já existe na base");

        Cliente cliente = ClienteFactory.create(clienteForm);

        Cliente clienteSalvo = clienteRepository.save(cliente);

        return ClienteFactory.create(clienteSalvo);
    }

    public ClienteDTO update(UUID id, ClienteForm clienteForm) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if(clienteOptional.isEmpty() || !clienteOptional.get().isActive())
            throw new RuntimeException("O cliente não existe na base");

        Cliente cliente = clienteOptional.get();

        cliente.setNome(clienteForm.nome);
        cliente.setTelefone(clienteForm.telefone);
        cliente.setEmail(clienteForm.email);
        cliente.setCpf(clienteForm.cpf);
        cliente.setRg(clienteForm.rg);
        cliente.setRua(clienteForm.rua);
        cliente.setCidade(clienteForm.cidade);
        cliente.setEstado(clienteForm.estado);
        cliente.setCep(clienteForm.cep);
        cliente.setPais(clienteForm.pais);

        Cliente clienteSaved = clienteRepository.save(cliente);

        return ClienteFactory.create(clienteSaved);
    }

    public Cliente remove(UUID id) throws ClienteNotFoundException {
        Optional<Cliente> clienteOptional = Optional.ofNullable(
                clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id))
        );

        if(clienteOptional.isEmpty() || !clienteOptional.get().isActive())
            throw new RuntimeException("O cliente não existe na base");

        clienteOptional.get().setActive(false);

        return clienteRepository.save(clienteOptional.get());
    }
}
