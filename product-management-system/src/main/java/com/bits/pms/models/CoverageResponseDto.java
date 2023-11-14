package com.bits.pms.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class CoverageResponseDto extends RepresentationModel<CoverageResponseDto> {
    private String id;
    private String code;
    private String name;
    private String description;
}
