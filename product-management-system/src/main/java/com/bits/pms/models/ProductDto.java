package com.bits.pms.models;

import com.bits.pms.entity.Plan;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter
@Setter
public class ProductDto extends RepresentationModel<ProductDto> {
    private String productId;
    private String name;
    private String lob;
    private String description;
    private List<Characteristic> characteristics;
    private List<Plan> plans;
}
