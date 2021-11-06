package br.com.blueacademy.bluebank.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContaDTO {
    public UUID id;
    public Integer agencia;
    public Integer numeroDaConta;
    public UUID idClient;
    public Float saldo;
    public Boolean active;
}