package com.bits.rule.engine.service;

import com.bits.rule.engine.entity.Rule;
import com.bits.rule.engine.exception.NotFoundException;
import com.bits.rule.engine.exception.RuleExecutionException;
import com.bits.rule.engine.models.QuotationDto;
import com.bits.rule.engine.models.RuleDto;
import com.bits.rule.engine.repository.RuleRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

@Service
@Slf4j
public class RuleEngineService {
    @Autowired
    private RuleRepository ruleRepository;
    @Autowired
    private ModelMapper modelMapper;
    private final ScriptEngine scriptEngine;
    private final Gson gson = new Gson();

    public RuleEngineService() {
        ScriptEngineManager factory = new ScriptEngineManager();
        this.scriptEngine = factory.getEngineByName("graal.js");

    }

    public RuleDto createRule(RuleDto ruleDto) {
        return modelMapper.map(ruleRepository.save(modelMapper.map(ruleDto, Rule.class)), RuleDto.class);
    }

    public RuleDto modifyRule(String ruleCode, String script) {
        Rule rule = getRuleByRuleCode(ruleCode);
        rule.setScript(script);
        return modelMapper.map(ruleRepository.save(rule), RuleDto.class);
    }
    public void deleteRule(String ruleCode) {
        Rule rule = getRuleByRuleCode(ruleCode);
        ruleRepository.deleteById(rule.getId());
    }

    public QuotationDto executeRule(QuotationDto quotationDto, String ruleCode) {
        Rule rule = getRuleByRuleCode(ruleCode);
        log.info("script:: {}", rule.getScript());
        try {
            Bindings bindings = this.scriptEngine.createBindings();
            String data = gson.toJson(quotationDto);
            log.info("data::{}", data);
            bindings.put("data", data);
            quotationDto = gson.fromJson((String) scriptEngine.eval(rule.getScript(), bindings), QuotationDto.class);
            log.info("execution result::{}", quotationDto);
        } catch (ScriptException e) {

            System.out.println("Error occurred during rule execution : "
                    + e.getMessage() + " ,line no:" + e.getLineNumber() + " ,column no:" + e.getColumnNumber());
            throw new RuleExecutionException("Error occurred during rule execution : "
                    + e.getMessage() + " ,line no:" + e.getLineNumber() + " ,column no:" + e.getColumnNumber());
        }
        return quotationDto;
    }

    public RuleDto getRule(String ruleCode) {
        return modelMapper.map(getRuleByRuleCode(ruleCode), RuleDto.class);
    }

    private Rule getRuleByRuleCode(String ruleCode) {
        return ruleRepository.findByCode(ruleCode)
                .orElseThrow(() -> new NotFoundException("Rule not found by code:" + ruleCode));
    }
}
