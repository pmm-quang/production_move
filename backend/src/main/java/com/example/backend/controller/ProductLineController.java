package com.example.backend.controller;

import com.example.backend.payload.ProductLineDto;
import com.example.backend.repository.ProductLineRepo;
import com.example.backend.service.ProductLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/productline/")
public class ProductLineController {
    @Autowired
    ProductLineService productLineService;

    @GetMapping("/")
    public ResponseEntity<List<ProductLineDto>> getAllProductLine() {
        List<ProductLineDto> productLineDtos = productLineService.getAllProductLine();
        return new ResponseEntity<>(productLineDtos, HttpStatus.OK);
    }

    @GetMapping("/{productLineID}")
    public ResponseEntity<ProductLineDto> getProductLineById(@PathVariable Long productLineID) {
        ProductLineDto productLineDto = productLineService.getProductLineById(productLineID);
        return new ResponseEntity<>(productLineDto, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductLineDto> createProductLine(@RequestBody ProductLineDto productLineDto) {
        ProductLineDto newProductLine = productLineService.createProductLine(productLineDto);
        return new ResponseEntity<>(newProductLine, HttpStatus.CREATED);
    }

    @PutMapping("/update/{productLineID}")
    public ResponseEntity<ProductLineDto> updateProductLine(@RequestBody ProductLineDto productLineDto,
                                                            @PathVariable Long productLineID) {
        ProductLineDto productLine = productLineService.updateProductLine(productLineID,productLineDto);
        return new ResponseEntity<>(productLine, HttpStatus.OK);
    }
}
