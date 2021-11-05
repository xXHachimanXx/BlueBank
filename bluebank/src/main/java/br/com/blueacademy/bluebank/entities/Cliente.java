package br.com.blueacademy.bluebank.entities;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.validation.constraints.Email;

@Entity
@Data
@Builder
public class Cliente extends AbstractEntity {

    private String nome;
    private String telefone;
    @Email
    private String email;
    @CPF
    private String cpf;
    private String rg;
    private Endereco endereco;
}
