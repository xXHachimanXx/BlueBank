package br.com.blueacademy.bluebank.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class TransacaoForm {

    @NotNull @NotEmpty @NotBlank
    private UUID idContaOrigem;
    @NotNull @NotEmpty @NotBlank
    private UUID idContaDestino;
    private Float valor;
    @NotNull @NotEmpty @NotBlank
    private String tipoTransacao;

    public UUID getIdContaOrigem() {
        return idContaOrigem;
    }

    public void setIdContaOrigem(UUID idContaOrigem) {
        this.idContaOrigem = idContaOrigem;
    }

    public UUID getIdContaDestino() {
        return idContaDestino;
    }

    public void setIdContaDestino(UUID idContaDestino) {
        this.idContaDestino = idContaDestino;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }
}
