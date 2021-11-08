package br.com.blueacademy.bluebank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNotFoundException extends Exception{
    public ClienteNotFoundException(String clientCpf) {
        super(String.format("Cliente with cpf %s not found in the system.", clientCpf));
    }

    public ClienteNotFoundException(UUID id) {
        super(String.format("Cliente with id %s not found in the system.", id));
    }
}
