package com.example.backend.service;

import com.example.backend.payload.QuarterDto;

import java.util.List;

public interface QuarterService {
    List<QuarterDto> getAllFactory();
    List<QuarterDto> getAllShop();
    List<QuarterDto> getAllWarrantyCenter();
    QuarterDto findQuarterByID(Long quarterID);
    QuarterDto createQuarter(QuarterDto quarterDto, Long roleID);
    QuarterDto updateQuarter(QuarterDto quarterDto, Long roleID, Long quarterID);
    void deleteQuarter(Long quarterID);
}
