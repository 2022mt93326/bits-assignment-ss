package com.bits.pms.models;

import com.bits.pms.entity.Coverage;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanDto {
    @NotEmpty
    @NotNull
    private String name;
    @NotEmpty
    @NotNull
    private String description;
    @NotEmpty
    @NotNull
    @DBRef
    private List<CoverageDto> coverages;
}
