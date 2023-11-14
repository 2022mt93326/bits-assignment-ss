package com.bits.pms.service;

import com.bits.pms.entity.Coverage;
import com.bits.pms.entity.Plan;
import com.bits.pms.entity.Product;
import com.bits.pms.exception.NotFoundException;
import com.bits.pms.models.CreateProductDto;
import com.bits.pms.models.ProductDto;
import com.bits.pms.models.UpdateProductDto;
import com.bits.pms.repository.CoverageRepository;
import com.bits.pms.repository.PlanRepository;
import com.bits.pms.repository.ProductRepository;
import com.bits.pms.validators.ProductValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private CoverageRepository coverageRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          PlanRepository planRepository,
                          CoverageRepository coverageRepository,
                          ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.planRepository = planRepository;
        this.coverageRepository = coverageRepository;
        this.modelMapper = modelMapper;

    }

    public ProductDto createProduct(CreateProductDto createProductDto) {
        ProductValidator.validate(createProductDto);
        Product product = modelMapper.map(createProductDto, Product.class);
        product.getPlans().forEach(plan -> {
            plan.getCoverages().forEach(coverage -> {
                coverageRepository.findByCode(coverage.getCode()).map(existingCoverage -> {
                    coverage.setId(existingCoverage.getId());
                    return coverage;
                });
                Coverage createdCoverage = coverageRepository.save(coverage);
                coverage.setId(createdCoverage.getId());
            });
            Plan createdPlan = planRepository.save(plan);
            plan.setId(createdPlan.getId());
        });

        return modelMapper.map(productRepository.save(product), ProductDto.class);
    }

    public List<ProductDto> getProductsByLob(String lob) {
        return productRepository.findByLob(lob).stream().map(product -> modelMapper.map(product, ProductDto.class)).toList();
    }

    public ProductDto getProductById(String productId) {
        return productRepository.findById(productId).map(product -> modelMapper.map(product, ProductDto.class))
                .orElseThrow(() -> new NotFoundException("Product not found by id:" + productId));
    }

    public ProductDto modifyProduct(String productId, UpdateProductDto updateProductDto) {
        return productRepository.findById(productId).map(product -> {
            if (updateProductDto.getDescription() != null && !updateProductDto.getDescription().isEmpty()) {
                product.setDescription(updateProductDto.getDescription());
            }
            if (updateProductDto.getName() != null && !updateProductDto.getName().isEmpty()) {
                product.setName(updateProductDto.getName());
            }
            if (updateProductDto.getLob() != null && !updateProductDto.getLob().isEmpty()) {
                product.setLob(updateProductDto.getLob());
            }
            if (updateProductDto.getCharacteristics() != null && !updateProductDto.getCharacteristics().isEmpty()) {
                product.setCharacteristics(updateProductDto.getCharacteristics());
            }
            if (updateProductDto.getPlans() != null && !updateProductDto.getPlans().isEmpty()) {
                product.setPlans(updateProductDto.getPlans().stream()
                        .map(planDto -> modelMapper.map(planDto, Plan.class)).collect(Collectors.toList()));
            }
            return modelMapper.map(productRepository.save(product), ProductDto.class);
        }).orElseThrow(() -> new NotFoundException("Product not found by id:" + productId));

    }

    public Boolean deleteProduct(String productId) {
        return productRepository.findById(productId).map(product -> {
            productRepository.delete(product);
            return true;
        }).orElseThrow(() -> new NotFoundException("Product not found by id:" + productId));
    }
}
