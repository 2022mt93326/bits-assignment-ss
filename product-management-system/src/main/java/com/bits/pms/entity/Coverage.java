package com.bits.pms.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class Coverage {
    @Id
    private String id;
    @Indexed(unique = true)
    private String code;
    private String name;
    private String description;
}
