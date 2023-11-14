package com.bits.rule.engine.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class QuotationDto {
    @NotNull
    @NotEmpty
    private String lob;
    @NotNull
    @NotEmpty
    private String productId;
    @NotNull
    @NotEmpty
    private List<@Valid ProductCharacteristic> productCharacteristics;
    private int tenure;
    @NotNull
    @NotEmpty
    private String plan;
    @NotNull
    @NotEmpty
    private List<Coverage> coverages;
    private double sumAssured;
    private double totalPremium;
}
