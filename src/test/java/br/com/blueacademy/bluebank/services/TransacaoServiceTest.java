package br.com.blueacademy.bluebank.services;

import br.com.blueacademy.bluebank.dtos.TransacaoDTO;
import br.com.blueacademy.bluebank.entities.Conta;
import br.com.blueacademy.bluebank.entities.Transacao;
import br.com.blueacademy.bluebank.enums.TipoTransacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @Test
    void getATransactionShouldReturnTrue() {
        Transacao transacao = new Transacao(
                UUID.randomUUID(),
                UUID.randomUUID(),
                12.0F,
                TipoTransacao.TRANSFERENCIA
        );
        TransacaoDTO transacaoDTO = new TransacaoDTO(transacao);

        assertEquals(transacao.getContaOrigem(),transacaoDTO.getContaOrigem());
    }

    @Test
    void withdrawAmountShouldReduceBalace(){
        Float expectedValue = 500.0F;
        Conta conta = new Conta(
               1,
               200,
                UUID.randomUUID(),
                1000.0F
        );
        conta.setWithdraw(500.0F);
        Assertions.assertEquals(expectedValue,conta.getSaldo());

    }

    @Test
    void depositAmountShouldIncreaseBalace(){

        Float expectedValue = 1500.0F;
        Conta conta = new Conta(
                1,
                200,
                UUID.randomUUID(),
                1000.0F
        );
        conta.setDeposit(500.0F);
        Assertions.assertEquals(expectedValue,conta.getSaldo());
    }

}