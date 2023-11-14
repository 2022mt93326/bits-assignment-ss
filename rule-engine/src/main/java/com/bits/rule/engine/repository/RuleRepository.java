package com.bits.rule.engine.repository;

import com.bits.rule.engine.entity.Rule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RuleRepository extends CrudRepository<Rule, String> {
    Optional<Rule> findByCode(String code);
}
