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
    private String agencia;
    private String numeroDaConta;
    private UUID idClient;
    private Float saldo;
}