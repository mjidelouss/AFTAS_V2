package com.example.aftas.Dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class HuntingRequest {

    @NotNull(message = "Number Of Fish Cannot Be Null")
    @PositiveOrZero(message = "Number Of Fish Must Be Positive")
    private Integer numberOfFish;
    @NotNull(message = "Membership Number Cannot Be Null")
    private Integer membershipNumber;
    @NotNull(message = "Fish Name Cannot Be Null")
    private String fishName;
    @NotNull(message = "Competition Code cannot be Null")
    private String competitionCode;
    @NotNull(message = "Hunt Weight Cannot Be Null")
    private Double huntWeight;
}
