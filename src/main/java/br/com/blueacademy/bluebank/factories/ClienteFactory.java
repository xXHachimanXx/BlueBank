package br.com.blueacademy.bluebank.factories;

import br.com.blueacademy.bluebank.dtos.ClienteDTO;
import br.com.blueacademy.bluebank.entities.Cliente;
import br.com.blueacademy.bluebank.forms.ClienteForm;

public class ClienteFactory {
    public static Cliente create(ClienteForm clienteForm) {
        return new Cliente(
                clienteForm.nome,
                clienteForm.telefone,
                clienteForm.email,
                clienteForm.cpf,
                clienteForm.rg,
                clienteForm.rua,
                clienteForm.cidade,
                clienteForm.estado,
                clienteForm.cep,
                clienteForm.pais
        );
    }

    public static ClienteDTO create(Cliente cliente) {
        return new ClienteDTO()
                .builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .telefone(cliente.getTelefone())
                .email(cliente.getEmail())
                .cpf(cliente.getCpf())
                .rg(cliente.getRg())
                .rua(cliente.getRua())
                .cidade(cliente.getCidade())
                .estado(cliente.getEstado())
                .cep(cliente.getCep())
                .pais(cliente.getPais())
                .build();
    }

    public static Cliente create(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(
                clienteDTO.nome,
                clienteDTO.telefone,
                clienteDTO.email,
                clienteDTO.cpf,
                clienteDTO.rg,
                clienteDTO.rua,
                clienteDTO.cidade,
                clienteDTO.estado,
                clienteDTO.cep,
                clienteDTO.pais
        );

        cliente.setId(clienteDTO.id);

        return cliente;
    }

}
