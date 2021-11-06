package br.com.blueacademy.bluebank.repositories;

import br.com.blueacademy.bluebank.dtos.ClienteDTO;
import br.com.blueacademy.bluebank.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByCpf(String cpf);
}
