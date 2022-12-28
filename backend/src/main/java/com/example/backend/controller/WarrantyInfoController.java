package com.example.backend.controller;

import com.example.backend.payload.WarrantyInfoDto;
import com.example.backend.service.WarrantyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/warrantyinfo/")
public class WarrantyInfoController {
    @Autowired
    private WarrantyInfoService service;

    @GetMapping("/")
    public ResponseEntity<List<WarrantyInfoDto>> getAllWarrantyInfo() {
        List<WarrantyInfoDto> list = service.getAllWarrantyInfo();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/product/{productID}/")
    public ResponseEntity<List<WarrantyInfoDto>> getWarrantyInfoByProduct(@PathVariable String productID) {
        List<WarrantyInfoDto> list = service.getWarrantyInfoByProduct(productID);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/warrantycenter/{warrantyCenterID}/")
    public ResponseEntity<List<WarrantyInfoDto>> getWarrantyInfoByWarrantyCenter(@PathVariable Long warrantyCenterID) {
        List<WarrantyInfoDto> list = service.getWarrantyInfoByWarrantyCenter(warrantyCenterID);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/shop/{shopID}/")
    public ResponseEntity<List<WarrantyInfoDto>> getWarrantyInfoByShop(@PathVariable Long shopID) {
        List<WarrantyInfoDto> list = service.getWarrantyInfoByShop(shopID);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{warrantyInfoID}")
    public ResponseEntity<WarrantyInfoDto> getWarrantyInfoById(@PathVariable Long warrantyInfoID) {
        WarrantyInfoDto warrantyInfoDto = service.getWarrantyInfoById(warrantyInfoID);
        return new ResponseEntity<>(warrantyInfoDto, HttpStatus.OK);
    }

    @PostMapping("/add/product/{productID}/shop/{shopID}/warrantycenter/{warrantyCenterID}/")
    public ResponseEntity<WarrantyInfoDto> createWarrantyInfo(@RequestBody WarrantyInfoDto warrantyInfoDto,
                                                              @PathVariable String productID,
                                                              @PathVariable Long shopID,
                                                              @PathVariable Long warrantyCenterID) {
        WarrantyInfoDto newWarrantyInfo = service.createWarrantyInfo(warrantyInfoDto, productID, shopID, warrantyCenterID);
        return new ResponseEntity<>(newWarrantyInfo, HttpStatus.CREATED);
    }

    @PutMapping("/update/{warrantyInfoID}/")
    public ResponseEntity<WarrantyInfoDto> updateWarrantyInfo(@RequestBody WarrantyInfoDto warrantyInfoDto,
                                                              @PathVariable Long warrantyInfoID) {
        WarrantyInfoDto warrantyInfoDto1 = service.updateWarrantyInfo(warrantyInfoDto, warrantyInfoID);
        return new ResponseEntity<>(warrantyInfoDto1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{warrantyInfoID}")
    public ResponseEntity<String> deleteWarrantyInfo(@PathVariable Long warrantyInfoID) {
        service.deleteWarrantyInfo(warrantyInfoID);
        return new ResponseEntity<>("Delete!!!", HttpStatus.OK);
    }

}