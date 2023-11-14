package com.bits.pms.repository;

import com.bits.pms.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, String> {
    List<Product> findByLob(String lob);
}
