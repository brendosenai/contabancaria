package com.senai.conta_bancaria.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DepositoRequestDTO(
        @NotNull
        Long valorDepositado
) {
}