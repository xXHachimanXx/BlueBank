package br.com.blueacademy.bluebank.forms;

import br.com.blueacademy.bluebank.enums.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class TransacaoForm {

    @NotNull @NotEmpty @NotBlank
    private UUID contaOrigem;
    @NotNull @NotEmpty @NotBlank
    private UUID contaDestino;
    private Float valor;
    @NotNull @NotEmpty @NotBlank
    private TipoTransacao tipoTransacao;

    public UUID getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(UUID contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public UUID getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(UUID contaDestino) {
        this.contaDestino = contaDestino;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }
}
