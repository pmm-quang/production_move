package com.example.backend.service;

import com.example.backend.payload.WarrantyInfoDto;

import java.util.List;

public interface WarrantyInfoService {
    List<WarrantyInfoDto> getAllWarrantyInfo();
    List<WarrantyInfoDto> getWarrantyInfoByProduct(String productID);
    List<WarrantyInfoDto> getWarrantyInfoByShop(Long shopID);
    List<WarrantyInfoDto> getWarrantyInfoByWarrantyCenter(Long warrantyCenterID);
    WarrantyInfoDto getWarrantyInfoById(Long warrantyInfoID);
    WarrantyInfoDto createWarrantyInfo(WarrantyInfoDto wDto, String productID, Long warrantyCenterID, Long shopID);
    WarrantyInfoDto updateWarrantyInfo(WarrantyInfoDto wDto, Long warrantyInfoID);
    void deleteWarrantyInfo(Long warrantyInfoID);
}
