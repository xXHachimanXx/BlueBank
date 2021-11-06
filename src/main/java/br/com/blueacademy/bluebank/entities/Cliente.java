package br.com.blueacademy.bluebank.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
    private String rua;
    private String cidade;
    private String estado;
    private String cep;
    private String pais;

    public Cliente() {

    }

    public Cliente(String nome, String telefone, String email, String cpf, String rg,
                   String rua, String cidade, String estado, String cep, String pais) {
        super();
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.pais = pais;
    }
}
