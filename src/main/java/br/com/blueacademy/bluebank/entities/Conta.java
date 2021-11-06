package br.com.blueacademy.bluebank.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
}
