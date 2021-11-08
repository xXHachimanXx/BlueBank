package br.com.blueacademy.bluebank.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClienteDTO {
    public UUID id;
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
