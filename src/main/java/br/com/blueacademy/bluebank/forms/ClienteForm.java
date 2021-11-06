package br.com.blueacademy.bluebank.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ClienteForm {
    @NotNull @NotEmpty @NotBlank
    public String nome;
    public String telefone;
    public String email;
    public String cpf;
    @NotNull @NotEmpty @NotBlank
    public String rg;
    public String rua;
    public String cidade;
    public String estado;
    public String cep;
    public String pais;
}
