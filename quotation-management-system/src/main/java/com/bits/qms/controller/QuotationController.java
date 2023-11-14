package com.bits.qms.controller;

import com.bits.qms.models.QuotationDto;
import com.bits.qms.models.QuotationResponseDto;
import com.bits.qms.service.QuotationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quotations")
@Validated
@CrossOrigin
public class QuotationController {

    @Autowired
    private QuotationService quotationService;

    @PostMapping
    public ResponseEntity<QuotationResponseDto> createQuote(@RequestBody @Valid QuotationDto quotationDto,
                                                            @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(quotationService.createQuotation(quotationDto, token), HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<QuotationResponseDto> getQuote(@PathVariable String id) {
        return ResponseEntity.ok(quotationService.getQuotation(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable String id) {
        quotationService.deleteQuotation(id);
        return ResponseEntity.noContent().build();
    }
}
