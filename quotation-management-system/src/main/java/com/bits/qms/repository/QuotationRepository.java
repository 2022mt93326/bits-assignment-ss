package com.bits.qms.repository;

import com.bits.qms.entity.Quotation;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface QuotationRepository extends CrudRepository<Quotation, String> {
}
