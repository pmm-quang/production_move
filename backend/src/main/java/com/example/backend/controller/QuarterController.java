package com.example.backend.controller;

import com.example.backend.config.AppConstant;
import com.example.backend.payload.QuarterDto;
import com.example.backend.service.QuarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/quarter/")
public class QuarterController {
    @Autowired
    private QuarterService quarterService;

    @GetMapping("/shop/")
    @Secured({AppConstant.ADMIN_ROLE})
    public ResponseEntity<List<QuarterDto>> getAllShop() {
        List<QuarterDto> shops = quarterService.getAllShop();
        return new ResponseEntity<>(shops, HttpStatus.OK);
    }
    @GetMapping("/factory/")
    @Secured({AppConstant.ADMIN_ROLE})
    public ResponseEntity<List<QuarterDto>> getAllFactory() {
        List<QuarterDto> factories = quarterService.getAllFactory();
        return new ResponseEntity<>(factories, HttpStatus.OK);
    }

    @GetMapping("/warrantycenter/")
    @Secured({AppConstant.ADMIN_ROLE})
    public ResponseEntity<List<QuarterDto>> getAllWarrantyCenter() {
        List<QuarterDto> warrantyCenters = quarterService.getAllWarrantyCenter();
        return new ResponseEntity<>(warrantyCenters, HttpStatus.OK);
    }

    @GetMapping("/{quarterID}")
    @Secured({AppConstant.ADMIN_ROLE, AppConstant.SHOP_ROLE, AppConstant.FACTORY_ROLE, AppConstant.WARRANTY_CENTER_ROLE})
    public ResponseEntity<QuarterDto> getQuarterById(@PathVariable Long quarterID) {
        QuarterDto quarter = quarterService.findQuarterByID(quarterID);
        return new ResponseEntity<>(quarter, HttpStatus.OK);
    }

    @PostMapping("/add/{roleID}")
    @Secured({AppConstant.ADMIN_ROLE})
    public ResponseEntity<QuarterDto> createQuarter(@RequestBody QuarterDto quarterDto, @PathVariable Long roleID) {
        QuarterDto quarter = quarterService.createQuarter(quarterDto, roleID);
        return new ResponseEntity<>(quarter, HttpStatus.CREATED);
    }

    @PutMapping("/{roleID}/update/{quarterID}")
    @Secured({AppConstant.ADMIN_ROLE})
    public ResponseEntity<QuarterDto> updateQuarter(@RequestBody QuarterDto quarterDto,
                                                    @PathVariable Long roleID,
                                                    @PathVariable Long quarterID) {
        QuarterDto quarter = quarterService.updateQuarter(quarterDto, roleID, quarterID);
        return new ResponseEntity<>(quarter, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{quarterID}")
    public ResponseEntity<String> deleteQuarter(@PathVariable Long quarterID) {
        quarterService.deleteQuarter(quarterID);
        return new ResponseEntity<>("delete!!!", HttpStatus.OK);
    }

}
