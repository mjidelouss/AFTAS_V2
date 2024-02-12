package com.example.aftas.Dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotNull(message = "Competition Code cannot be Null")
    private String competitionCode;

    @NotNull(message = "membershipNumber Cannot Be Null")
    private Integer membershipNumber;
}
