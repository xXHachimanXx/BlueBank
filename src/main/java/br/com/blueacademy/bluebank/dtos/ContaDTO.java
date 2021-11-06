package br.com.blueacademy.bluebank.dtos;

import br.com.blueacademy.bluebank.entities.Conta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContaDTO {
    public UUID id;
    private String agencia;
    private String numeroDaConta;
    private UUID idClient;
    private Float saldo;

    public ContaDTO(Conta conta) {
        id = conta.getId();
        agencia = conta.getAgencia();
        numeroDaConta = conta.getNumeroDaConta();
        idClient = conta.getIdClient();
        saldo = conta.getSaldo();
    }
}