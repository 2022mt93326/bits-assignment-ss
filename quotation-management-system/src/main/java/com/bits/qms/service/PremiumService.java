package com.bits.qms.service;

import com.bits.qms.client.GatewayClient;
import com.bits.qms.models.CalculatePremiumDto;
import com.bits.qms.models.QuotationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PremiumService {
    @Value("${rule.code}")
    private String ruleCode;
    @Autowired
    private GatewayClient gatewayClient;
    @Autowired
    private ModelMapper modelMapper;

    public CalculatePremiumDto calculatePremium(CalculatePremiumDto calculatePremiumDto, String token) {
        return modelMapper.map(gatewayClient
                        .executeRule(modelMapper.map(calculatePremiumDto, QuotationDto.class), ruleCode, token),
                CalculatePremiumDto.class);
    }
}
