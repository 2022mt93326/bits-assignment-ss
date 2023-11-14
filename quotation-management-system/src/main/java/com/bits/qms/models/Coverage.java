package com.bits.qms.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coverage {
    @NotNull
    @NotEmpty
    private String id;
    @NotNull
    @NotEmpty
    private String code;
    @NotNull
    @NotEmpty
    private String name;
    private String description;
    private double premium;
}
