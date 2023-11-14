package com.bits.pms.service;

import com.bits.pms.entity.Coverage;
import com.bits.pms.models.CoverageDto;
import com.bits.pms.models.CoverageResponseDto;
import com.bits.pms.repository.CoverageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoverageService {
    @Autowired
    private CoverageRepository coverageRepository;
    @Autowired
    private ModelMapper modelMapper;

    public CoverageResponseDto createCoverage(CoverageDto coverageDto) {
        return modelMapper.map(coverageRepository.save(modelMapper.map(coverageDto, Coverage.class)), CoverageResponseDto.class);
    }

    public CoverageResponseDto getCoverageById(String coverageId) {
        return modelMapper.map(coverageRepository.findById(coverageId), CoverageResponseDto.class);
    }
}
