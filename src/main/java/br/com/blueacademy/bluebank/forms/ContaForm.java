package br.com.blueacademy.bluebank.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ContaForm {
    @NotNull @NotEmpty @NotBlank
    public String agencia;
    @NotNull @NotEmpty @NotBlank
    public String numeroDaConta;
    @NotNull @NotEmpty @NotBlank
    public UUID idClient;
    public Float saldo;

}
