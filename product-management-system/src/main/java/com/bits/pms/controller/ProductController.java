package com.bits.pms.controller;

import com.bits.pms.models.CreateProductDto;
import com.bits.pms.models.ProductDto;
import com.bits.pms.models.UpdateProductDto;
import com.bits.pms.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/products")
@Validated
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody @Valid CreateProductDto createProductDto) {
        ProductDto productDto = productService.createProduct(createProductDto);
        productDto.add(linkTo(methodOn(ProductController.class)
                .getProductById(productDto.getProductId())).withSelfRel());
        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProductsByLob(@RequestParam("lob") String lob) {
        List<ProductDto> products = productService.getProductsByLob(lob);
        products.forEach(product -> product.add(linkTo(methodOn(ProductController.class)
                .getProductById(product.getProductId())).withSelfRel()));
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable String id) {
        ProductDto productDto = productService.getProductById(id);
        productDto.add(linkTo(methodOn(ProductController.class)
                .getProductsByLob(productDto.getLob())).withRel("list-products-by-lob"));
        return ResponseEntity.ok(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> modifyProduct(@PathVariable String id, @RequestBody @Valid UpdateProductDto updateProductDto) {
        ProductDto productDto = productService.modifyProduct(id, updateProductDto);
        productDto.add(linkTo(methodOn(ProductController.class)
                .getProductById(productDto.getProductId())).withSelfRel());
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        if (productService.deleteProduct(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.internalServerError().build();
    }
}
