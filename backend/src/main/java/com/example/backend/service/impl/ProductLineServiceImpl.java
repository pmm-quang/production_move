package com.example.backend.service.impl;

import com.example.backend.entities.ProductLine;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.payload.ProductLineDto;
import com.example.backend.repository.ProductLineRepo;
import com.example.backend.service.ProductLineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductLineServiceImpl implements ProductLineService {

    @Autowired
    ProductLineRepo productLineRepo;
    @Autowired
    ModelMapper mapper;

    @Override
    public List<ProductLineDto> getAllProductLine() {
        List<ProductLine> productLines = productLineRepo.findAll();
        List<ProductLineDto> productLineDtos = productLines.stream().map(
                productLine -> mapper.map(productLine, ProductLineDto.class)).collect(Collectors.toList());
        return productLineDtos;
    }

    @Override
    public ProductLineDto getProductLineById(Long productLineID) {
        ProductLine productLine = productLineRepo.findById(productLineID).orElseThrow(
                () -> new ResourceNotFoundException("ProductLine", "ProductLineID", productLineID));
        return mapper.map(productLine, ProductLineDto.class);
    }

    @Override
    public ProductLineDto createProductLine(ProductLineDto productLineDto) {
        ProductLine productLine = mapper.map(productLineDto, ProductLine.class);
        productLineRepo.save(productLine);
        return productLineDto;
    }

    @Override
    public ProductLineDto updateProductLine(Long productLineID, ProductLineDto productLineDto) {
        ProductLine productLine = productLineRepo.findById(productLineID).orElseThrow(
                () -> new ResourceNotFoundException("ProductLine", "ProductLineID", productLineID));
        ProductLine updateProductLine = mapper.map(productLineDto, ProductLine.class);
        updateProductLine.setId(productLineID);
        productLineRepo.save(updateProductLine);
        return productLineDto;
    }

    @Override
    public void deleteProductLine(Long productLineID) {

    }
}
