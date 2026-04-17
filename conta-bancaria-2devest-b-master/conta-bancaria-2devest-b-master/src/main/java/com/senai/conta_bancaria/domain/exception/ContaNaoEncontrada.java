package com.senai.conta_bancaria.domain.exception;

public class ContaNaoEncontrada extends RuntimeException {
    public ContaNaoEncontrada(Long idConta) {
        super("Conta com o id "+idConta+ " não foi  encontrado");
    }
}
