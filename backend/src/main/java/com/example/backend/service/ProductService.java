package com.example.backend.service;

import com.example.backend.entities.custom.ErrorPerLine;
import com.example.backend.payload.ProductDto;
import com.example.backend.payload.QuarterDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProduct();
    List<ProductDto> getProductsByProductLineId(Long productLineID);
    List<ProductDto> getProductById(String id);
    List<ProductDto> getProductByEngineNumber(String engineNumber);
    ProductDto createProduct(ProductDto productDto, Long ProductLineID);
    ProductDto updateProduct(ProductDto productDto, String chassisNumber);
    void deleteProduct(String chassisNumber);

    List<ProductDto> getProductsByShopAndStatus(Long shopID, String status);

    List<ErrorPerLine> getErrorPerLineOfFactory(Long factoryID);

    List<ErrorPerLine> getErrorPerShopOfFactory(Long factoryID);


}
