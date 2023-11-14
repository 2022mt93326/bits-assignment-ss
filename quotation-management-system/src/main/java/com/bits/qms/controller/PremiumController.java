package com.bits.qms.controller;

import com.bits.qms.models.CalculatePremiumDto;
import com.bits.qms.service.PremiumService;
import com.bits.qms.service.QuotationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/premium")
@Validated
@CrossOrigin
public class PremiumController {

    @Autowired
    private PremiumService premiumService;

    @PostMapping("/calculate")
    public ResponseEntity<CalculatePremiumDto> calculatePremium(@RequestBody @Valid CalculatePremiumDto calculatePremiumDto,
                                                                @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(premiumService.calculatePremium(calculatePremiumDto, token));
    }
}
