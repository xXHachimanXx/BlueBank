package br.com.blueacademy.bluebank.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ContaForm {
    @NotNull @NotEmpty @NotBlank
    public Integer agencia;
    @NotNull @NotEmpty @NotBlank
    public Integer numeroDaConta;
    @NotNull @NotEmpty @NotBlank
    public UUID idClient;

}
