package br.com.blueacademy.bluebank.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@Data
@Entity
@Builder
public class Conta extends AbstractEntity {
    private String agencia;
    private String numeroDaConta;
    private UUID idClient;
    private Float saldo;

    protected Conta() {

    }

    public Conta(String agencia,String numeroDaConta,UUID idClient,Float saldo) {
        super();
        this.agencia = agencia;
        this.numeroDaConta = numeroDaConta;
        this.idClient = idClient;
        this.saldo = saldo;
    }


    public void setDeposit(float floatValue) {
        saldo+=floatValue;
    }

    public void setWithdraw(float floatValue) {
        saldo-=floatValue;
    }
}
