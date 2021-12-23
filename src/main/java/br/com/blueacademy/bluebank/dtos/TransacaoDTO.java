package br.com.blueacademy.bluebank.dtos;

import br.com.blueacademy.bluebank.entities.Transacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TransacaoDTO {
    private UUID id;
    private UUID contaOrigem;
    private UUID contaDestino;
    private Float valor;
    private String tipoTransacao;

    public TransacaoDTO(Transacao transacao) {
        id = transacao.getId();
        contaOrigem = transacao.getContaOrigem();
        contaDestino = transacao.getContaDestino();
        valor = transacao.getValor();
        tipoTransacao = transacao.getTipoTransacao().getDescription();
    }
}