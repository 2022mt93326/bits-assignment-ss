package com.bits.pms.models;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class UpdateProductDto {
    private String name;
    private String lob;
    private String description;
    private List<@Valid Characteristic> characteristics;
    private List<@Valid PlanDto> plans;
}
