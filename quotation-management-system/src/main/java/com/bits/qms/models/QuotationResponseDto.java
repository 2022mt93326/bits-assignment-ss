package com.bits.qms.models;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class QuotationResponseDto extends QuotationDto {
    private String quoteId;
}
