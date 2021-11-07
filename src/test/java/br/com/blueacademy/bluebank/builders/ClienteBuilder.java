package br.com.blueacademy.bluebank.builders;

import br.com.blueacademy.bluebank.dtos.ClienteDTO;
import br.com.blueacademy.bluebank.entities.AbstractEntity;
import br.com.blueacademy.bluebank.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
public class ClienteBuilder {
    @Builder.Default
    private UUID id = UUID.randomUUID();
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
    @Builder.Default
    private boolean active = true;
    @Builder.Default
    private String nome = "JUCA BALA";
    @Builder.Default
    private String telefone = "3333-3333";
    @Builder.Default
    private String email = "aaaa@gmail.com";
    @Builder.Default
    private String cpf = "123.123.123-32";
    @Builder.Default
    private String rg = "12312312";
    @Builder.Default
    private String rua = "RUA TEST";
    @Builder.Default
    private String cidade = "CIDADE TEST";
    @Builder.Default
    private String estado = "MINAS GERAIS";
    @Builder.Default
    private String cep = "35662-000";
    @Builder.Default
    private String pais = "BRASIL";

    public ClienteDTO toClienteDTO() {
        return ClienteDTO
                .builder()
                .id(this.id)
                .nome(this.getNome())
                .telefone(this.getTelefone())
                .email(this.getEmail())
                .cpf(this.getCpf())
                .rg(this.getRg())
                .rua(this.getRua())
                .cidade(this.getCidade())
                .estado(this.getEstado())
                .cep(this.getCep())
                .pais(this.getPais())
                .build();
    }
}
