package com.bits.qms.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    @NotNull
    @NotEmpty
    private String addressLine1;
    private String addressLine2;
    @NotNull
    @NotEmpty
    private String city;
    @NotNull
    @NotEmpty
    private String state;
    @NotNull
    @NotEmpty
    private String country;
    @NotNull
    @NotEmpty
    private String postOrZipCode;
}
