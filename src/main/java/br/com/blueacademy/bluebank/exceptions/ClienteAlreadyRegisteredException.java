package br.com.blueacademy.bluebank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClienteAlreadyRegisteredException extends Exception {
    public ClienteAlreadyRegisteredException(String clienteCpf) {
        super(String.format("Cliente with CPF %s already registered in the system.", clienteCpf));
    }
}
