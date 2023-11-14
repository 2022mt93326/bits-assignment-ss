package com.bits.pms.service;

import com.bits.pms.entity.Coverage;
import com.bits.pms.entity.Plan;
import com.bits.pms.entity.Product;
import com.bits.pms.models.CoverageDto;
import com.bits.pms.models.CreateProductDto;
import com.bits.pms.models.PlanDto;
import com.bits.pms.models.ProductDto;
import com.bits.pms.repository.CoverageRepository;
import com.bits.pms.repository.PlanRepository;
import com.bits.pms.repository.ProductRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTests {

    @MockBean
    private final ProductRepository productRepository = mock(ProductRepository.class);
    @MockBean
    private final PlanRepository planRepository = mock(PlanRepository.class);
    @Mock
    private CoverageRepository coverageRepository = mock(CoverageRepository.class);

    private final ModelMapper modelMapper = new ModelMapper();

    private final ProductService productService = new ProductService(productRepository, planRepository,
            coverageRepository, modelMapper);


    @Test
    public void whenProductIsSavedReturnsProductDetails() {
        CoverageDto coverageDto = CoverageDto.builder()
                .code("C1")
                .name("coverage1")
                .description("coverage desc")
                .build();
        PlanDto planDto = PlanDto.builder()
                .name("silver")
                .coverages(singletonList(coverageDto))
                .build();
        CreateProductDto productDto = CreateProductDto.builder()
                .lob("vehicle")
                .name("test")
                .description("some desc")
                .plans(singletonList(planDto))
                .build();
        when(productRepository.save(any(Product.class))).thenReturn(modelMapper.map(productDto, Product.class));
        when(coverageRepository.findByCode("C1")).thenReturn(Optional.empty());
        when(coverageRepository.save(any(Coverage.class))).thenReturn(modelMapper.map(coverageDto, Coverage.class));
        when(planRepository.save(any(Plan.class))).thenReturn(modelMapper.map(planDto, Plan.class));
        ProductDto createdProduct = productService.createProduct(productDto);
        Assert.assertEquals(createdProduct.getName(), productDto.getName());
        Assert.assertEquals(createdProduct.getDescription(), productDto.getDescription());

    }
}
