package com.example.backend.service.impl;

import com.example.backend.entities.CallbackInfo;
import com.example.backend.entities.Product;
import com.example.backend.entities.Quarter;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.payload.CallbackInfoDto;
import com.example.backend.payload.WarrantyInfoDto;
import com.example.backend.repository.CallbackInfoRepo;
import com.example.backend.repository.ProductRepo;
import com.example.backend.repository.QuarterRepo;
import com.example.backend.service.CallbackInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CallbackInfoServiceImpl implements CallbackInfoService {
    @Autowired
    private CallbackInfoRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private QuarterRepo quarterRepo;

    @Override
    public List<CallbackInfoDto> getAllCallbackInfo() {
        List<CallbackInfo> list = repo.findAll();
        List<CallbackInfoDto> listDto = list.stream().map(l-> mapper.map(l, CallbackInfoDto.class)).collect(Collectors.toList());
        return listDto;
    }

    @Override
    public List<CallbackInfoDto> getCallbackInfoByProduct(String productID) {
        Product product = productRepo.findById(productID).orElseThrow(
                ()-> new ResourceNotFoundException("Product", "ProductID", 12345)
        );
        List<CallbackInfo> list = repo.findByProduct(product);
        List<CallbackInfoDto> listDto = list.stream().map(l-> mapper.map(l, CallbackInfoDto.class)).collect(Collectors.toList());
        return listDto;
    }

    @Override
    public List<CallbackInfoDto> getCallbackInfoByShop(Long shopID) {
        Quarter quarter = quarterRepo.findById(shopID).orElseThrow(
                ()-> new ResourceNotFoundException("Quarter", "QuarterID", shopID)
        );
        List<CallbackInfo> list = repo.findByShop(quarter);
        List<CallbackInfoDto> listDto = list.stream().map(l-> mapper.map(l, CallbackInfoDto.class)).collect(Collectors.toList());
        return listDto;
    }

    @Override
    public List<CallbackInfoDto> getCallbackInfoByFactory(Long factoryID) {
        Quarter quarter = quarterRepo.findById(factoryID).orElseThrow(
                ()-> new ResourceNotFoundException("Quarter", "QuarterID", factoryID)
        );
        List<CallbackInfo> list = repo.findByFactory(quarter);
        List<CallbackInfoDto> listDto = list.stream().map(l-> mapper.map(l, CallbackInfoDto.class)).collect(Collectors.toList());
        return listDto;
    }

    @Override
    public CallbackInfoDto getCallbackInfoById(Long id) {
        CallbackInfo callbackInfo = repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("CallbackInfo", "CallbackInfoID", id)
        );
        return mapper.map(callbackInfo, CallbackInfoDto.class);
    }

    @Override
    public CallbackInfoDto createCallbackInfo(CallbackInfoDto caDto, String productID, Long shopID, Long factoryID) {
        Product product = productRepo.findById(productID).orElseThrow(
                ()-> new ResourceNotFoundException("Product", "ProductID", 12345)
        );
        Quarter shop = quarterRepo.findById(shopID).orElseThrow(
                ()-> new ResourceNotFoundException("Quarter", "QuarterID", shopID)
        );
        Quarter factory = quarterRepo.findById(factoryID).orElseThrow(
                ()-> new ResourceNotFoundException("Quarter", "QuarterID", factoryID)
        );
        CallbackInfo callbackInfo = mapper.map(caDto, CallbackInfo.class);
        callbackInfo.setProduct(product);
        callbackInfo.setShop(shop);
        callbackInfo.setFactory(factory);
        CallbackInfo newCallbackInfo = repo.save(callbackInfo);
        return mapper.map(newCallbackInfo , CallbackInfoDto.class);
    }

    @Override
    public CallbackInfoDto updateCallbackInfo(CallbackInfoDto caDto, Long id) {
        CallbackInfo callbackInfo = repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("CallbackInfo", "CallbackInfoID", id)
        );
        CallbackInfo c = mapper.map(caDto, CallbackInfo.class);
        c.setId(id);
        CallbackInfo newCallbackInfo = repo.save(c);
        return mapper.map(newCallbackInfo, CallbackInfoDto.class);
    }

    @Override
    public void deleteCallbackInfo(Long id) {
        CallbackInfo callbackInfo = repo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("CallbackInfo", "CallbackInfoID", id)
        );
        repo.delete(callbackInfo);
    }
}
