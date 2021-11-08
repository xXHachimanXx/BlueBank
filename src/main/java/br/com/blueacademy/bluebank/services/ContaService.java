package br.com.blueacademy.bluebank.services;

import br.com.blueacademy.bluebank.dtos.ClienteDTO;
import br.com.blueacademy.bluebank.dtos.ContaDTO;
import br.com.blueacademy.bluebank.dtos.TransacaoDTO;
import br.com.blueacademy.bluebank.entities.Cliente;
import br.com.blueacademy.bluebank.entities.Conta;
<<<<<<< HEAD
import br.com.blueacademy.bluebank.enums.TipoTransacao;
=======
import br.com.blueacademy.bluebank.factories.ClienteFactory;
>>>>>>> develop
import br.com.blueacademy.bluebank.factories.ContaFactory;
import br.com.blueacademy.bluebank.forms.ContaForm;
import br.com.blueacademy.bluebank.forms.TransacaoForm;
import br.com.blueacademy.bluebank.repositories.ClienteRepository;
import br.com.blueacademy.bluebank.repositories.ContaRepository;
import br.com.blueacademy.bluebank.services.exceptions.ResourceNotFoundException;
<<<<<<< HEAD
=======
import lombok.AllArgsConstructor;
>>>>>>> develop
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ContaService {
    private ContaRepository contaRepository;
    private ClienteRepository clienteRepository;

    public ContaDTO findById(UUID id) {
        Optional<Conta> conta = contaRepository.findById(id);
        return conta.isPresent() && conta.get().isActive()? ContaFactory.Create(conta.get()) : null;
    }

    public List<ContaDTO> findAll() {
        return contaRepository.findAll().stream().filter(c -> c.isActive()).map(ContaFactory::Create).collect(Collectors.toList());
    }

    public ContaDTO create(ContaForm form) {
        Conta conta = ContaFactory.Create(form);
        Optional<Cliente> clienteOptional = clienteRepository.findById(form.idClient);
        if(clienteOptional.isEmpty()) throw new RuntimeException("O cliente não existe na base.");

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

    public ContaDTO deposit(UUID id, ContaForm form) {
        try{
            Conta conta = contaRepository.getById(id);
            conta.setDeposit(form.saldo.floatValue());
            conta = contaRepository.save(conta);
            return new ContaDTO(conta);
        }
        catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Account id not found " + id);
        }
    }

<<<<<<< HEAD
    public ContaDTO deposit(UUID id, ContaForm form) {
        try{
            Conta conta = contaRepository.getById(id);
            conta.setDeposit(form.saldo.floatValue());
            conta = contaRepository.save(conta);


            return new ContaDTO(conta);
        }
        catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Account id not found " + id);
        }
    }

    public ContaDTO withdraw(UUID id, ContaForm form) {
        try{
            Conta conta = contaRepository.getById(id);
            conta.setWithdraw(form.saldo.floatValue());
            conta = contaRepository.save(conta);
            return new ContaDTO(conta);
        }
        catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Account id not found " + id);
        }
=======
    public ContaDTO withdraw(UUID id, ContaForm form) {
        try{
        Conta conta = contaRepository.getById(id);
        conta.setWithdraw(form.saldo.floatValue());
        conta = contaRepository.save(conta);
        return new ContaDTO(conta);
    }
        catch(EntityNotFoundException e){
        throw new ResourceNotFoundException("Account id not found " + id);
    }
>>>>>>> develop
    }
}
