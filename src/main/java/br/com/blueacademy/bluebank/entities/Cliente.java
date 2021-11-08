package br.com.blueacademy.bluebank.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
@Builder
public class Cliente extends AbstractEntity {

    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private String rg;
    private String rua;
    private String cidade;
    private String estado;
    private String cep;
    private String pais;

    protected Cliente() {

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
