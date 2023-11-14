package com.bits.rule.engine.controller;

import com.bits.rule.engine.models.ModifyRuleDto;
import com.bits.rule.engine.models.QuotationDto;
import com.bits.rule.engine.models.RuleDto;
import com.bits.rule.engine.service.RuleEngineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@CrossOrigin
@RequestMapping("/rules")
public class RuleEngineController {
    @Autowired
    private RuleEngineService ruleEngineService;

    @PostMapping
    public ResponseEntity<RuleDto> createRule(@RequestBody @Valid RuleDto ruleDto) {
        return new ResponseEntity<>(ruleEngineService.createRule(ruleDto), HttpStatus.CREATED);
    }

    @GetMapping("/{ruleCode}")
    public ResponseEntity<RuleDto> getRuleByCode(@PathVariable String ruleCode) {
        return ResponseEntity.ok(ruleEngineService.getRule(ruleCode));
    }

    @PutMapping("/{ruleCode}")
    public ResponseEntity<RuleDto> updateRule(@PathVariable String ruleCode, @RequestBody @Valid ModifyRuleDto ruleDto) {
        return ResponseEntity.ok(ruleEngineService.modifyRule(ruleCode, ruleDto.getScript()));
    }

    @PostMapping("/{ruleCode}/execute")
    public ResponseEntity<QuotationDto> executeRule(@RequestBody @Valid QuotationDto quotationDto, @PathVariable String ruleCode) {
        return ResponseEntity.ok(ruleEngineService.executeRule(quotationDto, ruleCode));
    }

    @DeleteMapping("/{ruleCode}")
    public ResponseEntity<Void> deleteRule(@PathVariable String ruleCode) {
        ruleEngineService.deleteRule(ruleCode);
        return ResponseEntity.noContent().build();
    }

}
