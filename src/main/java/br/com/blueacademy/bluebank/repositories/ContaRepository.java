package br.com.blueacademy.bluebank.repositories;

import br.com.blueacademy.bluebank.entities.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContaRepository extends JpaRepository<Conta, UUID> {

    Optional<Conta> findByNumeroDaContaAndAgencia(String numeroDaConta, String agencia);

}
