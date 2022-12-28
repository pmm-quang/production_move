package com.example.backend.service;

import com.example.backend.payload.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProduct();
    List<ProductDto> getProductsByProductLineId(Long productLineID);
    List<ProductDto> getProductById(String id);
    List<ProductDto> getProductByEngineNumber(String engineNumber);
    ProductDto createProduct(ProductDto productDto, Long ProductLineID);
    ProductDto updateProduct(ProductDto productDto, String chassisNumber);
    void deleteProduct(String chassisNumber);

}
