package br.com.blueacademy.bluebank.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import java.util.UUID;

@Data
@Entity
@Builder
public class Conta extends AbstractEntity {
    private Integer agencia;
    private Integer numeroDaConta;
    private UUID idClient;
    private Float saldo;

    protected Conta() {

    }

    public Conta(Integer agencia,Integer numeroDaConta,UUID idClient,Float saldo) {
        super();
        this.agencia = agencia;
        this.numeroDaConta = null;
        this.idClient = idClient;
        this.saldo = saldo;
    }

<<<<<<< HEAD
=======

>>>>>>> develop
    public void setDeposit(float floatValue) {
        saldo+=floatValue;
    }

    public void setWithdraw(float floatValue) {
        saldo-=floatValue;
    }
}
