package com.example.backend.service;

import com.example.backend.payload.ProductLineDto;

import java.util.List;

public interface ProductLineService {
    List<ProductLineDto> getAllProductLine();
    ProductLineDto getProductLineById(Long productLineID);
    ProductLineDto createProductLine(ProductLineDto productLineDto);
    ProductLineDto updateProductLine(Long productLineID, ProductLineDto productLineDto);
    void deleteProductLine(Long productLineID);
}
