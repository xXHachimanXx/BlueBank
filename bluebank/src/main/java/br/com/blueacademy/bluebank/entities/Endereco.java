package br.com.blueacademy.bluebank.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@Builder
public class Endereco {
    @Id
    private UUID id;
    private String rua;
    private String cidade;
    private String estado;
    private String cep;
    private String pais;
}
