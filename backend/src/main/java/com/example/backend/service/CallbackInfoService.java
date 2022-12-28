package com.example.backend.service;

import com.example.backend.payload.CallbackInfoDto;

import java.util.List;

public interface CallbackInfoService {
    List<CallbackInfoDto> getAllCallbackInfo();
    List<CallbackInfoDto> getCallbackInfoByProduct(String productID);
    List<CallbackInfoDto> getCallbackInfoByShop(Long shopID);
    List<CallbackInfoDto> getCallbackInfoByFactory(Long factoryID);
    CallbackInfoDto getCallbackInfoById(Long id);
    CallbackInfoDto createCallbackInfo(CallbackInfoDto caDto, String productID, Long shopID, Long factoryID);
    CallbackInfoDto updateCallbackInfo(CallbackInfoDto caDto, Long id);
    void deleteCallbackInfo(Long id);
}
