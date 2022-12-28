package com.example.backend.service.impl;

import com.example.backend.entities.Product;
import com.example.backend.entities.Quarter;
import com.example.backend.entities.WarrantyInfo;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.payload.WarrantyInfoDto;
import com.example.backend.repository.ProductRepo;
import com.example.backend.repository.QuarterRepo;
import com.example.backend.repository.WarrantyInfoRepo;
import com.example.backend.service.WarrantyInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarrantyInfoServiceImpl implements WarrantyInfoService {
    @Autowired
    private WarrantyInfoRepo repo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private QuarterRepo quarterRepo;

    @Override
    public List<WarrantyInfoDto> getAllWarrantyInfo() {
        List<WarrantyInfo> list = repo.findAll();
        List<WarrantyInfoDto> listDto = list.stream().map(l-> mapper.map(l, WarrantyInfoDto.class)).collect(Collectors.toList());
        return listDto;
    }

    @Override
    public List<WarrantyInfoDto> getWarrantyInfoByProduct(String productID) {
        Product product = productRepo.findById(productID).orElseThrow(
                ()-> new ResourceNotFoundException("Product", "ProductID", 12345)
        );
        List<WarrantyInfo> list = repo.findByProduct(product);
        List<WarrantyInfoDto> listDto = list.stream().map(l-> mapper.map(l, WarrantyInfoDto.class)).collect(Collectors.toList());
        return listDto;
    }

    @Override
    public List<WarrantyInfoDto> getWarrantyInfoByShop(Long shopID) {
        Quarter quarter = quarterRepo.findById(shopID).orElseThrow(
                ()-> new ResourceNotFoundException("Quarter", "QuarterID", shopID)
        );
        List<WarrantyInfo> list = repo.findByShop(quarter);
        List<WarrantyInfoDto> listDto = list.stream().map(l-> mapper.map(l, WarrantyInfoDto.class)).collect(Collectors.toList());
        return listDto;
    }

    @Override
    public List<WarrantyInfoDto> getWarrantyInfoByWarrantyCenter(Long warrantyCenterID) {
        Quarter quarter = quarterRepo.findById(warrantyCenterID).orElseThrow(
                ()-> new ResourceNotFoundException("Quarter", "QuarterID", warrantyCenterID)
        );
        List<WarrantyInfo> list = repo.findByWarrantyCenter(quarter);
        List<WarrantyInfoDto> listDto = list.stream().map(l-> mapper.map(l, WarrantyInfoDto.class)).collect(Collectors.toList());
        return listDto;
    }

    @Override
    public WarrantyInfoDto getWarrantyInfoById(Long warrantyInfoID) {
        WarrantyInfo warrantyInfo = repo.findById(warrantyInfoID).orElseThrow(
                ()-> new ResourceNotFoundException("WarrantyInfo", "WarrantyInfoID", warrantyInfoID)
        );
        return mapper.map(warrantyInfo, WarrantyInfoDto.class);
    }

    @Override
    public WarrantyInfoDto createWarrantyInfo(WarrantyInfoDto wDto, String productID, Long warrantyCenterID, Long shopID) {
        Product product = productRepo.findById(productID).orElseThrow(
                ()-> new ResourceNotFoundException("Product", "ProductID", 12345)
        );
        Quarter shop = quarterRepo.findById(shopID).orElseThrow(
                ()-> new ResourceNotFoundException("Quarter", "QuarterID", shopID)
        );
        Quarter warrantyCenter = quarterRepo.findById(warrantyCenterID).orElseThrow(
                ()-> new ResourceNotFoundException("Quarter", "QuarterID", warrantyCenterID)
        );
        WarrantyInfo warrantyInfo = mapper.map(wDto, WarrantyInfo.class);
        warrantyInfo.setProduct(product);
        warrantyInfo.setShop(shop);
        warrantyInfo.setWarrantyCenter(warrantyCenter);
        WarrantyInfo newWarrantyInfo = repo.save(warrantyInfo);
        return mapper.map(newWarrantyInfo, WarrantyInfoDto.class);
    }

    @Override
    public WarrantyInfoDto updateWarrantyInfo(WarrantyInfoDto wDto, Long warrantyInfoID) {
        WarrantyInfo warrantyInfo = repo.findById(warrantyInfoID).orElseThrow(
                ()-> new ResourceNotFoundException("WarrantyInfo", "WarrantyInfoID", warrantyInfoID)
        );
        WarrantyInfo w = mapper.map(wDto, WarrantyInfo.class);
        w.setId(warrantyInfoID);
        WarrantyInfo updateWarrantyInfo = repo.save(w);
        return mapper.map(updateWarrantyInfo, WarrantyInfoDto.class);
    }

    @Override
    public void deleteWarrantyInfo(Long warrantyInfoID) {
        WarrantyInfo warrantyInfo = repo.findById(warrantyInfoID).orElseThrow(
                ()-> new ResourceNotFoundException("WarrantyInfo", "WarrantyInfoID", warrantyInfoID)
        );
        repo.delete(warrantyInfo);
    }
}
