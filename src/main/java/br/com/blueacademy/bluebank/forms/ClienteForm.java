package br.com.blueacademy.bluebank.forms;

import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ClienteForm {
    public String nome;
    public String telefone;
    public String email;
    public String cpf;
    public String rg;
    public String rua;
    public String cidade;
    public String estado;
    public String cep;
    public String pais;
}

