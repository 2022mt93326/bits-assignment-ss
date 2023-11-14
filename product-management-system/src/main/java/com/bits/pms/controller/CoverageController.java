package com.bits.pms.controller;

import com.bits.pms.models.CoverageDto;
import com.bits.pms.models.CoverageResponseDto;
import com.bits.pms.service.CoverageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/coverages")
@CrossOrigin
public class CoverageController {

    @Autowired
    private CoverageService coverageService;

    @PostMapping
    public ResponseEntity<CoverageResponseDto> createCoverage(@RequestBody CoverageDto coverageDto) {
        CoverageResponseDto coverageResponseDto = coverageService.createCoverage(coverageDto);
        coverageResponseDto.add(linkTo(methodOn(ProductController.class)
                .getProductById(coverageResponseDto.getId())).withSelfRel());
        return new ResponseEntity<>(coverageResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoverageResponseDto> getCoverageById(@PathVariable String id) {
        return ResponseEntity.ok(coverageService.getCoverageById(id));
    }
}
