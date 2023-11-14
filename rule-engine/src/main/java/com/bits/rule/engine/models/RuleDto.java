package com.bits.rule.engine.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
public class RuleDto {
    @NotNull
    @NotEmpty
    private String code;
    @NotNull
    @NotEmpty
    private String script;
}
