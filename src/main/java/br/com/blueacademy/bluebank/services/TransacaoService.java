package br.com.blueacademy.bluebank.services;

import br.com.blueacademy.bluebank.dtos.ContaDTO;
import br.com.blueacademy.bluebank.dtos.TransacaoDTO;
import br.com.blueacademy.bluebank.entities.Conta;
import br.com.blueacademy.bluebank.entities.Transacao;
import br.com.blueacademy.bluebank.enums.TipoTransacao;
import br.com.blueacademy.bluebank.factories.ContaFactory;
import br.com.blueacademy.bluebank.forms.ContaForm;
import br.com.blueacademy.bluebank.forms.TransacaoForm;
import br.com.blueacademy.bluebank.repositories.ContaRepository;
import br.com.blueacademy.bluebank.repositories.TransacaoRepository;
import br.com.blueacademy.bluebank.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ContaRepository contaRepository;

    public List<TransacaoDTO> findAll() {
        return transacaoRepository.findAll().stream().map(x -> new TransacaoDTO(x)).collect(Collectors.toList());
    }

    public TransacaoDTO transaction(UUID id, TransacaoForm form) {
        Conta contaOrigen = contaRepository.getById(id);
        UUID idContaDestino = form.getContaDestino();
        Conta contaDestino = contaRepository.getById(idContaDestino);
            contaOrigen.setWithdraw(form.getValor());
            contaDestino.setDeposit(form.getValor());
            contaRepository.save(contaOrigen);
            contaRepository.save(contaDestino);

            Transacao transacao = new Transacao(contaOrigen.getId(),
                    contaDestino.getId(),
                    form.getValor(),
                    TipoTransacao.TRANSFERENCIA);

            transacaoRepository.save(transacao);

        TransacaoDTO transacaoDTO = new TransacaoDTO(
                    contaOrigen.getId(),
                    contaDestino.getId(),
                    form.getValor(),
                    TipoTransacao.TRANSFERENCIA);

        return transacaoDTO;
    }
}

