package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Conta;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ContaRequestDTO(
@NotEmpty
String agencia,
@NotEmpty
String numero,
@NotEmpty
String tipo,
@NotNull
Long saldo,
@NotNull
Boolean ativo
){
    public Conta toEntity(){
        return Conta.builder()
                .agencia(this.agencia)
                .numero(this.numero)
                .tipo(this.tipo)
                .saldo(this.saldo)
                .ativo(true)
                .build();
    }

}