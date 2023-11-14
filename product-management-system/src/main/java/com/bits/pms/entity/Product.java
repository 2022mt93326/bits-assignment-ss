package com.bits.pms.entity;

import com.bits.pms.models.Characteristic;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
public class Product {
    @Id
    private String productId;
    private String name;
    private String lob;
    private String description;
    private List<Characteristic> characteristics;
    @DBRef
    private List<Plan> plans;
}
