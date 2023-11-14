package com.bits.qms.entity;

import com.bits.qms.models.Coverage;
import com.bits.qms.models.Party;
import com.bits.qms.models.ProductCharacteristic;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Document
public class Quotation {
    @Id
    private String quoteId;
    private String lob;
    private String productId;
    private List<ProductCharacteristic> productCharacteristics;
    private int tenure;
    private String plan;
    private List<Coverage> coverages;
    private double totalPremium;
    private double sumAssured;
    private Party party;
}
