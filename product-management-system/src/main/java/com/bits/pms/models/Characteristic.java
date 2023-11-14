package com.bits.pms.models;

import com.bits.pms.constants.DataType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Characteristic {
    @NotEmpty
    @NotNull
    private String name;
    @NotNull
    private boolean isMandatory;
    @NotNull
    private DataType type;
    private List<String> allowedValues;
}
