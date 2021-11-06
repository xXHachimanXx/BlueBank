package br.com.blueacademy.bluebank.entities;

import br.com.blueacademy.bluebank.TipoTransacao;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class Transacao extends AbstractEntity {
    private UUID contaOrigem;
    private UUID contaDestino;
    private Float valor;
    private TipoTransacao tipoTransacao;
}
