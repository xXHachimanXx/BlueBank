package br.com.blueacademy.bluebank.entities;

import br.com.blueacademy.bluebank.enums.TipoTransacao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Transacao extends AbstractEntity {

    private UUID contaOrigem;
    private UUID contaDestino;
    private Float valor;
    private String tipoTransacao;

    public Transacao(){

    }
    public Transacao(UUID contaOrigem, UUID contaDestino, Float valor, String tipoTransacao) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.tipoTransacao = tipoTransacao;
    }

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

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao String) {
        this.tipoTransacao = tipoTransacao;
    }
}
