package br.com.blueacademy.bluebank.factories;


import br.com.blueacademy.bluebank.dtos.ContaDTO;
import br.com.blueacademy.bluebank.entities.Conta;
import br.com.blueacademy.bluebank.forms.ContaForm;

public class ContaFactory {

    public static Conta Create(ContaForm contaForm) {
        return new Conta(
                contaForm.agencia,
                contaForm.numeroDaConta,
                contaForm.idClient,
                (float) 0
        );
    }

    public static ContaDTO Create(Conta conta) {
        return new ContaDTO()
                .builder()
                .id(conta.getId())
                .agencia(conta.getAgencia())
                .numeroDaConta(conta.getNumeroDaConta())
                .idClient(conta.getIdClient())
                .saldo(conta.getSaldo())
                .active(conta.isActive())
                .build();
    }

    public static Conta Create(ContaDTO contaDTO) {
        return new Conta(
                contaDTO.agencia,
                contaDTO.numeroDaConta,
                contaDTO.idClient,
                (float) 0
        );
    }

}
