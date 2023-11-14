package com.bits.qms.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Party {
    @NotNull
    @NotEmpty
    private String fullName;
    @NotNull
    @NotEmpty
    private String contactNo;
    @NotNull
    private @Valid Address address;
}

