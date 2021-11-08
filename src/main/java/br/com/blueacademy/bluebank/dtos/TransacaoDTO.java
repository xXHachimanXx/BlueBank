package br.com.blueacademy.bluebank.dtos;

import br.com.blueacademy.bluebank.entities.Conta;
import br.com.blueacademy.bluebank.entities.Transacao;
import br.com.blueacademy.bluebank.enums.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransacaoDTO {
    private UUID contaOrigem;
    private UUID contaDestino;
    private Float valor;
    private TipoTransacao tipoTransacao;

    public TransacaoDTO(Transacao transacao) {
        contaOrigem = transacao.getContaOrigem();
        contaDestino = transacao.getContaDestino();
        valor = transacao.getValor();
        tipoTransacao = transacao.getTipoTransacao();
    };
}