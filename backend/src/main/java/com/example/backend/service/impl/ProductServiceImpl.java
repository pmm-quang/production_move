package com.example.backend.service.impl;

import com.example.backend.entities.Product;
import com.example.backend.entities.ProductLine;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.payload.ProductDto;
import com.example.backend.repository.ProductLineRepo;
import com.example.backend.repository.ProductRepo;
import com.example.backend.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ModelMapper mapper;
    @Autowired
    private ProductLineRepo productLineRepo;

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepo.findAll();
        List<ProductDto> productDtos = products.stream().map(
                product -> mapper.map(product, ProductDto.class)
        ).collect(Collectors.toList());

        return productDtos;
    }

    @Override
    public List<ProductDto> getProductsByProductLineId(Long productLineID) {
        ProductLine productLine = productLineRepo.findById(productLineID).orElseThrow(
                () -> new ResourceNotFoundException("ProductLine", "ProductLineID", productLineID)
        );
        List<Product> products = productRepo.findByProductLine(productLine);
        List<ProductDto> productDtos = products.stream().map(
                product -> mapper.map(product, ProductDto.class)
        ).collect(Collectors.toList());
        return productDtos;
    }



    @Override
    public List<ProductDto> getProductById(String id) {
        List<Product> products = productRepo.findAllById(id);
        if (products.isEmpty()) {
            return null;
        }
        List<ProductDto> productDtos = products.stream().map(
                product -> mapper.map(product, ProductDto.class)
        ).collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public List<ProductDto> getProductByEngineNumber(String engineNumber) {
        List<Product> products = productRepo.findByEngineNumber(engineNumber);
        if (products.isEmpty()) {
            return null;
        }
        List<ProductDto> productDtos = products.stream().map(
                product -> mapper.map(product, ProductDto.class)
        ).collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto, Long productLineID) {
        ProductLine productLine = productLineRepo.findById(productLineID).orElseThrow(
                () -> new ResourceNotFoundException("ProductLine", "ProductLineID", productLineID)
        );
        Product product = mapper.map(productDto, Product.class);
        product.setProductLine(productLine);
        Product newProduct = productRepo.save(product);
        return mapper.map(newProduct, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, String chassisNumber) {
        Product product = productRepo.findById(chassisNumber).orElseThrow(
                () -> new ResourceNotFoundException("Product", "chassisNumber", Long.parseLong(chassisNumber))
        );
        Product updateProduct = mapper.map(productDto, Product.class);
        productRepo.save(updateProduct);
        return mapper.map(updateProduct, ProductDto.class);
    }

    @Override
    public void deleteProduct(String chassisNumber) {
        Product product = productRepo.findById(chassisNumber).orElseThrow(
                () -> new ResourceNotFoundException("Product", "ProductID", 123456)
        );
        productRepo.delete(product);
    }
}
