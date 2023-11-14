package com.bits.rule.engine.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyRuleDto {
    @NotNull
    @NotEmpty
    private String script;
}
