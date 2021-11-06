package br.com.blueacademy.bluebank.services;

import br.com.blueacademy.bluebank.dtos.ContaDTO;
import br.com.blueacademy.bluebank.entities.Conta;
import br.com.blueacademy.bluebank.factories.ContaFactory;
import br.com.blueacademy.bluebank.forms.ContaForm;
import br.com.blueacademy.bluebank.repositories.ContaRepository;
import br.com.blueacademy.bluebank.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

        return conta.isPresent()? ContaFactory.Create(conta.get()) : null;
    }

    public List<ContaDTO> findAll() {
        return contaRepository.findAll().stream().map(ContaFactory::Create).collect(Collectors.toList());
    }

    public ContaDTO add(ContaForm form) {
        Conta car = ContaFactory.Create(form);

        var result = contaRepository.findByNumeroDaContaAndAgencia(form.numeroDaConta, form.agencia);

        //if(result != null) throw new RuntimeException("Conta já cadastrada");

        contaRepository.save(car);

        return ContaFactory.Create(car);
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
    }
}
