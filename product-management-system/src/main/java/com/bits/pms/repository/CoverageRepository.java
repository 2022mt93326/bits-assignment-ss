package com.bits.pms.repository;

import com.bits.pms.entity.Coverage;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CoverageRepository extends CrudRepository<Coverage, String> {
    Optional<Coverage> findByCode(String code);
}
