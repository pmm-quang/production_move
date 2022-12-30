package com.example.backend.controller;

import com.example.backend.config.AppConstant;
import com.example.backend.entities.custom.ErrorPerLine;
import com.example.backend.payload.ProductDto;
import com.example.backend.repository.ProductRepo;
import com.example.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/product/")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/")
    @Secured({AppConstant.ADMIN_ROLE})
    public ResponseEntity<List<ProductDto>> getAllProduct() {
        List<ProductDto> productDtos = productService.getAllProduct();
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @GetMapping("/productlineid/{productLineID}")
    @Secured({AppConstant.ADMIN_ROLE, AppConstant.SHOP_ROLE})
    public ResponseEntity<List<ProductDto>> getProductsByProductLineId(@PathVariable Long productLineID) {
        List<ProductDto> productDtos = productService.getProductsByProductLineId(productLineID);
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @GetMapping("/chassisnumber/{id}")
    @Secured({AppConstant.ADMIN_ROLE, AppConstant.SHOP_ROLE})
    public ResponseEntity<List<ProductDto>> getProductsByChassisNumber(@PathVariable String id) {
        List<ProductDto> productDtos = productService.getProductById(id);
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @GetMapping("/enginenumber/{engineNumber}")
    @Secured({AppConstant.ADMIN_ROLE, AppConstant.SHOP_ROLE})
    public ResponseEntity<List<ProductDto>> getProductsByEngineNumber(@PathVariable String engineNumber) {
        List<ProductDto> productDtos = productService.getProductByEngineNumber(engineNumber);
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @PostMapping("/add/{productLineID}")
    @Secured({AppConstant.FACTORY_ROLE})
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto, @PathVariable Long productLineID) {
        ProductDto newProduct = productService.createProduct(productDto, productLineID);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/update/{chassisNumber}")
    @Secured({AppConstant.FACTORY_ROLE, AppConstant.SHOP_ROLE})
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String chassisNumber, @RequestBody ProductDto productDto) {
        ProductDto product = productService.updateProduct(productDto, chassisNumber);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{chassisNumber}")
    public ResponseEntity<String> deleteProduct(@PathVariable String chassisNumber) {
        productService.deleteProduct(chassisNumber);
        return new ResponseEntity<String>("delete!!", HttpStatus.OK);
    }

    @GetMapping("/statistics/shop/{shopID}/")
    public ResponseEntity<List<ProductDto>> getProductsByShopAndStatus(@PathVariable Long shopID) {
        List<ProductDto> productDtos = productService.getProductsByShopAndStatus(shopID, "Đã bán");
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @GetMapping("/statistics/factory/errorperline/{factoryID}")
    @Secured({AppConstant.FACTORY_ROLE})
    public ResponseEntity<List<ErrorPerLine>> getErrorPerLineOfFactory(@PathVariable Long factoryID) {
        List<ErrorPerLine> list = productService.getErrorPerLineOfFactory(factoryID);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/statistics/factory/errorpershop/{factoryID}")
    @Secured({AppConstant.FACTORY_ROLE})
    public ResponseEntity<List<ErrorPerLine>> getErrorPerShopOfFactory(@PathVariable Long factoryID) {
        List<ErrorPerLine> list = productService.getErrorPerShopOfFactory(factoryID);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
