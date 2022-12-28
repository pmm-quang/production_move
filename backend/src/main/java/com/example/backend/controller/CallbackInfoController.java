package com.example.backend.controller;


import com.example.backend.payload.CallbackInfoDto;
import com.example.backend.service.CallbackInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/callbackinfo/")
public class CallbackInfoController {
    @Autowired
    private CallbackInfoService service;

    @GetMapping("/")
    public ResponseEntity<List<CallbackInfoDto>> getAllCallbackInfo() {
        List<CallbackInfoDto> list = service.getAllCallbackInfo();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/product/{productID}/")
    public ResponseEntity<List<CallbackInfoDto>> getCallbackInfoByProduct(@PathVariable String productID) {
        List<CallbackInfoDto> list = service.getCallbackInfoByProduct(productID);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/factory/{factoryID}/")
    public ResponseEntity<List<CallbackInfoDto>> getCallbackInfoByFactory(@PathVariable Long factoryID) {
        List<CallbackInfoDto> list = service.getCallbackInfoByFactory(factoryID);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/shop/{shopID}/")
    public ResponseEntity<List<CallbackInfoDto>> getCallbackInfoByShop(@PathVariable Long shopID) {
        List<CallbackInfoDto> list = service.getCallbackInfoByShop(shopID);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{callbackInfoID}")
    public ResponseEntity<CallbackInfoDto> getCallbackInfoById(@PathVariable Long callbackInfoID) {
        CallbackInfoDto warrantyInfoDto = service.getCallbackInfoById(callbackInfoID);
        return new ResponseEntity<>(warrantyInfoDto, HttpStatus.OK);
    }

    @PostMapping("/add/product/{productID}/shop/{shopID}/factory/{factoryID}/")
    public ResponseEntity<CallbackInfoDto> createCallbackInfo(@RequestBody CallbackInfoDto callbackInfoDto,
                                                              @PathVariable String productID,
                                                              @PathVariable Long shopID,
                                                              @PathVariable Long factoryID) {
        CallbackInfoDto newWarrantyInfo = service.createCallbackInfo(callbackInfoDto, productID, shopID, factoryID);
        return new ResponseEntity<>(newWarrantyInfo, HttpStatus.CREATED);
    }

    @PutMapping("/update/{callbackInfoID}/")
    public ResponseEntity<CallbackInfoDto> updateCallbackInfo(@RequestBody CallbackInfoDto callbackInfoDto,
                                                              @PathVariable Long callbackInfoID) {
        CallbackInfoDto warrantyInfoDto1 = service.updateCallbackInfo(callbackInfoDto, callbackInfoID);
        return new ResponseEntity<>(warrantyInfoDto1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{callbackInfoID}")
    public ResponseEntity<String> deleteCallbackInfo(@PathVariable Long callbackInfoID) {
        service.deleteCallbackInfo(callbackInfoID);
        return new ResponseEntity<>("Delete!!!", HttpStatus.OK);
    }
}
