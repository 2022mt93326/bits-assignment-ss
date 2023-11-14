package com.bits.qms.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CalculatePremiumDto {
    @NotNull
    @NotEmpty
    private String lob;
    @NotNull
    @NotEmpty
    private String productId;
    @NotNull
    @NotEmpty
    private List<ProductCharacteristic> productCharacteristics;
    private int tenure;
    @NotNull
    @NotEmpty
    private String plan;
    @NotNull
    @NotEmpty
    private List<Coverage> coverages;
    @NotNull
    private @Valid Party party;
    private double sumAssured;
    private double totalPremium;
}
